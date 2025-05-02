package network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class GamePlayer implements AutoCloseable {
  final Client client = new Client();
  private final Queue<String> msgQue = new ConcurrentLinkedQueue<>();
  private String msgToSend;

  void connectToHost(final String hostUniqueIp,
                     final int hostTcpPort) throws IOException {
    final int timeOutMs = 5000;

    client.start();
    client.connect(timeOutMs, hostUniqueIp, hostTcpPort);
    addClientListener();
  }

  public String getMsg() {
    return msgQue.poll();
  }

  void send(final String message) {
    client.sendTCP(message);
  }

  @Override
  public void close() {
    client.close();
  }

  private void addClientListener() {
    client.addListener(new Listener() {
      public void received(final Connection connection, final Object object) {
        if (object instanceof String) {
          msgQue.add((String) object);
        }
      }
    });
  }
}
