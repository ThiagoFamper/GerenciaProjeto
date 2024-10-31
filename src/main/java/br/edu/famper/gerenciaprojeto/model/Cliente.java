package br.edu.famper.gerenciaprojeto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "cpf_cnpj", length = 20)
    private String cpf_cnpj;

    @Column(name = "fone", length = 20)
    private String fone;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "endereco", length = 100)
    private String endereco;

    @OneToMany(mappedBy = "cliente", targetEntity = Projeto.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Projeto> projetos;
}