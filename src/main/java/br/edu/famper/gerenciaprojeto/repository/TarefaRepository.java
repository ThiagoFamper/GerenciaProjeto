package br.edu.famper.gerenciaprojeto.repository;

import br.edu.famper.gerenciaprojeto.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
