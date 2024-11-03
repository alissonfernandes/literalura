package br.com.alura.literalura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "language")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;

    public LanguageModel(String language) {
        this.language = language;
    }
}
