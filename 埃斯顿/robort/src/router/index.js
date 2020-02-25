import Vue from 'vue'
import Router from 'vue-router'
import Robot from "../components/Robot";
import Login from "../components/Login";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      component: Login
    },
    {
      path: '/Robot',
      component: Robot
    }
  ]
})
