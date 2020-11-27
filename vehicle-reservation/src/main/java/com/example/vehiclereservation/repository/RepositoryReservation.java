package com.example.vehiclereservation.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.vehiclereservation.model.Reservation;

import org.springframework.stereotype.Component;

@Component
public class RepositoryReservation 
{

	private List<Reservation> reservations = new ArrayList<Reservation>();

	public boolean verifyDate(int codeVehicle, Reservation reservation) 
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


  
}
