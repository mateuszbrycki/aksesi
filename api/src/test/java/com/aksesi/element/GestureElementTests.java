package com.aksesi.element;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;

/**
 * Created by Mateusz Brycki on 14/03/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class GestureElementTests {

    @Test
    public void emptyPointsTest() {
        List<Point> points = Arrays.asList();
        GestureElement element = new GestureElement(points);

        assertEquals(points.size(), element.getPoints().size());
        assertSame(points, element.getPoints());
    }

    @Test
    public void getPointsTest() {
        List<Point> points = Arrays.asList(
                new Point(1L,1L),
                new Point(2L,2L)
        );
        GestureElement element = new GestureElement(points);

        assertEquals(points.size(), element.getPoints().size());
        assertSame(points, element.getPoints());
    }


}
