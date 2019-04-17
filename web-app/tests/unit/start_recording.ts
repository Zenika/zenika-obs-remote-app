import * as sinon from 'sinon'
import * as chai from 'chai'
import nock from 'nock';
import api from '../../api.json'
import Commands from '@/components/Commands.vue'
import {shallowMount} from "@vue/test-utils";

const apiURL = process.env.VUE_APP_API_URL;
const base = "http://localhost:3000";

 describe('Setting up test variables', () => {
  beforeEach(() => {
  });
  afterEach(() => {
  });

  describe('Test GET api/obs/recording/start', () => {
    it('Verify that start button actually starts the recording', function () {
      nock(base)
        .post(api.commands.start_recording)
        .reply(200);

      const wrapper = shallowMount(Commands);
      wrapper.startRecording();
    });
  });
});
