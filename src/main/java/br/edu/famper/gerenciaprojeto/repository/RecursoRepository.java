package br.edu.famper.gerenciaprojeto.repository;

import br.edu.famper.gerenciaprojeto.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
}
