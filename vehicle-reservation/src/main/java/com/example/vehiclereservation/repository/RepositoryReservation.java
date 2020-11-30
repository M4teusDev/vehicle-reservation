package com.example.vehiclereservation.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.vehiclereservation.DTO.UpdateOrSaves.ReservationDTO;
import com.example.vehiclereservation.model.Client;
import com.example.vehiclereservation.model.Reservation;
import com.example.vehiclereservation.model.Vehicle;

import org.springframework.stereotype.Component;

@Component
public class RepositoryReservation 
{

	private List<Reservation> reservations = new ArrayList<Reservation>();
	private int nextCode = 1;


	public boolean verifyDate(int codeVehicle, ReservationDTO reservation) 
	{
		for(Reservation aux: reservations)
		{
			if(aux.getVehicle().getcode() == codeVehicle)
			{
				if((reservation.getDateIni().isAfter(aux.getDateIni()) && reservation.getDateIni().isBefore(aux.getDateEnd())) == false)
				{
					if((reservation.getDateEnd().isAfter(aux.getDateIni()) && reservation.getDateEnd().isBefore(aux.getDateEnd())) == false)
					{
						if((reservation.getDateIni().isEqual(aux.getDateIni()) || reservation.getDateIni().isEqual(aux.getDateEnd()) ||
						    reservation.getDateEnd().isEqual(aux.getDateIni()) || reservation.getDateEnd().isEqual(aux.getDateEnd())) == false )
						{
							if((reservation.getDateIni().isBefore(aux.getDateIni()) && reservation.getDateEnd().isAfter(aux.getDateIni())) == false)
							{
								if((reservation.getDateIni().isBefore(aux.getDateEnd()) && reservation.getDateEnd().isAfter(aux.getDateEnd())) == false)
								{
									continue;
								}
								else return false;
							}
							else return false;
						}
						else return false;
					}
					else return false;
				}
				else return false ;
			}
		}

		return true;
	}

	public Reservation saveReservation(Client client, Vehicle vehicle, Reservation reservation) {
		reservation.setCode(nextCode++);
		reservation.setClient(client);
		reservation.setVehicle(vehicle);

		reservations.add(reservation);

		return reservation;
	}

	public List<Reservation> getAllReservation() {
		return reservations;
	}

	public Optional<Reservation> getReservationByCode(int code) {
		for(Reservation aux : reservations)
        {
            if(aux.getCode() == code)
            {
                return Optional.of(aux);
            }
        }

        return Optional.empty();
	}

	public Optional<List<Reservation>> getReservationByClient(Client client) {

		List<Reservation> reservationsOfClient = new ArrayList<Reservation>();

		for(Reservation aux : reservations)
        {
            if(aux.getClient() == client){
				reservationsOfClient.add(aux);
            }
		}
		
		if(reservationsOfClient.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(reservationsOfClient);
		}
		
        
	}

	public Optional<List<Reservation>> getReservationByVehicle(Vehicle vehicle) {

		List<Reservation> reservationsOfVehicle = new ArrayList<Reservation>();

		for(Reservation aux : reservations)
        {
            if(aux.getVehicle() == vehicle)
            {
				reservationsOfVehicle.add(aux);
            }
		}
		
		if(reservationsOfVehicle.isEmpty()) {
			return Optional.empty();
		}
		else {
			return Optional.of(reservationsOfVehicle);
		}
	}
}
