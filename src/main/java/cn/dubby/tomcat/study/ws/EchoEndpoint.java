package cn.dubby.tomcat.study.ws;

import javax.websocket.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class EchoEndpoint extends Endpoint {

    private static final AtomicLong count = new AtomicLong();

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        System.out.println("在线人数:" + count.incrementAndGet());
        session.addMessageHandler(new EchoMessageHandlerText(session));
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("在线人数:" + count.decrementAndGet());
    }

    private static class EchoMessageHandlerText implements MessageHandler.Partial<String> {

        private final Session session;

        private EchoMessageHandlerText(Session session) {
            this.session = session;
        }

        @Override
        public void onMessage(String message, boolean last) {
            if (session == null)
                return;

            System.out.println(session.getId() + "\t" + message);
            try {
                session.getBasicRemote().sendText(message, last);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
