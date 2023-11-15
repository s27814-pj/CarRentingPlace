package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Car car1 = new Car("marka1", "model1", "123456ASF", type.PREMIUM);
        Car car2 = new Car("marka2", "model2", "inne", type.PREMIUM);


        CarStorage carStorage = CarStorage.getInstance();
        RentalStorage rentalStorage = RentalStorage.getInstance();
        RentalService rentalService = new RentalService(carStorage, rentalStorage);



        carStorage.addCar(car1);

        System.out.println(carStorage.getCars());

        CarStorage carStorage2 = CarStorage.getInstance();

        carStorage2.addCar(car2);

        System.out.println(carStorage2.getCars());

        System.out.println(rentalService.findCarByBin("inne"));

        rentalService.estimatePrice("inne", LocalDate.now(), LocalDate.now().plusDays(3));
        System.out.println(rentalService.estimatePrice("inne", LocalDate.now(), LocalDate.now().plusDays(3)));
    }

}