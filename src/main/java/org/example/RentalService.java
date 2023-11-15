package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RentalService {
    /*
    rentalService.rent() **3
    czy auto istnieje
    czy auto dostepne**4
    if true wynajac samochod
        else powiadomic o braku
    zworic status wynajecia

    rentalService.isAvailable()**6
    czy samochod istnieje
    czy istnieje rental dla samochodu
        if true
            data poczatkowa dostepnosci mniejsza od daty koncowej rentala
            data koncowa dostepnosci mniejsza od poczatkowej rentala
        if false
            zwracamy ze dostepne


    rentalService.estimatePrice **2
    wyszukac samochod dla VIN **1

                    Optional<Car> abc = carStorage.getCars().stream()
                        .filter(car -> car.getVin().equals("inne"))
                                .findFirst();

    ilosc dni * cena za dzien * wspolczynnik z typu samochodu
     */


//    private static RentalService rentalService;
//    private RentalService(){
//
//    }
//
//    public static RentalService getInstance(){
//        if (rentalService == null) {
//            rentalService = new RentalService();
//        }
//
//        return rentalService;
//    }

    private final CarStorage carStorage;
    private final RentalStorage rentalStorage;

    public RentalService(CarStorage carStorage, RentalStorage rentalStorage){
        this.carStorage = carStorage;
        this.rentalStorage = rentalStorage;
    }

    //    public void findCar(String vin, CarStorage carStorage){
////        for (String s : carStorage){
////            if (s = vin){
////                System.out.println("znalazlo");
////            }
////        }
//        System.out.println(carStorage.getCars());
//
//    }
    public Optional<Car> findCarByBin(String vin){
        return carStorage.getCars().stream()
                .filter(car-> car.getVin().equals(vin))
                .findFirst();
    }

    public double estimatePrice(String vin, LocalDate startDate, LocalDate endDate){

        Car carByVin = findCarByBin(vin).orElseThrow();
        long days = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();

        return days * 500 * carByVin.getType().getMultiplayer();
    }
    public Rental rent(String vin, int userId, LocalDate startDate, LocalDate endDate){
        Car carByVin = findCarByBin(vin).orElseThrow();
        if (isAvailable(vin, startDate, endDate)){
            Rental rental = new Rental(new User(userId), carByVin, startDate, endDate);
            rentalStorage.addRental(rental);
            return rental;
        } else {
            return null;
        }
    }
    public boolean isAvailable (String vin, LocalDate startDate, LocalDate endDate){
        //if (findCarByBin(vin).isEmpty()) return false;
        boolean carDoesNotExist = findCarByBin(vin).isEmpty();
        if (carDoesNotExist){
            return false;
        }

        if (rentalStorage.getAllRentals().isEmpty()){
            return true;
        }

        List<Rental> rentalsForVin = rentalStorage.getAllRentals().stream()
                .filter(rental -> rental.getCar().getVin().equals(vin))
                .toList();

        for (Rental rental : rentalsForVin) {
            if (isOverlappingDate (startDate, endDate, rental)){
                return false;
            }
        }
        return true;
    }

    private boolean isOverlappingDate(LocalDate startDate, LocalDate endDate, Rental rental){
        boolean isEndDateBeforeRentalStart = endDate.isBefore(rental.getStartDate());
        boolean isStartDateAfterRentalEnd = startDate.isAfter(rental.getEndDate());
        return !(isEndDateBeforeRentalStart || isStartDateAfterRentalEnd);
    }

}

