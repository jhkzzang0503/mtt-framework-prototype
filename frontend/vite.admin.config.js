import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from "path";

export default defineConfig({
  plugins: [react()],
  root: 'admin',
  resolve: {
    alias: {
      '@': '/src',
      '@web': path.resolve(__dirname, './target/src')
    }
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  },
  build: {
    outDir: '../dist',
    emptyOutDir: true
  },
  define: {
    'process.env.WEB_PATH': JSON.stringify(path.resolve(__dirname, './target/src')),
  },
  base: '/'
});