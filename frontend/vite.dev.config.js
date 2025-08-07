import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path'; // path 모듈을 import 해야 합니다.

export default defineConfig({
  plugins: [react()],
  root: 'target',
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
    }
  },
  build: {
    outDir: '../../src/main/resources/static',
    emptyOutDir: true
  },
  base: '/'
});