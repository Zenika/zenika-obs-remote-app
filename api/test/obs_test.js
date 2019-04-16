const assert = require('chai').assert;
const sinon = require('sinon');

const OBSWebSocket = require('obs-websocket-js');
const remote = require('../remotes/obs_remote');

const obs = new OBSWebSocket();

before( function () {
    remote.openConnection(obs);
});

after( function () {
    remote.closeConnection(obs);
});

describe('Testing obs remote when everything is ok', function() {

    it('Verify that OBS started recording', function() {
        const obsMock = sinon.mock(obs);
        obsMock.expects("send").once().withArgs('StartRecording')
            .returns(
                new Promise(function(resolve, reject){
                    resolve({
                        status: "ok"
                    });
                }));

        remote.startRecording(obs)
            .then(result => {
                console.log(result);
            })
            .catch(err => {
                console.log(err);
            });

        obsMock.verify();
        //assert.isTrue(remote.startRecording(), "OBS started recording");
    });

    it('Retrieve scene list from OBS', function() {
        const obsMock = sinon.mock(obs);
        obsMock.expects("send").once().withArgs('GetSceneList')
            .returns(
                new Promise(function(resolve, reject) {
                    let scenes = [
                        { name: 'Scene1', sources: [] },
                        { name: 'Scene2', sources: [] },
                        { name: 'Scene3', sources: [] }
                    ];
                    resolve({ scenes: scenes });
                })
            );

        remote.getScenes(obs)
            .then(result => {
                assert.exists(result, 'There are scenes that have been retrieved');
                // console.log("Test gone well");
                console.log(result);
            })
            .catch(err => {
                // console.log('Error while testing obs_remote.getScenes');
                console.log(err);
            });

        obsMock.verify();
    });

    it('Verify that OBS stopped recording', function() {
        const obsMock = sinon.mock(obs);
        obsMock.expects("send").once().withArgs('StopRecording')
            .returns(
                new Promise(function(resolve, reject) {
                    resolve({
                        status: "ok"
                    });
                }));

        remote.stopRecording(obs)
            .then(result => {
                console.log(result);
            })
            .catch(err => {
                console.log(err);
            });


        obsMock.verify();
    });

    it('Get the current scene', function() {
        const obsMock = sinon.mock(obs);
        obsMock.expects("send").once().withArgs('GetCurrentScene')
            .returns(
                new Promise(function(resolve, reject) {
                    let scene = { name: 'Scene1', sources: [] };
                    resolve(scene);
                })
            );

        remote.getCurrentScene(obs)
            .then(result => {
                assert.exists(result, 'Current scene has been retrieved');
                console.log(result);
            })
            .catch(err => {
                console.log(err);
            });

        obsMock.verify();
    });

    it('Set the current scene', function() {
        const obsMock = sinon.mock(obs);
        obsMock.expects("send").once().withArgs('SetCurrentScene')
            .returns(
                new Promise(function(resolve, reject) {
                    resolve({
                        status: 'ok'
                    });
                })
            );

        remote.setCurrentScene(obs, 'Screen')
            .then(result => {
                assert.equal('ok', result.status, 'Current scene has been set');
                console.log(result);
            })
            .catch(err => {
                // console.log('Error while testing obs_remote.getScenes');
                console.log(err);
            });

        obsMock.verify();
    });
});

describe('Testing obs remote when nothing is ok', function() {

    it('Verify that error is handled when starting recording fails', function() {
        const obsMock = sinon.mock(obs);
        obsMock.expects("send").once().withArgs('StartRecording')
            .returns(
                new Promise(function(resolve, reject){
                    reject({
                        error: "This is an error, couldn't start recording for some reason"
                    });
                }));

        remote.startRecording(obs)
            .then(result => {
                console.log("Error while testing obs_remote.startRecording");
                console.log(result);
            })
            .catch(err => {
                console.log("Test gone well");
                console.log(err);
            });


        obsMock.verify();
    });

    /*it('Verify that error is handled when starting recording is actioned more than once', function() {
        const obsMock = sinon.mock(obs);
        obsMock.expects("send").atLeast(2).withArgs('StartRecording')
            .returns(
                new Promise(function(resolve, reject){
                    reject({
                        error: "recording already active"
                    });
                }));

        remote.startRecording(obs);

        obsMock.verify();
        //assert.isTrue(remote.startRecording(), "OBS started recording");
    });*/

    it('Verify that error is handled when stoping recording fails', function() {
        const obsMock = sinon.mock(obs);
        obsMock.expects("send").once().withArgs('StopRecording')
            .returns(
                new Promise(function(resolve, reject){
                    reject({
                        error: "This is an error, couldn't stop recording for some reason"
                    });
                }));

        remote.stopRecording(obs)
            .then(result => {
                console.log("Error while testing obs_remote.stopRecording");
                console.log(result);
            })
            .catch(err => {
                console.log("Test gone well");
                console.log(err);
            });

        obsMock.verify();
    });
});
