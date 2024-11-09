package br.com.alura.literalura.view;

import br.com.alura.literalura.dto.Author;
import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.exception.booksNotFoundException;
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
                    searchBookByTitle();
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

    private void searchBookByTitle() {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        Book book = null;

        try {
            book = bookService.search(title);
            printBooks(Arrays.asList(book));

            System.out.println("Salvar " + book.title() + " no bando de dados [s/n]");
            String res = scanner.nextLine();

            if (res.equals("s")) bookService.save(book);

        } catch (booksNotFoundException e) {
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
        System.out.println("Insira um idioma para realizar a busca: ");
        String language = scanner.nextLine();
        try {
            List<Book> books = bookService.getAllBooksByLanguage(language);
            printBooks(books);
        } catch (booksNotFoundException e) {
            System.out.println("\n" + e.getMessage() + "\n");
        }
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
