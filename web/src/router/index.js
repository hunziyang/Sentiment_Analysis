import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/task'
    },
    {
      path: '/task',
      component: () => import('@/views/task/task')
    }
  ]
})
