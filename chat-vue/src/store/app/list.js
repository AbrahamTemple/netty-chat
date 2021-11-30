const list = {
    state: {
        data: '',
		active: '',
    },
    mutations: {
        //默认类型是string，赋值后类型为object
        listData(state, value) {
            state.data = value
        },
		selectActive(state, value){
			state.active = value
		}
    },
    actions: {
    }
}

export default list;