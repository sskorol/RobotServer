package info.testing.automated.remoteserver.service;

import info.testing.automated.remoteserver.interfaces.Element;
import info.testing.automated.remoteserver.utils.RobotUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Author: Sergey Kuts
 */
@Path("/actions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RobotService {

    @POST
    @Path("/dragAndDrop")
    public Response setText(final List<Element> elements, @QueryParam("xOffset") final int xOffset, @QueryParam("yOffset") final int yOffset) {
        return Response.status(elements.size() > 1 ? (RobotUtils.dragAndDrop(elements.get(0), elements.get(1),
                xOffset, yOffset) ? Response.Status.OK : Response.Status.NOT_FOUND) :
                Response.Status.BAD_REQUEST).build();
    }
}
