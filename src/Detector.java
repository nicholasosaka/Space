import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Detector {
	private JFrame frame;
	private ShapePanel shapePanel;
	public static final int TRIANGLE = 0;
	public static final int SQUARE = 1;
	public static final int RECTANGLE = 2;
	public static final int CIRCLE = 3;

	public Detector(String title, int width, int height) {
		EventQueue.invokeLater(() -> {
			this.frame = new JFrame(title);
			this.frame.setSize(width, height);
			System.out.println("Adding panel");
			this.shapePanel = new ShapePanel(this.frame);
			this.frame.add(this.shapePanel);
			this.frame.pack();
			this.frame.repaint();
			this.frame.setDefaultCloseOperation(3);
			this.frame.setVisible(true);
			System.out.println("Added panel");
		});
	}

	public void exit() {
		this.frame.dispatchEvent(new WindowEvent(this.frame, 201));
	}

	public void addShape(int name, int locX, int locY, int width, int height) {
		while (this.shapePanel == null) {
			System.out.println("Waiting for UI to build...");
		}
		System.out.println("Frame width / height:" + this.frame.getHeight() + " " + this.frame.getWidth());
		this.shapePanel.addUserShape(new UserShape(name, this.frame.getHeight(), this.frame.getWidth(), locY, locX, width, height));
		this.shapePanel.repaint();
	}

	public void addShape(UserShape userShape) {
		while (this.shapePanel == null) {
			System.out.println("Waiting for UI to build...");
		}
		System.out.println("Frame width / height:" + this.frame.getHeight() + " " + this.frame.getWidth());
		userShape.setPanelSizeX(this.frame.getWidth());
		userShape.setPanelSizeY(this.frame.getHeight());
		this.shapePanel.addUserShape(userShape);
		this.frame.repaint();
	}

	public void addShape(int name, int locX, int locY, int radius) {
		while (this.shapePanel == null) {
			System.out.println("Waiting for UI to build...");
		}
		UserShape userShape = new UserShape(name, this.frame.getHeight(), this.frame.getWidth(), locY, locX, radius);
		this.shapePanel.addUserShape(userShape);
		this.frame.repaint();
	}

	public boolean isPointInShapes(UserShape point) {
		for (UserShape userShape : this.shapePanel.getUserShapes()) {
			System.out.println("Checking isPoint");
			if (userShape.isPointInShape(this.getLocationX(point), this.getLocationY(point)) && !point.equals(userShape)) {
				System.out.println("Returning true");
				return true;
			}
			System.out.println("Returning false");
		}
		return false;
	}

	public boolean isPointInShapes(int pointX, int pointY) {
		for (UserShape userShape : this.shapePanel.getUserShapes()) {
			System.out.println("Checking isPoint");
			if (userShape.isPointInShape(pointX, pointY)) {
				System.out.println("Returning true");
				return true;
			}
			System.out.println("Returning false");
		}
		return false;
	}

	public int getLocationY(UserShape userShape) {
		for (UserShape shape : this.shapePanel.getUserShapes()) {
			if (!shape.equals(userShape)) continue;
			return shape.getLocY();
		}
		return -1;
	}

	public int getLocationX(UserShape userShape) {
		for (UserShape shape : this.shapePanel.getUserShapes()) {
			if (!shape.equals(userShape)) continue;
			return shape.getLocX();
		}
		return -1;
	}

	public boolean isShapeInShapes(UserShape userShape) {
		boolean isInShape = false;
		for (UserShape shape : this.shapePanel.getUserShapes()) {
			if (shape.equals(userShape)) continue;
		}
		return false;
	}

	public void addPoint(int x, int y) {
		this.addShape(3, x, y, 10);
		this.frame.repaint();
	}

	public void addPoint(int x, int y, int radius) {
		this.addShape(3, x, y, radius);
		this.frame.repaint();
	}

	public void removeAllShapes() {
		try {
			Thread.sleep(400L);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.shapePanel.removeAllShapes();
		this.frame.repaint();
	}

	public void removeAllShapes(double secondsDelay) {
		try {
			Thread.sleep((long)(secondsDelay * 1000.0));
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.shapePanel.removeAllShapes();
		this.frame.repaint();
	}

	public void removeShape(UserShape userShape) {
		int index = 0;
		for (UserShape shape : this.shapePanel.getUserShapes()) {
			if (shape.equals(userShape)) {
				this.shapePanel.userShapes.remove(index);
				break;
			}
			++index;
		}
		this.frame.repaint();
	}

	public void moveLeft(int numberOfPixels, Integer increment, UserShape userShapeParam) {
		if (increment == null) {
			increment = 1;
		}
		for (int n = 0; n < numberOfPixels; n += increment.intValue()) {
			ArrayList<UserShape> userShapes = new ArrayList<UserShape>();
			for (UserShape userShape : this.shapePanel.getUserShapes()) {
				if (userShapeParam == null || userShape.equals(userShapeParam)) {
					userShape.setLocX(userShape.getLocX() - increment);
				}
				userShapes.add(new UserShape(userShape.getName(), this.frame.getHeight(), this.frame.getWidth(), userShape.getLocY(), userShape.getLocX(), userShape.getWidth(), userShape.getHeight(), userShape.getId()));
			}
			this.removeAllShapes(0.1);
			for (UserShape userShape : userShapes) {
				this.shapePanel.addUserShape(userShape);
			}
			this.frame.repaint();
		}
	}

	public void moveRight(int numberOfPixels, Integer increment, UserShape userShapeParam) {
		if (increment == null) {
			increment = 1;
		}
		for (int n = 0; n < numberOfPixels; n += increment.intValue()) {
			ArrayList<UserShape> userShapes = new ArrayList<UserShape>();
			for (UserShape userShape : this.shapePanel.getUserShapes()) {
				if (userShapeParam == null || userShape.equals(userShapeParam)) {
					userShape.setLocX(userShape.getLocX() + increment);
				}
				userShapes.add(new UserShape(userShape.getName(), this.frame.getHeight(), this.frame.getWidth(), userShape.getLocY(), userShape.getLocX(), userShape.getWidth(), userShape.getHeight(), userShape.getId()));
			}
			this.removeAllShapes(0.1);
			for (UserShape userShape : userShapes) {
				this.shapePanel.addUserShape(userShape);
			}
			this.frame.repaint();
		}
	}

	public void moveDown(int numberOfPixels, Integer increment, UserShape userShapeParam) {
		if (increment == null) {
			increment = 1;
		}
		for (int n = 0; n < numberOfPixels; n += increment.intValue()) {
			ArrayList<UserShape> userShapes = new ArrayList<UserShape>();
			for (UserShape userShape : this.shapePanel.getUserShapes()) {
				if (userShapeParam == null || userShape.equals(userShapeParam)) {
					userShape.setLocY(userShape.getLocY() + increment);
				}
				userShapes.add(new UserShape(userShape.getName(), this.frame.getHeight(), this.frame.getWidth(), userShape.getLocY(), userShape.getLocX(), userShape.getWidth(), userShape.getHeight(), userShape.getId()));
			}
			this.removeAllShapes(0.1);
			for (UserShape userShape : userShapes) {
				this.shapePanel.addUserShape(userShape);
			}
			this.frame.repaint();
		}
	}

	public void moveUp(int numberOfPixels, Integer increment, UserShape userShapeParam) {
		if (increment == null) {
			increment = 1;
		}
		for (int n = 0; n < numberOfPixels; n += increment.intValue()) {
			ArrayList<UserShape> userShapes = new ArrayList<UserShape>();
			for (UserShape userShape : this.shapePanel.getUserShapes()) {
				if (userShapeParam == null || userShape.equals(userShapeParam)) {
					userShape.setLocY(userShape.getLocY() - increment);
				}
				userShapes.add(new UserShape(userShape.getName(), this.frame.getHeight(), this.frame.getWidth(), userShape.getLocY(), userShape.getLocX(), userShape.getWidth(), userShape.getHeight(), userShape.getId()));
			}
			this.removeAllShapes(0.1);
			for (UserShape userShape : userShapes) {
				this.shapePanel.addUserShape(userShape);
			}
			this.frame.repaint();
		}
	}

	public void move(int x, int y, UserShape userShapeParam) {
		ArrayList<UserShape> userShapes = new ArrayList<UserShape>();
		for (UserShape userShape : this.shapePanel.getUserShapes()) {
			if (userShapeParam == null || userShape.equals(userShapeParam)) {
				userShape.setLocY(y);
				userShape.setLocX(x);
			}
			userShapes.add(new UserShape(userShape.getName(), this.frame.getHeight(), this.frame.getWidth(), userShape.getLocY(), userShape.getLocX(), userShape.getWidth(), userShape.getHeight(), userShape.getId()));
		}
		this.removeAllShapes(0.1);
		for (UserShape userShape : userShapes) {
			this.shapePanel.addUserShape(userShape);
		}
		this.frame.repaint();
	}
}