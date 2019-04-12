<template>
  <v-card-actions>
    <v-btn flat color="red" v-on:click="startRecording">play</v-btn>
    <v-btn flat color="grey" v-on:click="stopRecording">stop</v-btn>
    <v-btn flat color="grey" v-on:click="moveCamera('up')">up</v-btn>
    <v-btn flat color="grey" v-on:click="moveCamera('down')">down</v-btn>
    <v-btn flat color="orange" v-on:click="moveCamera('stop')">stop</v-btn>
    <v-btn flat color="grey" v-on:click="moveCamera('right')">right</v-btn>
    <v-btn flat color="grey" v-on:click="moveCamera('left')">left</v-btn>
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
    },
    moveCamera: async function (direction: string) {
      switch (direction) {
        case 'up': {
          axios.get(apiURL.concat(api.commands.move_up));
          //  .then(setTimeout(axios.get(apiURL.concat(api.commands.stop_moving)), 2000));
          break;
        }
        case 'down': {
          axios.get(apiURL.concat(api.commands.move_down));
          break;
        }
        case 'right': {
          axios.get(apiURL.concat(api.commands.move_right));
          break;
        }
        case 'left': {
          axios.get(apiURL.concat(api.commands.move_left));
          break;
        }
        case 'stop': {
          axios.get(apiURL.concat(api.commands.stop_moving));
          break;
        }
      }
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
