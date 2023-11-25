package org.example;
//ctrl+P

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;



class RentalServiceTest {
    private CarStorage carStorage;
    private RentalStorage rentalStorage;
    private RentalService rentalService;


    @BeforeEach
    void startUp(){
        rentalStorage = RentalStorage.getInstance();
        carStorage = CarStorage.getInstance();
        rentalService = new RentalService(carStorage, rentalStorage);
    }

    @AfterEach
    void cleanup(){
        carStorage.purgeList();
    }
    @Test
    void shouldFindCar(){
        //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
        //WHEN
        Optional<Car> carByVin = rentalService.findCarByBin("some-vin");

        //THEN
        assertThat(carByVin).isPresent();
        //assertThat(carByVin.get().getMake()).isEqualTo("ran");

    }

    @Test
    void shouldNotFindCar(){
        //GIVEN

        //WHEN
        Optional<Car> carByVin = rentalService.findCarByBin("random-vin");

        //THEN
        assertThat(carByVin).isEmpty();
    }

    @Test
    void shouldNotBeAvailable(){
        //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
        User user = new User(1);
        LocalDate startRentalDate = LocalDate.now();
        LocalDate endRentalDate = LocalDate.now().plusDays(10);
        Rental rental = new Rental(user, car, startRentalDate, endRentalDate);
        rentalStorage.addRental(rental);
        //WHEN
        Boolean isAvailable = rentalService.isAvailable("some-vin",LocalDate.now().plusDays(2),LocalDate.now().plusDays(5));
        //THEN
        assertThat(isAvailable).isFalse();
    }

    @Test
    void shouldBeAvailable(){
        //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
        User user = new User(1);
        LocalDate startRentalDate = LocalDate.now();
        LocalDate endRentalDate = LocalDate.now().plusDays(10);
        Rental rental = new Rental(user, car, startRentalDate, endRentalDate);
        rentalStorage.addRental(rental);
        //WHEN
        Boolean isAvailable = rentalService.isAvailable("some-vin",LocalDate.now().plusDays(12),LocalDate.now().plusDays(25));
        //THEN
        assertThat(isAvailable).isTrue();
    }

    @Test
    void shouldBeAvailableBorderValue(){
        //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
        User user = new User(1);
        LocalDate startRentalDate = LocalDate.now();
        LocalDate endRentalDate = LocalDate.now().plusDays(10);
        Rental rental = new Rental(user, car, startRentalDate, endRentalDate);
        rentalStorage.addRental(rental);
        //WHEN
        Boolean isAvailable = rentalService.isAvailable("some-vin",LocalDate.now().plusDays(11),LocalDate.now().plusDays(25));
        //THEN
        assertThat(isAvailable).isTrue();
    }


    @Test
    void shouldGiveProperPrice(){
        //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
        //WHEN
        double value = rentalService.estimatePrice("some-vin", LocalDate.now(), LocalDate.now().plusDays(10));
        //THEN
        assertThat(value).isEqualTo(15000.0);
    }
    @Test
    void shouldRentRental(){
    //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
    //WHEN
        Rental rent = rentalService.rent("some-vin",1,LocalDate.now(),LocalDate.now().plusDays(10));
    //THEN
       assertThat(rent).isNotNull();
    }
@Test
    void shouldNotBeAvailableStartLowerThanAllEndHigherThanAll(){
        //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
        User user = new User(1);
        LocalDate startRentalDate = LocalDate.now();
        LocalDate endRentalDate = LocalDate.now().plusDays(10);
        Rental rental = new Rental(user, car, startRentalDate, endRentalDate);
        rentalStorage.addRental(rental);
        //WHEN
        Boolean isAvailable = rentalService.isAvailable("some-vin",LocalDate.now().plusDays(-11),LocalDate.now().plusDays(25));
        //THEN
        assertThat(isAvailable).isFalse();
    }

    @Test
    void shouldBeAvailableAllDatesBefore(){
        //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
        User user = new User(1);
        LocalDate startRentalDate = LocalDate.now();
        LocalDate endRentalDate = LocalDate.now().plusDays(10);
        Rental rental = new Rental(user, car, startRentalDate, endRentalDate);
        rentalStorage.addRental(rental);
        //WHEN
        Boolean isAvailable = rentalService.isAvailable("some-vin",LocalDate.now().plusDays(-11),LocalDate.now().plusDays(-5));
        //THEN
        assertThat(isAvailable).isTrue();
    }

}