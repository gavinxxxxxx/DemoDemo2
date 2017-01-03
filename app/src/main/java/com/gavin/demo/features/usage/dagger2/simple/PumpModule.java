package com.gavin.demo.features.usage.dagger2.simple;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PumpModule {
    @Binds
    public abstract Pump providePump(Thermosiphon pump);
}
