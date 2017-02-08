package com.estella;

/**
 * @author      Estella Zhu
 * @version     0.0.1
 * @since       2016-10-5
 *
 * Start the canvas application, and create three ­windows where users can paint
 * on a canvas using the mouse. All windows should see changes made to the canvas
 * in any other window. Singleton Pattern is applied to CanvasModel, Observer Pattern
 * is applied as the architecture, and Builder Pattern is applied in Stroke.
 */
public class CanvasApp {
  private void startGame() {
    CanvasModel model = CanvasModel.INSTANCE;
    CanvasView view1 = new CanvasView(model);
    CanvasView view2 = new CanvasView(model);
    CanvasView view3 = new CanvasView(model);
  }

  public static void main(String[] args) {
    new CanvasApp().startGame();
  }
}
