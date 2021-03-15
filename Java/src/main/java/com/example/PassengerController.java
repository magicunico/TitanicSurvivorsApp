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
    @GetMapping("/passengers")
        public ResponseEntity<List<Passenger>> getAllPassengers(@RequestParam(required = false) String title) {

            return null;
        }

    @CrossOrigin
        @GetMapping("/passengers/{id}")
        public ResponseEntity<Passenger> getPassengerById(@PathVariable("id") String id) {

            return null;
        }

    @CrossOrigin
        @PostMapping("/passengers")
        public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger) {

            return null;
        }

    @CrossOrigin
        @PutMapping("/passengers/{id}")
        public ResponseEntity<Passenger> updatePassenger(@PathVariable("id") String id, @RequestBody Passenger passenger) {

            return null;
        }

    @CrossOrigin
        @DeleteMapping("/passengers/{id}")
        public ResponseEntity<HttpStatus> deletePassenger(@PathVariable("id") String id) {

            return null;
        }

    @CrossOrigin
        @DeleteMapping("/passengers")
        public ResponseEntity<HttpStatus> deleteAllPassengers() {

            return null;
        }

    @CrossOrigin
        @GetMapping("/passengers/published")
        public ResponseEntity<List<Passenger>> findByPublished() {
            return null;
        }


}
