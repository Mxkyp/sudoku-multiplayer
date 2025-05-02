/**
 * Provides networking classes for hosting and joining a simple multiplayer game with asynchronous message handling.
 * <p>
 * This package contains:
 * <ul>
 *   <li>{@link network.AsyncMessageReceiver} — a base class offering a thread-safe queue for storing and retrieving messages asynchronously.</li>
 *   <li>{@link network.GameHost} — a server-side class that listens for client connections, receives messages,
 *   and broadcasts messages to connected players.</li>
 *   <li>{@link network.GamePlayer} — a client-side class that connects to a game host, sends messages, and
 *   retrieves received messages asynchronously.</li>
 * </ul>
 *
 * All classes use asynchronous message queuing to ensure non-blocking communication.
 */
package network;
