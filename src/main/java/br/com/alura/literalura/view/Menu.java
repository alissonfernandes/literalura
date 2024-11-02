package br.com.alura.literalura.view;

import br.com.alura.literalura.BookService;

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
                case 0:
                    System.exit(0);
            }
        }
    }

    private void searchBookByTitle() {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        String response = bookService.search(title);
        System.out.println(response);
    }
}
