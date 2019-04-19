package zenika.api.obs;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/obs")
public class ObsResource {

    @Inject
    ObsService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/connection/open")
    public Response openConnection() {
        service.openConnection();

        if(service.isConnected()) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Connection succeed")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Connection failed")
                .build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/recording/start")
    public Response startRecording() {
        try {
            service.startRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(service.isRecording()) {
            return Response
                    .status(Response.Status.OK)
                    .entity("OBS is recording")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Couldn't start recording")
                .build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/recording/stop")
    public Response stopRecording() {
        try {
            service.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!service.isRecording()) {
            return Response
                    .status(Response.Status.OK)
                    .entity("OBS stopped recording")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Couldn't stop recording")
                .build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/connection/close")
    public Response closeConnection() {
        try {
            service.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!service.isConnected()) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Connection closed")
                    .build();
        }

        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Couldn't close connection")
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/scenes")
    public Set<Scene> getScenes() {
        return service.getScenes();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/current-scene")
    public Scene getCurrentScene() {
        return service.getCurrentScene();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/current-scene/set/{scene}")
    public Scene setCurrentScene(@PathParam("scene") String scene) {
        return service.setCurrentScene(new Scene(scene));
    }
}
