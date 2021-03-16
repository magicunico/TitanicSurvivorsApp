package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    PassengerRepository passengerRepository;

    @CrossOrigin
    @GetMapping("/all")
    public List<Passenger> getAll() throws NoSuchMethodException {
        List<Passenger> passengers = this.passengerRepository.findAll();
        return passengers;
    }

    @CrossOrigin
    @PutMapping
    public void createPassenger(@RequestBody Passenger passenger) {
        this.passengerRepository.insert(passenger);
    }

    @CrossOrigin
    @PostMapping
    public void updatePassenger(@RequestBody Passenger passenger) {
        this.passengerRepository.save(passenger);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable("id") String id) {
        this.passengerRepository.deleteById(id);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Passenger getById(@PathVariable("id") String passengerID) {
        return this.passengerRepository.findByPassengerID(passengerID);
    }
}
