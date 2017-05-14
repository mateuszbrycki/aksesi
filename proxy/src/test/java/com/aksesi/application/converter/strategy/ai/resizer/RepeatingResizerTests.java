package com.aksesi.application.converter.strategy.ai.resizer;

import com.aksesi.application.element.Gesture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static junit.framework.TestCase.assertSame;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mateusz Brycki on 14/05/2017.
 */
public class RepeatingResizerTests {

    private RepeatingResizer repeatingResizer = new RepeatingResizer();

    @Before
    public void setupFields() {
        ReflectionTestUtils.setField(repeatingResizer, "GESTURE_SIZE", 200);
    }

    @Test
    public void testEmptyCollection() {

        Collection<Gesture.Point> result = repeatingResizer.resize(Collections.emptyList());

        assertSame(Collections.emptyList(), result);
    }

    @Test
    public void testOneElementCollection() {

        List<Gesture.Point> points = Arrays.asList(new Gesture.Point(0f, 0f));

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertSame(points, result);
    }

    @Test
    public void testTwoElementsCollection() {

        List<Gesture.Point> points = Arrays.asList(
                new Gesture.Point(0f, 0f),
                new Gesture.Point(200f, 200f)
        );

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(200, result.size());
        assertTrue(result.contains(new Gesture.Point(0f, 0f)));
        assertTrue(result.contains(new Gesture.Point(200f, 200f)));
    }

    @Test
    public void testThreeElementsCollection() {

        List<Gesture.Point> points = Arrays.asList(
                new Gesture.Point(0f, 0f),
                new Gesture.Point(10f, 10f),
                new Gesture.Point(20f, 20f)
        );

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(200, result.size());
        assertTrue(result.contains(new Gesture.Point(0f, 0f)));
        assertTrue(result.contains(new Gesture.Point(10f, 10f)));
        assertTrue(result.contains(new Gesture.Point(20f, 20f)));
    }

    @Test
    public void testThreeIrregularElementsCollection() {

        List<Gesture.Point> points = Arrays.asList(
                new Gesture.Point(-5f, -5f),
                new Gesture.Point(10f, 20f),
                new Gesture.Point(100f, 20f)
        );

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(200, result.size());
        assertTrue(result.contains(new Gesture.Point(-5f, -5f)));
        assertTrue(result.contains(new Gesture.Point(10f, 20f)));
        assertTrue(result.contains(new Gesture.Point(100f, 20f)));
    }

    @Test
    public void testMoreThanHalfOfCollection() {

        ReflectionTestUtils.setField(repeatingResizer, "GESTURE_SIZE", 10);

        List<Gesture.Point> points = Arrays.asList(
                new Gesture.Point(1f, 1f),
                new Gesture.Point(2f, 2f),
                new Gesture.Point(15f, 20f),
                new Gesture.Point(13f, 23f),
                new Gesture.Point(20f, 30f),
                new Gesture.Point(100f, 100f)
        );

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(10, result.size());
        assertTrue(result.contains(new Gesture.Point(1f, 1f)));
        assertTrue(result.contains(new Gesture.Point(2f, 2f)));
        assertTrue(result.contains(new Gesture.Point(15f, 20f)));
        assertTrue(result.contains(new Gesture.Point(13f, 23f)));
        assertTrue(result.contains(new Gesture.Point(20f, 30f)));
        assertTrue(result.contains(new Gesture.Point(100f, 100f)));
    }

    @Test
    public void testHalfOfCollection() {

        ReflectionTestUtils.setField(repeatingResizer, "GESTURE_SIZE", 10);

        List<Gesture.Point> points = Arrays.asList(
                new Gesture.Point(1f, 1f),
                new Gesture.Point(3f, 3f),
                new Gesture.Point(5f, 5f),
                new Gesture.Point(7f, 7f),
                new Gesture.Point(9f, 9f)
        );

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(10, result.size());
        assertTrue(result.contains(new Gesture.Point(1f, 1f)));
        assertTrue(result.contains(new Gesture.Point(2f, 2f))); //generated
        assertTrue(result.contains(new Gesture.Point(3f, 3f)));
        assertTrue(result.contains(new Gesture.Point(4f, 4f))); //generated
        assertTrue(result.contains(new Gesture.Point(5f, 5f)));
        assertTrue(result.contains(new Gesture.Point(6f, 6f))); //generated
        assertTrue(result.contains(new Gesture.Point(7f, 7f)));
        assertTrue(result.contains(new Gesture.Point(7.6666665f, 7.6666665f))); //generated
        assertTrue(result.contains(new Gesture.Point(8.333333f, 8.333333f))); //generated
        assertTrue(result.contains(new Gesture.Point(9f, 9f)));
    }

    @Test
    public void testRemovingDuplicatesFromGesture() {
        ReflectionTestUtils.setField(repeatingResizer, "GESTURE_SIZE", 2);

        List<Gesture.Point> points = new ArrayList<>(Arrays.asList(
                new Gesture.Point(1f, 1f),
                new Gesture.Point(2f, 2f),
                new Gesture.Point(2f, 2f) //duplicate -> should be deletes
        ));

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Gesture.Point(1f, 1f)));
        assertTrue(result.contains(new Gesture.Point(2f, 2f)));
    }


    @Test
    public void testStopConditionForRemovingDuplicates() {
        ReflectionTestUtils.setField(repeatingResizer, "GESTURE_SIZE", 3);

        List<Gesture.Point> points = new ArrayList<>(Arrays.asList(
                new Gesture.Point(1f, 1f),
                new Gesture.Point(1f, 1f), //first duplicate -> should be removed
                new Gesture.Point(2f, 2f),
                new Gesture.Point(2f, 2f)
        ));

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(3, result.size());
        assertTrue(result.contains(new Gesture.Point(1f, 1f)));
        assertTrue(result.contains(new Gesture.Point(2f, 2f)));
    }

    @Test
    public void testRemovingNeighboringPoints() {
        ReflectionTestUtils.setField(repeatingResizer, "GESTURE_SIZE", 2);

        List<Gesture.Point> points = new ArrayList<>(Arrays.asList(
                new Gesture.Point(1f, 1f),
                new Gesture.Point(2f, 2f), //neighbour (1,1 difference) -> should be removed
                new Gesture.Point(5f, 5f),
                new Gesture.Point(6f, 6f) //neighbour (1,1 difference) -> should be removed
        ));

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(2, result.size());
        assertTrue(result.contains(new Gesture.Point(1f, 1f)));
        assertTrue(result.contains(new Gesture.Point(5f, 5f)));
    }


    @Test
    public void testRemovingDuplicatesAndNeighboringPoints() {
        ReflectionTestUtils.setField(repeatingResizer, "GESTURE_SIZE", 3);

        List<Gesture.Point> points = new ArrayList<>(Arrays.asList(
                new Gesture.Point(1f, 1f),
                new Gesture.Point(1f, 1f), //first duplicate -> should be removed
                new Gesture.Point(2f, 2f),
                new Gesture.Point(3f, 3f), //neighbour (1,1 difference) -> should be removed
                new Gesture.Point(5f, 5f),
                new Gesture.Point(5f, 5f), //first duplicate -> should be removed
                new Gesture.Point(6f, 6f)  //neighbour (1,1 difference) -> should be removed
        ));

        Collection<Gesture.Point> result = repeatingResizer.resize(points);

        assertEquals(3, result.size());
        assertTrue(result.contains(new Gesture.Point(1f, 1f)));
        assertTrue(result.contains(new Gesture.Point(3f, 3f)));
        assertTrue(result.contains(new Gesture.Point(5f, 5f)));
    }
}
