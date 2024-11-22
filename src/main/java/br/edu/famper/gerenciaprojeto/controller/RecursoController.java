package br.edu.famper.gerenciaprojeto.controller;

import br.edu.famper.gerenciaprojeto.dto.RecursoDto;
import br.edu.famper.gerenciaprojeto.exception.ResourceNotFoundException;
import br.edu.famper.gerenciaprojeto.model.Recurso;
import br.edu.famper.gerenciaprojeto.service.RecursoService;
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
@RequestMapping("/api/recurso")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Recurso",
        description = "Operation for resource")
public class RecursoController {

    private final RecursoService recursoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all resources from DB",
            description = "Fetches all resources from DB and return " +
                    "in JSON Array"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not fund")
    })
    public List<RecursoDto> getAllRecursos() {
        log.info("Buscando todos os recursos");
        return recursoService.getAllRecursos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one resource from DB",
            description = "Fetches one resource from DB and return " +
                    "in JSON Object"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not fund")
    })
    public ResponseEntity<RecursoDto> getRecursoById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando recurso por id: {}", id);
        return ResponseEntity.ok().body(recursoService.getRecursoById(id));
    }

    @PostMapping
    @Operation(summary = "Save resource",
            description = "Save a resource in database"
    )
    public Recurso createRecurso(@RequestBody RecursoDto recursoDto) throws ResourceNotFoundException {
        log.info("Cadastro recurso: {}", recursoDto.toString());
        return recursoService.saveRecurso(recursoDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update resource",
            description = "Update a resource in database"
    )
    public ResponseEntity<RecursoDto> updateRecurso(@PathVariable(name = "id") Long id, @RequestBody RecursoDto recursoDto) throws ResourceNotFoundException {
        log.info("Atualizando recurso: {}", recursoDto);
        return ResponseEntity.ok(recursoService.editRecurso(id, recursoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove resource",
            description = "Remove a resource in database"
    )
    public Map<String, Boolean> deleteRecurso(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando recurso: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", recursoService.deleteRecurso(id));
        return response;
    }
}
