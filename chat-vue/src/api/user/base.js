//引入封装的请求服务
import { GET, POST, UPLOAD, PATH, PARAM } from '@/services/request.js'

//导出接口
export default {
  register(data = {})
  {
    return POST({
      // /api/user/base/register
      url: '/user/base/register',
      params: data
    })
  },
  login(data = {})
  {
    return GET({
      url: '/user/login',
      params: data
    })
  },
  friend(data = {})
  {
	return PATH({
	  url: '/with/list',
	  params: data
	})
  },
  message(data = {})
  {
  	return PARAM({
  	  url: '/asset/log',
  	  params: data
  	})
  },
  check(data = {})
  {
    return POST({
      url: '/user/base/check',
      params: data
    })
  },
  email(data = {})
  {
    return POST({
      url: '/user/base/email',
      params: data
    })
  }
}