//引入axios异步请求插件
import axios from 'axios'
import store from '@/store/index.js'

let cancel ,promiseArr = {}

const CancelToken = axios.CancelToken;

//请求拦截器
axios.interceptors.request.use(
	config => {
		//如果store的token不是空的，那就把它挂载到全局请求头上
		if (!Object.is(store.state.auth.token,'')) {
			config.headers.token = store.state.auth.token
		} 
		//发起请求时，取消掉当前正在进行的相同请求
		if (promiseArr[config.url]) {
			promiseArr[config.url]('操作取消')
			promiseArr[config.url] = cancel
		} else {
			promiseArr[config.url] = cancel
		}
		  return config
	}, error => {
		return Promise.reject(error)
})

//响应拦截器即异常处理
axios.interceptors.response.use(
  response => {
      // return response
      return response.data
  }, 
  err => {
      if (err && err.response) {
        switch (err.response.status) {
          case 400:
            err.message = '错误请求'
            break;
          case 401:
            err.message = '未授权，请重新登录'
            break;
          case 403:
            err.message = '拒绝访问'
            break;
          case 404:
            err.message = '请求错误,未找到该资源'
            break;
          case 405:
            err.message = '请求方法未允许'
            break;
          case 408:
            err.message = '请求超时'
            break;
          case 500:
            err.message = '服务器端出错'
            break;
          case 501:
            err.message = '网络未实现'
            break;
          case 502:
            err.message = '网络错误'
            break;
          case 503:
            err.message = '服务不可用'
            break;
          case 504:
            err.message = '网络超时'
            break;
          case 505:
            err.message = 'http版本不支持该请求'
            break;
          default:
            err.message = `连接错误${err.response.status}`
        }
      } else {
        err.message = "连接到服务器失败"
      }
      console.err(err.message)
        return Promise.resolve(err.response)
  }
)

//请求的默认前缀 只要是发出去请求就会 默认带上这个前缀
axios.defaults.baseURL = '/api'

//设置默认请求头 异步的
axios.defaults.headers = {
    'X-Requested-With': 'XMLHttpRequest'
}

//设置超时请求时间
axios.defaults.timeout = 10000


//get请求
let GET = (data = {}) => {
  return new Promise((resolve,reject) => {
    axios({
      method: 'get',
      url:data.url,
      params: data.params,
      cancelToken: new CancelToken(c => {
        cancel = c
      })
    }).then(res => {
      resolve(res)
    })
  })
}

//post请求
let POST = (data = {}) =>
{
  return new Promise((resolve,reject) => {
    axios({
      method: 'post',
      url:data.url,
      data:data.params,
      cancelToken: new CancelToken(c => {
        cancel = c
      })
    }).then(res => {
      resolve(res)
    })
  })
}

//路径变量请求
let PATH = (data = {}) =>
{
  return new Promise((resolve,reject) => {
    axios({
      method: 'get',
      url:`${data.url}/${data.params}`,
      cancelToken: new CancelToken(c => {
        cancel = c
      })
    }).then(res => {
      resolve(res)
    })
  })
}

//路径键值对请求
let PARAM = (data = {}) =>
{
  return new Promise((resolve,reject) => {
    axios({
      method: 'get',
      url:`${data.url}/${data.params.key}/${data.params.value}`,
	  
      cancelToken: new CancelToken(c => {
        cancel = c
      })
    }).then(res => {
      resolve(res)
    })
  })
}

// 文件上传请求
let UPLOAD = (data = {}) =>
{
  //封装表单数据对象
  var RequestData = new FormData()

  if(JSON.stringify(data.params) != "{}")
  {
    for(var key in data.params)
    {
      RequestData.append(key, data.params[key])
    }
  }

  return new Promise((resolve,reject) => {
    axios({
      method: 'post',
      url:data.url,
      data:RequestData,
      headers:{
		  'Content-Type': 'multipart/form-data',
		  'token': ''
	  },
      cancelToken: new CancelToken(c => {
        cancel = c
      })
    }).then(res => {
      resolve(res)
    })
  })
}


export {
  GET,
  POST,
  UPLOAD,
  PATH,
  PARAM
}