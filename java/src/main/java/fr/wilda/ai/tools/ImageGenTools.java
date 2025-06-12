package fr.wilda.ai.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageGenTools {
    private static final Logger _LOG = LoggerFactory.getLogger(ImageGenTools.class);

    // java-20
    @Tool("""
                Tool to create an image with Stable Diffusion XL given a prompt and a negative prompt.
                """)
    void generateImage(@P("Prompt that explains the image") String prompt, @P("Negative prompt that explains what the image must not contains") String negativePrompt) throws IOException, InterruptedException {
        _LOG.info("Prompt: {}", prompt);
        _LOG.info("Negative prompt: {}", negativePrompt);

        // java-21
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("OVH_AI_ENDPOINTS_SD_URL")))
                .POST(HttpRequest.BodyPublishers.ofString("""
                            {"prompt": "%s", 
                             "negative_prompt": "%s"}
                            """.formatted(prompt, negativePrompt)))
                .header("accept", "application/octet-stream")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + System.getenv("OVH_AI_ENDPOINTS_SDXL_ACCESS_TOKEN"))
                .build();

        // java-22
        HttpResponse<byte[]> response = HttpClient.newHttpClient()
                .send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());

        _LOG.debug("SDXL status code: {}", response.statusCode());
        Files.write(Path.of("generated-image.jpeg"), response.body());
    }
}
