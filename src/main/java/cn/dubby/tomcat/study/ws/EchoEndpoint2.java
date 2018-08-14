package cn.dubby.tomcat.study.ws;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

@ServerEndpoint("/websocket2")
public class EchoEndpoint2 {

    private static final AtomicLong count = new AtomicLong();

    @OnOpen
    public void open(Session session) {
        System.out.println("在线人数:" + count.incrementAndGet());
        try {
            session.getBasicRemote().sendText("welcome");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session) {
        System.out.println("在线人数:" + count.decrementAndGet());
    }

    @OnMessage
    public void echoTextMessage(Session session, String message) {
        System.out.println(session.getId() + "\t" + message);
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void echoBinaryMessage(Session session, ByteBuffer bb) {
        System.out.println(session.getId() + "\t" + bb.toString());
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendBinary(bb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void echoPongMessage(PongMessage pm) {
        //pass
    }

}
