import { createStore } from 'vuex'
import auth from './app/auth'
import list from './app/list'

export default createStore({
  modules: {
    auth,
    list
  }
})