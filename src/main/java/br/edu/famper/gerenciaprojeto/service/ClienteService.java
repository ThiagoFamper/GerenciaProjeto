package br.edu.famper.gerenciaprojeto.service;

import br.edu.famper.gerenciaprojeto.dto.ClienteDto;
import br.edu.famper.gerenciaprojeto.model.Cliente;
import br.edu.famper.gerenciaprojeto.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDto> getAllClientes() {
        return clienteRepository
                .findAll()
                .stream()
                .map(cliente -> ClienteDto
                        .builder()
                        .nome(cliente.getNome())
                        .cpf_cnpj(cliente.getCpf_cnpj())
                        .fone(cliente.getFone())
                        .email(cliente.getEmail())
                        .endereco(cliente.getEndereco())
                        .build()
                )
                .toList();
    }

    public ClienteDto getClienteById(Long id) {
        Cliente cli = clienteRepository.findById(id).orElseThrow();
        return new ClienteDto()
                .builder()
                .nome(cli.getNome())
                .cpf_cnpj(cli.getCpf_cnpj())
                .fone(cli.getFone())
                .email(cli.getEmail())
                .endereco(cli.getEndereco())
                .build();
    }

    public Cliente saveCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setCpf_cnpj(clienteDto.getCpf_cnpj());
        cliente.setFone(clienteDto.getFone());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setEndereco(clienteDto.getEndereco());
        return clienteRepository.save(cliente);
    }

    public ClienteDto editCliente(Long id, ClienteDto clienteDto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setNome(clienteDto.getNome());
        cliente.setCpf_cnpj(clienteDto.getCpf_cnpj());
        cliente.setFone(clienteDto.getFone());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setEndereco(clienteDto.getEndereco());
        Cliente clienteEdit = clienteRepository.save(cliente);
        return new ClienteDto()
                .builder()
                .nome(cliente.getNome())
                .cpf_cnpj(cliente.getCpf_cnpj())
                .fone(cliente.getFone())
                .email(cliente.getEmail())
                .endereco(cliente.getEndereco())
                .build();
    }

    public boolean deleteCliente(Long id) {
        try {
            Cliente cliente = clienteRepository.findById(id).orElseThrow();
            clienteRepository.delete(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
