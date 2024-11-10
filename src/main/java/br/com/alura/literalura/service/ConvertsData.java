package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.Author;
import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.model.AuthorModel;
import br.com.alura.literalura.model.BookModel;
import br.com.alura.literalura.model.LanguageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertsData implements IConvertsData {

    private final ObjectMapper mapper;

    public ConvertsData() {
        mapper = new ObjectMapper();
    }

    @Override
    public <T> T getDataObject(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            System.out.println("Error - " + ConvertsData.class.getName() + ": " + e.getMessage());
            return null;
        }
    }
    // Converte lista do tipo BookModel para BookDTO
    public List<Book> bookModelToBookDTO(List<BookModel> bookModels) {
        return bookModels.stream().map(b -> {
            Author author = new Author(b.getAuthor().getName(), b.getAuthor().getBirthYear(), b.getAuthor().getDeathYear());
            List<String> languages = b.getLanguages().stream().map(l -> l.getLanguage()).collect(Collectors.toList());
            return new Book(b.getTitle(), Arrays.asList(author), languages, b.getDownloadCount());
        }).collect(Collectors.toList());
    }

    // Converte bookmodel para bookDTO
    public Book bookModelToBookDTO(BookModel bookModel) {
        return new Book(bookModel.getTitle(),
                 Arrays.asList(authorModelToAuthorDTO(bookModel.getAuthor())),
                 languageModelToLanguageDTO(bookModel.getLanguages()),
                 bookModel.getDownloadCount());
    }

    // Converte authorModel para authorDTO
    public Author authorModelToAuthorDTO(AuthorModel authorModel) {
        return new Author(authorModel.getName(), authorModel.getBirthYear(), authorModel.getDeathYear());
    }

    public List<Author> authorModelToAuthorDTO(List<AuthorModel> authorModels) {
        return authorModels.stream().map(a -> new Author(a.getName(), a.getBirthYear(), a.getDeathYear()))
                .collect(Collectors.toList());
    }

    // Converte lista do tipo languageModel para uma lista de String
    public List<String> languageModelToLanguageDTO(List<LanguageModel> languageModel) {
        return languageModel.stream().map(l -> l.getLanguage()).collect(Collectors.toList());
    }
}
