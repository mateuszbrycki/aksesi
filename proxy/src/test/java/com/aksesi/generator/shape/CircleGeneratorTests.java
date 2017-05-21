package com.aksesi.generator.shape;

import com.aksesi.generator.Configuration;
import com.aksesi.generator.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by Mateusz Brycki on 21/05/2017.
 */
public class CircleGeneratorTests {

    @Test
    public void twoPointsCircleDrawing() {
        Configuration configuration = new Configuration(
                2, 1, 5, 100, 0
        );

        CircleGenerator generator = spy(new CircleGenerator(configuration));
        doReturn(new Point(0f, 0f)).when(generator).getCircleCenter();
        doReturn(4f).when(generator).getR();

        assertEquals(new Point(-2.3938403f, -3.2046106f), generator.getPoint(1));
        assertEquals(new Point(-1.1347643f, 3.8356628f), generator.getPoint(2));

    }

    @Test
    public void threePointsCircleDrawing() {
        Configuration configuration = new Configuration(
                3, 1, 5, 100, 0
        );

        CircleGenerator generator = spy(new CircleGenerator(configuration));
        doReturn(new Point(0f, 0f)).when(generator).getCircleCenter();
        doReturn(4f).when(generator).getR();

        assertEquals(new Point(3.2567239f, 2.3224447f), generator.getPoint(1));
        assertEquals(new Point(1.3031253f, 3.7817807f), generator.getPoint(2));
        assertEquals(new Point(-1.1347643f, 3.8356628f), generator.getPoint(3));

    }

    @Test
    public void fourPointsCircleDrawing() {
        Configuration configuration = new Configuration(
                3, 1, 5, 100, 0
        );

        CircleGenerator generator = spy(new CircleGenerator(configuration));
        doReturn(new Point(0f, 0f)).when(generator).getCircleCenter();
        doReturn(4f).when(generator).getR();

        assertEquals(new Point(3.2567239f, 2.3224447f), generator.getPoint(1));
        assertEquals(new Point(1.3031253f, 3.7817807f), generator.getPoint(2));
        assertEquals(new Point(-1.1347643f, 3.8356628f), generator.getPoint(3));
        assertEquals(new Point(-3.1509323f, 2.4640667f), generator.getPoint(4));
    }

}
