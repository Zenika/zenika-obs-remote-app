const camera = require('../remotes/camera_remote');

exports.remote_get_camera_move_up = (req, res, next) => {
    camera.move_cam_up()
        .then(result => {
            res.status(200).json({ data: result });
        })
        .catch(err => {
            res.status(500).json({ error: err });
        });
};

exports.remote_get_camera_move_down = (req, res, next) => {
    camera.move_cam_down()
        .then(result => {
            res.status(200).json({ data: result });
        })
        .catch(err => {
            res.status(500).json({ error: err });
        });
};

exports.remote_get_camera_move_right = (req, res, next) => {
    camera.move_cam_right()
        .then(result => {
            res.status(200).json({ data: result });
        })
        .catch(err => {
            res.status(500).json({ error: err });
        });
};

exports.remote_get_camera_move_left = (req, res, next) => {
    camera.move_cam_left()
        .then(result => {
            res.status(200).json({ data: result });
        })
        .catch(err => {
            res.status(500).json({ error: err });
        });
};

exports.remote_get_camera_stop = (req, res, next) => {
    camera.stop_cam()
        .then(result => {
            res.status(200).json({ data: result });
        })
        .catch(err => {
            res.status(500).json({error: err });
        })
}
