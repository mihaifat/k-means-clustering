package com.mihai.fat.processor;

import com.mihai.fat.distance.DistanceCalculator;
import com.mihai.fat.distance.DistanceCalculatorFactory;
import com.mihai.fat.distance.Pixel;
import com.mihai.fat.exception.KMeansClusteringException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class is used to obtained a clustered representation of the input {@link BufferedImage}.
 */
public class KMeansClusteringProcessor {

    private String inputFile;
    private List<Pixel> inputElements;
    private List<Pixel> centroids;
    private int numberOfClusters;
    private DistanceCalculator distanceCalculator;

    public KMeansClusteringProcessor(String inputFile, int numberOfClusters) {
        centroids = new ArrayList<>();
        inputElements = new ArrayList<>();
        this.inputFile = inputFile;
        this.numberOfClusters = numberOfClusters;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<Pixel> getInputElements() {
        return inputElements;
    }

    public void setInputElements(List<Pixel> inputElements) {
        this.inputElements = inputElements;
    }

    public List<Pixel> getCentroids() {
        return centroids;
    }

    public void setCentroids(List<Pixel> centroids) {
        this.centroids = centroids;
    }

    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    public void setNumberOfClusters(int numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }

    public DistanceCalculator getDistanceCalculator() {
        return distanceCalculator;
    }

    public void setDistanceCalculator(DistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    /**
     * This method is used to obtain a clustered [@link {@link BufferedImage}.
     *
     * @return  the clustered image
     */
    public BufferedImage process() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(inputFile));

            initializeCentroids();

            readInputElements(bufferedImage);

            DistanceCalculatorFactory factory = new DistanceCalculatorFactory();
            distanceCalculator = factory.getDistanceCalculator("EuclideanDistanceCalculator");

            List<Pixel> oldCentroids;

            do {
                //retain a copy of the old centroids in order to determine if the  color components has changed.
                oldCentroids = centroids.stream().map(pixel -> new Pixel(pixel)).collect(Collectors.toList());
                assignCentroid();
                recalculateCentroids();
            } while (!oldCentroids.equals(centroids));

            //mark pixel with the color associated with the centroid
            for (Pixel pixel : inputElements) {
                bufferedImage.setRGB(pixel.getX(), pixel.getY(), pixel.getCentroid().getColor().getRGB());
            }
            return bufferedImage;
        } catch (IOException | KMeansClusteringException e) {
            return new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB);
        }
    }

    /**
     * This method is used to read the pixels provided in the {@code bufferedImage} parameter and then load the into
     * {@link KMeansClusteringProcessor#inputElements}.
     *
     * @param bufferedImage source image
     */
    private void readInputElements(BufferedImage bufferedImage) {
        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                inputElements.add(new Pixel(color, x, y, null));
            }
        }
    }

    /**
     * This method is used to initialize the centroids. A random color will be assigned to each of the centroid.
     */
    private void initializeCentroids() {
        Random random = new SecureRandom();
        for (int i = 0; i < numberOfClusters; i++) {
            Color color = new Color(random.nextInt());
            centroids.add(new Pixel(color, 0, 0, null));
        }
    }

    /**
     * This method is used to assign to each of the pixels the nearest centroid. In order to compute the nearest
     * centroid, a {@link DistanceCalculator} is used.
     */
    private void assignCentroid() {
        for (Pixel pixel : inputElements) {
            double min = Double.MAX_VALUE;
            for (Pixel centroid : centroids) {
                double distance = distanceCalculator.calculateDistance(pixel, centroid);
                if (min > distance) {
                    min = distance;
                    pixel.setCentroid(centroid);
                }
            }
        }
    }

    /**
     * This method is used to recalculate each of the centroids based on the cluster that each of them are assigned to.
     */
    private void recalculateCentroids() {
        for (Pixel centroid : centroids) {
            List<Pixel> pixelRelatedToCentroid = inputElements.stream().
                    filter(pixel -> pixel.getCentroid().equals(centroid)).collect(Collectors.toList());
            if (pixelRelatedToCentroid.size() != 0) {
                int red = 0;
                int green = 0;
                int blue = 0;
                for (Pixel pixel : pixelRelatedToCentroid) {
                    red += pixel.getColor().getRed();
                    green += pixel.getColor().getGreen();
                    blue += pixel.getColor().getBlue();
                }
                centroid.setColor(new Color(red / pixelRelatedToCentroid.size(),
                        green / pixelRelatedToCentroid.size(), blue / pixelRelatedToCentroid.size()));
            }
        }
    }
}
