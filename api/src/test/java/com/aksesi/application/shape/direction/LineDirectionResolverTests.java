package com.aksesi.application.shape.direction;

import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.direction.exception.ResolvingException;
import com.aksesi.application.shape.direction.LineDirectionResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertSame;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LineDirectionResolverTests {

    @Test
    public void testHorizontalLineTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();

        Line.LineDirection direction = resolver.resolve(9.9D);
        assertSame(Line.LineDirection.HORIZONTAL, direction);

        direction = resolver.resolve(171D);
        assertSame(Line.LineDirection.HORIZONTAL, direction);

        direction = resolver.resolve(0D);
        assertSame(Line.LineDirection.HORIZONTAL, direction);

        direction = resolver.resolve(180D);
        assertSame(Line.LineDirection.HORIZONTAL, direction);
    }

    @Test
    public void testDiagonalRightLineTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();
        Line.LineDirection direction = resolver.resolve(79D);
        assertSame(Line.LineDirection.DIAGONAL_RIGHT, direction);

        direction = resolver.resolve(10D);
        assertSame(Line.LineDirection.DIAGONAL_RIGHT, direction);
    }

    @Test
    public void testVerticalLineTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();

        Line.LineDirection direction = resolver.resolve(109D);
        assertSame(Line.LineDirection.VERTICAL, direction);

        direction = resolver.resolve(80D);
        assertSame(Line.LineDirection.VERTICAL, direction);
    }

    @Test
    public void testDiagonalLeftLineTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();

        Line.LineDirection direction = resolver.resolve(169D);
        assertSame(Line.LineDirection.DIAGONAL_LEFT, direction);

        direction = resolver.resolve(110D);
        assertSame(Line.LineDirection.DIAGONAL_LEFT, direction);
    }

    @Test(expected = ResolvingException.class)
    public void testExceptionTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();
        Line.LineDirection direction = resolver.resolve(200D);

    }

    @Test(expected = ResolvingException.class)
    public void testExceptionNegativeAngleTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();
        Line.LineDirection direction = resolver.resolve(-200D);
    }
}
