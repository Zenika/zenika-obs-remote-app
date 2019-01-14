const remote = {
    infos: {
        connected: false,
        recording: false,
        stopped: true,
        startedAt: 0,
        recorded: 0
    },

    openConnection: function(obs) {
        let result = obs.connect({
            address: 'localhost:4444',
            password: '$up3rSecretP@ssw0rd'
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
            console.log(err);
            throw err;
        });

        // Avoiding uncaught exceptions.
        obs.on('error', err => {
            console.error('socket error:', err);
            //throw err;
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
    }
}

module.exports = remote;