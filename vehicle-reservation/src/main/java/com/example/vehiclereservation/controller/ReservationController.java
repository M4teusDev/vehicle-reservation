package com.example.vehiclereservation.controller;

import java.util.List;

import com.example.vehiclereservation.model.Reservation;
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
    public List<Reservation> getAllReservation() 
    {
        return serviceReservation.getAllReservation();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Reservation> getReservationByCode(@PathVariable int code)
    {
        return ResponseEntity.ok(serviceReservation.getReservationByCode(code));
    }

    @GetMapping("/clientes/{code}")
    public ResponseEntity<List<Reservation>> getReservationByClient(@PathVariable int code)
    {
        return ResponseEntity.ok(serviceReservation.getReservationByClient(code));
    }

    @GetMapping("/veiculos/{code}")
    public ResponseEntity<List<Reservation>> getReservationByVehicle(@PathVariable int code)
    {
        return ResponseEntity.ok(serviceReservation.getReservationByVehicle(code));
    }
}