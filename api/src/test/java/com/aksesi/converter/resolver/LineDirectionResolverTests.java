package com.aksesi.converter.resolver;

import com.aksesi.shape.LineDirection;
import com.aksesi.converter.exception.ResolvingException;
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

        LineDirection direction = resolver.resolve(9.9D);
        assertSame(LineDirection.HORIZONTAL, direction);

        direction = resolver.resolve(171D);
        assertSame(LineDirection.HORIZONTAL, direction);

        direction = resolver.resolve(0D);
        assertSame(LineDirection.HORIZONTAL, direction);

        direction = resolver.resolve(180D);
        assertSame(LineDirection.HORIZONTAL, direction);
    }

    @Test
    public void testDiagonalRightLineTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();
        LineDirection direction = resolver.resolve(79D);
        assertSame(LineDirection.DIAGONAL_RIGHT, direction);

        direction = resolver.resolve(10D);
        assertSame(LineDirection.DIAGONAL_RIGHT, direction);
    }

    @Test
    public void testVerticalLineTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();

        LineDirection direction = resolver.resolve(109D);
        assertSame(LineDirection.VERTICAL, direction);

        direction = resolver.resolve(80D);
        assertSame(LineDirection.VERTICAL, direction);
    }

    @Test
    public void testDiagonalLeftLineTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();

        LineDirection direction = resolver.resolve(169D);
        assertSame(LineDirection.DIAGONAL_LEFT, direction);

        direction = resolver.resolve(110D);
        assertSame(LineDirection.DIAGONAL_LEFT, direction);
    }

    @Test(expected = ResolvingException.class)
    public void testExceptionTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();
        LineDirection direction = resolver.resolve(200D);

    }

    @Test(expected = ResolvingException.class)
    public void testExceptionNegativeAngleTest() throws ResolvingException {
        LineDirectionResolver resolver = new LineDirectionResolver();
        LineDirection direction = resolver.resolve(-200D);
    }
}
