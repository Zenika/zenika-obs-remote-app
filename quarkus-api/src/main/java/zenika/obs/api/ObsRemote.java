package zenika.obs.api;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

@ApplicationScoped
public class ObsRemote {
    @ConfigProperty(name = "obs.base.url") public String baseUrl;
    @ConfigProperty(name = "open.connection") public String openConnection;
    @ConfigProperty(name = "close.connection") public String closeConnection;
    @ConfigProperty(name = "start.recording") public String startRecording;
    @ConfigProperty(name = "stop.recording") public String stopRecording;
    @ConfigProperty(name = "scenes") public String remoteScenes;
    @ConfigProperty(name = "current.scene") public String remoteCurrentScene;
    @ConfigProperty(name = "set.current.scene") public String setCurrentScene;

    private Boolean connected;
    private Boolean recording;
    private String currentScene;
    private Set<String> scenes;

    private CloseableHttpClient httpClient;

    public ObsRemote() {
        this.connected = false;
        this.recording = false;
        this.currentScene = "No current scene";
        this.scenes = new HashSet<>();
    }

    public Boolean isConnected() {
        return connected;
    }

    public void openConnection() throws Exception {
        this.httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse =
                Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(openConnection), null);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            this.connected = true;
        }
        this.httpClient.close();
    }

    public void closeConnection() throws Exception {
        this.httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse =
                Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(closeConnection), null);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            this.connected = false;
        }
        this.httpClient.close();
    }

    public Boolean isRecording() {
        return recording;
    }

    public void startRecording() throws Exception {
        this.httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse =
                Utils.sendPostRequestToNodeAPI(httpClient, baseUrl.concat(startRecording), null);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            this.recording = true;
        }
        this.httpClient.close();
    }

    public void stopRecording() throws Exception{
        this.httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse =
                Utils.sendPostRequestToNodeAPI(httpClient, baseUrl.concat(stopRecording), null);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            this.recording = false;
        }
        this.httpClient.close();
    }

    public Set<String> getScenes() throws Exception {
        this.httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse =
                Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(remoteScenes), null);

        if(httpResponse.getStatusLine().getStatusCode() == 200) {
            JSONObject json = new JSONObject(
                    Utils.readResponseFromNodeAPI(httpResponse));
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i ++) {
                this.scenes.add(jsonArray.getJSONObject(i).getString("name"));
            }
        }

        this.httpClient.close();
        return this.scenes;
    }

    public String getCurrentScene() throws Exception {
        this.httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse =
                Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(remoteCurrentScene), null);

        if(httpResponse.getStatusLine().getStatusCode() == 200) {
            JSONObject json = new JSONObject(
                    Utils.readResponseFromNodeAPI(httpResponse));
            this.currentScene = json.getString("data");
        }

        this.httpClient.close();
        return currentScene;
    }

    public String setCurrentScene(String scene) throws Exception {
        this.httpClient = HttpClients.createDefault();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("scene-name", scene));
        CloseableHttpResponse httpResponse =
                Utils.sendPostRequestToNodeAPI(httpClient, baseUrl.concat(setCurrentScene), params);

        if(httpResponse.getStatusLine().getStatusCode() == 200) {
            this.currentScene = scene;
        }

        this.httpClient.close();
        return this.currentScene;
    }
}
