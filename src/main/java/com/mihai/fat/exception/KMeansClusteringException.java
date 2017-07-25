package com.mihai.fat.exception;

/**
 * This {@link Exception} should be used onto the implementation of the K-Means clustering algorithm.
 */
public class KMeansClusteringException extends Exception {

    public KMeansClusteringException() {
        super();
    }

    public KMeansClusteringException(String message) {
        super(message);
    }

    public KMeansClusteringException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public KMeansClusteringException(Throwable throwable) {
        super(throwable);
    }

}
