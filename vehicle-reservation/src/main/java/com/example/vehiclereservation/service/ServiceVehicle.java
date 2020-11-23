package com.example.vehiclereservation.service;

import java.util.List;
import java.util.Optional;

import com.example.vehiclereservation.DTO.VehicleDTO;
import com.example.vehiclereservation.model.Vehicle;
import com.example.vehiclereservation.repository.RepositoryVehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceVehicle {
    
    @Autowired
    private RepositoryVehicle repositoryVehicle;

	public Vehicle saveVehicle(Vehicle vehicle) {
        
        return repositoryVehicle.saveVehicle(vehicle);
	}

	public List<Vehicle> getAllVehicles() {
		return repositoryVehicle.getAllVehicles();
	}

	public Vehicle getVehicleByCode(int code) {
        
        Optional<Vehicle> opVehicle = repositoryVehicle.getVehicleByCode(code);
        return opVehicle.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle nao encontrado!"));
	}

	public Vehicle updateDTO(int code, VehicleDTO vehicleDTO) {
		
		Vehicle auxVehicle = getVehicleByCode(code); // 404 == true ? continue : break;
		return repositoryVehicle.updateDTO(vehicleDTO, auxVehicle);
	}    
}
