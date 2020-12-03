package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Reserva;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepository {
    
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private static int nextNumber = 1;

    public List<Reserva> getAllReservas() {
        return reservas;
    }

    public Optional<Reserva> getReservaByNumero(int numero) {
        for(Reserva aux : reservas) {
            if(aux.getNumero() == numero) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
    }

    public Reserva cadastrarReserva(Reserva reserva) {
        reserva.setNumero(nextNumber++);
        reservas.add(reserva);
        return reserva;
    }

    public void removerReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public Reserva alterarReserva(Reserva reserva) {

        Reserva aux = getReservaByNumero(reserva.getNumero()).get();

        if(aux != null) {
            aux.setDataInicio(reserva.getDataInicio());
            aux.setDataFim(reserva.getDataFim());
            aux.setTotal(reserva.getTotal());
        }
        return aux;
    }

}
