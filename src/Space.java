import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class Space {
	private JFrame frame;
	private Panel panel;
	private int speed = 50;

	/**
	 * Constructor for Space objects
	 * @param title name of window
	 * @param width width of window
	 * @param length length of window
	 */
	Space(String title, int width, int length){
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
		if(shape != null){
			this.panel.addShape(shape);
		}
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
		Shape s = new Shape(id, this.panel.getHeight(), this.panel.getWidth(), locX, locY, width, height);
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
		Shape s = new Shape(3, this.panel.getHeight(), this.panel.getWidth(), locX, locY, radius);
		this.panel.addShape(s);
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
	 * Method to remove shape from Space
	 * @param index index of the shape to remove
	 */
	public void remove(int index){
		this.panel.remove(index);
	}

	/**
	 * Method to remove all shapes from Space
	 */
	public void removeAll(){
		this.panel.removeAllShapes();
	}

	public void moveLeft(Shape shape, int pixels, int increment){
		if(increment <= 0) increment = 1;
		if(shape == null) return;

		try {
			for (int i = 0; i < pixels; i += increment) {
				shape.setLocX(shape.getLocX() - increment);
				shape.populatePointData();

				Thread.sleep(speed);
				this.frame.repaint();
			}
		}catch(InterruptedException ie){
			System.out.println("Failure to move " + shape);
		}
	}

	public void moveRight(Shape shape, int pixels, int increment){
		if(increment <= 0) increment = 1;
		if(shape == null) return;

		try {
			for (int i = 0; i < pixels; i += increment) {
				shape.setLocX(shape.getLocX() + increment);
				shape.populatePointData();

				Thread.sleep(speed);
				this.frame.repaint();
			}
		}catch(InterruptedException ie){
			System.out.println("Failure to move " + shape);
		}
	}

	public void moveUp(Shape shape, int pixels, int increment){
		if(increment <= 0) increment = 1;
		if(shape == null) return;

		try {
			for (int i = 0; i < pixels; i += increment) {
				shape.setLocY(shape.getLocY() - increment);
				shape.populatePointData();

				Thread.sleep(speed);
				this.frame.repaint();
			}
		}catch(InterruptedException ie){
			System.out.println("Failure to move " + shape);
		}
	}

	public void moveDown(Shape shape, int pixels, int increment){
		if(increment <= 0) increment = 1;
		if(shape == null) return;

		try {
			for (int i = 0; i < pixels; i += increment) {
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
		for(Shape s : this.panel.getShapes()){
			if(s.isPointInShape(x,y)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to check if a point is in a shape within the Space
	 * @param point point to check
	 * @return true if point is within a shape in Space, false otherwise
	 */
	public boolean isPointInSpace(Shape point){
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

	public void setSpeed(int speed) {
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

		Space space = (Space) o;

		if (!frame.equals(space.frame)) return false;
		return panel.equals(space.panel);
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
