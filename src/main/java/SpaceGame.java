import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class SpaceGame {
	private JFrame frame;
	private Panel panel;
	private int speed = 50;

	/**
	 * Constructor for Space objects
	 * @param title name of window
	 * @param width width of window
	 * @param length length of window
	 */
	SpaceGame(String title, int width, int length){
		this.frame = new JFrame(title);
		this.frame.setSize(width, length);
		this.panel = new Panel(this.frame);
		this.frame.add(this.panel);
		this.frame.repaint();
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
	}

	/**
	 * Method to add shape to Space
	 * @param shape shape to add
	 */
	public void addShape(Shape shape){
		this.panel.addShape(shape);
		update();
	}

	/**
	 * Method to add shape to Space
	 * @param id id of shape to add
	 * @param locX x-axis of the center of the shape
	 * @param locY y-axis of the center of the shape
	 * @param width width of the shape
	 * @param height height of the shape
	 */
	public void addShape(int id, int locX, int locY, int width, int height){
		Shape s = new Shape(id, locX, locY, width, height);
		this.panel.addShape(s);
		update();
	}

	/**
	 * Method to add shape to Space
	 * @param locX x-axis of the center of the shape
	 * @param locY y-axis of the center of the shape
	 * @param radius radius of the shape
	 */
	public void addShape(int locX, int locY, int radius){
		Circle s = new Circle(locX, locY, radius);
		this.panel.addShape(s);
		update();
	}

	/**
	 * Method to add point to Space
	 * @param x x coordinate of point
	 * @param y y coordinate of point
	 */
	public void addPoint(int x, int y){
		Point p = new Point(x,y);
		this.panel.addShape(p);
		update();
	}

	/**
	 * Method to remove shape from Space
	 * @param shape desired shape to remove from Space
	 */
	public void remove(Shape shape){
		this.panel.removeShape(shape);
	}


	/**
	 * Method to remove all shapes from Space
	 */
	public void removeAll(){
		this.panel.removeAllShapes();
	}

	/**
	 * Method to move a given shape to the left
	 * @param shape Shape to move
	 * @param pixels number of pixels to move shape by
	 * @param increment how far the shape should be moved at a time (in pixels)
	 */
	public void moveLeft(Shape shape, int pixels, int increment){
		if(increment <= 0) increment = 1;
		if(shape == null) return;

		try {
			for (int i = 0; i < pixels; i += increment) {   //moves shape over number of pixels in steps of increment.
				shape.setLocX(shape.getLocX() - increment);
				shape.populatePointData();

				Thread.sleep(speed);
				this.frame.repaint();
			}
		}catch(InterruptedException ie){
			System.out.println("Failure to move " + shape);
		}
	}

	/**
	 * Method to move a given shape to the right
	 * @param shape Shape to move
	 * @param pixels number of pixels to move shape by
	 * @param increment how far the shape should be moved at a time (in pixels)
	 */
	public void moveRight(Shape shape, int pixels, int increment){
		if(increment <= 0) increment = 1;
		if(shape == null) return;

		try {
			for (int i = 0; i < pixels; i += increment) { //moves shape over number of pixels in steps of increment.
				shape.setLocX(shape.getLocX() + increment);
				shape.populatePointData();

				Thread.sleep(speed);
				this.frame.repaint();
			}
		}catch(InterruptedException ie){
			System.out.println("Failure to move " + shape);
		}
	}

	/**
	 * Method to move a given shape upwards
	 * @param shape Shape to move
	 * @param pixels number of pixels to move shape by
	 * @param increment how far the shape should be moved at a time (in pixels)
	 */
	public void moveUp(Shape shape, int pixels, int increment){
		if(increment <= 0) increment = 1;
		if(shape == null) return;

		try {
			for (int i = 0; i < pixels; i += increment) { //moves shape over number of pixels in steps of increment.
				shape.setLocY(shape.getLocY() - increment);
				shape.populatePointData();

				Thread.sleep(speed);
				this.frame.repaint();
			}
		}catch(InterruptedException ie){
			System.out.println("Failure to move " + shape);
		}
	}

	/**
	 * Method to move a given shape downwards
	 * @param shape Shape to move
	 * @param pixels number of pixels to move shape by
	 * @param increment how far the shape should be moved at a time (in pixels)
	 */
	public void moveDown(Shape shape, int pixels, int increment){
		if(increment <= 0) increment = 1;
		if(shape == null) return;

		try {
			for (int i = 0; i < pixels; i += increment) { //moves shape over number of pixels in steps of increment.
				shape.setLocY(shape.getLocY() + increment);
				shape.populatePointData();

				Thread.sleep(speed);
				this.frame.repaint();
			}
		}catch(InterruptedException ie){
			System.out.println("Failure to move " + shape);
		}
	}

	/**
	 * Method to check if a shape is in the Space
	 * @param shape shape to check
	 * @return true if shape is in Space, false otherwise
	 */
	public boolean isShapeInSpace(Shape shape){
		if(shape == null) return false;

		for(Shape s : this.panel.getShapes()){
			if(s.equals(shape)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to check if a point is in a shape within the Space
	 * @param x x coordinate of point
	 * @param y y coordinate of point
	 * @return true if point is within a shape in Space, false otherwise
	 */
	public boolean isPointInSpace(int x, int y){

		Point p = new Point(x,y);

		for(Shape s : this.panel.getShapes()){
			if(!p.equals(s)) return s.isPointInShape(x,y);
		}

		return false;
	}

	/**
	 * Method to check if a point is in a shape within the Space
	 * @param point point to check
	 * @return true if point is within a shape in Space, false otherwise
	 */
	public boolean isPointInSpace(Point point){
		if(point == null) return false;

		for(Shape s : this.panel.getShapes()){
			if(!s.equals(point) && s.isPointInShape(point.getLocX(), point.getLocY())){
				return true;
			}
		}
		return false;
	}

	/**
	 * Methid to get the list of shapes within Space
	 * @return list of shapes in Space
	 */
	public ArrayList<Shape> getShapes(){
		return this.panel.getShapes();
	}

	/**
	 * Method to change speed at which shapes move
	 * @param speed integer from 1 to 30
	 */
	public void setSpeed(int speed) {
		if(speed < 1) speed = 1;
		if(speed > 30) speed = 30;
		this.speed = (int)((1.0/speed)*250);
	}

	/**
	 * Method to redraw Space graphics
	 */
	private void update() {
		this.frame.repaint();
	}

	/**
	 * Method to close Space window
	 */
	public void exit() {
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Method to check if Space object is equal to another object
	 * @param o object to check against
	 * @return true if equal, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SpaceGame spaceGame = (SpaceGame) o;

		if (!frame.equals(spaceGame.frame)) return false;
		return panel.equals(spaceGame.panel);
	}

	/**
	 * Method to determine hashcode of Space objects
	 * @return integer hashcode
	 */
	@Override
	public int hashCode() {
		int result = frame.hashCode();
		result = 31 * result + panel.hashCode();
		return result;
	}

	/**
	 * Method to claim a toString override
	 * @return String format for output
	 */
	@Override
	public String toString() {
		return "Space{" +
				"frame=" + frame +
				", panel=" + panel +
				'}';
	}
}
