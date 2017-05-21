package com.aksesi.generator.shape.testable;

import com.aksesi.generator.Configuration;
import com.aksesi.generator.Point;
import com.aksesi.generator.shape.LineHorizontalGenerator;

/**
 * Created by Mateusz Brycki on 21/05/2017.
 */
public class LineHorizontalGeneratorTestable extends LineHorizontalGenerator {

    public LineHorizontalGeneratorTestable(Configuration configuration) {
        super(configuration);
    }


    @Override
    public Point getPoint(Integer i) {
        this.startPoint = createRandomPoint();
        this.endPoint = createRandomPoint();

        return super.getPoint(i);
    }
}
