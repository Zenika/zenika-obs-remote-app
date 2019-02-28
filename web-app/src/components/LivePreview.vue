<template>
  <div class="columns">
    <div class="column is-8 is-offset-1">
      <video id="player-preview" autoplay>
        <source :src="live_url" type="application/x-mpegURL">
      </video>
    </div>
    <div class="column is-2" id="board-remote-scenes">
      <!--b-table :data="scenes"
               :columns="columns"
               :selected.sync="selected"
               @click="setCurrentScene">
      </b-table-->
      <v-data-table :headers='headers'
                    :items="scenes"
                    class="elevation-1">
        <template slot="items"
                  slot-scope="props">
          <td class="text" v-on:click="setCurrentScene(props.item.name)">
            {{ props.item.name }}
          </td>
        </template>
      </v-data-table>
    </div>
  </div>
</template>

<script>
import videojs from 'video.js';
import 'videojs-contrib-hls';
import { Scene } from '@/entities/scene';
import api from '../config/api.json';

const axios = require('axios');
const fetch = require('node-fetch');
// const { URLSearchParams } = require('url');

const apiURL = process.env.VUE_APP_API_REMOTE_URL;

export default {
  name: 'LivePreview',
  components: {
  },
  data: function() {
    // const scenes = [];
    return {
      live_url: process.env.VUE_APP_PREVIEW_URL,
      headers: [
        { text: 'Scènes', value: 'name' }
      ],
      scenes: [],
      // numbers: [{ id: 0, value: '123' }, { id: 1, value: '456' }, { id: 2, value: '789' }],
      // selected: scenes['Main'],
      columns: [
        {
          field: 'name',
          label: 'Scènes'
        }
      ]
    };
  },
  methods: {
    getCurrentScene: function() {
      let response = axios.get(apiURL.concat(api.commands.get_current_scene));
      return response.data;
    },
    setCurrentScene: function(sceneName) {
      /* const params = new URLSearchParams();
      params.append('scene-name', sceneName); */
      axios.post(
        apiURL
          .concat(api.commands.set_current_scene)
          .concat('?scene-name=' + sceneName))
        .catch(err => {
          alert('Changement de scène impossible');
          console.log(err);
        });
      /* fetch(apiURL.concat(api.commands.set_current_scene), {
        method: 'post',
        headers: {
          'Content-type': 'application/json'
        },
        body: params
      })
        .then(res => console.log(res))
        .catch(err => {
          alert('Changement de scène impossible');
          console.log(err);
        }); */
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
      var player = videojs('player-preview');
      sceneList.data.forEach(scene => {
        // console.log(scene);
        this.$data.scenes.push(scene);
      });
    } catch (e) {
      throw e;
    }
  }
};
</script>

<style scoped>
  #player-preview {
    width: 100%;
    height: 100%;
  }
  #scene-list {
    height: 120px;
    overflow-y: scroll;
  }
</style>
