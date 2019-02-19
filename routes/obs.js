const express = require('express');
const router = express.Router();
const remoteController = require('../controllers/obs_remote');

router.get('/', remoteController.remote_get_open_connection);

router.get('/start-recording', remoteController.remote_post_start_recording);

router.get('/stop-recording', remoteController.remote_post_stop_recording);

router.get('/close', remoteController.remote_get_close_connection);

router.get('/scenes', remoteController.remote_get_scenes);

module.exports = router;
