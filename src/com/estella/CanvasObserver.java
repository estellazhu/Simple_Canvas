package com.estella;

public interface CanvasObserver {
  /**
   * after a mouse-pressed movement, make a corresponding stroke on canvas.
   * @param stroke - the stroke to be made.
   */
  void makeStroke(Stroke stroke);
  
  /**
   * When we start or reset the canvas, clear all previous strokes on the canvas.
   */
  void clearCanvas();
}
