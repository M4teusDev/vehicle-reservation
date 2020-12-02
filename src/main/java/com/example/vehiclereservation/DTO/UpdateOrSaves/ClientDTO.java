package com.example.vehiclereservation.DTO.UpdateOrSaves;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClientDTO {

    @NotBlank(message = "Nome obrigatório")
    @Length(min=3, max=20, message = "Nome deve ter no minimo de 3 e no maximo 20 caracteres")
    private String name;

    @NotBlank(message = "Endereço obrigatório")
    @Length(min=3, max=40, message = "Endereço deve ter no minimo de 4 e no maximo 40 caracteres")
    private String adress;
    
    @CPF
    private String cpf;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
