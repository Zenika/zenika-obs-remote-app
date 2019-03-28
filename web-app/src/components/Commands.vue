<template>
  <v-card-actions>
    <v-btn flat color="red" v-on:click="startRecording">play</v-btn>
    <v-btn flat color="grey" v-on:click="stopRecording">stop</v-btn>
  </v-card-actions>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator';
import api from '../../api.json';
import preview from './LivePreview.vue';

const axios = require('axios');
const apiURL = process.env.VUE_APP_API_URL;

@Component({
  components: {
    preview
  },
  data() {
    return {
    };
  },
  methods: {
    startRecording: function() {
      axios.post(apiURL.concat(api.commands.start_recording))
        .then(alert('Recording started'))
        .catch((error: any) => alert(error));
    },
    stopRecording: function() {
      axios.post(apiURL.concat(api.commands.stop_recording))
        .then(alert('Recording stopped'))
        .catch((error: any) => alert(error));
    }
  }
})

export default class HelloWorld extends Vue {
  @Prop() private msg!: string;
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
