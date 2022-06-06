package com.example.beerlover;

public class BeerViewModel {
    String beerName, details;

    public BeerViewModel(String beerName, String details) {
        this.beerName = beerName;
        this.details = details;
    }

    public String getBeerName() {
        return beerName;
    }
    public String getBeerDetails() {
        return details;
    }

}
