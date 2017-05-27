package com.aksesi.application.converter.strategy.ai.network;

import com.aksesi.application.converter.strategy.ai.INeuralNetwork;
import org.encog.engine.network.activation.ActivationLOG;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 26/05/2017.
 */
@Component
public class NeuralNetwork implements INeuralNetwork {

    private final BasicNetwork network;

    public NeuralNetwork() {
        network = new BasicNetwork();

        network.addLayer(new BasicLayer(null, true, 400)); //input layer
        network.addLayer(new BasicLayer(new ActivationTANH(), true, 300));
        network.addLayer(new BasicLayer(new ActivationTANH(), true, 200));
        network.addLayer(new BasicLayer(new ActivationTANH(), false, 4)); //output layer
        network.getStructure().finalizeStructure();
        network.reset();
    }

    @Override
    public BasicNetwork getNetwork() {
        return network;
    }
}
