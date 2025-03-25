package fr.wilda.ai.chatbot;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.mistralai.MistralAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryStreamingChatbot {

    private static final Logger _LOG = LoggerFactory.getLogger(MemoryStreamingChatbot.class);

    // java-02-mem-interface
    interface Assistant {
        @SystemMessage("You are Nestor, a virtual assistant. Answer to the question.")
        TokenStream chat(String message);
    }

    public static void main(String[] args) {
        // java-03-mem-model
        MistralAiStreamingChatModel streamingChatModel = MistralAiStreamingChatModel.builder()
                .apiKey(System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN"))
                .modelName(System.getenv("OVH_AI_ENDPOINTS_MODEL_NAME"))
                .baseUrl(
                        System.getenv("OVH_AI_ENDPOINTS_MODEL_URL"))
                .maxTokens(512)
                .build();

        // java-04-mem-memory
        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        // java-05-mem-assistant
        Assistant assistant = AiServices.builder(Assistant.class)
                .streamingChatLanguageModel(streamingChatModel)
                .chatMemory(chatMemory)
                .build();

        // java-06-mem-prompt
        _LOG.info("💬: My name is Stéphane.\n");
        TokenStream tokenStream = assistant.chat("My name is Stéphane.");
        _LOG.info("🤖: ");
        tokenStream
                .onNext(_LOG::info)
                .onComplete(token -> {
                    _LOG.info("\n💬: Do you remember what is my name?\n");
                    _LOG.info("🤖: ");
                    assistant.chat("Do you remember what is my name?")
                            .onNext(_LOG::info)
                            .onError(Throwable::printStackTrace).start();
                })
                .onError(Throwable::printStackTrace).start();
    }
}