// 引入当前目录下面的所有文件
const ModulesFile = import.meta.globEager('./*.js')

// 路由集合
const RouterList = []

//提取每个对象下的值 同步获取
Object.values(ModulesFile).map(async mod => {
  if(mod.default)
  {
    RouterList.push(...mod.default)
  }
})

//定义父组件
const layout = {
  template:`<router-view></router-view>`
}

//导出
export default [
  {
    path:'/chat', // 父路由
    component:layout,
    children:RouterList
  }
]