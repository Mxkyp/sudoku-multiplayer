package network;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public final class GamePlayer implements AutoCloseable {
  final Client client = new Client();

  void connectToHost(final String hostUniqueIp,
                     final int hostTcpPort) throws IOException {
    final int timeOutMs = 5000;

    client.start();
    client.connect(timeOutMs, hostUniqueIp, hostTcpPort);
  }

  void send(final String message) {
    client.sendTCP(message);
  }

  @Override
  public void close() {
    client.close();
  }
}
