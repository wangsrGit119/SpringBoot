import Vue from 'vue'
import Router from 'vue-router'
import wechatOrder from './components/main/orderManager/wechatOrder'
import wechatOrderBalance from './components/main/orderManager/wechatOrderBalance'
import orderEcharts from './components/main/orderManager/orderEcharts'
import goodsItemDel from './components/main/goodsManager/goodsItemDel'

import Home from './components/main/Home'



import goodsItemAdd from './components/main/goodsManager/goodsItemAdd'
import Login from './components/Login'
const NotFound = { template: '<p>Page not found</p>' }

const About = { template: '<p>about page</p>' }



Vue.use(Router)

export default new Router({
	 mode: 'history',
	 base:'/',
  routes: [
    {
      path: '/404',
      name: 'NotFound',
      component: NotFound,
      hidden: true
    },
    {
      path: '/about',
      name: 'About',
      component: About,
      hidden:true
    },
    {
      path:'/login',
      name:'Login',
      component:Login,
      hidden:true
    },
    {
      path:'/',
      name:'Home',
      component:Home,
      meta:{auth:true},
      redirect:'/wechatOrder',
      children: [
        { path: '/wechatOrder', component: wechatOrder, name: '订单处理' },
        { path: '/goodsItemAdd', component: goodsItemAdd, name: '菜单更新' },
        { path: '/goodsItemDel', component: goodsItemDel, name: '菜品下架' },
        {path:'/wechatOrderBalance',component:wechatOrderBalance,name:'订单结算'},
        {path:'/orderEcharts',component:orderEcharts,name:'订单统计'},
      ]
    },

  ]
})
