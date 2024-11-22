package br.edu.famper.gerenciaprojeto.service;

import br.edu.famper.gerenciaprojeto.dto.TarefaDto;
import br.edu.famper.gerenciaprojeto.model.Tarefa;
import br.edu.famper.gerenciaprojeto.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<TarefaDto> getAllTarefas() {
        return tarefaRepository
                .findAll()
                .stream()
                .map(tarefa -> TarefaDto
                        .builder()
                        .nome(tarefa.getNome())
                        .descricao(tarefa.getDescricao())
                        .build()
                )
                .toList();
    }

    public TarefaDto getTarefaById(Long id) {
        Tarefa tar = tarefaRepository.findById(id).orElseThrow();
        return new TarefaDto()
                .builder()
                .nome(tar.getNome())
                .descricao(tar.getDescricao())
                .build();
    }

    public Tarefa saveTarefa(TarefaDto tarefaDto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome(tarefaDto.getNome());
        tarefa.setDescricao(tarefaDto.getDescricao());
        return tarefaRepository.save(tarefa);
    }

    public TarefaDto editTarefa(Long id, TarefaDto tarefaDto) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow();
        tarefa.setNome(tarefaDto.getNome());
        tarefa.setDescricao(tarefaDto.getDescricao());
        Tarefa TarefaEdit = tarefaRepository.save(tarefa);
        return new TarefaDto()
                .builder()
                .nome(tarefa.getNome())
                .descricao(tarefa.getDescricao())
                .build();
    }

    public boolean deleteTarefa(Long id) {
        try {
            Tarefa tarefa = tarefaRepository.findById(id).orElseThrow();
            tarefaRepository.delete(tarefa);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
