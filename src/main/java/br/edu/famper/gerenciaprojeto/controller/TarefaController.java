package br.edu.famper.gerenciaprojeto.controller;

import br.edu.famper.gerenciaprojeto.dto.TarefaDto;
import br.edu.famper.gerenciaprojeto.exception.ResourceNotFoundException;
import br.edu.famper.gerenciaprojeto.model.Tarefa;
import br.edu.famper.gerenciaprojeto.service.TarefaService;
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
@RequestMapping("/api/tarefa")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Tarefa",
        description = "Operation for task")
public class TarefaController {

    private final TarefaService tarefaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all tasks from DB",
            description = "Fetches all tasks from DB and return " +
                    "in JSON Array"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not fund")
    })
    public List<TarefaDto> getAllTarefas() {
        log.info("Buscando todas as tarefas");
        return tarefaService.getAllTarefas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get one task from DB",
            description = "Fetches one task from DB and return " +
                    "in JSON Object"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful"),
            @ApiResponse(responseCode = "404", description = "not fund")
    })
    public ResponseEntity<TarefaDto> getTarefaById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        log.info("Buscando tarefa por id: {}", id);
        return ResponseEntity.ok().body(tarefaService.getTarefaById(id));
    }

    @PostMapping
    @Operation(summary = "Save task",
            description = "Save a task in database"
    )
    public Tarefa createTarefa(@RequestBody TarefaDto tarefaDto) throws ResourceNotFoundException {
        log.info("Cadastro tarefa: {}", tarefaDto.toString());
        return tarefaService.saveTarefa(tarefaDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update task",
            description = "Update a task in database"
    )
    public ResponseEntity<TarefaDto> updateTarefa(@PathVariable(name = "id") Long id, @RequestBody TarefaDto tarefaDto) throws ResourceNotFoundException {
        log.info("Atualizando tarefa: {}", tarefaDto);
        return ResponseEntity.ok(tarefaService.editTarefa(id, tarefaDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove task",
            description = "Remove a task in database"
    )
    public Map<String, Boolean> deleteTarefa(@PathVariable(name = "id") Long id) throws Exception {
        log.info("Deletando tarefa: {}", id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", tarefaService.deleteTarefa(id));
        return response;
    }
}
