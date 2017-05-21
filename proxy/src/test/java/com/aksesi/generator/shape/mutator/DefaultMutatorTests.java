package com.aksesi.generator.shape.mutator;

import com.aksesi.generator.Configuration;
import com.aksesi.generator.IMutator;
import com.aksesi.generator.Point;
import com.aksesi.generator.mutator.DefaultMutator;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Mateusz Brycki on 21/05/2017.
 */
public class DefaultMutatorTests {

    Configuration configuration = new Configuration(0, 5, 10, 100, 0);
    IMutator mutator = new DefaultMutator(configuration);

    @Test
    public void testWithPositiveConfiguration() {
        Point mutatedPoint = mutator.mutate(new Point(1f, 1f));

        assertNotEquals(new Point(1f, 1f), mutatedPoint);
        assertTrue(mutatedPoint.getX() <= 6f); //1f + 5f (config)
        assertTrue(mutatedPoint.getY() <= 6f); //1f + 5f (config)
    }

    @Test
    public void testWithNegativeConfiguration() {
        Point mutatedPoint = mutator.mutate(new Point(-1f, -1f));

        assertNotEquals(new Point(1f, 1f), mutatedPoint);
        assertTrue(mutatedPoint.getX() <= 4f); //-1f + 5f (config)
        assertTrue(mutatedPoint.getY() <= 4f); //-1f + 5f (config)
        assertTrue(mutatedPoint.getX() > -6f); //-1f + 5f (config)
        assertTrue(mutatedPoint.getY() > -6f); //-1f + 5f (config)
    }
}
