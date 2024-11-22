package br.edu.famper.gerenciaprojeto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecursoDto {

    @Schema(description = "Descrição do Recurso",
            example = "500 Folhas A4 e 2 Impressoras",
            title = "descricao",
            maxLength = 500)
    private String descricao;
}
