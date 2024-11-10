package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    Optional<BookModel> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT b FROM BookModel b JOIN b.languages l WHERE l.language ILIKE :language")
    List<BookModel> findByLanguage(String language);
}
