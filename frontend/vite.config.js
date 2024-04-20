import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  build: {
     rollupOptions: {
          output: {
            // Define static file name patterns here
            entryFileNames: `assets/[name].js`, // For entry files
            chunkFileNames: `assets/[name].js`, // For dynamically imported modules
            assetFileNames: `assets/[name].[ext]`, // For other assets like images and styles
          }
        }
  }
})
