const ws = {
    state: {
        socket: '',
		logs: '',
        value: '',
        wapper: {}
    },
    mutations: {
        //实例化websocket
        initSocket(state, value) {
            state.socket = value
        },
		refreshLogs(state, value) {
			state.logs = value
		},
        calculateVal(state, value){
            state.value = value
        },
        //把hash消息列表键值对缓存到wapper
        pushWapper(state, value){
            if(state.wapper.hasOwnProperty(value.hash)){
                state.wapper[value.hash].push(value.data)
				console.log(state.wapper[value.hash])
            } else {
                let obj = Object.create(null)
                obj[value.hash] = []
                obj[value.hash].push(value.data)
                var newObj = Object.assign(state.wapper,obj)
            }
            
        }
    },
    actions: {
        closeSocket(context, value) {
            context.commit('init', '')
        },
    }
}

export default ws;