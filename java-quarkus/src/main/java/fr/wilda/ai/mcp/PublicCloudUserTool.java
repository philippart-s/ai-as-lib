package fr.wilda.ai.mcp;

import fr.wilda.ai.service.OVHcloudMe;
import org.eclipse.microprofile.rest.client.inject.RestClient;

public class PublicCloudUserTool {

    @RestClient
    OVHcloudMe ovhcloudMe;

    // quarkus-10
}
