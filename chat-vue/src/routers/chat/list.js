import DialogsIndex from '@/components/chat/list/dialogs.vue'
import RelationIndex from '@/components/chat/list/relation.vue'

export default [
	{
	    path:'list/dialogs:id',
	    name:'DialogsIndex',
	    component:DialogsIndex,
	    meta: {
	        auth: false
	    }
	},
	{
	    path:'list/relation:id',
	    name:'RelationIndex',
	    component:RelationIndex,
	    meta: {
	        auth: false
	    }
	},
]