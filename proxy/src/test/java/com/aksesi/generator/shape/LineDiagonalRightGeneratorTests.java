package com.aksesi.generator.shape;

import com.aksesi.generator.Configuration;
import com.aksesi.generator.Point;
import com.aksesi.generator.shape.testable.LineDiagonalRightGeneratorTestable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by Mateusz Brycki on 21/05/2017.
 */
public class LineDiagonalRightGeneratorTests {
    @Test
    public void testTwoPointsLine() {
        Configuration configuration = new Configuration(
                2, 1, 5, 100, 0
        );

        LineDiagonalRightGeneratorTestable generator = spy(new LineDiagonalRightGeneratorTestable(configuration));

        doReturn(new Point(10f, 10f), new Point(20f, 20f)).when(generator).createRandomPoint();

        assertEquals(new Point(10f, 10f), generator.getPoint(1));
        assertEquals(new Point(20f, 20f), generator.getPoint(2));
    }

    @Test
    public void testThreePointsLine() {
        Configuration configuration = new Configuration(
                3, 1, 5, 100, 0
        );

        LineDiagonalRightGeneratorTestable generator = spy(new LineDiagonalRightGeneratorTestable(configuration));

        //TODO mbrycki find better way for mocking
        doReturn(new Point(10f, 10f), new Point(20f, 20f),
                new Point(10f, 10f), new Point(20f, 20f),
                new Point(10f, 10f), new Point(20f, 20f)).when(generator).createRandomPoint();

        assertEquals(new Point(10f, 10f), generator.getPoint(1));
        assertEquals(new Point(15f, 15f), generator.getPoint(2));
        assertEquals(new Point(20f, 20f), generator.getPoint(3));
    }

    @Test
    public void testFourPointsLine() {
        Configuration configuration = new Configuration(
                4, 1, 5, 100, 0
        );

        LineDiagonalRightGeneratorTestable generator = spy(new LineDiagonalRightGeneratorTestable(configuration));

        doReturn(new Point(10f, 10f), new Point(20f, 20f),
                new Point(10f, 10f), new Point(20f, 20f),
                new Point(10f, 10f), new Point(20f, 20f),
                new Point(10f, 10f), new Point(20f, 20f)).when(generator).createRandomPoint();

        assertEquals(new Point(10f, 10f), generator.getPoint(1));
        assertEquals(new Point(13.333333f, 13.333333f), generator.getPoint(2));
        assertEquals(new Point(16.666666f, 16.666666f), generator.getPoint(3));
        assertEquals(new Point(20f, 20f), generator.getPoint(4));
    }
}
