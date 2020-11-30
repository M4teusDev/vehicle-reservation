package com.example.vehiclereservation.service;

import java.util.List;
import java.util.Optional;

import com.example.vehiclereservation.DTO.UpdateOrSaves.VehicleDTO;
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
	
	@Autowired
	private ServiceReservation serviceReservation;

	public Vehicle saveVehicle(VehicleDTO vehicleDTO) {
        return repositoryVehicle.saveVehicle(DTOToVehicle(vehicleDTO));
	}

	private Vehicle DTOToVehicle(VehicleDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle();

		vehicle.setModel(vehicleDTO.getModel());
		vehicle.setValue(vehicleDTO.getValue());

		return vehicle;
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

	public void deleteVehicle(Vehicle vehicle) {
		if(serviceReservation.isDeleteVehiclePossible(vehicle) == false){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel deletar o veiculo pois existem reservas pendentes");
		}
		repositoryVehicle.deleteVehicle(vehicle);
	}    
}
