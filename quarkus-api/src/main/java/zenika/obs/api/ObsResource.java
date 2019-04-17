package zenika.obs.api;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/connection/open")
    public String openConnection() {
        service.openConnection();
        return new JSONObject(service.getRemote().isConnected()).toString();//service.getRemote().toString();
    }*/

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
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/scenes")
    public Response getScenes() {
        return Response
                .status(Response.Status.OK)
                .entity(service.getScenes())
                .build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/current-scene")
    public Response getCurrentScene() throws Exception {
        return Response
                .status(Response.Status.OK)
                .entity(service.getCurrentScene())
                .build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/current-scene/set/{scene}")
    public Response setCurrentScenne(@PathParam("scene") String scene) throws Exception {
        return Response
                .status(Response.Status.OK)
                .entity(service.setCurrentScene(scene))
                .build();
    }
}
