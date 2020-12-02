package com.example.vehiclereservation.DTO.UpdateOrSaves;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class VehicleDTO {

    @NotBlank(message = "Nome obrigatório")
    @Length(min=3, max=20, message = "Nome deve ter no minimo de 3 e no maximo 20 caracteres")
    private String model;

    @NotNull(message = "Valor obrigatório")
    @Min(value = 40, message = "Valor minimo de R$40")
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
