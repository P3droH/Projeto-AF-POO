package com.example.af_poo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo.dto.ClienteDTO;
import com.example.af_poo.dto.ReservaDTOlist;
import com.example.af_poo.model.Cliente;
import com.example.af_poo.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService service;

    @Autowired
    private ReservaService reservaService;


    @GetMapping()
    public List<Cliente> getClientes() {
        return service.getAllClientes();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removerCliente(@PathVariable int codigo) {
        service.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody ClienteDTO clienteDTO, @PathVariable int codigo) {
        Cliente cliente = service.fromDTO(clienteDTO);
        cliente.setCodigo(codigo);
        cliente = service.update(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getClienteByCodigo(@PathVariable int codigo) {
        Cliente cliente = service.getClienteByCodigo(codigo);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping()
    public ResponseEntity<Cliente> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO, HttpServletRequest request, UriComponentsBuilder builder) {
        Cliente cliente = service.fromDTO(clienteDTO);
        Cliente novoCliente = service.save(cliente);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoCliente.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/{idCliente}/reservas")
    public List<ReservaDTOlist> getReservasClientes(@PathVariable int idCliente) {
        Cliente cliente = service.getClienteByCodigo(idCliente);
        return reservaService.toListDTO(cliente.getReservas());
    }

}