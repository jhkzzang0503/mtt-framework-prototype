import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from "path";

export default defineConfig({
  plugins: [react()],
  root: 'target',
  resolve: {
    alias: {
      '@': '/src',
      '@modulePath': path.resolve(__dirname, './admin/src')
    }
  },
  build: {
    outDir: '../../src/main/resources/static',
    emptyOutDir: true
  },
  base: '/'
});