package br.edu.famper.gerenciaprojeto.repository;

import br.edu.famper.gerenciaprojeto.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
}
