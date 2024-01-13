package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RentalServiceUnitTest {
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

}