package network;

import board.SudokuBoard;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public final class GameHost implements AutoCloseable {
  final Server server;

  public GameHost(final int tcpPort) throws IOException {
    server = new Server();
    server.start();
    server.bind(tcpPort);
  }

  public void receiveUpdate() {
    server.addListener(new Listener() {
      public void received (Connection connection, Object object) {
        if (object instanceof String) {
          String change = (String) object;
          System.out.println(change);
        }
      }
    });

  }

  @Override
  public void close() {
    server.close();
  }

}
