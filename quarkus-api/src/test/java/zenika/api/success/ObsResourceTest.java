package zenika.api.success;

import io.quarkus.test.junit.QuarkusTest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import zenika.api.obs.Scene;

import java.util.HashSet;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
public class ObsResourceTest {

    @Test
    public void testOpenConnectionEndpointSuccess() {
        given()
          .when().get("/obs/connection/open")
          .then()
             .statusCode(200)
             .body(is("Connection succeed"));
    }

    @Test
    public void testStartAndStopRecordingEndpointSuccess() {
        given()
                .when().post("/obs/recording/start")
                .then()
                .statusCode(200)
                .body(is("OBS is recording"));

        given()
                .when().post("/obs/recording/stop")
                .then()
                .statusCode(200)
                .body(is("OBS stopped recording"));
    }

    @Test
    public void testGetScenesSuccess() {
        given()
                .when().get("/obs/scenes")
                .then()
                .statusCode(200)
                .body("name", hasItems("OBS", "Main"));
    }

    @Test
    public void testCloseConnectionEndpointSuccess() {
        given()
                .when().get("/obs/connection/close")
                .then()
                .statusCode(200)
                .body(is("Connection closed"));
    }

}
