package com.aksesi.sample;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

/**
 * Created by Mateusz Brycki on 28/04/2017.
 */
public class AksesiSampleModuleProvider implements BQModuleProvider {

    @Override
    public Module module() {
        return new AksesiSampleModule();
    }

}
