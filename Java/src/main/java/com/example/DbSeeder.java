package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private PassengerRepository passengerRepository;

    public DbSeeder(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.passengerRepository.deleteAll();

        List<Passenger> passengers = readPassengersFromCSV("src\\main\\java\\com\\example\\test.csv", "src\\main\\java\\com\\example\\gender_submission.csv");

        this.passengerRepository.saveAll(passengers);
    }


    private static List<Passenger> readPassengersFromCSV(String fileName, String survivedFileName) {
        List<Passenger> passengers = new ArrayList<>();

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br  = new BufferedReader(new FileReader(new File(fileName).getAbsolutePath()));
             BufferedReader survived  = new BufferedReader(new FileReader(new File(survivedFileName).getAbsolutePath()))) {

            // read the first line from the text file
            String line = br.readLine();
            String lineSurvived = survived.readLine();

            // loop until all lines are read
            while (line != null && lineSurvived != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");
                String[] attributesSurvived = lineSurvived.split(",");
                if(!attributes[0].equals("PassengerId")){

                Passenger passenger = createPassenger(attributes, attributesSurvived[1]);

                // adding passenger into ArrayList
                passengers.add(passenger);
                }

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
                lineSurvived = survived.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return passengers;
    }

    private static Passenger createPassenger(String[] metadata, String survivedData) {
        String id = metadata[0];
        int pClass = Integer.parseInt(metadata[1]);
        String name = metadata[2] + metadata[3];
        String sex = metadata[4];
        float age = 0;
        if(!metadata[5].equals(""))
            age = Float.parseFloat(metadata[5]);
        int sibSp = Integer.parseInt(metadata[6]);
        int parch = Integer.parseInt(metadata[7]);
        String ticket = metadata[8];
        float fare = 0;
        if (!metadata[9].equals(""))
            fare = Float.parseFloat(metadata[9]);
        String cabin = metadata[10];
        String embarked = metadata[11];
        boolean survived = false;
        if(!survivedData.equals("") && !survivedData.equals("0"))
            survived = true;
        // create and return passenger of this metadata
        return new Passenger(id, pClass, name, sex, age, sibSp, parch, ticket, fare, cabin, embarked, survived);
    }

}
