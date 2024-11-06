package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.Author;
import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.dto.Search;
import br.com.alura.literalura.model.AuthorModel;
import br.com.alura.literalura.model.BookModel;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private ConsumeService consumeService;
    private ConvertsData convertsData;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

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

    public void save(Book book) {
        BookModel bookToSave = new BookModel(book);

        Optional<AuthorModel> authorFound = authorRepository.findByName(book.authors().get(0).name());

        if (authorFound.isPresent()) bookToSave.setAuthor(authorFound.get());
        else {
            Author author = book.authors().get(0);
            AuthorModel authorSaved = authorRepository.save(new AuthorModel(author));
            bookToSave.setAuthor(authorSaved);
        }
        bookRepository.save(bookToSave);
    }

    public List<Book> getAllBooks() {
        List<BookModel> bookFound = bookRepository.findAll();
        return convertsData.bookModelToBookDTO(bookFound);
    }

    public List<Author> getAllAuthor() {
        List<AuthorModel> authorFound = authorRepository.findAll();
        return convertsData.authorModelToAuthorDTO(authorFound);
    }

    public List<Author> getAllAuthorsAliveIn(Integer year) {
        List<AuthorModel> authorsAlive = authorRepository.getAllAliveIn(year);
        return convertsData.authorModelToAuthorDTO(authorsAlive);
    }

}
