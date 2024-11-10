package br.com.alura.literalura.view;

import br.com.alura.literalura.dto.Author;
import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.exception.BookNotFoundException;
import br.com.alura.literalura.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private BookService bookService;
    Scanner scanner = new Scanner(System.in);

    public Menu(BookService bookService) {
        this.bookService = bookService;
    }

    public void show() {
        String menu = """
                Escolha o número de sua opção: 
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - listar livros em um determinado idioma
                0 - Sair
                """;
        while (true) {
            System.out.println(menu);

            int op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    getBook();
                    break;
                case 2:
                    getAllBooks();
                    break;
                case 3:
                    getAllAuthors();
                    break;
                case 4:
                    getAllAuthorsAlive();
                    break;
                case 5:
                    getAllBooksLanguage();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    private void getBook() {
        System.out.print("Digite o título do livro: ");
        String titleBook = scanner.nextLine();

        try {
            printBook(bookService.get(titleBook));
        } catch (BookNotFoundException e) {
           searchBookByTitle(titleBook);
        }
    }

    private void searchBookByTitle(String titleBook) {
        Book book = null;

        try {
            book = bookService.search(titleBook);
            printBooks(Arrays.asList(book));

            System.out.println("O livro: \"" + book.title() + "\" não está salvo no bando de dados. Deseja salva-lo [s/n]");
            String res = scanner.nextLine();

            if (res.equals("s")) bookService.save(book);

        } catch (BookNotFoundException e) {
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }

    private void getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        printBooks(books);
    }

    private void getAllAuthors() {
        List<Author> authors = bookService.getAllAuthor();
        showAuthor(authors);
    }

    private void getAllAuthorsAlive() {
        System.out.println("Insira o ano que deseja pesquisar: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        List<Author> authorsAlive = bookService.getAllAuthorsAliveIn(year);
        showAuthor(authorsAlive);
    }

    private void getAllBooksLanguage() {
        System.out.println("Insira um idioma para realizar a busca(pt, en, fr): ");
        String language = scanner.nextLine();
        try {
            List<Book> books = bookService.getAllBooksByLanguage(language);
            System.out.println("==========\n\nTotal de livros encontrados: " + books.stream().count() + "\n");
            printBooks(books);
        } catch (BookNotFoundException e) {
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }

    private void printBook(Book b) {
        String show = """
                    ----- LIVRO -----
                    Título: %s
                    Autor: %s
                    Indioma: %s
                    Número de downloads: %d
                    ------------------
                    """;
        String language = "";
        for (String languageBook : b.languages()) language += languageBook;

        System.out.println(String.format(show, b.title(), b.authors().getFirst().name(), language, b.downloadCount()));
    }

    private void printBooks(List<Book> books) {
        books.forEach(b -> {
            String show = """
                    ----- LIVRO -----
                    Título: %s
                    Autor: %s
                    Indioma: %s
                    Número de downloads: %d
                    ------------------
                    """;

            String language = "";
            for (String languageBook : b.languages()) language += languageBook;

            System.out.println(String.format(show, b.title(), b.authors().getFirst().name(), language, b.downloadCount()));
        });
    }

    private void showAuthor(List<Author> authors) {
        authors.forEach(a -> {
            String show = """
                    Autor: %s
                    Ano de nascimento: %d
                    Ano de falecimento: %d
                    """;
            System.out.println(String.format(show, a.name(), a.birthYear(), a.deathYear()));
        });
    }

}
