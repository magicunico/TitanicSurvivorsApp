package com.example;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.opencsv.bean.CsvBindByPosition;

@Document(collection = "Passengers")
public class Passenger {
    @Id
    @CsvBindByPosition(position = 0)
    private int passengerID;
    @CsvBindByPosition(position = 1)
    private int pClass;

    @CsvBindByPosition(position = 2)
    private String name;
    @CsvBindByPosition(position = 3)
    private String sex;
    @CsvBindByPosition(position = 4)
    private float age;
    @CsvBindByPosition(position = 5)
    private int sibSp;
    @CsvBindByPosition(position = 6)
    private int parch;
    @CsvBindByPosition(position = 7)
    private String ticket;
    @CsvBindByPosition(position = 8)
    private float fare;
    @CsvBindByPosition(position = 9)
    private String cabin;
    @CsvBindByPosition(position = 10)
    private String embarked;

    public Passenger(int passengerID, int pClass, String name, String sex, float age){
        this.passengerID = passengerID;
        this.pClass = pClass;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public class Country {

        private String startIp;

        @CsvBindByPosition(position = 1)
        private String endIp;

        @CsvBindByPosition(position = 2)
        private String countryCode;

        @CsvBindByPosition(position = 3)
        private String country;

        //  getters, setters, toString
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public int getpClass() {
        return pClass;
    }

    public void setpClass(int pClass) {
        this.pClass = pClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public int getSibSp() {
        return sibSp;
    }

    public void setSibSp(int sibSp) {
        this.sibSp = sibSp;
    }

    public int getParch() {
        return parch;
    }

    public void setParch(int parch) {
        this.parch = parch;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    public void setEmbarked(String embarked) {
        this.embarked = embarked;
    }
}