package com.example.af_poo.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.example.af_poo.model.Cliente;
import com.example.af_poo.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservaDTO {
    
    @NotBlank(message = "Data de inicio obrigatorio!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    
    @NotBlank(message = "Data de fim obrigatorio!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;
    
    private int total;

    private Cliente cliente;
    private Veiculo veiculo;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        if(dataInicio.isAfter(LocalDate.now())) {
            this.dataInicio = dataInicio;
        }
        else {
            System.out.println("Data de início tem que ser maior que a data do sistema!");
        }
        
        
        if(dataInicio.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            System.out.println("Início da reserva no domingo indisponível!");
        }
        else {
            this.dataInicio = dataInicio;
        }
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        if(dataFim.isAfter(dataInicio)) {
            this.dataFim = dataFim;
        }
        else {
            System.out.println("Data final da reserva tem que ser maior que a data de início!");
        }
        
        
        if(dataFim.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            System.out.println("Final da reserva no domingo indisponível!");
        }
        else {
            this.dataFim = dataFim;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public double calcularTotalReserva() {
        return dataFim.compareTo(dataInicio) * veiculo.getValorDiaria();
    }
}
