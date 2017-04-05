package com.aksesi.application.shape.direction;

import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.direction.exception.ResolvingException;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public interface IDirectionResolver {

    Line.LineDirection resolve(Double value) throws ResolvingException;
}
