const OBSWebSocket = require('obs-websocket-js');

//Setting obs-websocket up
const obs = new OBSWebSocket();
const remote = require('../obs/obs_remote');

exports.remote_get_open_connection = (req, res, next) => {
    remote.openConnection(obs)
        .then(result => {
            res.status(200).json({
                message: "Connection to obs succeeded!",
                response: result
            });
        })
        .catch(err => {
            res.status(500).json({
                error: "Couldn't connect to obs!"
            });
        });
};

exports.remote_get_close_connection = (req, res, next) => {
    if(remote.closeConnection(obs)){
        res.status(200).json({
            message: "Connection to obs gone down!"
        });
    } else {
        res.status(500).json({
            error: "Closing connection to obs failed!"
        });
    }
}

exports.remote_post_start_recording = (req, res, next) => {
    remote.startRecording(obs)
        .then(result => {
            res.status(200).json({
                message: "You're now recording! \n " +
                    "Recording started at : " + result.startedAt,
                response: result
            });
        })
        .catch(err => {
            res.status(500).json({
                message: "Starting recording failed!",
                error: err
            });
        });
};

exports.remote_post_stop_recording = (req, res, next) => {
    remote.stopRecording(obs)
        .then(result => {
            res.status(200).json({
                message: "Recording has been stopped! " +
                    "It started at " + remote.infos.startedAt +
                    "Record duration : " + remote.infos.recorded.toString(),
                response: result
            });
        })
        .catch(err => {
            res.status(500).json({
                message: "Stoping recording failed!",
                error: err
            });
        });
}