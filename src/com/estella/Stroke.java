package com.estella;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;

public class Stroke {
  private final Point startPoint;
  private final Point endPoint;
  private final Color color;
  private final int size;
  private final float transparency;
  private Shape shape;
  
  public static class Builder {
    private final Point startPoint;
    private final Point endPoint;
    private Color color = Color.BLACK;
    private int size = 5;
    private float transparency = (float) 1.0;
    
    /**
     * Builder Pattern to deal with many Stroke constructor parameters.
     * @param sp - start point of a stroke
     * @param ep - end point of a stroke.
     * @throws IllegalArgumentException - points cannot be null.
     */
    public Builder(Point sp, Point ep) {
      if (sp == null || ep == null) {
        throw new IllegalArgumentException("point cannot be null.");
      }
      startPoint = sp;
      endPoint = ep;
    }
    
    /**
     * Builder Pattern to deal with many Stroke constructor parameters.
     * @param c - Color.
     * @throws IllegalArgumentException - color cannot be null.
     * @return Builder object
     */
    public Builder color(Color c) {
      if (c == null) {
        throw new IllegalArgumentException("color cannot be null.");
      }
      color = c;
      return this;
    }
    
    /**
     * Builder Pattern to deal with many Stroke constructor parameters.
     * @param sz - stroke size.
     * @throws IllegalArgumentException - size must be positive.
     * @return Builder object
     */
    public Builder size(int sz) {
      if (sz <= 0) {
        throw new IllegalArgumentException("stroke size must be positive.");
      }
      size = sz;
      return this;
    }
    
    /**
     * Builder Pattern to deal with many Stroke constructor parameters.
     * @param tr - stroke transparency.
     * @throws IllegalArgumentException - transparency should belong to (0,1].
     * @return Builder object
     */
    public Builder transparency(float tr) {
      if (tr <= 0 || tr > 1) {
        throw new IllegalArgumentException("transparency should belong to (0,1].");
      }
      transparency = tr;
      return this;
    }
    
    /**
     * Builder Pattern to deal with many Stroke constructor parameters.
     * @return Stroke.
     */
    public Stroke build() {
      Stroke stroke = new Stroke(this);
      return stroke;
    }
  }
  
  private Stroke(Builder bd) {
    this.startPoint = bd.startPoint;
    this.endPoint = bd.endPoint;
    this.color = bd.color;
    this.size = bd.size;
    this.transparency = bd.transparency;
  }
  
  /**
   * Get start point of the stroke
   * @return start point.
   */
  public Point getStartPoint() {
    return startPoint;
  }
  
  /**
   * Get end point of the stroke
   * @return end point.
   */
  public Point getEndPoint() {
    return endPoint;
  }
  
  /**
   * Get color of the stroke
   * @return stroke color.
   */
  public Color getColor() {
    return color;
  }
  
  /**
   * Get size of the stroke
   * @return stroke size.
   */
  public int getSize() {
    return size;
  }
  
  /**
   * Get transparency of the stroke
   * @return stroke transparency.
   */
  public float getTransparency() {
    return transparency;
  }
  
  /**
   * set shape of the stroke
   * @param sp - Shape object of the stroke.
   */
  public void setShape(Shape sp) {
    shape = sp;
  }
  
  /**
   * Get shape of the stroke
   * @return stroke shape.
   */
  public Shape getShape() {
    return shape;
  }
  
  /**
   * Returns a string representation of the stroke.
   * @return a string representation of the stroke.
   */
  @Override
  public String toString() {
    return "start at " + startPoint + ", end at " + endPoint + "\n"
        + "color is " + color + ", size is " + size + ", transparency is" + transparency + "\n";
  }
}