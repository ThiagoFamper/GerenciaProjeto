package br.edu.famper.gerenciaprojeto.service;

import br.edu.famper.gerenciaprojeto.dto.ProjetoDto;
import br.edu.famper.gerenciaprojeto.model.Projeto;
import br.edu.famper.gerenciaprojeto.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<ProjetoDto> getAllProjetos() {
        return projetoRepository
                .findAll()
                .stream()
                .map(projeto -> ProjetoDto
                        .builder()
                        .nome(projeto.getNome())
                        .descricao(projeto.getDescricao())
                        .build()
                )
                .toList();
    }

    public ProjetoDto getProjetoById(Long id) {
        Projeto proj = projetoRepository.findById(id).orElseThrow();
        return new ProjetoDto()
                .builder()
                .nome(proj.getNome())
                .descricao(proj.getDescricao())
                .build();
    }

    public Projeto saveProjeto(ProjetoDto projetoDto) {
        Projeto projeto = new Projeto();
        projeto.setNome(projetoDto.getNome());
        projeto.setDescricao(projetoDto.getDescricao());
        return projetoRepository.save(projeto);
    }

    public ProjetoDto editProjeto(Long id, ProjetoDto projetoDto) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow();
        projeto.setNome(projetoDto.getNome());
        projeto.setDescricao(projetoDto.getDescricao());
        Projeto ProjetoEdit = projetoRepository.save(projeto);
        return new ProjetoDto()
                .builder()
                .nome(projeto.getNome())
                .descricao(projeto.getDescricao())
                .build();
    }

    public boolean deleteProjeto(Long id) {
        try {
            Projeto projeto = projetoRepository.findById(id).orElseThrow();
            projetoRepository.delete(projeto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
