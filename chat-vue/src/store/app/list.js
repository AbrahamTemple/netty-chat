const list = {
    state: {
        data: ''
    },
    mutations: {
        //默认类型是string，赋值后类型为object
        listData(state, value) {
            state.data = value
        }
    },
    actions: {
    }
}

export default list;