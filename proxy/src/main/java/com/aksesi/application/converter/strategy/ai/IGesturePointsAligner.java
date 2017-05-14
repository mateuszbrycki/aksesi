package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.element.Gesture;

import java.util.Collection;
import java.util.List;

/**
 * Created by Mateusz Brycki on 13/05/2017.
 */
public interface IGesturePointsAligner {
    List<Gesture.Point> align(List<Gesture.Point> points);
}
