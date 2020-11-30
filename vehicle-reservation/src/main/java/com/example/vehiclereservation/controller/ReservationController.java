package com.example.vehiclereservation.controller;

import java.util.List;

import com.example.vehiclereservation.DTO.Gets.ReservationGetDTO;
import com.example.vehiclereservation.service.ServiceReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservationController {

    @Autowired
    private ServiceReservation serviceReservation;

    @GetMapping()
    public List<ReservationGetDTO> getAllReservation() 
    {
        return serviceReservation.reservationsToDTO(serviceReservation.getAllReservation());
    }

    @GetMapping("/{code}")
    public ResponseEntity<ReservationGetDTO> getReservationByCode(@PathVariable int code)
    {
        return ResponseEntity.ok(serviceReservation.reservationToDTO(serviceReservation.getReservationByCode(code)));
    }

    @GetMapping("/clientes/{code}")
    public ResponseEntity<List<ReservationGetDTO>> getReservationByClient(@PathVariable int code)
    {
        return ResponseEntity.ok(serviceReservation.reservationsToDTO(serviceReservation.getReservationByClient(code)));
    }

    @GetMapping("/veiculos/{code}")
    public ResponseEntity<List<ReservationGetDTO>> getReservationByVehicle(@PathVariable int code)
    {
        return ResponseEntity.ok(serviceReservation.reservationsToDTO(serviceReservation.getReservationByVehicle(code)));
    }
}