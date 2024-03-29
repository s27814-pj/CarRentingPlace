package org.example;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
//import java.util.Arrays;

@Component
public class CarStorage {
    private List<Car> cars = new ArrayList<>();


    public void addCar(Car samochod) {
        cars.add(samochod);
    }

    public List<Car> getCars() {
        //System.out.println(Arrays.toString(cars.toArray()));
        //System.out.println(cars);
        return cars;

    }

    public void purgeList(){
        cars.clear();
    }

/*    public void getAllCars(){
        System.out.println(Arrays.toString(listaSamochod.toArray()));
        listaSamochod.forEach(System.out::println);
        System.out.println(listaSamochod.get(0).getVin());
    }*/

    public Optional<Car> findCarByBin(String vin){
        return getCars().stream()
                .filter(car-> car.getVin().equals(vin))
                .findFirst();
    }

}