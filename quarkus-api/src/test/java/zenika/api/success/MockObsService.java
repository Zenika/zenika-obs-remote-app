package zenika.api.success;

import zenika.api.obs.ObsService;
import zenika.api.obs.Scene;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.HashSet;
import java.util.Set;

@Alternative
@Priority(1)
@ApplicationScoped
public class MockObsService extends ObsService {
    Boolean connected = true;
    Boolean recording = true;

    @Override
    public void openConnection() {
        connected = true;
    }

    @Override
    public void closeConnection() {
        connected = false;
    }

    @Override
    public Boolean isConnected() {
        return connected;
    }

    @Override
    public void startRecording() {
        recording = true;
    }

    @Override
    public void stopRecording() {
        recording = false;
    }

    @Override
    public Boolean isRecording() {
        return recording;
    }

    @Override
    public Set<Scene> getScenes() {
        HashSet<Scene> result = new HashSet<>();
        result.add(new Scene("Main"));
        result.add(new Scene("OBS"));

        return result;
    }
}
