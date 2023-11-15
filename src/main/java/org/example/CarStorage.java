package org.example;

import java.util.List;
import java.util.ArrayList;
//import java.util.Arrays;


public class CarStorage {
    private static CarStorage carStorage;
    private List<Car> cars = new ArrayList<>();


    private CarStorage() {

    }

    public static CarStorage getInstance() {

        if (carStorage == null) {
            carStorage = new CarStorage();
        }

        return carStorage;
    }

    public void addCar(Car samochod) {
        cars.add(samochod);
    }

    public List<Car> getCars() {
        //System.out.println(Arrays.toString(cars.toArray()));
        System.out.println(cars);
        return cars;

    }
/*    public void getAllCars(){
        System.out.println(Arrays.toString(listaSamochod.toArray()));
        listaSamochod.forEach(System.out::println);
        System.out.println(listaSamochod.get(0).getVin());
    }*/


}