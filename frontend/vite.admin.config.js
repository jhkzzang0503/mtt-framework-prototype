import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path'; // path 모듈을 import 해야 합니다.

export default defineConfig({
  plugins: [react()],
  root: 'admin',
  resolve: {
    alias: {
      '@': '/src',
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
  base: '/'
});