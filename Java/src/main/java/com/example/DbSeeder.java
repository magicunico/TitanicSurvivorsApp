package com.example;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private PassengerRepository passengerRepository;

    public DbSeeder(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Passenger passengerAdam = new Passenger(
            0, 1,"Adam Zieliński", "male", 23f
        );
        Passenger passengerJuliet = new Passenger(
                1, 1,"Julia Dziuba", "female", 23f
        );

        this.passengerRepository.deleteAll();
//        List<Passenger> passengers = Arrays.asList(passengerAdam, passengerJuliet);
//        System.out.println("WYNIKI: " + this.passengerRepository.findAll());

        List<Passenger> passengers = readPassengersFromCSV("E:\\Projects\\JAVA\\titanic-java\\src\\main\\java\\com\\example\\test.csv");

        this.passengerRepository.saveAll(passengers);
    }


    private static List<Passenger> readPassengersFromCSV(String fileName) {
        List<Passenger> passengers = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");
                if(!attributes[0].equals("PassengerId")){

                Passenger passenger = createPassenger(attributes);

                // adding passenger into ArrayList
                passengers.add(passenger);

                }
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return passengers;
    }

    private static Passenger createPassenger(String[] metadata) {
        int id = Integer.parseInt(metadata[0]);
        int pClass = Integer.parseInt(metadata[1]);
        String name = metadata[2] + metadata[3];
        String sex = metadata[4];
        float age = 0;
        if(metadata[5] != "")
            age = Float.parseFloat(metadata[5]);

        // create and return passenger of this metadata
        return new Passenger(id, pClass, name, sex, age);
    }
    
    private void loadData(){

        String fileName = "c:\\test\\csv\\country.csv";

        List<Passenger> beans = null;
        try {
            beans = new CsvToBeanBuilder(new FileReader("test.csv"))
                    .withType(Passenger.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        beans.forEach(System.out::println);
    }
}
