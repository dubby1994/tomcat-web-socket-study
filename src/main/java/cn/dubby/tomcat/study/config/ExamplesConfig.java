package cn.dubby.tomcat.study.config;

import cn.dubby.tomcat.study.ws.EchoEndpoint;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

public class ExamplesConfig implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {

        Set<ServerEndpointConfig> result = new HashSet<>();

        if (scanned.contains(EchoEndpoint.class)) {
            result.add(ServerEndpointConfig.Builder.create(EchoEndpoint.class, "/websocket").build());
        }

        return result;
    }


    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        //Deploy all WebSocket endpoints defined by annotations in the examples
        // web application. Filter out all others to avoid issues when running
        // tests on Gump
        Set<Class<?>> results = new HashSet<>();
        for (Class<?> clazz : scanned) {
            if (clazz.getPackage().getName().contains("ws")) {
                results.add(clazz);
            }
        }
        return results;
    }
}
