package com.example.af_poo.service;

import java.util.List;
import java.util.Optional;

import com.example.af_poo.dto.ClienteDTO;
import com.example.af_poo.model.Cliente;
import com.example.af_poo.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    public Cliente fromDTO(ClienteDTO dto) {
        
        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCpf(dto.getCpf());

        return cliente;
    }

    public List<Cliente> getAllClientes() {
        return repository.getAllClientes();
    }

    public void removeByCodigo(int codigo) {
        repository.removerCliente(getClienteByCodigo(codigo));
    }

    public Cliente update(Cliente cliente) {
        getClienteByCodigo(cliente.getCodigo());
        return repository.alterarCliente(cliente);
    }

    public Cliente getClienteByCodigo(int codigo) {
        Optional<Cliente> op = repository.getClienteByCodigo(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao cadastrado!"));
    }

    public Cliente save(Cliente cliente) {
        return repository.cadastrarCliente(cliente);
    }
}