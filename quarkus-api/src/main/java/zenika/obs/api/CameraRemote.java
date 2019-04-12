package zenika.obs.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CameraRemote {
    @ConfigProperty(name = "camera.base.url") public String baseUrl;
    @ConfigProperty(name = "camera.move.up") public String moveUp;
    @ConfigProperty(name = "camera.move.down") public String moveDown;
    @ConfigProperty(name = "camera.move.right") public String moveRight;
    @ConfigProperty(name = "camera.move.left") public String moveLeft;
    @ConfigProperty(name = "camera.stop") public String stop;

    private CloseableHttpClient httpClient;

    public Boolean moveTo(String direction) {

        String moveTo = null;

        switch (direction) {
            case "up":
                moveTo = moveUp;
                break;
            case "down":
                moveTo = moveDown;
                break;
            case "right":
                moveTo = moveRight;
                break;
            case "left":
                moveTo = moveLeft;
                break;
            case "stop":
                moveTo = stop;
                break;
            default:
                break;
        }

        return move(moveTo);
    }

    private Boolean move(String direction) {
        if(direction == null)
            return false;

        this.httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        Boolean moved = false;

        try {
            httpResponse = Utils.sendGetRequestToNodeAPI(httpClient, baseUrl.concat(direction), null);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                moved = true;
            } else {
                moved = false;
            }
            this.httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return moved;
        }
    }
}
