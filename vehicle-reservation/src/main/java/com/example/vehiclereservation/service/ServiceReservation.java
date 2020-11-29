package com.example.vehiclereservation.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.vehiclereservation.model.Client;
import com.example.vehiclereservation.model.Reservation;
import com.example.vehiclereservation.model.Vehicle;
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
    private ServiceVehicle serviceVehicle;


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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conflito de reserva");
        }
    }

    public void verifyIni(Reservation reservation)
    {    
        if(LocalDateTime.now().isAfter(reservation.getDateIni())   || reservation.getDateIni().isAfter(reservation.getDateEnd()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data invalida");
        }
    }

	public Reservation save(int codeClient, int codeVehicle, Reservation reservation) {
        
        Client  client   = serviceClient.getClientByCode(codeClient);
        Vehicle vehicle  = serviceVehicle.getVehicleByCode(codeVehicle);        

        verifyDateOfWeek(reservation); //Verifica se o dia de entrega e de devolução não corresponde a um domingo
        verifyDate(codeVehicle,reservation); //Verifica se a data para reserva, conflita com algum outro cliente
        verifyIni(reservation); //Verifica se a data inserida é maior que a do sistema 
        
        repositoryReservation.saveReservation(client,vehicle,reservation);

        return null;
	}

	public List<Reservation> getAllReservation() {
		return repositoryReservation.getAllReservation();
	}

	public Reservation getReservationByCode(int code) {
        Optional<Reservation> opReservation = repositoryReservation.getReservationByCode(code);
        return opReservation.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva nao encontrado!"));
	}

	public List<Reservation> getReservationByClient(int code) {
        Client client = serviceClient.getClientByCode(code);
        Optional<List<Reservation>> reservationsOfClient = repositoryReservation.getReservationByClient(client);
        return reservationsOfClient.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva  do cliente nao encontrado!"));
	}

	public List<Reservation> getReservationByVehicle(int code) {
        Vehicle vehicle = serviceVehicle.getVehicleByCode(code);
        Optional<List<Reservation>> reservationsOfVehicle = repositoryReservation.getReservationByVehicle(vehicle);
        return reservationsOfVehicle.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva nao encontrado!"));
	}


}