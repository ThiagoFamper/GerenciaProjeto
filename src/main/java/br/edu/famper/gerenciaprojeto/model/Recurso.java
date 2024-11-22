package br.edu.famper.gerenciaprojeto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Recurso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "tarefa_id", referencedColumnName = "id")
    private Tarefa tarefa;
}