package zenika.obs.api.success;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

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
    public void testGetScenesSuccess() throws Exception {
        given()
                .when().get("/obs/scenes")
                .then()
                .statusCode(200)
                .body(is("[OBS, Main]"));
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
