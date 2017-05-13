package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.element.Gesture;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Mateusz Brycki on 14/05/2017.
 */
public interface IArraySupplier extends Function<Collection<Gesture.Point>, List<Double>> {

}
