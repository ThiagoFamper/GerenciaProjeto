package br.edu.famper.gerenciaprojeto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDto {

    @Schema(description = "Nome da Tarefa",
            example = "Panfletos",
            title = "nome",
            maxLength = 150)
    private String nome;

    @Schema(description = "Descrição da Tarefa",
            example = "Imprimir 500 panfletos da nobank",
            title = "descricao",
            maxLength = 500)
    private String descricao;
}
