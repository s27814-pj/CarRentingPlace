package org.example;

public class Main {
    public static void main(String[] args) {

        Car car1 = new Car("marka1", "model1", "123456ASF", type.PREMIUM);
        System.out.println("te");
        CarStorage carStorage1 = CarStorage.getInstance();


        carStorage1.addCar(car1);

        System.out.println(carStorage1.getCars());

        CarStorage carStorage2 = CarStorage.getInstance();

        carStorage2.addCar(car1);

        System.out.println(carStorage2.getCars());


    }
}
