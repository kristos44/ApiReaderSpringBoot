package com.example.ApiClient.integration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Component;

@Component
public class FuelClient {
    private Client client;

    public FuelClient() {
        client = Client.create();
    }

    public String fetchStationData(String url) throws RuntimeException {
        WebResource webResource = client.resource(url);

        ClientResponse clientResponse = webResource.accept("application/json").get(ClientResponse.class);

        if(clientResponse.getStatus() != 200) {
            throw new RuntimeException("HTTP error " + clientResponse.getStatus());
        }

        return clientResponse.getEntity(String.class);
    }
}
