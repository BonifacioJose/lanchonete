<template>
  <v-data-table
    :headers="headers"
    :items="porcoesIngredientes"
    hide-actions
    class="elevation-1"
  >
    <template slot="items" slot-scope="props">
      <td>{{ props.item.ingrediente.nome }}</td>
      <td>{{ props.item.quantidade }}</td>
      <td class="text-xs-right">{{ props.item.ingrediente.valor.toLocaleString('pt-br', {minimumFractionDigits: 2}) }}</td>
    </template>
    <template slot="no-data">
        Nenhum ingrediente selecionado
    </template>
  </v-data-table>
</template>

<script>
export default {
  name: 'LancheIngredientes',
  props: {
    porcoesIngredientes: Array
  },
  data() {
    return {
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
        }
      ]
    }
  }
};
</script>