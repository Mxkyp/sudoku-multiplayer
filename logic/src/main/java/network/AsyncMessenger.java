package network;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Provides asynchronous message handling via a thread-safe queue.
 * <p>
 * Designed to be extended by classes
 * that receive messages from external sources.
 */
public class AsyncMessenger {
  private final Queue<String> msgQue = new ConcurrentLinkedQueue<>();

  /**
   * Retrieves and removes the next received message,
   * or returns {@code null} if no messages are available.
   *
   * @return the next received message, or {@code null} if the queue is empty
   */
  public String getMsg() {
    return msgQue.poll();
  }


  /**
   * Stores a new message in the internal message queue.
   *
   * @param mess the message to store
   */
  public void storeMessage(String mess) {
    msgQue.add(mess);
  }

}
