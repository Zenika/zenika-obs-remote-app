package zenika.obs.api.failure;

import zenika.obs.api.ObsRemote;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@Priority(10)
@ApplicationScoped
public class MockObsRemoteFailure extends ObsRemote {
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
