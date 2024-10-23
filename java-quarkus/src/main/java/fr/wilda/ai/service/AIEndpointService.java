package fr.wilda.ai.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

// quarkus-02-ai-ai-service
@SessionScoped
@RegisterAiService(chatMemoryProviderSupplier = RegisterAiService.NoChatMemoryProviderSupplier.class)
public interface AIEndpointService {
  // quarkus-03-ai-prompt
  @SystemMessage("You are a virtual assistant and your name is JARVIS.")
  @UserMessage("Answer as best possible to the following question: {question}. The answer must be in a style of a virtual assistant.")
  Multi<String> askAQuestion(String question);
}
