package br.com.alura.literalura;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BookService {

    public String search(String bookName) {
        String uri = "http://gutendex.com/books/?search=" + bookName.replace(" ", "%20");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> res = null;

        try {
            res = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return res.body();
    }
}
