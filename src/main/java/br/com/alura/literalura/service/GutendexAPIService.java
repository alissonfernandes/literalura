package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.dto.Search;
import br.com.alura.literalura.exception.booksNotFoundException;

import java.net.URI;

public class GutendexAPIService extends ConsumeService {

    private final String URI_BASE;
    private ConvertsData convertsData;

    public GutendexAPIService() {
        this.URI_BASE = "http://gutendex.com/books/";
        convertsData = new ConvertsData();
    }

    public Book searchBook(String bookName) throws booksNotFoundException {
        URI uri = URI.create(URI_BASE + "?search=" + bookName.replace(" ", "%20"));
        String jsonResponse = getResponse(uri);
        Search search = convertsData.getDataObject(jsonResponse, Search.class);
        if (search.results().isEmpty()) throw new booksNotFoundException("Livro não encontrado com o nome de '"+ bookName + "'");
        else return search.results().get(0);
    }

}
