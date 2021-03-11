package com.example.Repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Model.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String> {
    List<Passenger> findByTitleContaining(String title);
    List<Passenger> findByPublished(boolean published);
}

