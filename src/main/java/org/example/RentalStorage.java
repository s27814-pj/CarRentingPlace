package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class RentalStorage {

    private List<Rental> rentals = new ArrayList<>();


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

