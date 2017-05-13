package com.aksesi.application.converter.strategy.ai.supplier;

import com.aksesi.application.converter.strategy.ai.IArraySupplier;
import com.aksesi.application.element.Gesture;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Mateusz Brycki on 14/05/2017.
 */
@Component
public class PointsToArraySupplier implements IArraySupplier {

    @Override
    public List<Double> apply(Collection<Gesture.Point> points) {
        List<Double> result = new ArrayList<>();

        points.forEach(p ->  {

            BigDecimal value = new BigDecimal(p.getX());
            result.add(value.doubleValue());

            value = new BigDecimal(p.getY());
            result.add(value.doubleValue());
        });


        return result;
    }
}
