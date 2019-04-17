const assert = require('chai').assert;
const expect = require('chai').expect;
const nock = require('nock');

const remote = require('../remotes/camera_remote');
const config = require('../config');
const base = config.camera.protocol.concat('://')
    .concat(config.camera.user).concat(':')
    .concat(config.camera.pwd).concat('@')
    .concat(config.camera.host);

describe('Testing camera remote when everything is ok', function() {
    /* beforeEach(() => {
        nock();
    });
    afterEach(() => {
        nock.restore();
    }); */

    it('Verify that camera moves up', function() {
        nock(base)
            .get('/','cmd=ptzctrl&-step=0&-act=up&-speed=20')
            .reply(200);

        remote.move_cam_up()
            .then(result => expect(result).to.eql('[Succeed]set ok.'))
            .catch(error => console.log(error));
        nock.restore();
    });

    it('Verify that camera moves down', function() {
        nock(base)
            .get('/','cmd=ptzctrl&-step=0&-act=down&-speed=20')
            .reply(200);

        remote.move_cam_down()
            .then(result => assert.equal(result, '[Succeed]set ok.', 'Camera moved down'))
            .catch(error => console.log(error));
        nock.restore();
    });

    it('Verify that camera moves right', function() {
        nock(base)
            .get('/','cmd=ptzctrl&-step=0&-act=right&-speed=20')
            .reply(200);

        remote.move_cam_right()
            .then(result => assert.equal(result, '[Succeed]set ok.', 'Camera moved right'))
            .catch(error => console.log(error));
        nock.restore();
    });

    it('Verify that camera moves left', function() {
        nock(base)
            .get('/','cmd=ptzctrl&-step=0&-act=left&-speed=20')
            .reply(200);

        remote.move_cam_left()
            .then(result => assert.equal(result, '[Succeed]set ok.', 'Camera moved left'))
            .catch(error => console.log(error));
        nock.restore();
    });

    it('Verify that camera stops moving', function() {
        nock(base)
            .get('/', 'cmd=ptzctrl&-step=0&-act=stop&-speed=20')
            .reply(200);

        remote.stop_cam()
            .then(result => assert.equal(result, '[Succeed]set ok.', 'Camera stopped moving'))
            .catch(error => console.log(error));
        nock.restore();
    });
});
