import Vue from 'vue'
import Router from 'vue-router'
import Pedidos from './components/Pedidos.vue'
import Pedido from './components/Pedido.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Pedidos
    },
    {
      path: '/pedido',
      name: 'novoPedido',
      component: Pedido
    },
    {
      path: '/pedido/:id',
      name: 'editarPedido',
      component: Pedido
    }
  ]
})
