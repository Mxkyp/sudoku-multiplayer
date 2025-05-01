package network;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameHostTest {

  @Test
  void testCreateServer() throws IOException {
    GameHost gameHost = new GameHost();
    /*
    try (GameHost gameHost = new GameHost()) {

    } catch (IOException e) {
      fail("server creation failed");
    } catch (Exception e) {
      fail("close failed");
    }
     */
  }

}