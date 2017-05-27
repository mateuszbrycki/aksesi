package com.aksesi.application.converter.strategy.ai;

import com.aksesi.application.converter.exception.ConversionException;
import com.aksesi.application.converter.strategy.IConversionStrategy;
import com.aksesi.application.converter.strategy.ai.normalizer.GestureNormalizer;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.encrypter.PasswordEncrypter;
import com.aksesi.application.shape.Line;
import com.aksesi.application.shape.Shape;
import com.aksesi.infrastructure.logger.AksesiLogger;
import org.encog.mathutil.Equilateral;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.util.arrayutil.NormalizeArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by Mateusz Brycki on 13/05/2017.
 */
@Component
public class AIConversionStrategy implements IConversionStrategy {

    private static AksesiLogger log = AksesiLogger.getLogger(AIConversionStrategy.class.getName());

    private IGestureNormalizer gestureNormalizer;
    private INeuralNetwork neuralNetwork;

    public AIConversionStrategy(@Autowired IGestureNormalizer normalizer,
                                @Autowired INeuralNetwork neuralNetwork) {
        this.gestureNormalizer = normalizer;
        this.neuralNetwork = neuralNetwork;
    }

    @Override
    public Shape convert(Gesture element) throws ConversionException {

        List<Gesture.Point> pointsList = element.points();

        if(pointsList.size() < 2) {
            throw new ConversionException("Gesture should be more than 2 points long.");
        }

        log.info("Converting a gesture with AIConversionStrategy");
        log.debug("Number of points before normalization " + pointsList.size());

        double[] normalizedArray = gestureNormalizer.normalize(pointsList);

        log.debug("Number of points after normalization " + normalizedArray.length);

       /* MLData data = new BasicMLData(normalizedArray);
        MLData result = neuralNetwork.getNetwork().compute(data);

        Equilateral eq = new Equilateral(5, -1, 1);
        Integer reult = eq.decode(result.getData());

        log.info("The neural network responded with " + reult);*/

        return new Line(Line.LineDirection.DIAGONAL_LEFT);
    }
}
