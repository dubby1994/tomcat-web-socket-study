package cn.dubby.tomcat.study.ws;

import javax.websocket.OnMessage;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

@ServerEndpoint("/websocket/echoStreamAnnotation")
public class EchoStreamAnnotation {

    Writer writer;
    OutputStream stream;

    @OnMessage
    public void echoTextMessage(Session session, String msg) throws IOException {
        System.out.println(session.getId() + "\t" + msg);
        if (writer == null) {
            writer = session.getBasicRemote().getSendWriter();
        }
        writer.write(msg);
        writer.flush();
    }

    @OnMessage
    public void echoBinaryMessage(byte[] msg, Session session) throws IOException {
        System.out.println(session.getId() + "\t" + msg.length);
        if (stream == null) {
            stream = session.getBasicRemote().getSendStream();
        }
        stream.write(msg);
        stream.flush();

    }

    @OnMessage
    public void echoPongMessage(PongMessage pm) {
        // NO-OP
    }

}
