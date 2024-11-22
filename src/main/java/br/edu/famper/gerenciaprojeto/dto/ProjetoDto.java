package br.edu.famper.gerenciaprojeto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjetoDto {
    @Schema(description = "Nome do Projeto",
            example = "Nobank",
            title = "nome",
            maxLength = 150)
    private String nome;

    @Schema(description = "Descrição do Projeto",
            example = "App de banco para concorrer com a Nubank",
            title = "descricao",
            maxLength = 500)
    private String descricao;
}
