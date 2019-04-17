const config = require('../config');

const remote = {
    infos: {
        connected: false,
        recording: false,
        stopped: true,
        startedAt: 0,
        recorded: 0,
        scenes: []
    },

    openConnection: function(obs) {
        let result = obs.connect({
            address: config.obs.host.concat(':').concat(config.obs.port),
            password: config.obs.pwd
        })
            .then(() => {
                console.log("Success! We're connected & authenticated.");
                this.infos.connected = true;

                return this.infos
            })
            .catch(err => {
                console.log(err);
                throw err;
            });

        obs.on('SwitchScenes', data => {
            console.log(`New Active Scene: ${data.sceneName}`);
        });

        // Avoiding uncaught exceptions.
        obs.on('error', err => {
            console.error('socket error:', err);
        });

        return result;
    },

    closeConnection: function (obs) {
        obs.disconnect();
        return true;
    },

    startRecording: function(obs) {
        let result = obs.send('StartRecording')
        .then(response => {
            if(response.status == "ok") {
                console.log("OBS is now recording");
                this.infos.recording = true;
                this.infos.startedAt = new Date().getTime();
                this.infos.stopped = false;
            }

            return this.infos;
        })
        .catch(err => {
            // console.log(err);
            throw err;
        });

        // Avoiding uncaught exceptions.
        obs.on('error', err => {
            console.error('socket error:', err);
        });

        return result;
    },

    stopRecording: function (obs) {
        let result = obs.send('StopRecording')
            .then(response => {
                if(response.status == "ok") {
                    console.log("OBS recording has been stopped");
                    this.infos.stopped = true;
                    this.infos.recording = false;
                    this.infos.recorded = new Date().getTime() - this.infos.startedAt;
                }
                return this.infos;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });

        // Avoiding uncaught exceptions.
        obs.on('error', err => {
            console.error('socket error:', err);
        });

        return result;
    },

    getScenes: function (obs) {
        let result = obs.send('GetSceneList')
            .then(response => {
                console.log("Retrieving scenes list");
                //console.log(response.scenes);
                this.infos.scenes = response.scenes;
                return this.infos.scenes;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });

        // Avoiding uncaught exceptions.
        obs.on('error', err => {
            console.error('socket error:', err);
        });

        return result;
    },

    getCurrentScene: function(obs) {
        let result = obs.send('GetCurrentScene')
            .then(response => {
                const sceneName = response.name;
                console.log('Current scene is ' + sceneName);
                return sceneName;
            })
            .catch(err => {
                console.log(err);
                throw err;
            });

        // Avoiding uncaught exceptions.
        obs.on('error', err => {
            console.error('socket error:', err);
        });

        return result;
    },

    setCurrentScene: function(obs, sceneName) {
        let result = obs.send('SetCurrentScene', {
            'scene-name': sceneName
        })
            .catch(err => {
                console.log(err);
                throw err;
            });

        // Avoiding uncaught exceptions.
        obs.on('error', err => {
            console.error('socket error:', err);
        });

        return result;
    }
};

module.exports = remote;
