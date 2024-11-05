package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
}
