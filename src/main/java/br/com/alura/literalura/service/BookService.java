package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.dto.Search;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class BookService {

    private ObjectMapper mapper = new ObjectMapper();
    private ConsumeService consumeService;
    private ConvertsData convertsData;

    public BookService() {
        consumeService = new ConsumeService();
        convertsData = new ConvertsData();
    }

    public Book search(String bookName) {
        URI uri = URI.create("http://gutendex.com/books/?search=" + bookName.replace(" ", "%20"));
        String jsonResponse = consumeService.get(uri);
        Search search = convertsData.getDataObject(jsonResponse, Search.class);
        return search.results().get(0);
    }

}
