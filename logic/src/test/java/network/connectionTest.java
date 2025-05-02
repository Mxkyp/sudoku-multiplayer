package network;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class connectionTest {

  @Test
  void testSendToServer() throws UnknownHostException {
    final int hostTcpPort = 54777;
    InetAddress localhost = InetAddress.getLocalHost();

    try (GameHost gameHost = new GameHost(hostTcpPort)) {

      try(GamePlayer gamePlayer = new GamePlayer()) {
        final String hostAddr = localhost.getHostAddress();
        gamePlayer.connectToHost(hostAddr, hostTcpPort);

        final String msgToSend = "message";
        gamePlayer.send(msgToSend);

        String received = gameHost.getMsg();

        //TODO: stop this from busy waiting
        while(received == null) {
          received = gameHost.getMsg();
        }
        assertEquals(received, msgToSend);
      }

    } catch (IOException e) {
      fail("Server creation or client connection failed");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Test
  void testSendToClient() throws UnknownHostException {
    final int hostTcpPort = 54777;
    InetAddress localhost = InetAddress.getLocalHost();

    try (GameHost gameHost = new GameHost(hostTcpPort)) {

      try(GamePlayer gamePlayer = new GamePlayer()) {
        final String hostAddr = localhost.getHostAddress();
        gamePlayer.connectToHost(hostAddr, hostTcpPort);

        final String msgToSend = "message2";
        gameHost.send(msgToSend);

        String received = gamePlayer.getMsg();

        //TODO: stop this from busy waiting
        while(received == null) {
          received = gamePlayer.getMsg();
        }
        assertEquals(received, msgToSend);
      }

    } catch (IOException e) {
      fail("Server creation or client connection failed");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
