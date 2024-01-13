package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Main {

    private final CarStorage carStorage;
    private final RentalService rentalService;

    public Main(CarStorage carStorage, RentalService rentalService){
        this.carStorage = carStorage;
        this.rentalService = rentalService;

        //execProcess();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void execProcess(){
        Car car1 = new Car("marka1", "model1", "123456ASF", type.PREMIUM);
        Car car2 = new Car("marka2", "model2", "inne", type.PREMIUM);



        carStorage.addCar(car1);

       System.out.println(carStorage.getCars());


        System.out.println(carStorage.findCarByBin("inne"));



    }

}