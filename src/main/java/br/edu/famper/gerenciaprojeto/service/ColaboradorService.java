package br.edu.famper.gerenciaprojeto.service;

import br.edu.famper.gerenciaprojeto.dto.ColaboradorDto;
import br.edu.famper.gerenciaprojeto.model.Colaborador;
import br.edu.famper.gerenciaprojeto.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public List<ColaboradorDto> getAllColaboradores() {
        return colaboradorRepository
                .findAll()
                .stream()
                .map(colaborador -> ColaboradorDto
                        .builder()
                        .nome(colaborador.getNome())
                        .cpf_cnpj(colaborador.getCpf_cnpj())
                        .fone(colaborador.getFone())
                        .email(colaborador.getEmail())
                        .endereco(colaborador.getEndereco())
                        .build()
                )
                .toList();
    }

    public ColaboradorDto getColaboradorById(Long id) {
        Colaborador col = colaboradorRepository.findById(id).orElseThrow();
        return new ColaboradorDto()
                .builder()
                .nome(col.getNome())
                .cpf_cnpj(col.getCpf_cnpj())
                .fone(col.getFone())
                .email(col.getEmail())
                .endereco(col.getEndereco())
                .build();
    }

    public Colaborador saveColaborador(ColaboradorDto colaboradorDto) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(colaboradorDto.getNome());
        colaborador.setCpf_cnpj(colaboradorDto.getCpf_cnpj());
        colaborador.setFone(colaboradorDto.getFone());
        colaborador.setEmail(colaboradorDto.getEmail());
        colaborador.setEndereco(colaboradorDto.getEndereco());
        return colaboradorRepository.save(colaborador);
    }

    public ColaboradorDto editColaborador(Long id, ColaboradorDto colaboradorDto) {
        Colaborador colaborador = colaboradorRepository.findById(id).orElseThrow();
        colaborador.setNome(colaboradorDto.getNome());
        colaborador.setCpf_cnpj(colaboradorDto.getCpf_cnpj());
        colaborador.setFone(colaboradorDto.getFone());
        colaborador.setEmail(colaboradorDto.getEmail());
        colaborador.setEndereco(colaboradorDto.getEndereco());
        Colaborador ColaboradorEdit = colaboradorRepository.save(colaborador);
        return new ColaboradorDto()
                .builder()
                .nome(colaborador.getNome())
                .cpf_cnpj(colaborador.getCpf_cnpj())
                .fone(colaborador.getFone())
                .email(colaborador.getEmail())
                .endereco(colaborador.getEndereco())
                .build();
    }

    public boolean deleteColaborador(Long id) {
        try {
            Colaborador colaborador = colaboradorRepository.findById(id).orElseThrow();
            colaboradorRepository.delete(colaborador);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
