package com.aksesi.configuration.service;

import com.aksesi.application.converter.strategy.ai.IGestureNormalizer;
import com.aksesi.application.converter.strategy.ai.IGestureResizer;
import com.aksesi.application.converter.strategy.ai.ITrainingService;
import com.aksesi.application.element.Gesture;
import com.aksesi.application.shape.Line;
import com.aksesi.generator.*;
import com.aksesi.generator.mutator.DefaultMutator;
import com.aksesi.generator.shape.*;
import com.aksesi.infrastructure.logger.AksesiLogger;
import org.encog.mathutil.Equilateral;
import org.jfree.ui.RefineryUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mateusz Brycki on 26/05/2017.
 */
@Service
public class InitService {

    private final static Integer TRAINING_SET_SIZE = 500;

    private Configuration configuration = new Configuration(200, 3, 1, 400, -400);

    private Map<Integer, IShapeGenerator> generators = new HashMap<>();

    private IGestureNormalizer gestureNormalizer;
    private ITrainingService trainingService;


    private static AksesiLogger log = AksesiLogger.getLogger(InitService.class.getName());

    public InitService(@Autowired IGestureNormalizer gestureNormalizer,
                       @Autowired ITrainingService trainingService) {

        this.gestureNormalizer = gestureNormalizer;
        this.trainingService = trainingService;

        generators.put(1, new LineVerticalGenerator(configuration));
        generators.put(2, new LineHorizontalGenerator(configuration));
        generators.put(3, new LineDiagonalLeftGenerator(configuration));
        generators.put(4, new LineDiagonalRightGenerator(configuration));
        generators.put(5, new CircleGenerator(configuration));
    }

//    @PostConstruct
    public void trainNeuralNetwork() {
        GenerationStrategy generationStrategy = new GenerationStrategy(new DefaultMutator(configuration), configuration);

        List<double[]> gestures = new ArrayList<>();
        List<double[]> results = new ArrayList<>();

        Equilateral eq = new Equilateral(generators.size(), -1, 1);
        for (Map.Entry<Integer, IShapeGenerator> generatorEntry : generators.entrySet()) {

            IShapeGenerator generator = generatorEntry.getValue();
            for (int i = 1; i <= TRAINING_SET_SIZE; i++) {

                List<Point> points = generationStrategy.generate(generator);
                log.debug("Generated a gesture with " + points.size() + " points.");

                List<Gesture.Point> convertedPoints = convertPoints(points);
                log.debug("Converted a gesture with " + points.size() + " points.");

                double[] normalize = gestureNormalizer.normalize(convertedPoints);

                gestures.add(normalize);
                results.add(eq.encode(generatorEntry.getKey() - 1));
            }

        }

        trainingService.train(gestures.toArray(new double[][]{})
                , results.toArray(new double[][]{})
                , generators.size());
    }

    private List<Gesture.Point> convertPoints(List<Point> points) {
        return points.stream()
                .map(p -> new Gesture.Point(p.getX(), p.getX()))
                .collect(Collectors.toList());
    }

}
