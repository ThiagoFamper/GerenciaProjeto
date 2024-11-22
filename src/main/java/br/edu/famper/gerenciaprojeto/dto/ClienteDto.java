package br.edu.famper.gerenciaprojeto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {
    @Schema(description = "Nome do Cliente",
            example = "Ariel",
            title = "nome",
            maxLength = 150)
    private String nome;

    @Schema(description = "CPF ou CNPJ do Cliente",
            example = "123.456.789-10 ou 12.345.678/9010-11",
            title = "cpf_cnpj",
            maxLength = 20)
    private String cpf_cnpj;

    @Schema(description = "Telefone do Cliente",
            example = "(46) 1234-5678",
            title = "fone",
            maxLength = 20)
    private String fone;

    @Schema(description = "E-mail do Cliente",
            example = "ariel@gmail.com",
            title = "email",
            maxLength = 30)
    private String email;

    @Schema(description = "Endereço do Cliente",
            example = "Rua Ariel, 666 - Ampére, PR",
            title = "endereco",
            maxLength = 20)
    private String endereco;
}
