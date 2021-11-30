import ProfileIndex from '@/components/user/home/profile.vue'

export default [
    {
        path:'home/profile',
        name:'ProfileIndex',
        component:ProfileIndex,
        meta: {
            auth: true
        }
    },
 
]