package com.example.vehiclereservation.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.vehiclereservation.DTO.VehicleDTO;
import com.example.vehiclereservation.model.Vehicle;

import org.springframework.stereotype.Component;

@Component
public class RepositoryVehicle {

    private List<Vehicle> vehicles = new ArrayList<Vehicle>();
    private static int nextCode = 1;

	public Vehicle saveVehicle(Vehicle vehicle) {

        vehicle.setcode(nextCode++);
        vehicle.setIsRent(false);
        vehicles.add(vehicle);
        
        return vehicle;
    }

	public List<Vehicle> getAllVehicles() {
		return vehicles;
	}

	public Optional<Vehicle> getVehicleByCode(int code) {
        
        for(Vehicle aux : vehicles)
        {
            if(aux.getcode() == code)
            {
                return Optional.of(aux);
            }
        }

        return Optional.empty();
	}

    public Vehicle updateDTO(VehicleDTO VehicleDTO, Vehicle auxVehicle) 
    {
        auxVehicle.setModel(VehicleDTO.getModel());
        auxVehicle.setValue(VehicleDTO.getValue());

        return auxVehicle;
	}      
}
