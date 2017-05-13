package com.aksesi.application.converter.strategy.ai.modifier;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static com.aksesi.application.element.Gesture.Point;

/**
 * Created by Mateusz Brycki on 13/05/2017.
 */

public class CentricPointsModifierTests {

    private CentricPointsModifier modifier = new CentricPointsModifier();

    @Test
    public void testModifyEmptyList() {

        Collection<Point> result = modifier.modify(Collections.emptyList());

        assertEquals(Collections.emptyList(), result);

    }

    @Test
    public void testOneElementList() {

        Collection<Point> points = Arrays.asList(
                new Point(1f, 1f)
        );

        Collection<Point> result = modifier.modify(points);

        assertEquals(1, result.size());
        assertTrue(result.contains(new Point(0.f, 0.f)));
    }

    @Test
    public void testThreeElementsList() {

        Collection<Point> points = Arrays.asList(
                new Point(10f, 8f),
                new Point(13.5f, 5.5f),
                new Point(17f, 3f)
        );

        Collection<Point> result = modifier.modify(points);

        assertEquals(3, result.size());
        assertTrue(result.contains(new Point(0f, 0f)));
        assertTrue(result.contains(new Point(3.5f, -2.5f)));
        assertTrue(result.contains(new Point(-3.5f, 2.5f)));
    }

    @Test
    public void testSquarElementsList() {

        Collection<Point> points = Arrays.asList(
            new Point(-7f, -7f),
            new Point(-8f, -7f),
            new Point(-9f, -7f),
            new Point(-9f, -8f),
            new Point(-9f, -9f),
            new Point(-8f, -9f),
            new Point(-7f, -9f),
            new Point(-7f, -8f)
        );

        Collection<Point> result = modifier.modify(points);

        assertEquals(8, result.size());

        assertTrue(result.contains(new Point(0f, 1f)));
        assertTrue(result.contains(new Point(0f, -1f)));
        assertTrue(result.contains(new Point(-1f, 0f)));
        assertTrue(result.contains(new Point(1f, 0f)));
        assertTrue(result.contains(new Point(1f, 1f)));
        assertTrue(result.contains(new Point(1f, -1f)));
        assertTrue(result.contains(new Point(-1f, -1f)));
        assertTrue(result.contains(new Point(-1f, 1f)));
    }

    @Test
    public void testCircleElementsList() {

        Collection<Point> points = Arrays.asList(
                new Point(11f, 5f),
                new Point(12f, 4f),
                new Point(13f, 3f),
                new Point(14f, 3f),
                new Point(15f, 3f),
                new Point(16f, 3f),
                new Point(17f, 3f),
                new Point(18f, 4f),
                new Point(19f, 5f),
                new Point(19f, 6f),
                new Point(19f, 7f),
                new Point(19f, 8f),
                new Point(19f, 9f),
                new Point(18f, 10f),
                new Point(17f, 11f),
                new Point(16f, 11f),
                new Point(15f, 11f),
                new Point(14f, 11f),
                new Point(13f, 11f),
                new Point(12f, 10f),
                new Point(11f, 9f),
                new Point(11f, 8f),
                new Point(11f, 7f),
                new Point(11f, 6f)
        );

        Collection<Point> result = modifier.modify(points);

        assertEquals(24, result.size());

        assertTrue(result.contains(new Point(0f, -4f)));
        assertTrue(result.contains(new Point(1f, -4f)));
        assertTrue(result.contains(new Point(2f, -4f)));
        assertTrue(result.contains(new Point(3f, -3f)));
        assertTrue(result.contains(new Point(4f, -2f)));
        assertTrue(result.contains(new Point(4f, -1f)));
        assertTrue(result.contains(new Point(4f, 0f)));
        assertTrue(result.contains(new Point(4f, 1f)));
        assertTrue(result.contains(new Point(4f, 2f)));
        assertTrue(result.contains(new Point(3f, 3f)));
        assertTrue(result.contains(new Point(2f, 4f)));
        assertTrue(result.contains(new Point(1f, 4f)));
        assertTrue(result.contains(new Point(0f, 4f)));
        assertTrue(result.contains(new Point(-1f, 4f)));
        assertTrue(result.contains(new Point(-2f, 4f)));
        assertTrue(result.contains(new Point(-3f, 3f)));
        assertTrue(result.contains(new Point(-4f, 2f)));
        assertTrue(result.contains(new Point(-4f, 1f)));
        assertTrue(result.contains(new Point(-4f, 0f)));
        assertTrue(result.contains(new Point(-4f, -1f)));
        assertTrue(result.contains(new Point(-4f, -2f)));
        assertTrue(result.contains(new Point(-3f, -3f)));
        assertTrue(result.contains(new Point(-2f, -4f)));
        assertTrue(result.contains(new Point(-1f, -4f)));

    }



}
