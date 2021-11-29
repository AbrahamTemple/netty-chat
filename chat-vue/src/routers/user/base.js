import BaseIndex from '@/components/user/base/index.vue'
import BaseRegister from '@/components/user/base/register.vue'
import BaseLogin from '@/components/user/base/login.vue'
import BaseProfile from '@/components/user/base/profile.vue'


export default [
    {
        // /user/base/index
        path: 'base/index',
        name: 'BaseIndex',
        component:BaseIndex,
        meta: {
            auth: true
        }
    },
    {
        path: 'base/register',
        name: 'BaseRegister',
        component: BaseRegister
    },
    {
        path: 'base/login',
        name: 'BaseLogin',
        component: BaseLogin
    },
    {
        path: 'base/profile',
        name: 'BaseProfile',
        component: BaseProfile,
        meta: {
            auth: true
        }
    },
]