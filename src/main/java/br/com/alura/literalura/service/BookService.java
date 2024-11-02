package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.dto.Search;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class BookService {

    private ObjectMapper mapper = new ObjectMapper();
    private ConsumeService consumeService;

    public BookService() {
        consumeService = new ConsumeService();
    }

    public Book search(String bookName) {
        URI uri = URI.create("http://gutendex.com/books/?search=" + bookName.replace(" ", "%20"));
        String jsonReponse = consumeService.get(uri);
        Search search = toObject(jsonReponse);
        return search.results().get(0);
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
