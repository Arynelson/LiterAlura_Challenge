package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Importar Query
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.anoDeNascimento <= :ano AND (a.anoDeFalecimento IS NULL OR a.anoDeFalecimento >= :ano)")
    List<Autor> findAutoresVivosEmAno(Integer ano);

    Optional<Autor> findByNome(String nome);
}