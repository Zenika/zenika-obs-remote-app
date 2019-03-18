<template>
  <div class="container" id="main">
    <img alt="Vue logo" src="../assets/logo.png">
    <div class="columns">
      <div class="column is-10 is-offset-1">
        <v-card>
          <v-card-title>
            <h2><strong>OBS REMOTE WEB-APP</strong></h2>
          </v-card-title>
          <LivePreview/>
          <Commands/>
        </v-card>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Commands from '@/components/Commands.vue';
import LivePreview from '@/components/LivePreview.vue'; // @ is an alias to /src

@Component({
  components: {
    LivePreview,
    Commands
  },
  data: function() {
    return {
      env: process.env.NODE_ENV
    };
  }
})
export default class Home extends Vue {}
</script>
<style>
</style>
