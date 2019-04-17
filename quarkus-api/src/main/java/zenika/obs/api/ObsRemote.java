package zenika.obs.api;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ObsRemote implements Serializable {
    private Boolean connected;
    private Boolean recording;
    private String currentScene;
    private Set<String> scenes;

    public ObsRemote() {
        this.connected = false;
        this.recording = false;
        this.currentScene = "No current scene";
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

    public String getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
    }

    public Set<String> getScenes() {
        return scenes;
    }

    public void setScenes(Set<String> scenes) {
        this.scenes = scenes;
    }

    public void addScene(String scene) {
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
