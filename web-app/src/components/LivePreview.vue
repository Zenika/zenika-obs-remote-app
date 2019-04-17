<template>
  <div class="columns">
    <div class="column is-8 is-offset-1">
      <video id="player-preview" autoplay>
        <source :src="live_url" type="application/x-mpegURL">
      </video>
    </div>
    <div class="column is-2" id="board-remote-scenes">
      <div class="columns">
        <div class="column is-4 is-offset-4" v-on:click="moveCamera('up')">UP</div>
      </div>
      <div class="columns">
        <div class="column is-4" v-on:click="moveCamera('left')">LEFT</div>
        <div class="column is-4" v-on:click="moveCamera('stop')">STOP</div>
        <div class="column is-4" v-on:click="moveCamera('right')">RIGHT</div>
      </div>
      <div class="columns">
        <div class="column is-4 is-offset-4" v-on:click="moveCamera('down')">DOWN</div>
      </div>
      <!--v-data-table :headers='headers'
                    :items="scenes"
                    class="elevation-1">
        <template slot="items"
                  slot-scope="props">
          <td class="text" v-on:click="setCurrentScene(props.item.name)">
            {{ props.item.name }}
          </td>
        </template>
      </v-data-table-->
    </div>
  </div>
</template>

<script>
import videojs from 'video.js';
import 'videojs-contrib-hls';
import { Scene } from '@/entities/scene';
import api from '../../api.json';

const axios = require('axios');
const apiURL = process.env.VUE_APP_API_URL;

export default {
  name: 'LivePreview',
  components: {
  },
  data: function() {
    return {
      live_url: process.env.VUE_APP_PREVIEW_URL,
      headers: [
        { text: 'Scènes', value: 'name' }
      ],
      // scenes: [],
      columns: [
        {
          field: 'name',
          label: 'Scènes'
        }
      ]
    };
  },
  methods: {
    moveCamera: async function (direction) {
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
  },
  async mounted() {
    var player = videojs('player-preview');
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
