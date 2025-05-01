package network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class GameHost implements AutoCloseable {
  private final Server server;
  private final Queue<String> msgQue = new ConcurrentLinkedQueue<>();

  public GameHost(final int tcpPort) throws IOException {
    server = new Server();
    server.start();
    server.bind(tcpPort);
    addServerListener();
  }

  public String getMsgReceived() {
    String update = msgQue.poll();
    return update;
  }

  void send(final String message) {
    //server.sendToTCP(); add this
  }

  private void addServerListener() {
    server.addListener(new Listener() {
      public void received(final Connection connection, final Object object) {
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
