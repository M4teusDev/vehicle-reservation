package com.example.vehiclereservation.DTO.UpdateOrSaves;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class VehicleDTO {

    @NotBlank(message = "Nome obrigatório")
    @Length(min=3, max=20, message = "Nome deve ter no minimo de 3 e no maximo 20 caracteres")
    private String model;

    @NotEmpty(message = "Valor obrigatório")
    private float value;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
