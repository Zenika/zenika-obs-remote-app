const express = require('express');
const router = express.Router();
const remoteController = require('../controllers/obs_remote');

router.get('/connection/open', remoteController.remote_get_open_connection);

router.post('/recording/start', remoteController.remote_post_start_recording);

router.post('/recording/stop', remoteController.remote_post_stop_recording);

router.get('/connection/close', remoteController.remote_get_close_connection);

router.get('/scenes', remoteController.remote_get_scenes);

router.get('/current-scene', remoteController.remote_get_current_scene);

router.post('/current-scene/set', remoteController.remote_post_set_current_scene);

module.exports = router;
