//引入路由对象
import { createRouter, createWebHashHistory } from "vue-router"

// 设置一个默认首页
import chat from '@/components/user/base/login.vue'

// 引入每个目录下面的index.js文件
const ModulesFile = import.meta.globEager("./*/index.js")

// 总路由集合
const RouterList = []

//提取每个对象下的值 同步获取 map循环
Object.values(ModulesFile).map(async mod => {
  if(mod.default)
  {
    //将每个目录下面所有的子路由 拿出来并合并到一起去
    RouterList.push(...mod.default)
  }
})

//手动添加了一个 默认首页进去
RouterList.push({
  path:'/', //传参选项 query
  name:'chat', //传参选项 params
  component: chat,
  // meta:{ //自定义参数
  //   Auth:true, //自定义命名 比如Auth为true就是需要登录 否则不需要登录
  // }
})

// 创建一个总路由对象
export default createRouter({
  history: createWebHashHistory(),  //history模式 并且不会有/# 
  // linkExactActiveClass:'active',  //激活链接状态class名称
  routes:RouterList,   //routes 很容易写成 routers !!!!!!!!!
})