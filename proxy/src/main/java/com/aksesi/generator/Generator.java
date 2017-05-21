package com.aksesi.generator;

import com.aksesi.generator.mutator.DefaultMutator;
import com.aksesi.generator.shape.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Brycki on 21/05/2017.
 */
public class Generator {

    private static Configuration configuration = new Configuration(200, 1, 1, 400, -400);

    private static List<IShapeGenerator> generators = new ArrayList<>();

    static {
        generators.add(new LineVerticalGenerator(configuration));
        generators.add(new LineHorizontalGenerator(configuration));
        generators.add(new LineDiagonalLeftGenerator(configuration));
        generators.add(new LineDiagonalRightGenerator(configuration));
        generators.add(new CircleGenerator(configuration));
    }

    private static FileSaver fileSaver = new FileSaver();

    public static void main(String[] args) {

        GenerationStrategy generationStrategy = new GenerationStrategy(new DefaultMutator(configuration), configuration);

        for (IShapeGenerator generator : generators) {

            List<Point> points = generationStrategy.generate(generator);
            System.out.println("Generated points " + points.size());
            try {
                fileSaver.save(points, generator.getClass().getName());
                System.out.println("Saved points to " + generator.getClass().getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ChartFrame chart = new ChartFrame("Gesture chart", generator.getClass().getCanonicalName(), points);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible(true);
        }
    }

}

class ChartFrame extends ApplicationFrame {
    public ChartFrame(String applicationTitle, String chartTitle, List<Point> points) {
        super(applicationTitle);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "X", "Y",
                createDataset(points),
                PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = lineChart.getXYPlot();

        XYDotRenderer renderer = new XYDotRenderer();
        renderer.setDotHeight(3);
        renderer.setDotWidth(3);

        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 560));

        setContentPane(chartPanel);

    }

    private XYDataset createDataset(List<Point> points) {

        XYSeries series = new XYSeries("x");

        for (Point point : points) {
            series.add(point.getX().doubleValue(), point.getY().doubleValue());
        }

        XYDataset dataset = new XYSeriesCollection(series);
        return dataset;
    }

}
