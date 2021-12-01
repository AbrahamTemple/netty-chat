const ws = {
    state: {
        socket: '',
		logs: '',
    },
    mutations: {
        //实例化websocket
        initSocket(state, value) {
            state.socket = value
        },
		refreshLogs(state, value) {
			state.logs = value
		}
    },
    actions: {
        closeSocket(context, value) {
            context.commit('init', '')
        },
    }
}

export default ws;