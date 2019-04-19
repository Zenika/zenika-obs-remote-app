package zenika.api.obs;

import java.util.HashSet;
import java.util.Set;

public class ObsRemote {
    private Boolean connected;
    private Boolean recording;
    private Scene currentScene;
    private Set<Scene> scenes;

    public ObsRemote() {
        this.connected = false;
        this.recording = false;
        this.currentScene = null;
        this.scenes = new HashSet<>();
    }

    public Boolean isConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public Boolean isRecording() {
        return recording;
    }

    public void setRecording(Boolean recording) {
        this.recording = recording;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Set<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(Set<Scene> scenes) {
        this.scenes = scenes;
    }

    public void addScene(Scene scene) {
        this.scenes.add(scene);
    }

    @Override
    public String toString() {
        return "{" +
                "connected:" + connected +
                ", recording:" + recording +
                ", currentScene:'" + currentScene + '\'' +
                ", scenes:" + scenes +
                '}';
    }
}
