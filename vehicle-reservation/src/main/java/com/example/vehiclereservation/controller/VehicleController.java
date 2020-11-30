package com.example.vehiclereservation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.vehiclereservation.DTO.Update.VehicleDTO;
import com.example.vehiclereservation.model.Vehicle;
import com.example.vehiclereservation.service.ServiceVehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/veiculos")
public class VehicleController {

    
    @Autowired
    private ServiceVehicle serviceVehicle;

    @PostMapping()
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle, HttpServletRequest request, UriComponentsBuilder builder)
    {
        vehicle = serviceVehicle.saveVehicle(vehicle);
     
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + vehicle.getcode()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping()
    public List<Vehicle> getAllVehicles() 
    {
        return serviceVehicle.getAllVehicles();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Vehicle> getVehicleByCode(@PathVariable int code)
    {
        return ResponseEntity.ok(serviceVehicle.getVehicleByCode(code));
    }

    @PutMapping("/{code}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable int code, @RequestBody VehicleDTO vehicleDTO)
    {
        return ResponseEntity.ok(serviceVehicle.updateDTO(code, vehicleDTO));
    }
    
}
