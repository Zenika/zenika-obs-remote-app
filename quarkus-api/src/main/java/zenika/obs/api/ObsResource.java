package zenika.obs.api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/obs")
public class ObsResource {

    @Inject ObsRemote remote;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/connection/open")
    public Response openConnection() {
        try {
            remote.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(remote.isConnected()) {
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
            remote.startRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(remote.isRecording()) {
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
            remote.stopRecording();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!remote.isRecording()) {
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
            remote.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!remote.isConnected()) {
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
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/scenes")
    public Response getScenes() throws Exception{
        return Response
                .status(Response.Status.OK)
                .entity(remote.getScenes())
                .build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/current-scene")
    public Response getCurrentScene() throws Exception {
        return Response
                .status(Response.Status.OK)
                .entity(remote.getCurrentScene())
                .build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/current-scene/set/{scene}")
    public Response setCurrentScenne(@PathParam("scene") String scene) throws Exception {
        return Response
                .status(Response.Status.OK)
                .entity(remote.setCurrentScene(scene))
                .build();
    }
}
