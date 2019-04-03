const express = require('express');
const router = express.Router();
const remoteController = require('../controllers/camera_controller');

router.get('/move-up', remoteController.remote_get_camera_move_up);

router.get('/move-down', remoteController.remote_get_camera_move_down);

router.get('/move-right', remoteController.remote_get_camera_move_right);

router.get('/move-left', remoteController.remote_get_camera_move_left);

router.get('/stop', remoteController.remote_get_camera_stop);

module.exports = router;
