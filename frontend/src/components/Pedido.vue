<template>
  <div>    
    <v-btn :to="{name: 'home'}" class="info">Voltar</v-btn>
    <v-flex xs12 sm6 d-flex>
      <v-select
        :items="cardapio"
        label="Lanche"
        item-text="nome"
        v-model="pedido.lanche.nome"
        v-on:change="selectLanche"
      ></v-select>
    </v-flex>
    <div v-if="pedido.lanche.nome">
        <v-btn v-for="ingrediente in ingredientes" :key="ingrediente.nome" v-on:click="adicionarIngrediente(ingrediente)" class="info">
            +  {{ingrediente.nome}}
        </v-btn>

        <v-data-table
            :headers="headers"
            :items="pedido.lanche.porcoesIngredientes"
            hide-actions
            class="elevation-1"
        >
            <template slot="items" slot-scope="props">
                <td>{{ props.item.ingrediente.nome }}</td>
                <td>{{ props.item.quantidade }}</td>
                <td class="text-xs-right">{{ props.item.ingrediente.valor.toLocaleString('pt-br', {minimumFractionDigits: 2}) }}</td>
                <td class="text-xs-right">
                    <v-btn color="error" fab small dark v-on:click=removerIngrediente(props.item.ingrediente)>
                      -1
                    </v-btn>
                </td>
            </template>        
            <template slot="no-data">
              Nenhum ingrediente adicionado
            </template>            
            <template slot="footer">
              <td colspan="100%"  class="text-xs-right">
                <strong>Total com descontos: R$ {{pedido.lanche.valor.toLocaleString('pt-br', {minimumFractionDigits: 2})}}</strong>
              </td>
            </template>
        </v-data-table>
        <v-btn v-on:click="salvarPedido()" class="info">Salvar pedido</v-btn>
    </div>
  </div>
</template>

<script>
import _ from "lodash"
import Axios from "axios"

export default {
  mounted: function() {
    this.carregarCardapio()
    this.carregarIngredientes()
    this.carregarPedido()
  },
  methods: {
    carregarIngredientes() {
      let vue = this      
      Axios.get('/ingredientes').then(function(response) {
        vue.ingredientes = response.data
      })
    },
    carregarPedido: function() {
      let id = this.$route.params.id
      let vue = this
      if (id != undefined) {
        Axios.get('/pedido/' + id).then(function(response) {
          vue.pedido = response.data
          vue.atualizarPrecoTotal()
        })
      }
    },
    carregarCardapio: function() {
      let vue = this
        Axios.get('/cardapio').then(function(response) {
          vue.cardapio = response.data
      })      
    },
    salvarPedido: function() {
      let vue = this
      if (this.pedido.id == undefined) {
        Axios.post("/pedido", { lanche: this.pedido.lanche }).then(function() {
          vue.$router.push("/")
        })
      } else {
        Axios.put("/pedido/" + this.pedido.id, {
          lanche: this.pedido.lanche
        }).then(function() {
          vue.$router.push("/")
        })
      }
    },
    adicionarIngrediente: function(ingredienteSelecionado) {
      let porcaoIngrediente = _.find(this.pedido.lanche.porcoesIngredientes, {
        ingrediente: ingredienteSelecionado
      })
      if (porcaoIngrediente == undefined) {
        porcaoIngrediente = {
          quantidade: 1,
          ingrediente: ingredienteSelecionado
        }
        this.pedido.lanche.porcoesIngredientes.push(porcaoIngrediente)
      } else {
        porcaoIngrediente.quantidade += 1
      }
      this.atualizarPrecoTotal()
    },
    atualizarPrecoTotal: function() {
      let vue = this
      Axios.post("/promocao", this.pedido.lanche)
        .then(function(response) {
          vue.pedido.lanche.valor = response.data
        })
    },
    removerIngrediente: function(ingredienteSelecionado) {
      let porcaoIngrediente = _.find(this.pedido.lanche.porcoesIngredientes, {
        ingrediente: ingredienteSelecionado
      })
      if (porcaoIngrediente.quantidade == 1) {
        this.pedido.lanche.porcoesIngredientes = _.without(
          this.pedido.lanche.porcoesIngredientes,
          porcaoIngrediente
        )
      } else {
        porcaoIngrediente.quantidade -= 1
      }
      this.atualizarPrecoTotal()
    },
    selectLanche: function() {
      let vue = this
      Axios.get('/ingredientes/' + this.pedido.lanche.nome)
        .then(function(response) {
          vue.pedido.lanche.porcoesIngredientes = response.data
          vue.atualizarPrecoTotal()
        })
    }
  },
  data() {
    return {
      pedido: {
        lanche: {
          porcoesIngredientes: [],
          valor: 0
        }
      },
      headers: [
        {
          text: "Nome",
          value: "ingrediente.nome"
        },
        {
          text: "Quantidade",
          value: "quantidade"
        },
        {
          text: "Valor (R$)",
          align: "right",
          value: "ingrediente.valor"
        },
        {
          text: "Remover",
          align: "right",
          value: "quantidade"
        }
      ],
      cardapio: [
      ],
      ingredientes: [        
      ]
    }
  }
}
</script>