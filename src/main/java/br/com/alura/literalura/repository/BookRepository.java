package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    @Query("SELECT b FROM BookModel b JOIN b.languages l WHERE l.language ILIKE :language")
    List<BookModel> findByLanguage(String language);
}
