package network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

/**
 * Represents a game player that can connect to a game host,
 * send messages, and receive messages asynchronously.
 * <p>
 * Implements {@link AutoCloseable}
 * to ensure the underlying client connection can be properly closed.
 */
public final class GamePlayer
        extends AsyncMessageReceiver implements AutoCloseable {
  final Client client = new Client();

  /**
   * Connects this player to a remote game host.
   *
   * @param hostUniqueIp the IP address of the host
   * @param hostTcpPort  the TCP port of the host
   * @throws IOException if the connection attempt fails
   */
  void connectToHost(final String hostUniqueIp,
                     final int hostTcpPort) throws IOException {
    final int timeOutMs = 5000;

    client.start();
    client.connect(timeOutMs, hostUniqueIp, hostTcpPort);
    addClientListener();
  }

  /**
   * Sends a message to the connected host.
   */
  void send(final String message) {
    client.sendTCP(message);
  }

  @Override
  public void close() {
    client.close();
  }

  /**
   * Adds a listener to handle incoming messages and store them in the message queue.
   */
  private void addClientListener() {
    client.addListener(new Listener() {
      public void received(final Connection connection, final Object object) {
        if (object instanceof String) {
          storeMessage((String) object);
        }
      }
    });
  }
}
