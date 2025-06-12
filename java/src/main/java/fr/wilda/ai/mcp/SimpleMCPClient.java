package fr.wilda.ai.mcp;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.mistralai.MistralAiChatModel;
import dev.langchain4j.service.AiServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SimpleMCPClient {
    private static final Logger _LOG = LoggerFactory.getLogger(SimpleMCPClient.class);

    // java-29
    // Simple chatbot definition with AI Services from LangChain4J
    public interface Bot {
        String chat(String prompt);
    }

    public static void main(String[] args) {
        // java-30
        // Mistral model from OVHcloud AI Endpoints
        ChatModel chatModel = MistralAiChatModel.builder()
                .apiKey(System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN"))
                .baseUrl(System.getenv("OVH_AI_ENDPOINTS_MODEL_URL"))
                .modelName(System.getenv("OVH_AI_ENDPOINTS_MODEL_NAME"))
                .logRequests(false)
                .logResponses(false)
                .build();

        // java-31
        // Configure the MCP server to use
        McpTransport transport = new HttpMcpTransport.Builder()
                // https://xxxx/mcp/sse
                .sseUrl(System.getenv("MCP_SERVER_URL"))
                .logRequests(false)
                .logResponses(false)
                .build();

        // java-32
        // Create the MCP client for the given MCP server
        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();

        // java-33
        // Configure the tools list for the LLM
        McpToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(mcpClient)
                .build();

        // java-34
        // Create the chatbot with the given LLM and tools list
        Bot bot = AiServices.builder(Bot.class)
                .chatModel(chatModel)
                .toolProvider(toolProvider)
                .build();

        // java-35
        // Play with the chatbot 🤖
        String response = bot.chat("Can I have some details about my OVHcloud account?");
        _LOG.info("RESPONSE: {}",  response);

    }
}