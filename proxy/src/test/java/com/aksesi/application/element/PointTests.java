package com.aksesi.application.element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class PointTests {

    private final Long X = 100L;
    private final Long Y = 150L;

    @Test
    public void getPointPointsTest() {
        Gesture.Point point = new Gesture.Point(X, Y);

        assertEquals(X, point.getX());
        assertEquals(Y, point.getY());
    }

}
