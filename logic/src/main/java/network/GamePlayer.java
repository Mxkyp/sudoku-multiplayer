package network;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public final class GamePlayer implements AutoCloseable {
  final Client client = new Client();
  void connectToHost(final String hostUniqueIp, final int hostTcpPort) throws IOException {
    final int timeOutMs = 5000;

    client.start();
    client.connect(timeOutMs, hostUniqueIp, hostTcpPort);
  }

  @Override
  public void close() throws Exception {
    client.close();
  }
}
