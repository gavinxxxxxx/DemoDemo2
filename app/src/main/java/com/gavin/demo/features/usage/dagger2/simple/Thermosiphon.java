package com.gavin.demo.features.usage.dagger2.simple;

import javax.inject.Inject;

public class Thermosiphon implements Pump {
    private final Heater heater;

    @Inject
    public Thermosiphon(Heater heater) {
        this.heater = heater;
    }

    @Override
    public void pump() {
        if (heater.isHot()) {
            System.out.println("=> => pumping => =>");
        }
    }
}