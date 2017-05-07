package com.aksesi.sample;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.jersey.JerseyModule;

/**
 * Created by Mateusz Brycki on 28/04/2017.
 */

public class AksesiSampleModule implements Module {

    @Override
    public void configure(Binder binder) {
        JerseyModule.extend(binder).addResource(AuthenticationController.class);
    }
}
