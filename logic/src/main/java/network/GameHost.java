package network;

import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public final class GameHost implements AutoCloseable {
  Server server;

  public GameHost() throws IOException {
    final int tcpPort = 54777;
    server = new Server();
    server.start();
    server.bind(tcpPort);
  }

  @Override
  public void close() throws Exception {
    server.close();
  }

}
