package com.mihai.fat.distance;

import com.mihai.fat.exception.KMeansClusteringException;

/**
 * This class is used to obtain the {@link DistanceCalculator}.
 */
public class DistanceCalculatorFactory {

    private static final String CALCULATOR_EUCLIDEAN = "EuclideanDistanceCalculator";
    private static final String CALCULATOR_MANHATTAN = "ManhattanDistanceCalculator";

    /**
     * This method is used to obtain the {@link DistanceCalculator} based on the {@code calculatorName} parameter.
     *
     * @param calculatorName
     * @return
     * @throws KMeansClusteringException
     */
    public DistanceCalculator getDistanceCalculator(String calculatorName) throws KMeansClusteringException {
        if (CALCULATOR_EUCLIDEAN.equals(calculatorName)) {
            return new EuclideanDistanceCalculator();
        } else if (CALCULATOR_MANHATTAN.equals(calculatorName)) {
            return  new ManhattanDistanceCalculator();
        } else {
            throw new KMeansClusteringException();
        }
    }

}
