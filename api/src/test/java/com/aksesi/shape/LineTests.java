package com.aksesi.shape;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LineTests {

    @Test
    public void toStringWithHorizontalDirectionTest() {

        Line line = new Line(LineDirection.HORIZONTAL);
        assertEquals("LineHORIZONTAL", line.toString());
    }

    @Test
    public void toStringWithVerticalDirectionTest() {

        Line line = new Line(LineDirection.VERTICAL);
        assertEquals("LineVERTICAL", line.toString());
    }

    @Test
    public void toStringWithDiagonalRightDirectionTest() {

        Line line = new Line(LineDirection.DIAGONAL_RIGHT);
        assertEquals("LineDIAGONAL_RIGHT", line.toString());
    }

    @Test
    public void toStringWithDiagonalLEFTDirectionTest() {

        Line line = new Line(LineDirection.DIAGONAL_LEFT);
        assertEquals("LineDIAGONAL_LEFT", line.toString());
    }



}
