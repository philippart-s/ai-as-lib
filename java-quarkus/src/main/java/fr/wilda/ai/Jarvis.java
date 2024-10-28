package fr.wilda.ai;

import io.quarkus.websockets.next.OnTextMessage;
import io.smallrye.mutiny.Multi;

// quarkus-04-websocket
public class Jarvis {

  // quarkus-05-ws-inject-service

  @OnTextMessage
  public Multi<String> onMessage(String message) {
    // quarkus-06-ws-onMessage
    return null;
  }
}