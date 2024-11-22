package br.edu.famper.gerenciaprojeto.repository;

import br.edu.famper.gerenciaprojeto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
