package com.gavin.demo.features.usage.dagger2.simple;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(includes = PumpModule.class)
public class DripCoffeeModule {
    @Provides
    @Singleton
    public Heater provideHeater() {
        return new ElectricHeater();
    }
}