package com.gavin.demo.features.usage.dagger2.coffee;

import dagger.Module;
import dagger.Provides;

@Module
public class SimpleModule {

    @Provides
    Cooker provideCooker(){
        return new Cooker("James","Espresso");
    }
}