package com.aksesi.application.converter.strategy.ai;

import org.encog.neural.networks.BasicNetwork;

/**
 * Created by Mateusz Brycki on 26/05/2017.
 */
public interface INeuralNetwork {
    BasicNetwork getNetwork();
}
