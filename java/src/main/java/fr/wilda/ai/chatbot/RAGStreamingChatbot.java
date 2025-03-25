package fr.wilda.ai.chatbot;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.mistralai.MistralAiStreamingChatModel;
import dev.langchain4j.model.ovhai.OvhAiEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

public class RAGStreamingChatbot {
    private static final Logger _LOG = LoggerFactory.getLogger(RAGStreamingChatbot.class);
    private static final String OVHCLOUD_API_KEY = System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN");

    // java-02-mem-interface
    interface Assistant {
        @SystemMessage("You are Nestor, a virtual assistant. Answer to the question.")
        TokenStream chat(String userMessage);
    }

    public static void main(String[] args) {

        // java-08-rag-chunk
        // Load the document and split it into chunks
        DocumentParser documentParser = new TextDocumentParser();
        Document document = loadDocument(
                RAGStreamingChatbot.class.getResource("/rag-files/content.txt").getFile(),
                documentParser
        );
        DocumentSplitter splitter = DocumentSplitters.recursive(400, 0);

        List<TextSegment> segments = splitter.split(document);

        // java-09-rag-embed
        // Do the embeddings and store them in an embedding store
        EmbeddingModel embeddingModel = OvhAiEmbeddingModel.builder()
                .apiKey(OVHCLOUD_API_KEY)
                .baseUrl(System.getenv("OVH_AI_ENDPOINTS_EMBEDDING_MODEL_URL"))
                .build();

        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        // java-10-rag-store
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        embeddingStore.addAll(embeddings, segments);
        ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(5)
                .minScore(0.9)
                .build();

        // java-11-rag-model
        MistralAiStreamingChatModel streamingChatModel = MistralAiStreamingChatModel.builder()
                .apiKey(OVHCLOUD_API_KEY)
                .modelName(System.getenv("OVH_AI_ENDPOINTS_MODEL_NAME"))
                .baseUrl(
                        System.getenv("OVH_AI_ENDPOINTS_MODEL_URL")
                )
                .maxTokens(512)
                .build();

        // java-12-rag-assistant
        Assistant assistant = AiServices
                .builder(Assistant.class)
                .streamingChatLanguageModel(streamingChatModel)
                .contentRetriever(contentRetriever)
                .build();

        // java-13-rag-prompt
        _LOG.info("\n💬: What is AI Endpoints?\n");

        TokenStream tokenStream = assistant.chat("Can you explain me what is AI Endpoints?");
        _LOG.info("🤖: ");
        tokenStream
                .onNext(_LOG::info)
                .onError(Throwable::printStackTrace)
                .start();
    }
}
