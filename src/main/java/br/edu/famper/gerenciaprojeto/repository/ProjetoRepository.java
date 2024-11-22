package br.edu.famper.gerenciaprojeto.repository;

import br.edu.famper.gerenciaprojeto.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
