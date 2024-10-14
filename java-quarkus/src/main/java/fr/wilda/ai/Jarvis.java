package fr.wilda.ai;

import java.time.Duration;

import fr.wilda.ai.service.AIEndpointService;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

@WebSocket(path = "/jarvis")
public class Jarvis {

  @Inject
  AIEndpointService aiEndpointService;

  @OnTextMessage
  public Multi<String> onMessage(String message) {
    return aiEndpointService.askAQuestion(message)
        .onItem()
        .call(i -> Uni.createFrom()
            .nullItem()
            .onItem()
            .delayIt()
            .by(Duration.ofMillis(200)));
  }
}