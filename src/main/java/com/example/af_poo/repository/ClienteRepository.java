package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteRepository {

    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private static int nextCode = 1;

    public List<Cliente> getAllClientes() {
        return clientes;
    }

    public Optional<Cliente> getClienteByCodigo(int codigo) {
        for (Cliente aux : clientes) {
            if (aux.getCodigo() == codigo) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        cliente.setCodigo(nextCode++);
        clientes.add(cliente);
        return cliente;
    }

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public Cliente alterarCliente(Cliente cliente) {

        Cliente aux = getClienteByCodigo(cliente.getCodigo()).get();

        if(aux != null) {
            aux.setNome(cliente.getNome());
            aux.setEndereco(cliente.getEndereco());
            aux.setCpf(cliente.getCpf());
        }
        return aux;
    }
}