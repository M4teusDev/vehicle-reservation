package com.example.vehiclereservation.service;

import java.time.DayOfWeek;

import com.example.vehiclereservation.model.Client;
import com.example.vehiclereservation.model.Reservation;
import com.example.vehiclereservation.model.Vehicle;
import com.example.vehiclereservation.repository.RepositoryClient;
import com.example.vehiclereservation.repository.RepositoryReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ServiceReservation {
    
    @Autowired
    private RepositoryReservation repositoryReservation;

    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private RepositoryClient repositoryClient;

    @Autowired
    private ServiceVehicle serviceVehicle;

    // public void verifyVehicle(Vehicle vehicle)
    // {
    //     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veiculo reservado!");
    // }


    public void verifyDateOfWeek(Reservation reservation)
    {    
        if(reservation.getDateIni().getDayOfWeek().equals(DayOfWeek.SUNDAY) || reservation.getDateEnd().getDayOfWeek().equals(DayOfWeek.SUNDAY))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horario de atendimento - seg a sáb");
        }
    }

    private void verifyDate(int codeVehicle, Reservation reservation) 
    {
        if( repositoryReservation.verifyDate(codeVehicle,reservation) == false )
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horario de atendimento - seg a sáb");
        }
    }

	public Reservation save(int codeClient, int codeVehicle, Reservation reservation) {
        
        Client  client   = serviceClient.getClientByCode(codeClient);
        Vehicle vehicle  = serviceVehicle.getVehicleByCode(codeVehicle);        

        verifyDateOfWeek(reservation);
        verifyDate(codeVehicle,reservation);

        return null;
	}


}