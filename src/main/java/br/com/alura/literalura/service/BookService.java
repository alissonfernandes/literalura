package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.dto.Search;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class BookService {

    private ObjectMapper mapper = new ObjectMapper();

    public Book search(String bookName) {
        String uri = "http://gutendex.com/books/?search=" + bookName.replace(" ", "%20");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> res = null;

        try {
            res = client.send(req, HttpResponse.BodyHandlers.ofString());
            Search search = toObject(res.body());
            return search.results().get(0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T toObject(String json) {
        try {
            Search search = mapper.readValue(json, Search.class);
            return (T) search;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
