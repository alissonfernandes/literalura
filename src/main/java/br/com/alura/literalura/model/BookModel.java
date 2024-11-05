package br.com.alura.literalura.model;

import br.com.alura.literalura.dto.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne(cascade = CascadeType.MERGE)
    private AuthorModel author;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LanguageModel> languages;

    public BookModel(Book bookDTO) {
        title = bookDTO.title();
        author = new AuthorModel(bookDTO.authors().get(0));
        languages = bookDTO.languages().stream().map(l -> new LanguageModel(l)).collect(Collectors.toList());
    }
}
