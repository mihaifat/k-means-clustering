package com.mihai.fat.distance;

public class ManhattanDistanceCalculator implements DistanceCalculator {

    @Override
    public double calculateDistance(Pixel coordinate, Pixel centroid) {
        return Math.abs(coordinate.getColor().getRed() - centroid.getColor().getRed()) +
                Math.abs(coordinate.getColor().getGreen() - centroid.getColor().getGreen()) +
                Math.abs(coordinate.getColor().getBlue() - centroid.getColor().getBlue());
    }
}
