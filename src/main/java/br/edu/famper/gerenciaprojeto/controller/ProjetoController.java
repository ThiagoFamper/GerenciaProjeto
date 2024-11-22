package br.edu.famper.gerenciaprojeto.controller;

import br.edu.famper.gerenciaprojeto.dto.ProjetoDto;
import br.edu.famper.gerenciaprojeto.exception.ResourceNotFoundException;
import br.edu.famper.gerenciaprojeto.model.Projeto;
import br.edu.famper.gerenciaprojeto.service.ProjetoService;
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
@RequestMapping("/api/projeto")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Projeto",
        description = "Operation for project")
public class ProjetoController {

    private final ProjetoService projetoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all projects from DB",
            description = "Fetches all projects from DB and return " +
                    "in JSON Array"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not fund")
    })
    public List<ProjetoDto> getAllProjetos() {
        log.info("Buscando todos os projetos");
        return projetoService.getAllProjetos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one project from DB",
            description = "Fetches one project from DB and return " +
                    "in JSON Object"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not fund")
    })
    public ResponseEntity<ProjetoDto> getProjetoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando projeto por id: {}", id);
        return ResponseEntity.ok().body(projetoService.getProjetoById(id));
    }

    @PostMapping
    @Operation(summary = "Save project",
            description = "Save a project in database"
    )
    public Projeto createProjeto(@RequestBody ProjetoDto projetoDto) throws ResourceNotFoundException {
        log.info("Cadastro projeto: {}", projetoDto.toString());
        return projetoService.saveProjeto(projetoDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update project",
            description = "Update a project in database"
    )
    public ResponseEntity<ProjetoDto> updateProjeto(@PathVariable(name = "id") Long id, @RequestBody ProjetoDto projetoDto) throws ResourceNotFoundException {
        log.info("Atualizando projeto: {}", projetoDto);
        return ResponseEntity.ok(projetoService.editProjeto(id, projetoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove project",
            description = "Remove a project in database"
    )
    public Map<String, Boolean> deleteProjeto(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando projeto: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", projetoService.deleteProjeto(id));
        return response;
    }
}
