import WrapperIndex from '@/components/chat/base/wrapper.vue'
import SocialIndex from '@/components/chat/base/social.vue'

export default [
	{
	    path:'base/wrapper:hash',
	    name:'WrapperIndex',
	    component:WrapperIndex,
	    meta: {
	        auth: true
	    }
	},
	{
	    path:'base/social',
	    name:'SocialIndex',
	    component:SocialIndex,
	},
]