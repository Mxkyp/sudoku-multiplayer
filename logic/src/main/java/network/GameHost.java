package network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public final class GameHost implements AutoCloseable {
  private final Server server;
  private final Queue<String> msgQue;
  private String msgToSend;

  public String getMsgToSend() {
    return msgToSend;
  }

  public void setMsgToSend(final String msgToSend) {
    this.msgToSend = msgToSend;
  }


  public GameHost(final int tcpPort) throws IOException {
    server = new Server();
    server.start();
    server.bind(tcpPort);
    addServerListener();
    msgQue = new ConcurrentLinkedQueue<>();
  }

  public String getMsgReceived() {
    String update = msgQue.poll();
    return update;
  }

  private void addServerListener() {
    server.addListener(new Listener() {
      public void received(Connection connection, Object object) {
        if (object instanceof String) {
          msgQue.add((String) object);
        }
      }
    });
  }


  @Override
  public void close() {
    server.close();
  }

}
