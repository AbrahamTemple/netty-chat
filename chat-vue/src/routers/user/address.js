import AddressIndex from '@/components/user/address/index.vue'
import AddressAdd from '@/components/user/address/add.vue'

export default [
    {
        path:'address/index',
        name:'AddressIndex',
        component:AddressIndex,
        meta: {
            auth: true
        }
    },
    {
        path:'address/add',
        name:'AddressAdd',
        component:AddressAdd,
        meta: {
            auth: true
        }
    }
]