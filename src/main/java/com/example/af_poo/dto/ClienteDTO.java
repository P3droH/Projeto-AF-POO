package com.example.af_poo.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {
    
    @NotBlank(message = "Nome obrigatório!")
    @Length(min = 8, max = 40, message = "Nome deve ter no minimo 8 caracteres e no máximo 40")
    private String nome;
    
    @NotBlank(message = "Endereço obrigatório!")
    @Length(min = 4, max = 30, message = "Endereço deve ter no minimo 4 caracteres e no máximo 30")
    private String endereco;
    
    @NotBlank(message = "CPF obrigatório")
    @CPF
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}