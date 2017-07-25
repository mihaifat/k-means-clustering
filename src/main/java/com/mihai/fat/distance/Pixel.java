package com.mihai.fat.distance;

import java.awt.*;

/**
 * This is a POJO which encapsulates the properties necessary for the K-means clustering.
 */
public class Pixel {

    /**
     * Contains information related to the color representation of the Pixel.
     */
    private Color color;

    /**
     * x coordinate of the pixel.
     */
    private int x;

    /**
     * y coordinate of the pixel.
     */
    private int y;

    /**
     * Centroid associated with the pixel. If this field is null, then the Pixel itself is a centroid.
     */
    private Pixel centroid;

    public Pixel(Color color, int x, int y, Pixel centroid) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.centroid = centroid;
    }

    public Pixel(Pixel pixel) {
        color = pixel.color;
        x = pixel.x;
        y = pixel.y;
        centroid = pixel.centroid;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Pixel getCentroid() {
        return centroid;
    }

    public void setCentroid(Pixel centroid) {
        this.centroid = centroid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pixel pixel = (Pixel) o;

        return color != null ? color.equals(pixel.color) : pixel.color == null;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }

}
