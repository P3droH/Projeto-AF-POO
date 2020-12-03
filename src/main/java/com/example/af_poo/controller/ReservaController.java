package com.example.af_poo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo.dto.ReservaDTO;
import com.example.af_poo.model.Reserva;
import com.example.af_poo.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService service;

    @GetMapping()
    public List<Reserva> getReservas() {
        return service.getAllReservas();
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Reserva> getReservaByNumero(@PathVariable int numero) {
        Reserva reserva = service.getReservaByNumero(numero);
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> removerReserva(@PathVariable int numero) {
        service.removeByNumero(numero);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Reserva> atualizarCurso(@RequestBody ReservaDTO reservaDTO, @PathVariable int numero) {
        Reserva reserva = service.fromDTO(reservaDTO);
        reserva.setNumero(numero);
        reserva = service.update(reserva);
        return ResponseEntity.ok(reserva);
    }

    @PostMapping()
    public ResponseEntity<Reserva> salvarReserva(@Valid @RequestBody ReservaDTO reservaDTO, @PathVariable int idCliente, @PathVariable int idVeiculo, HttpServletRequest request, UriComponentsBuilder builder) {
        Reserva reserva = service.fromDTO(reservaDTO);
        Reserva novoVeiculo = service.save(reserva, idCliente, idVeiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoVeiculo.getNumero()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}