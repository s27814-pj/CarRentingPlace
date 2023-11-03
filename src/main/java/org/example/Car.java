package org.example;

public class Car {
    private String make;
    private String model;
    private  String vin;
    private type type;


    public Car(String marka, String model, String vin, type jakosc){
        this.make =marka;
        this.model=model;
        this.vin=vin;
        this.type =jakosc;
    }

    public void setType(type type) {
        this.type = type;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }


    public type getType() {
        return type;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }


}
