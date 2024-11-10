package br.com.alura.literalura.service;

import br.com.alura.literalura.dto.Author;
import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.exception.BookNotFoundException;
import br.com.alura.literalura.model.AuthorModel;
import br.com.alura.literalura.model.BookModel;
import br.com.alura.literalura.repository.AuthorRepository;
import br.com.alura.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private GutendexAPIService gutendex;
    private ConvertsData convertsData;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public BookService() {
        gutendex = new GutendexAPIService();
        convertsData = new ConvertsData();
    }

    public Book search(String bookName) throws BookNotFoundException {
        return gutendex.searchBook(bookName);
    }

    public Book get(String bookName) throws BookNotFoundException {
        Optional<BookModel> bookFound = bookRepository.findByTitleContainingIgnoreCase(bookName);
        if (bookFound.isPresent()) return convertsData.bookModelToBookDTO(bookFound.get());
        else throw new BookNotFoundException("Livro não encontrado");
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

    public List<Book> getAllBooksByLanguage(String language) throws BookNotFoundException {
        List<BookModel> booksFound = bookRepository.findByLanguage(language);
        if (booksFound.isEmpty()) throw new BookNotFoundException("Não existem livros nesse idioma no banco de dados.");
        return convertsData.bookModelToBookDTO(booksFound);
    }

}
