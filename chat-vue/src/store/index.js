import { createStore } from 'vuex'
import auth from './app/auth'
import list from './app/list'
import ws from './app/ws'

export default createStore({
  modules: {
    auth,
    list,
    ws
  }
})