import { createApp } from 'vue'
import App from './App.vue'

//引入路由对象
import router from './routers/index'

//引入Vuex的Store
import store from './store/index.js'

//引入ui组件
import Vant from 'vant'
import 'vant/lib/index.css'


//加载接口文件
import api from './api/index'

//引入cookie
import VueCookies from 'vue3-cookies'

const app = createApp(App)
    .use(router)  //挂载路由
    .use(Vant)   //挂载UI组件
    .use(api)  //挂载接口   === install
	.use(store)  //挂载store
    .use(VueCookies)
    .mount('#app')


//添加路由守卫做判断是否有无登录
router.beforeEach(async (to, from, next) => {
    if(to.meta.auth)
    {
        //需要登录

        //判断是否有无cookie 
        var LoginUser = app.$cookies.get('LoginUser')
		
		//判断是否有无token
		var token = app.$store.state.auth.token

        //没有登录就跳转到登录界面
        if(!LoginUser || Object.is(token,''))
        {
            next('/user/base/login')
            return
        }		
		//接下来验证这个id是否存在

  //       //获取id
  //       var userid = LoginUser.id ? LoginUser.id : 0;

  //       //接口请求
  //       var result = await api.check({uid:userid})

  //       if(result.status !== 200)
  //       {
  //           //验证失败
  //           app.$cookies.remove('LoginUser')

  //           //跳转了
  //           next('/user/base/login')
  //           return
  //       }
		// else
  //       {
  //           //验证成功
  //           //覆盖cookie
  //           app.$cookies.set('LoginUser', result.data)
            
  //           //让他去到该去的路由
  //           next()
  //       }
		next()
    }
	else
    {
        //不需要登录,直接跳转路由
        next()
    }
})

