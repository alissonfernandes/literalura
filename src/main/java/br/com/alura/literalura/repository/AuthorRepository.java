package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
    Optional<AuthorModel> findByName(String name);

    @Query("SELECT a FROM AuthorModel a WHERE :year >= a.birthYear AND :year <= a.deathYear")
    List<AuthorModel> getAllAliveIn(Integer year);
}


