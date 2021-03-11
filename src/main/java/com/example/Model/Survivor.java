package com.example.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "survivors")
public class Survivor {
    @Id
    private int passengerID;
    private boolean survived;

}