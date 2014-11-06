package info.testing.automated.client;

import info.testing.automated.remoteserver.interfaces.Element;
import info.testing.automated.remoteserver.utils.ObjectMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Author: Sergey Kuts
 */
public class Client {

    private javax.ws.rs.client.Client client;
    private WebTarget service;

    private String ip;

    private static final Logger CLIENT_LOGGER = Logger.getLogger(Client.class.getName());

    public Client(final String ip, final int port) {
        this.ip = ip;
        this.client = ClientBuilder.newBuilder()
                .register(ObjectMapperProvider.class)
                .register(JacksonFeature.class)
                .build();

        this.service = this.client.target("http://" + this.ip + ":" + port + "/robot");
    }

    public void dragAndDrop(final List<Element> elements, final int xOffset, final int yOffset) {
        final Response response = service.path("actions")
                .path("dragAndDrop")
                .queryParam("xOffset", xOffset)
                .queryParam("yOffset", yOffset)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(elements));

        final int status = response.getStatus();

        if (status == Response.Status.OK.getStatusCode()) {
            CLIENT_LOGGER.info(elements.get(0).getElement() + " has been successfully dragged and dropped to " +
                    elements.get(1).getElement() + " with offset [" + xOffset + ":" + yOffset + "] on " + ip);
        } else {
            CLIENT_LOGGER.severe("Unable to drag and drop elements on " + ip + "; status = " + status);
        }

        response.close();
    }

    public void close() {
        client.close();
    }

    public static void main(final String[] args) {
        final Client client = new Client("127.0.0.1", 4041);
        final List<Element> elements = new ArrayList<Element>(Arrays.asList(
                new RobotElement(30, 40, 5, 10),
                new RobotElement(50, 60, 15, 20)));

        client.dragAndDrop(elements, 100, 200);
        client.close();
    }
}
