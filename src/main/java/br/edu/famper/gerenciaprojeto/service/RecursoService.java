package br.edu.famper.gerenciaprojeto.service;

import br.edu.famper.gerenciaprojeto.dto.RecursoDto;
import br.edu.famper.gerenciaprojeto.model.Recurso;
import br.edu.famper.gerenciaprojeto.repository.RecursoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public List<RecursoDto> getAllRecursos() {
        return recursoRepository
                .findAll()
                .stream()
                .map(recurso -> RecursoDto
                        .builder()
                        .descricao(recurso.getDescricao())
                        .build()
                )
                .toList();
    }

    public RecursoDto getRecursoById(Long id) {
        Recurso rec = recursoRepository.findById(id).orElseThrow();
        return new RecursoDto()
                .builder()
                .descricao(rec.getDescricao())
                .build();
    }

    public Recurso saveRecurso(RecursoDto recursoDto) {
        Recurso recurso = new Recurso();
        recurso.setDescricao(recursoDto.getDescricao());
        return recursoRepository.save(recurso);
    }

    public RecursoDto editRecurso(Long id, RecursoDto recursoDto) {
        Recurso recurso = recursoRepository.findById(id).orElseThrow();
        recurso.setDescricao(recursoDto.getDescricao());
        Recurso RecursoEdit = recursoRepository.save(recurso);
        return new RecursoDto()
                .builder()
                .descricao(recurso.getDescricao())
                .build();
    }

    public boolean deleteRecurso(Long id) {
        try {
            Recurso recurso = recursoRepository.findById(id).orElseThrow();
            recursoRepository.delete(recurso);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
