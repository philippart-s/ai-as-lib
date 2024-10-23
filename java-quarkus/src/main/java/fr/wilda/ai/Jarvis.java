package fr.wilda.ai;

import java.time.Duration;

import fr.wilda.ai.service.AIEndpointService;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

// quarkus-04-websocket
@WebSocket(path = "/jarvis")
public class Jarvis {

  // quarkus-05-ws-inject-service
  @Inject
  AIEndpointService aiEndpointService;

  @OnTextMessage
  public Multi<String> onMessage(String message) {
    // quarkus-06-ws-onMessage
    return aiEndpointService.askAQuestion(message)
        .onItem()
        .call(i -> Uni.createFrom()
            .nullItem()
            .onItem()
            .delayIt()
            .by(Duration.ofMillis(200)));
  }
}