const config = require('../config');
const axios = require('axios');

const urlFirstPart = config.camera.protocol
    .concat(config.camera.user).concat(':')
    .concat(config.camera.pwd).concat('@')
    .concat(config.camera.host);

const remote = {
    move_cam_up: function () {
        axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=up&-speed=45'));
        // await delay(1);
        this.stop_cam();
    },
    move_cam_down: function () {
        axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=down&-speed=45'));
        // await delay(1);
        this.stop_cam();
    },
    move_cam_right: function () {
        axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=right&-speed=45'));
        // await delay(1);
        this.stop_cam();
    },
    move_cam_left: function () {
        axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=left&-speed=45'));
        // await delay(1);
        this.stop_cam();
    },
    stop_cam: function () {
        axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=stop&-speed=45'));
    },
    delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
};

module.exports = remote;
