package network;

import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public final class GameHost implements AutoCloseable {
  final Server server;

  public GameHost(final int tcpPort) throws IOException {
    server = new Server();
    server.start();
    server.bind(tcpPort);
  }

  @Override
  public void close() throws Exception {
    server.close();
  }

}
