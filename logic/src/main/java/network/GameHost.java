package network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Represents a game host that accepts client connections,
 * receives messages, and broadcasts messages to connected clients.
 * <p>
 * Implements {@link AutoCloseable} to allow proper shutdown of the server.
 */
public final class GameHost implements AutoCloseable {
  private final Server server;
  private final Queue<String> msgQue = new ConcurrentLinkedQueue<>();

  /**
   * Creates a new game host and binds it to the specified TCP port.
   * @param tcpPort the TCP port on which the server will listen for connections
   * @throws IOException if the server fails to start or bind to the port
   */
  public GameHost(final int tcpPort) throws IOException {
    server = new Server();
    server.start();
    server.bind(tcpPort);
    addServerListener();
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
   * Sends a message to all currently connected clients.
   * @param message the message to broadcast to clients
   */
  void sendToEachClient(final String message) {
    Connection[] connections = server.getConnections();
    for (Connection con : connections) {
      server.sendToTCP(con.getID(), message);
    }
  }

  /**
   * Adds a listener to handle incoming messages
   * and store them in the message queue.
   */
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
