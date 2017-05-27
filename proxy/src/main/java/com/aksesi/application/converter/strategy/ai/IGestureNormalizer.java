package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.element.Gesture;

import java.util.List;

/**
 * Created by Mateusz Brycki on 26/05/2017.
 */
public interface IGestureNormalizer {

    double[] normalize(List<Gesture.Point> pointsList);
}
