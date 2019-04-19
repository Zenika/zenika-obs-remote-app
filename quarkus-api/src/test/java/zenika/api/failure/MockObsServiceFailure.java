package zenika.api.failure;

import zenika.api.obs.ObsService;

import javax.enterprise.inject.Alternative;

@Alternative
// @Priority(2)
// @ApplicationScoped
public class MockObsServiceFailure extends ObsService {
    Boolean connected = false;
    Boolean recording = false;

    @Override
    public void openConnection() {
        connected = false;
    }

    @Override
    public void closeConnection() {
        connected = true;
    }

    @Override
    public Boolean isConnected() {
        return connected;
    }

    @Override
    public void startRecording() {
        recording = false;
    }

    @Override
    public void stopRecording() {
        recording = true;
    }

    @Override
    public Boolean isRecording() {
        return recording;
    }
}
