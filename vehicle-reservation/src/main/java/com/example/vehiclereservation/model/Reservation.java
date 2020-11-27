package com.example.vehiclereservation.model;


import java.time.LocalDateTime;

public class Reservation {
    
    private int code;
    private Client client; //CODE
    private Vehicle vehicle; //CODE
    private LocalDateTime dateIni;
    private LocalDateTime dateEnd;
    //private float valueTotal;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

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
