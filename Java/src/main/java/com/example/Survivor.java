package com.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Survivors")
public class Survivor {
    @Id
    private int passengerID;
    private boolean survived;

}