package fr.wilda.ai.mcp;

import fr.wilda.ai.service.OVHcloudMe;
import fr.wilda.ai.util.OVHcloudSignatureHelper;
import io.quarkiverse.mcp.server.TextContent;
import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

public class PublicCloudUserTool {

    @RestClient
    OVHcloudMe ovhcloudMe;

    // quarkus-10
    @Tool(description = "Tool to manage the OVHcloud public cloud user.")
    ToolResponse getUserDetails() {
        Long ovhTimestamp = System.currentTimeMillis() / 1000;
        return ToolResponse.success(
                new TextContent(ovhcloudMe.getMe(OVHcloudSignatureHelper.signature("me", ovhTimestamp),
                        Long.toString(ovhTimestamp)).toString()));
    }
}
