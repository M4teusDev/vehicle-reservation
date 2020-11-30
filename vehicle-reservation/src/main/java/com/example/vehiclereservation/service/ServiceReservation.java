package com.example.vehiclereservation.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.vehiclereservation.DTO.Gets.ReservationGetDTO;
import com.example.vehiclereservation.DTO.UpdateOrSaves.ReservationDTO;
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


    public void verifyDateOfWeek(ReservationDTO reservation)
    {    
        if(reservation.getDateIni().getDayOfWeek().equals(DayOfWeek.SUNDAY) || reservation.getDateEnd().getDayOfWeek().equals(DayOfWeek.SUNDAY))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horario de atendimento - seg a sáb");
        }
    }

    private void verifyDate(int codeVehicle, ReservationDTO reservation) 
    {
        if( repositoryReservation.verifyDate(codeVehicle,reservation) == false )
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conflito de reserva");
        }
    }

    public void verifyIni(ReservationDTO reservation)
    {    
        if(LocalDateTime.now().isAfter(reservation.getDateIni())   || reservation.getDateIni().isAfter(reservation.getDateEnd()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data invalida");
        }
    }

	public Reservation save(int codeClient, int codeVehicle, ReservationDTO reservationDTO) {
        
        Client  client   = serviceClient.getClientByCode(codeClient);
        Vehicle vehicle  = serviceVehicle.getVehicleByCode(codeVehicle);        

        verifyDateOfWeek(reservationDTO); //Verifica se o dia de entrega e de devolução não corresponde a um domingo
        verifyDate(codeVehicle,reservationDTO); //Verifica se a data para reserva, conflita com algum outro cliente
        verifyIni(reservationDTO); //Verifica se a data inserida é maior que a do sistema 
        
        return repositoryReservation.saveReservation(client,vehicle,DTOToReservation(reservationDTO));
	}

	private Reservation DTOToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        reservation.setDateIni(reservationDTO.getDateIni());
        reservation.setDateEnd(reservationDTO.getDateEnd());

        return reservation;
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

	public ReservationGetDTO reservationToDTO(Reservation reservation) {
        ReservationGetDTO reservationDTO = new ReservationGetDTO();
        float totalValue = (ChronoUnit.DAYS.between(reservation.getDateIni(), reservation.getDateEnd()) * reservation.getVehicle().getValue());


        reservationDTO.setTotalValue(totalValue);
        reservationDTO.setClientName(reservation.getClient().getName());
        reservationDTO.setDailyValue(reservation.getVehicle().getValue());
        reservationDTO.setDateEnd(reservation.getDateEnd());
        reservationDTO.setDateStart(reservation.getDateIni());
        reservationDTO.setVehicleModel(reservation.getVehicle().getModel());

        return reservationDTO;
	}

	public List<ReservationGetDTO> reservationsToDTO(List<Reservation> reservations) {
        List<ReservationGetDTO> reservationsDTO = new ArrayList<ReservationGetDTO>();
        ReservationGetDTO reservationDTO = new ReservationGetDTO();

        for (Reservation aux : reservations){

            reservationDTO.setTotalValue((ChronoUnit.DAYS.between(aux.getDateIni(), aux.getDateEnd()) * aux.getVehicle().getValue()));
            reservationDTO.setClientName(aux.getClient().getName());
            reservationDTO.setDailyValue(aux.getVehicle().getValue());
            reservationDTO.setDateEnd(aux.getDateEnd());
            reservationDTO.setDateStart(aux.getDateIni());
            reservationDTO.setVehicleModel(aux.getVehicle().getModel());
            
            reservationsDTO.add(reservationDTO);
        }
        
        
        return reservationsDTO;
	}

	public boolean isDeleteClientPossible(Client client) {
        if (repositoryReservation.isDeleteClientPossible(client)){
            return true;
        }
        return false;
	}

	public boolean isDeleteVehiclePossible(Vehicle vehicle) {
        if(repositoryReservation.isDeleteVehiclePossible(vehicle)){
            return true;
        }
        return false;
	}

	// public void deleteReservation(Reservation reservation){
    //     repositoryReservation.deleteReservation
	// }


}