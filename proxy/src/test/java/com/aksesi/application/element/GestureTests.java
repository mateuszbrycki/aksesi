package com.aksesi.application.element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class GestureTests {

    @Test
    public void emptyPointsTest() {
        List<Gesture.Point> points = Arrays.asList();
        Gesture element = new Gesture(points);

        assertEquals(points.size(), element.points().size());
        assertSame(points, element.points());
    }

    @Test
    public void getPointsTest() {
        List<Gesture.Point> points = Arrays.asList(
                new Gesture.Point(1L,1L),
                new Gesture.Point(2L,2L)
        );
        Gesture element = new Gesture(points);

        assertEquals(points.size(), element.points().size());
        assertSame(points, element.points());
    }


}
