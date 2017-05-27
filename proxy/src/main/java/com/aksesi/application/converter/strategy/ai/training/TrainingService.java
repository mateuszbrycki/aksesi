package com.aksesi.application.converter.strategy.ai.training;

import com.aksesi.application.converter.strategy.ai.INeuralNetwork;
import com.aksesi.application.converter.strategy.ai.ITrainingService;
import com.aksesi.infrastructure.logger.AksesiLogger;
import org.encog.mathutil.Equilateral;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by Mateusz Brycki on 26/05/2017.
 */
@Service
public class TrainingService implements ITrainingService {

    private static final Integer EPOCH_COUNT = 200;

    private INeuralNetwork neural;

    private static AksesiLogger log = AksesiLogger.getLogger(TrainingService.class.getName());

    public TrainingService(@Autowired INeuralNetwork neural) {
        this.neural = neural;
    }

    @Override
    public void train(double[][] gestures, double[][] responses, Integer numberOfResponses) {

        log.info("Training the neural network with " + gestures.length + " gestures");
        log.info("The number of epoch " + EPOCH_COUNT);

        MLDataSet trainingSet = new BasicMLDataSet(gestures, responses);
        Backpropagation backpropagation = new Backpropagation(neural.getNetwork(), trainingSet, 0.7, 0.3);

        IntStream.range(0, EPOCH_COUNT)
                .forEach((indx) -> backpropagation.iteration());

        backpropagation.finishTraining();

        log.info("Training finished");
    }
}
