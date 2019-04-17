package zenika.api.camera;

import zenika.api.camera.CameraRemote;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/camera")
@Produces(MediaType.TEXT_PLAIN)
public class CameraResource {
    @Inject
    CameraRemote remote;

    @GET
    @Path("/move-up")
    public Response moveUp() {
        if(remote.moveTo("up")) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Camera moved up")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Camera unmoved")
                .build();
    }

    @GET
    @Path("/move-down")
    public Response moveDown() {
        if(remote.moveTo("down")) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Camera moved down")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Camera unmoved")
                .build();
    }

    @GET
    @Path("/move-right")
    public Response moveRight() {
        if(remote.moveTo("right")) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Camera moved right")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Camera unmoved")
                .build();
    }

    @GET
    @Path("/move-left")
    public Response moveLeft() {
        if(remote.moveTo("left")) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Camera moved left")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Camera unmoved")
                .build();
    }

    @GET
    @Path("/stop")
    public Response stop() {
        if(remote.moveTo("stop")) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Camera stopped")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Camera unstopped")
                .build();
    }
}
