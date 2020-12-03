package com.example.af_poo.service;

import java.util.List;
import java.util.Optional;

import com.example.af_poo.dto.VeiculoDTO;
import com.example.af_poo.model.Veiculo;
import com.example.af_poo.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository repository;

    public Veiculo fromDTO(VeiculoDTO dto) {
        
        Veiculo veiculo = new Veiculo();

        veiculo.setModelo(dto.getModelo());
        veiculo.setValorDiaria(dto.getValorDiaria());

        return veiculo;
    }

    public List<Veiculo> getAllVeiculos() {
        return repository.getAllVeiculos();
    }

    public Veiculo getVeiculoByCodigo(int codigo) {
        Optional<Veiculo> op = repository.getVeiculoByCodigo(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo nao cadastrado!"));
    }

    public void removeByCodigo(int codigo) {
        repository.removerVeiculo(getVeiculoByCodigo(codigo));

    }

    public Veiculo save(Veiculo veiculo) {
        return repository.cadastrarVeiculo(veiculo);
    }

    public Veiculo update(Veiculo veiculo) {
        getVeiculoByCodigo(veiculo.getCodigo());
        return repository.alterarVeiculo(veiculo);
    }

}