package com.automwrite.assessment.service;

import com.automwrite.assessment.model.client.ClientData;
import com.automwrite.assessment.model.organization.OrganizationData;
import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class JsonParserService {
    private final Gson gson = new Gson();
    private final ClassPathResource clientJsonResource = new ClassPathResource("clientinfo/client.json");
    private final ClassPathResource orgJsonResource = new ClassPathResource("orginfo/org.json");

    public ClientData loadClientData() throws IOException {
        String json = new String(clientJsonResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return gson.fromJson(json, ClientData.class);
    }

    public OrganizationData loadOrganizationData() throws IOException {
        String json = new String(orgJsonResource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        return gson.fromJson(json, OrganizationData.class);
    }
}

