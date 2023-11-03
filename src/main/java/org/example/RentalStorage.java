package org.example;

import java.util.ArrayList;
import java.util.List;

public class RentalStorage {

    private List<Rental> rentals = new ArrayList<>();

    private static RentalStorage rentalStorage;

    private RentalStorage(){

    }

    public static RentalStorage getInstance(){
        if (rentalStorage == null){
            rentalStorage = new RentalStorage();
        }
        return rentalStorage;
    }

    public List<Rental> getRentals() {
        return rentals;
    }
}
