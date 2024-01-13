package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@SpringBootTest
class RentalServiceITTest {



    @MockBean
    private CarStorage carStorage;
    @MockBean
    private RentalStorage rentalStorage;
    @Autowired
    private RentalService rentalService;

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
    void shouldFindCar(){

    }
}