package com.estella;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class PaintingBoard extends JComponent {

  private static final long serialVersionUID = 1L;
  
  private Image image;
  private Graphics2D gra2d;
  private final CanvasModel model;
  private final List<Stroke> strokes; 
  
  /**
   * Build a painting board in this canvas app.
   * @param m - the singleton canvas model.
   */
  public PaintingBoard(CanvasModel m) {
    strokes = new ArrayList<>();
    model = m;
    addListeners();
  }
  
  private void addListeners() {
    this.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        model.setStartPoint(new Point(e.getX(), e.getY()));
      }

      public void mouseReleased(MouseEvent e) {
        model.setEndPoint(new Point(e.getX(), e.getY()));
      }
    });

    this.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        model.setInProgressPoint(new Point(e.getX(), e.getY()));
      }
    });
  }
  
  private void clearBoard() {
    gra2d = (Graphics2D)image.getGraphics();
    gra2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
    gra2d.setPaint(Color.WHITE);
    gra2d.fillRect(0, 0, getSize().width, getSize().height);
    repaint();
  }

  /**
   * This is UI delegate's paint method which allows you to draw on the canvas with mouse.
   * @param g - Graphics object
   */
  protected void paintComponent(Graphics g) {
    image = image == null ? createImage(getSize().width, getSize().height) : image;
    clearBoard();
    gra2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    for (Stroke s : strokes) {
      gra2d.setStroke(new BasicStroke(s.getSize(), 
          BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
      gra2d.setComposite(AlphaComposite.getInstance(
          AlphaComposite.SRC_OVER, s.getTransparency()));
      gra2d.setPaint(s.getColor());
      gra2d.draw(s.getShape());
      repaint();
    }
    g.drawImage(image, 0, 0, null);
  }
  
  /**
   * Draw a stroke based on the stroke information.
   * @param x1 - the coordinate of start point
   * @param y1 - the coordinate of start point
   * @param x2 - the coordinate of end point
   * @param y2 - the coordinate of end point
   * @return A stroke line segment specified with float coordinates.
   */
  public Line2D.Float drawLine(float x1, float y1, float x2, float y2) {
    return new Line2D.Float(x1, y1, x2, y2);
  }
  
  /**
   * Add a made stroke to painting board
   * @param st - stroke information
   * @return true if this stroke added.
   */
  public boolean addStroke(Stroke st) {
    return strokes.add(st);
  }
  
  /**
   * Clear all strokes in the painting board.
   */
  public void resetBoard() {
    strokes.clear();
    clearBoard();
  }
}