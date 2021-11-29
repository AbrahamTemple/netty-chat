const auth = {
    state: {
        token: '',
		hash: ''
    },
    mutations: {
        updateToken(state, value) {
            state.token = value
        },
		bindHash(state, value) {
			state.hash = value
		}
    },
    actions: {
        tokenClear(context, value) {
            context.commit('updateToken', '')
        },
        hashClear(context, value) {
            context.commit('bindHash', '')
        }
    }
}

export default auth;