package br.edu.famper.gerenciaprojeto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColaboradorDto {
    @Schema(description = "Nome do Colaborador",
            example = "Ariel",
            title = "nome",
            maxLength = 150)
    private String nome;

    @Schema(description = "CPF ou CNPJ do Colaborador",
            example = "123.456.789-10 ou 12.345.678/9010-11",
            title = "cpf_cnpj",
            maxLength = 20)
    private String cpf_cnpj;

    @Schema(description = "Telefone do Colaborador",
            example = "(46) 1234-5678",
            title = "fone",
            maxLength = 20)
    private String fone;

    @Schema(description = "E-mail do Colaborador",
            example = "ariel@gmail.com",
            title = "email",
            maxLength = 30)
    private String email;

    @Schema(description = "Endereço do Colaborador",
            example = "Rua Ariel, 666 - Ampére, PR",
            title = "endereco",
            maxLength = 20)
    private String endereco;
}
