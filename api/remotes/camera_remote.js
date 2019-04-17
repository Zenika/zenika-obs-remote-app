const config = require('../config');
const axios = require('axios');

const urlFirstPart = config.camera.protocol.concat('://')
    .concat(config.camera.user).concat(':')
    .concat(config.camera.pwd).concat('@')
    .concat(config.camera.host);

const remote = {
    move_cam_up: function () {
        let result = axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=up&-speed=20'))
            .then(res => {
                console.log(res.data);
                return res.data;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });
        return result;
    },
    move_cam_down: function () {
        let result = axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=down&-speed=20'))
            .then(res => {
                return res.data;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });
        return result;
    },
    move_cam_right: function () {
        let result = axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=right&-speed=20'))
            .then(res => {
                return res.data;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });
        return result;
    },
    move_cam_left: function () {
        let result = axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=left&-speed=20'))
            .then(res => {
                return res.data;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });
        return result;
    },
    stop_cam: async function () {
        // await this.delay();
        let result = axios.get(urlFirstPart.concat('?cmd=ptzctrl&-step=0&-act=stop&-speed=20'))
            .then(res => {
                return res.data;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });
        return result;
    }
};

module.exports = remote;
