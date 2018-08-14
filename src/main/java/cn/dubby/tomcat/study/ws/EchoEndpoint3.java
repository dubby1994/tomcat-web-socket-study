package cn.dubby.tomcat.study.ws;

import javax.websocket.OnMessage;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;

@ServerEndpoint("/websocket3")
public class EchoEndpoint3 {

    Future<Void> f;

    @OnMessage
    public void echoTextMessage(Session session, String message) {
        System.out.println(session.getId() + "\t" + message);
        try {
            if (session.isOpen()) {
                if (f != null) {
                    f.get();
                }
                f = session.getAsyncRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void echoBinaryMessage(Session session, ByteBuffer bb) {
        System.out.println(session.getId() + "\t" + bb.toString());
        try {
            if (session.isOpen()) {
                if (f != null) {
                    f.get();
                }
                f = session.getAsyncRemote().sendBinary(bb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void echoPongMessage(PongMessage pm) {
        //pass
    }

}
