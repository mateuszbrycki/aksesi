package com.aksesi.converter.resolver;

import com.aksesi.shape.LineDirection;
import com.aksesi.converter.exception.ResolvingException;

/**
 * Created by Mateusz Brycki on 15/03/2017.
 */
public interface IDirectionResolver {

    LineDirection resolve(Double value) throws ResolvingException;
}
