package com.aksesi.application.converter.strategy.ai.supplier;

import com.aksesi.application.element.Gesture;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by Mateusz Brycki on 14/05/2017.
 */
public class PointsToArraySupplierTests {

    PointsToArraySupplier supplier = new PointsToArraySupplier();

    @Test
    public void testEmptyList() {

        List<Double> list = supplier.apply(Collections.emptyList());

        assertEquals(0, list.size());
    }

    @Test
    public void testOnePointList() {

        List<Gesture.Point> list = Arrays.asList(
            new Gesture.Point(0f, 0f)
        );

        List<Double> result = supplier.apply(list);

        assertEquals(2, result.size());
        assertTrue(result.contains(0d));
    }

    @Test
    public void testTwoPointsList() {

        List<Gesture.Point> list = Arrays.asList(
            new Gesture.Point(0f, 1f),
            new Gesture.Point(2f, 3f)
        );

        List<Double> result = supplier.apply(list);

        assertEquals(4, result.size());
        assertTrue(result.contains(0d));
        assertTrue(result.contains(1d));
        assertTrue(result.contains(2d));
        assertTrue(result.contains(3d));
    }
}
