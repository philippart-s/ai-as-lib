package com.ovhcloud.examples.aiendpoints.nlp;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wilda.ai.nlp.Emotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SentimentsAnalysis {
    private static final Logger _LOG = LoggerFactory.getLogger(SentimentsAnalysis.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        // Create a HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create a request builder
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://roberta-base-go-emotions.endpoints.kepler.ai.cloud.ovh.net/api/text2emotions"))
                .POST(HttpRequest.BodyPublishers.ofString("I love Java"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + System.getenv("OVH_AI_ENDPOINTS_ACCESS_TOKEN"))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Deserialize the response to a Java object using Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        Emotion responseObject = objectMapper.readValue(response.body(), Emotion.class);

        // Print the response status code and body
        _LOG.debug("Status code: " + response.statusCode());
        _LOG.debug("Response body: " + response.body());
        _LOG.info("Emotion: " + responseObject.toEmoji());
    }
}
