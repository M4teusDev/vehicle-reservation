package com.example.vehiclereservation.DTO.UpdateOrSaves;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationDTO {

    @FutureOrPresent
    @NotEmpty(message = "Data de finalização necessária") 
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dateIni;

    @FutureOrPresent 
    @NotEmpty(message = "Data de finalização necessária")
    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dateEnd;

    public LocalDateTime getDateIni() {
        return dateIni;
    }

    public void setDateIni(LocalDateTime dateIni) {
        this.dateIni = dateIni;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    
}
