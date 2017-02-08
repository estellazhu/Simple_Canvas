package com.estella;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;

/**
 * Since the majority of the canvas app code is pretty straight forward, like getters and
 * setters, I only created test cases for CanvasModel#addObserver and #removeObserver.
 * That way, we can use Ant to automate the test process.
 */
public class CanvasModelTest {
  CanvasModel model = CanvasModel.INSTANCE;
  
  @After
  public void cleanUp() {
    model.clearObserverList();
  }
  
  @Test
  public void addObserverTest_NotNull() {
    CanvasObserver ob = new CanvasView(model);
    assertTrue(model.addObserver(ob));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void addObserverTest_Null() {
    CanvasObserver ob = null;
    model.addObserver(ob);
  }
  
  @Test
  public void removeObserverTest_NotNull() {
    CanvasObserver ob = new CanvasView(model);
    model.addObserver(ob);
    assertTrue(model.removeObserver(ob));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void removeObserverTest_Null() {
    CanvasObserver ob = null;
    model.removeObserver(ob);
  }
}
