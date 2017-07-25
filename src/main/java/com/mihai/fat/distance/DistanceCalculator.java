package com.mihai.fat.distance;

/**
 * This interface should be implemented by those classes which are used to calculate distances.
 */
public interface DistanceCalculator {

    /**
     * The implementation of this method should compute the distance between pixel and a centroid.
     *
     * @param coordinate    pixel from the source image
     * @param centroid      centroid of the cluster
     * @return
     */
    public double calculateDistance(Pixel coordinate, Pixel centroid);
}
