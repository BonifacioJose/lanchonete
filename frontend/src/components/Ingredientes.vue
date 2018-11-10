<template>
  <v-data-table
    :headers="headers"
    :items="ingredientes"
    hide-actions
    class="elevation-1"
  >
    <template slot="items" slot-scope="props">
      <td>{{ props.item.nome }}</td>
      <td class="text-xs-right">{{ props.item.valor }}</td>
    </template>
  </v-data-table>
</template>

<script>
export default {
  data() {
    return {
      headers: [
        {
          text: 'Nome',
          value: 'nome'
        },
        {
          text: 'Valor (R$)',
          align: 'right',
          value: 'valor'
        }
      ],
      ingredientes: [
        {
          nome: "Alface",
          valor: 0.4
        },
        {
          nome: "Bacon",
          valor: 2
        },
        {
          nome: "Hamburger de carne",
          valor: 3
        },
        {
          nome: "Ovo",
          valor: 0.8
        },
        {
          nome: "Queijo",
          valor: 1.5
        }
      ]
    };
  }
};
</script>