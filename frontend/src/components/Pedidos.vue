<template>
  <div>
    <v-data-table
      :headers="headers"
      :items="pedidos"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <tr @click="props.expanded = !props.expanded" :style="{ cursor: 'pointer'}">
          <td>{{ props.item.id }}</td>
          <td>{{ props.item.lanche.nome }}</td>
          <td class="text-xs-right">{{ props.item.lanche.valor.toLocaleString('pt-br', {minimumFractionDigits: 2}) }}</td>
        </tr>
      </template>
      <template slot="expand" slot-scope="props">
        <v-container>
          <v-btn :to="'/pedido/' + props.item.id" color="info">Editar</v-btn>
          <v-btn v-on:click="excluirPedido(props.item)" color="error">Excluir</v-btn>
          <v-card-text hidden>{{ props.item.id }}</v-card-text>
          <LancheIngredientes :porcoesIngredientes="props.item.lanche.porcoesIngredientes" />
        </v-container>
      </template>       
      <template slot="no-data">
          Nenhum pedido registrado
      </template>
    </v-data-table>    
    <v-btn :to="{name: 'novoPedido'}" class="info">Adicionar pedido</v-btn>
  </div>  
</template>

<script>
import LancheIngredientes from "./LancheIngredientes"
import Axios from 'axios'

export default {
  name: "Pedidos",
  components: {
    LancheIngredientes
  },
  methods: {
    excluirPedido: function(pedido) {
      let vue = this
      Axios.delete('/pedido/' + pedido.id)
        .then(function() {
          vue.buscarPedidos()
          vue.$forceReload
        })
    },
    buscarPedidos: function() {
      let vue = this
      Axios.get('/pedidos')
        .then(function(response) {
          vue.pedidos = response.data
        })
    }
  },
  mounted: function() {
    this.buscarPedidos()
  },
  data() {
    return {
      headers: [
        {
          text: "Id",
          value: "id"
        },
        {
          text: "Nome",
          value: "lanche.nome"
        },
        {
          text: "Valor total (R$)",
          align: "right",
          value: "ingrediente.valor"
        }
      ],
      pedidos: [
      ]
    }
  }
}
</script>