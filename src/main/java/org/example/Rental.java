package org.example;

import java.time.LocalDate;


public class Rental {

    private User user;
    private Car car;

    private LocalDate startRentalDate;

    private LocalDate endRentalDate;

    public Rental(User user, Car car, LocalDate startRentalDate, LocalDate endRentalDate){
        this.user=user;
        this.car=car;
        this.startRentalDate=startRentalDate;
        this.endRentalDate=endRentalDate;
    }

    public User getUser(){
        return user;
    }
}


