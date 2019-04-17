package zenika.api.success;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CameraResourceTest {

    @Test
    public void testCameraMoveUpEndpointSuccess() {
        given()
                .when().get("/camera/move-up")
                .then()
                .statusCode(200)
                .body(is("Camera moved up"));
    }

    @Test
    public void testCameraMoveDownEndpointSuccess() {
        given()
                .when().get("/camera/move-down")
                .then()
                .statusCode(200)
                .body(is("Camera moved down"));
    }

    @Test
    public void testCameraMoveRightEndpointSuccess() {
        given()
                .when().get("/camera/move-right")
                .then()
                .statusCode(200)
                .body(is("Camera moved right"));
    }

    @Test
    public void testCameraMoveLeftEndpointSuccess() {
        given()
                .when().get("/camera/move-left")
                .then()
                .statusCode(200)
                .body(is("Camera moved left"));
    }

    @Test
    public void testCameraStopEndpointSuccess() {
        given()
                .when().get("/camera/stop")
                .then()
                .statusCode(200)
                .body(is("Camera stopped"));
    }

}
