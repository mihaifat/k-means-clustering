package com.mihai.fat.distance;

public class EuclideanDistanceCalculator implements DistanceCalculator {

    public double calculateDistance(Pixel coordinate, Pixel centroid) {
        double result = Math.pow(coordinate.getColor().getRed() - centroid.getColor().getRed(), 2)
                + Math.pow(coordinate.getColor().getGreen() - centroid.getColor().getGreen(), 2)
                + Math.pow(coordinate.getColor().getBlue() - centroid.getColor().getBlue(), 2);
        return Math.sqrt(result);
    }

}
