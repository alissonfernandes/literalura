package br.com.alura.literalura.view;

import br.com.alura.literalura.dto.Book;
import br.com.alura.literalura.service.BookService;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                case 0:
                    System.exit(0);
            }
        }
    }

    private void searchBookByTitle() {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        Book book = bookService.search(title);

        String mensage = """
                Título: %s
                Autores: %s
                Linguagem: %s
                Downloads: %d
                """;
        List<String> authors = book.authors().stream().map(a -> a.name()).collect(Collectors.toList());

        String author = "";
        for (String authorBook : authors) author += authorBook;

        String language = "";
        for (String languageBook : book.languages()) language += languageBook;

        System.out.println(String.format(mensage,
                book.title(),
                author,
                language,
                book.downloadCount()));

        System.out.println("Salvar " + book.title() + " no bando de dados [s/n]");
        String res = scanner.nextLine();
        if (res.equals("s")) bookService.save(book);
    }

    private void getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        System.out.println(books.toString());
    }
}
