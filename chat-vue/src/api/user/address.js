//引入封装的请求服务
import { GET, POST, UPLOAD } from '@/services/request.js'

//导出接口
export default {
  AddressIndex(data = {})
  {
    return POST({
      url: '/user/address/index',
      params: data
    })
  },
}