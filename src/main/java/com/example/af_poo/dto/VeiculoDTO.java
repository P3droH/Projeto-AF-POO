package com.example.af_poo.dto;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

import com.example.af_poo.model.Reserva;


public class VeiculoDTO {

    @NotBlank(message = "Modelo obrigatório!")
    private String modelo;
    
    @NotBlank(message = "Valor da diária obrigatório!")
    private double valorDiaria;

    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
}