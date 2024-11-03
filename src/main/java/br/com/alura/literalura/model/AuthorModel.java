package br.com.alura.literalura.model;

import br.com.alura.literalura.dto.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @ManyToOne
    private BookModel book;

    public AuthorModel(Author authorDTO) {
        name = authorDTO.name();
        birthYear = authorDTO.birthYear();
        deathYear = authorDTO.birthYear();
    }
}
