package br.edu.famper.gerenciaprojeto.controller;

import br.edu.famper.gerenciaprojeto.dto.ColaboradorDto;
import br.edu.famper.gerenciaprojeto.exception.ResourceNotFoundException;
import br.edu.famper.gerenciaprojeto.model.Colaborador;
import br.edu.famper.gerenciaprojeto.service.ColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/colaborador")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Colaborador",
        description = "Operation for collaborators")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all collaborators from DB",
            description = "Fetches all collaborators from DB and return " +
                    "in JSON Array"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not fund")
    })
    public List<ColaboradorDto> getAllColaboradores() {
        log.info("Buscando todos os colaboradores");
        return colaboradorService.getAllColaboradores();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one collaborator from DB",
            description = "Fetches one collaborator from DB and return " +
                    "in JSON Object"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not fund")
    })
    public ResponseEntity<ColaboradorDto> getColaboradorById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando colaborador por id: {}", id);
        return ResponseEntity.ok().body(colaboradorService.getColaboradorById(id));
    }

    @PostMapping
    @Operation(summary = "Save collaborator",
            description = "Save a collaborator in database"
    )
    public Colaborador createColaborador(@RequestBody ColaboradorDto colaboradorDto) throws ResourceNotFoundException {
        log.info("Cadastro colaborador: {}", colaboradorDto.toString());
        return colaboradorService.saveColaborador(colaboradorDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update collaborator",
            description = "Update a collaborator in database"
    )
    public ResponseEntity<ColaboradorDto> updateColaborador(@PathVariable(name = "id") Long id, @RequestBody ColaboradorDto colaboradorDto) throws ResourceNotFoundException {
        log.info("Atualizando colaborador: {}", colaboradorDto);
        return ResponseEntity.ok(colaboradorService.editColaborador(id, colaboradorDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove collaborator",
            description = "Remove a collaborator in database"
    )
    public Map<String, Boolean> deleteColaborador(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando colaborador: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", colaboradorService.deleteColaborador(id));
        return response;
    }
}
