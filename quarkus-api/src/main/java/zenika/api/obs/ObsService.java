package zenika.api.obs;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.json.JSONArray;
import org.json.JSONObject;
import zenika.api.Utils;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class ObsService {
    @ConfigProperty(name = "obs.base.url") public String baseUrl;
    @ConfigProperty(name = "open.connection") public String openConnection;
    @ConfigProperty(name = "close.connection") public String closeConnection;
    @ConfigProperty(name = "start.recording") public String startRecording;
    @ConfigProperty(name = "stop.recording") public String stopRecording;
    @ConfigProperty(name = "scenes") public String remoteScenes;
    @ConfigProperty(name = "current.scene") public String remoteCurrentScene;
    @ConfigProperty(name = "set.current.scene") public String setCurrentScene;

    private ObsRemote remote;

    public ObsService() {
        remote = new ObsRemote();
    }

    public ObsRemote getRemote() {
        return remote;
    }

    public Boolean isConnected() {
        return remote.isConnected();
    }

    public void openConnection() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse =
                    Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(openConnection), null);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                remote.setConnected(true);
            }
            httpResponse.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse =
                    Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(closeConnection), null);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                remote.setConnected(false);
            }
            httpResponse.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isRecording() {
        return remote.isRecording();
    }

    public void startRecording() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse httpResponse =
                    Utils.sendPostRequestToNodeAPI(httpClient, baseUrl.concat(startRecording), null);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                remote.setRecording(true);
            }
            httpResponse.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse httpResponse =
                    Utils.sendPostRequestToNodeAPI(httpClient, baseUrl.concat(stopRecording), null);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                remote.setRecording(false);
            }
            httpResponse.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<Scene> getScenes() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse httpResponse =
                    Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(remoteScenes), null);
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
                JSONObject json = new JSONObject(
                        Utils.readResponseFromNodeAPI(httpResponse));
                JSONArray jsonArray = json.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i ++) {
                    Scene scene = new Scene(jsonArray.getJSONObject(i).getString("name"));
                    remote.getScenes().add(scene);
                }
            }
            httpResponse.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return remote.getScenes();
    }

    public Scene getCurrentScene() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse httpResponse =
                    Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(remoteCurrentScene), null);
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
                JSONObject json = new JSONObject(
                        Utils.readResponseFromNodeAPI(httpResponse));
                remote.setCurrentScene(new Scene(json.getString("data")));
            }
            httpResponse.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return remote.getCurrentScene();
    }

    public Scene setCurrentScene(Scene scene) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("scene-name", scene.getName()));

        try {
            CloseableHttpResponse httpResponse =
                    Utils.sendPostRequestToNodeAPI(httpClient, baseUrl.concat(setCurrentScene), params);
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
                remote.setCurrentScene(scene);
            }
            httpResponse.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return remote.getCurrentScene();
    }
}
