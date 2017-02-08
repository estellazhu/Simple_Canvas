package com.estella;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public enum CanvasModel {
  INSTANCE;
  
  private static final List<CanvasObserver> obs = new ArrayList<>();
  private Point startPoint;
  private Point currentPoint;
  private Color strokeColor = Color.BLACK;
  private int strokeSize = 5;
  private float transparency = 1;
  
  /**
   * Set a start point of a stroke by a mouse-press-drag-release movement
   * @param p - A point representing a location in (x,y) coordinate space of canvas.
   */
  public void setStartPoint(Point p) {
    if (p == null) {
      throw new IllegalArgumentException("Start point cannot be null.");
    }
    startPoint = p;
  }
  
  /**
   * Set in-progress point of a stroke by a mouse-press-drag-release movement
   * @param p - A point representing a location in (x,y) coordinate space of canvas.
   */
  public void setInProgressPoint(Point p) {
    if (p == null) {
      throw new IllegalArgumentException("In-progress point cannot be null.");
    }
    currentPoint = p;
    fireMakeStrokeEvent();
  }
  
  /**
   * Set end point of a stroke by a mouse-press-drag-release movement
   * @param p - A point representing a location in (x,y) coordinate space of canvas.
   */
  public void setEndPoint(Point p) {
    if (p == null) {
      throw new IllegalArgumentException("End point cannot be null.");
    } 
    currentPoint = p;
  }
  
  private void fireMakeStrokeEvent() {
    Stroke stroke = new Stroke.Builder(startPoint, currentPoint).
        color(strokeColor).size(strokeSize).transparency(transparency).build();
    for (CanvasObserver canvasOb : obs) {
      canvasOb.makeStroke(stroke);
    }
    startPoint = currentPoint;
  }
  
  /**
   * Fire clear-canvas event of all observers in this model.
   */
  public void fireClearCanvasEvent() {
    startPoint = null;
    currentPoint = null;
    for (CanvasObserver canvasOb : obs) {
      canvasOb.clearCanvas();
    }
  }
  
  /**
   * Register an observer to the model.
   * @param ob - a canvas observer.
   * @return true - if the observer added.
   */
  public boolean addObserver(CanvasObserver ob) {
    if (ob == null) {
      throw new IllegalArgumentException("observer cannot be null.");
    }
    return obs.add(ob);
  }
  
  /**
   * Unregister an observer to the model.
   * @param ob - a canvas observer.
   * @return true - if the observer removed.
   */
  public boolean removeObserver(CanvasObserver ob) {
    if (ob == null) {
      throw new IllegalArgumentException("observer cannot be null.");
    }
    return obs.remove(ob);
  }
  
  /**
   * For unit test purpose.
   */
  void clearObserverList() {
    obs.clear();
  }
}