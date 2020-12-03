package com.example.af_poo.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservaDTOlist {
    
    private int numero;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;
    
    private double total;


    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /*public double calcularTotalReserva() {
        return veiculo.getValorDiaria() * 
    }*/
}