package zenika.api.failure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ObsRemoteFailureTest {

   /* @Test
    public void testOpenConnectionEndpointFailure() {
        given()
                .when().get("/obs/connection/open")
                .then()
                .statusCode(500)
                .body(is("Connection failed"));
    }

    @Test
    public void testStartRecordingFailure() {
        given()
                .when().post("/obs/recording/start")
                .then()
                .statusCode(500)
                .body(is("Couldn't start recording"));
    }

    @Test
    public void testStopRecordingFailure() {
        given()
                .when().post("/obs/recording/stop")
                .then()
                .statusCode(500)
                .body(is("Couldn't stop recording"));
    }

    @Test
    public void testCloseConnectionEndpointFailure() {
        given()
                .when().get("/obs/connection/close")
                .then()
                .statusCode(500)
                .body(is("Couldn't close connection"));
    }*/

}
