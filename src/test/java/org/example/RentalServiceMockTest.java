package org.example;
//ctrl+P

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalServiceMockTest {
    @Mock
    private CarStorage carStorage;
    @Mock
    private RentalStorage rentalStorage;
    @InjectMocks
    private RentalService rentalService;


    @Test
    void shouldFindCar(){
        //GIVEN
        Car car = new Car("make","model","some-vin",type.PREMIUM);
        carStorage.addCar(car);
        //WHEN
        Optional<Car> carByVin = carStorage.findCarByBin("some-vin");

        //THEN
        assertThat(carByVin).isPresent();
        //assertThat(carByVin.get().getMake()).isEqualTo("ran");

    }

    @Test
    void shouldNotFindCar(){
        //GIVEN

        //WHEN
        Optional<Car> carByVin = carStorage.findCarByBin("random-vin");

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
        Car car = new Car("make","model","ssome-vin",type.PREMIUM);
        when(carStorage.findCarByBin(anyString())).thenReturn(Optional.of(car));
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

    @ParameterizedTest
    @MethodSource("inputData")
    void shouldBeRentalDateAvailable(LocalDate startDate, LocalDate endDate){
        Car car = new Car("make","model","abc",type.PREMIUM);
        carStorage.addCar(car);
        User user = new User(1);
        Rental rental = new Rental(user, car, LocalDate.of(2023,11,20), LocalDate.of(2023,11,30));
        rentalStorage.addRental(rental);
        boolean abc = rentalService.isAvailable(
                car.getVin(),
                startDate,
                endDate
        );

        assertThat(abc).isTrue();
    }

    public static Stream<Arguments> inputData(){
        return Stream.of(
                Arguments.of(LocalDate.of(2023,11,10),LocalDate.of(2023,11,19)),
                Arguments.of(LocalDate.of(2023,12,1),LocalDate.of(2023,12,5))
        );

    }



    @ParameterizedTest
    @MethodSource("inputDataForFalse")
    void shouldNotBeRentalDateAvailable(LocalDate startDate, LocalDate endDate){
        Car car = new Car("make","model","abc",type.PREMIUM);
        carStorage.addCar(car);
        User user = new User(1);
        Rental rental = new Rental(user, car, LocalDate.of(2023,11,20), LocalDate.of(2023,11,30));
        rentalStorage.addRental(rental);
        boolean abc = rentalService.isAvailable(
                car.getVin(),
                startDate,
                endDate
        );

        assertThat(abc).isFalse();
    }



    public static Stream<Arguments> inputDataForFalse(){
        return Stream.of(
                Arguments.of(LocalDate.of(2023,11,10),LocalDate.of(2023,11,20)),
                Arguments.of(LocalDate.of(2023,11,30),LocalDate.of(2023,12,5)),
                Arguments.of(LocalDate.of(2023,11,10),LocalDate.of(2023,12,5)),
                Arguments.of(LocalDate.of(2023,11,25),LocalDate.of(2023,12,5))
        );

    }



}