package br.com.alura.literalura.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ConsumeService {

    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;

    public ConsumeService() {
        client = HttpClient.newHttpClient();
    }

    public String get(URI uri) {
        request = HttpRequest.newBuilder().uri(uri).build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error " + ConsumeService.class.getName() + ": " + e.getMessage());
        }
        return response.body();
    }
}
