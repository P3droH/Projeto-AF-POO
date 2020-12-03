package com.example.af_poo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.dto.ReservaDTO;
import com.example.af_poo.dto.ReservaDTOlist;
import com.example.af_poo.model.Cliente;
import com.example.af_poo.model.Reserva;
import com.example.af_poo.model.Veiculo;
import com.example.af_poo.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;

    public Reserva fromDTO(ReservaDTO dto) {

        Reserva reserva = new Reserva();

        reserva.setDataInicio(dto.getDataInicio());
        reserva.setDataFim(dto.getDataFim());
        reserva.setTotal(dto.getTotal());

        return reserva;
    }

    public List<Reserva> getAllReservas() {
        return repository.getAllReservas();
    }

    public Reserva getReservaByNumero(int numero) {
        Optional<Reserva> op = repository.getReservaByNumero(numero);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva nao cadastrada!"));
    }

    public void removeByNumero(int numero) {
        repository.removerReserva(getReservaByNumero(numero));
    }

    public Reserva save(Reserva reserva, int idCliente, int idVeiculo) {
        
        Cliente cliente = clienteService.getClienteByCodigo(idCliente);
        Veiculo veiculo = veiculoService.getVeiculoByCodigo(idVeiculo);

        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);

        reserva.setTotal(reserva.calcularTotalReserva(reserva.getDataInicio(), reserva.getDataFim(), veiculo.getValorDiaria()));

        return repository.cadastrarReserva(reserva);
    }

    public Reserva update(Reserva reserva) {
        getReservaByNumero(reserva.getNumero());
        return repository.alterarReserva(reserva);
    }

    public ReservaDTOlist toDTO(Reserva reserva) {

        ReservaDTOlist dto = new ReservaDTOlist();

        dto.setNumero(reserva.getNumero());
        dto.setDataInicio(reserva.getDataInicio());
        dto.setDataFim(reserva.getDataFim());
        dto.setTotal(reserva.getTotal());

        return dto;
    }

    public List<ReservaDTOlist> toListDTO(List<Reserva> reservas) {
        ArrayList<ReservaDTOlist> listDTO = new ArrayList<ReservaDTOlist>();

        for(Reserva r: reservas) {
            listDTO.add(toDTO(r));
        }
        return listDTO;
    }
}
