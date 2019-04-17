<template>
  <v-card-actions>
    <v-btn flat color="red" v-on:click="startRecording">rec</v-btn>
    <v-btn flat color="grey" v-on:click="stopRecording">stop</v-btn>
    <v-btn v-for="scene of scenes"
           color="grey"
           :key="scene.name"
           v-on:click="setCurrentScene(scene.name)">
      {{scene.name}}
    </v-btn>
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
      scenes: []
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
    getCurrentScene: function() {
      let response = axios.get(apiURL.concat(api.commands.get_current_scene));
      return response.data;
    },
    setCurrentScene: function(sceneName) {
      axios.post(
        apiURL
          .concat(api.commands.set_current_scene)
          .concat('?scene-name=' + sceneName))
        .catch(err => {
          alert('Changement de scène impossible');
          console.log(err);
        });
    },
    findObjectByKey: function(array, key, value) {
      for (var i = 0; i < array.length; i++) {
        if (array[i][key] === value) {
          return array[i];
        }
      }
      return null;
    }
  },
  async mounted() {
    try {
      await axios.get(apiURL.concat(api.commands.open_connection));
      let res2 = await axios.get(apiURL.concat(api.commands.get_scenes));
      let sceneList = JSON.parse(JSON.stringify(res2.data));
      sceneList.data.forEach(scene => {
        console.log(scene);
        this.$data.scenes.push(scene);
      });
    } catch (e) {
      throw e;
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
