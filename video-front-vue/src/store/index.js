import Vue from 'vue'
import Vuex from 'vuex'
import Cookies from 'js-cookie'
Vue.use(Vuex)



export default new Vuex.Store({
  state: {
    userInfo: Cookies.get("userInfo")
  },
  mutations: {
    SET_USERINFO: (state, userInfo) => {
      Cookies.set("userInfo", userInfo);
      state.userInfo = userInfo
    },
    REMOVE_INFO: (state) => {
      state.token = ''
      state.userInfo = ''
      Cookies.set("Jwt_token", '')
      Cookies.set("userInfo", '')
    }

  },
  getters: {
    // get
    getUser: state => {
      return state.userInfo
    }

  },
  actions: {
  },
  modules: {
  }
})
