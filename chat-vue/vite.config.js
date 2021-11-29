import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// const pxtoviewport = require('postcss-px-to-viewport');

//引入路径模块
const path = require('path')

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    //配置别名
    alias: {
      "@": path.resolve(__dirname, "src"),
      "components": path.resolve(__dirname, "src/components"),
      'vue': 'vue/dist/vue.esm-bundler.js' // 定义vue的别名，如果使用其他的插件，可能会用到别名
      // "styles": path.resolve(__dirname, "src/styles"),
      // "plugins": path.resolve(__dirname, "src/plugins"),
      // "views": path.resolve(__dirname, "src/views"),
      // "layouts": path.resolve(__dirname, "src/layouts"),
      // "utils": path.resolve(__dirname, "src/utils"),
      // "apis": path.resolve(__dirname, "src/apis"),
      // "dirs": path.resolve(__dirname, "src/directives"),
    },
  },
  server: {
    proxy: {
      //设置代理请求 当代理商识别你的请求如果前缀是 /api的话 就会自动转移
      '/api': {
        target: 'http://43.225.158.156:8882',
        changeOrigin:true,  //跨域
        //替换掉api前缀 防止多个api地址
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    },
    hmr: {
      overlay: false,
    },
  },
  plugins: [
	  vue(),
	  // pxtoviewport({
	  //   viewportWidth: 375,
	  //   // selectorBlackList: ['ignore', '__KEFU__emoji-'],// 指定不转换为视窗单位的类，可以自定义，可以无限添加,建议定义一至两个通用的类名
	  //   // minPixelValue: 1,     // 小于或等于`1px`不转换为视窗单位，你也可以设置为你想要的值
	  //   // mediaQuery: false     // 允许在媒体查询中转换`px`
	  // })
  ]
})
