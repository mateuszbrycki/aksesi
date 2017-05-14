package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.element.Gesture;

import java.util.List;

/**
 * Created by Mateusz Brycki on 14/05/2017.
 */
public interface IGestureResizer {

    List<Gesture.Point> resize(List<Gesture.Point> points);
}
