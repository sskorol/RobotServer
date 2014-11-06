package info.testing.automated.remoteserver.server;

import info.testing.automated.remoteserver.service.RobotService;
import info.testing.automated.remoteserver.utils.ObjectMapperProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerConfiguration extends ResourceConfig {
    public ServerConfiguration() {
        super(ObjectMapperProvider.class, JacksonFeature.class, RobotService.class);
    }
}