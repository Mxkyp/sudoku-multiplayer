package network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a game player that can connect to a game host, send messages, and receive messages asynchronously.
 * <p>
 * Implements {@link AutoCloseable} to ensure the underlying client connection can be properly closed.
 */
public final class GamePlayer implements AutoCloseable {
  final Client client = new Client();
  private final Queue<String> msgQue = new ConcurrentLinkedQueue<>();

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
   * Retrieves and removes the next received message,
   * or returns {@code null} if no messages are available.
   * @return the next received message, or {@code null} if the queue is empty
   */
  public String getMsg() {
    return msgQue.poll();
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
          msgQue.add((String) object);
        }
      }
    });
  }
}
