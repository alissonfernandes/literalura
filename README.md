 # Literalura
LiterAlura é um catálogo de livros que permite você realizar consultas e obter informações sobre livros através de uma API específica.

![Badge em Desenvolviment](http://img.shields.io/static/v1?label=STATUS&message=Concluído&color=gree&style=for-the-badge)

![Badge em Desenvolviment](http://img.shields.io/static/v1?label=java&message=JDK%2021&color=red&style=for-the-badge) 
![Badge em Desenvolviment](http://img.shields.io/static/v1?label=framework&message=Spring%203.3.5&color=green&style=for-the-badge)
![Badge em Desenvolviment](http://img.shields.io/static/v1?label=mysql&message=MySQL%208&color=blue&style=for-the-badge)
![Badge em Desenvolviment](http://img.shields.io/static/v1?label=&message=git&color=red&style=for-the-badge)
![Badge em Desenvolviment](http://img.shields.io/static/v1?label=&message=github&color=purple&style=for-the-badge)

## ✨ Funcionalidades
Veja as funcionalidades disponíveis neste projeto:
 - **Buscar livro pelo título:** Busca um livro através de um título informado pelo usuário
 - **Listar livros registrado:** Lista todos os livros salvos no banco de dados
 - **Listar autores registrados:** Lista todos os autores que estão salvo no banco de dados
 - **Listar autores vivos em um determinado ano:** Lista todos os autores vivos em um determinado ano informado pelo usuário
 - **Listar livros em um determinado idioma:** Mostra a quantidade de livros registrados no banco de dados em um determinado idioma e lista todos eles

## 🚀 Tecnologias utilizadas
 - **Java 21:** Linguagem de programação java versão 21
 - **JDK:** Azul Zulu Community version 21.0.4
 - **Spring framework:** Framework utilizado no desenvolvimento do projeto na versão 3.3.5
 - [**Gutendex API:**](https://gutendex.com/) API Web utilizada para obter dados sobre livros
 - **MySQL 8.0:** Sistema de Gerenciamento de Banco de Dados empregado no projeto para armazenar dados
 - **Spring Data JPA:** Framework utilizado para persistir dados no banco de dados
 - **MySQL Driver:** Driver de conexão do banco de dados
 - **[Lombok:](https://projectlombok.org/)** Biblioteca Java para reduzir o código boilerplate
 
## 📁 Estrutura de pacotes do projeto
**Package by Layer, ou pacotes por camadas:** foi o estilo de organização adotado para este projeto. Para cada parte do projeto, ou camada, foi criado um pacote diferente para dividir o código com base em suas responsabilidades funcionais.

```
src/
├── main/
│   └── java/
│       └── br/com/alura/        
│                  └── literalura/
│                      └── dto/
│                      └── exception/
│                      └── model/
│                      └── repository/
│                      └── service/
│                      └── view/
├── test/
│   └── java/
│       └── br/com/alura/
│                  └── literalura/
│           
```
 ![badge literalura](https://github.com/user-attachments/assets/0fd7ebaf-f28b-4e36-838b-eb06a84487ce)
