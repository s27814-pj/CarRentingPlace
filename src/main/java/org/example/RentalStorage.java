package org.example;

import java.util.ArrayList;
import java.util.List;

public class RentalStorage {
    private static RentalStorage rentalStorage;
    private List<Rental> rentals = new ArrayList<>();


    private RentalStorage() {

    }

    public static RentalStorage getInstance() {

        if (rentalStorage == null) {
            rentalStorage = new RentalStorage();
        }

        return rentalStorage;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public List<Rental> getAllRentals() {
        return rentals;
    }
/*    public void getAllCars(){
        System.out.println(Arrays.toString(listaSamochod.toArray()));
        listaSamochod.forEach(System.out::println);
        System.out.println(listaSamochod.get(0).getVin());
    }*/
}