package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Veiculo;

import org.springframework.stereotype.Component;

@Component
public class VeiculoRepository {
    
    private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
    private static int nextCode = 1;

    public List<Veiculo> getAllVeiculos() {
        return veiculos;
    }

    public Optional<Veiculo> getVeiculoByCodigo(int codigo) {
        for(Veiculo aux : veiculos) {
            if(aux.getCodigo() == codigo) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        veiculo.setCodigo(nextCode++);
        veiculos.add(veiculo);
        return veiculo;
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }

    public Veiculo alterarVeiculo(Veiculo veiculo) {

        Veiculo aux = getVeiculoByCodigo(veiculo.getCodigo()).get();

        if(aux != null) {
            aux.setModelo(veiculo.getModelo());
            aux.setValorDiaria(veiculo.getValorDiaria());
        }
        return aux;
    }

}