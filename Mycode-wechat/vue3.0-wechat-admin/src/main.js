
import ElementUI from 'element-ui'
import charts from 'v-charts'
import 'element-ui/lib/theme-chalk/index.css'
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vuex from 'vuex'
Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(Vuex)
Vue.use(charts)

//登录拦截
router.beforeEach((to, from, next) => {
     var reg=/[0-9a-zA-Z\_\/]/i;
    if(reg.test(to.path) && to.path !='/login'){
        if(sessionStorage.getItem("userInfo")==null){
            next({
                path:'/login',
                query:{redirect:to.fullPath}
            })
        }else {
            next();
        }
    } else {
        next();
    }
})




new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
