<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>Lanchonete</span>
        <span class="font-weight-light">DEXTRA</span>
      </v-toolbar-title>
    </v-toolbar>

    <v-content>      
      <v-container>
        <router-view></router-view>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: "App",
  components: {
  },
  data() {
    return {
      //
    };
  }
};
</script>
