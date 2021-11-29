//引入当前目录下面所有的文件
const ModulesFile = import.meta.globEager("./*.js")

//接口列表
var ApiList = []

//循环引入的文件
Object.values(ModulesFile).map(async mod => {
  //判断每个js的文件是否有 默认的 export default
  if(mod.default)
  {
    //对象合并  将对象合并到数组里面去
    ApiList = Object.assign(ApiList, mod.default)
  }
})

export default ApiList