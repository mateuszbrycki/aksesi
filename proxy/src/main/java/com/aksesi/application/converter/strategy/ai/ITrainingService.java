package com.aksesi.application.converter.strategy.ai;

/**
 * Created by Mateusz Brycki on 26/05/2017.
 */
public interface ITrainingService {

    void train(double[][] gestures, double[][] responses, Integer numberOfResponses);
}
