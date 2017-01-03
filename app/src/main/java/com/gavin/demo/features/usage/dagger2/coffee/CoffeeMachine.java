package com.gavin.demo.features.usage.dagger2.coffee;

import javax.inject.Inject;

public class CoffeeMachine {

    private CoffeeMaker maker;

    @Inject
    public CoffeeMachine(CoffeeMaker maker){
        this.maker = maker;
    }

    public String makeCoffee(){
        return maker.makeCoffee();
    }
}
