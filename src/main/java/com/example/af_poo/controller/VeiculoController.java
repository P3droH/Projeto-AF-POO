package com.example.af_poo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo.dto.ReservaDTOlist;
import com.example.af_poo.dto.VeiculoDTO;
import com.example.af_poo.model.Veiculo;
import com.example.af_poo.service.ReservaService;
import com.example.af_poo.service.VeiculoService;

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
@RequestMapping("/veiculos")
public class VeiculoController {
    
    @Autowired
    private VeiculoService service;

    @Autowired
    private ReservaService reservaService;
    
    @GetMapping()
    public List<Veiculo> getVeiculos() {
        return service.getAllVeiculos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculo> getVeiculoByCodigo(@PathVariable int codigo) {
        Veiculo veiculo = service.getVeiculoByCodigo(codigo);
        return ResponseEntity.ok(veiculo);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removerVeiculo(@PathVariable int codigo) {
        service.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@RequestBody VeiculoDTO veiculoDTO, @PathVariable int codigo) {
        Veiculo veiculo = service.fromDTO(veiculoDTO);
        veiculo.setCodigo(codigo);
        veiculo = service.update(veiculo);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping()
    public ResponseEntity<Veiculo> salvarVeiculo(@Valid @RequestBody VeiculoDTO veiculoDTO,  HttpServletRequest request, UriComponentsBuilder builder) {
        Veiculo veiculo = service.fromDTO(veiculoDTO);
        Veiculo novoVeiculo = service.save(veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoVeiculo.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
    
    @GetMapping("/{idVeiculo}/reservas")
    public List<ReservaDTOlist> getReservasVeiculos(@PathVariable int idVeiculo) {
        Veiculo veiculo = service.getVeiculoByCodigo(idVeiculo);
        return reservaService.toListDTO(veiculo.getReservas());
    }
}