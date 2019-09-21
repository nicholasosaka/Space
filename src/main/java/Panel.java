import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class Panel extends JPanel {
	private JFrame frame;
	private ArrayList<Shape> shapes;
	private Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA};  //colors for drawing, add more as desired

	Panel(JFrame frame) {
		this.frame = frame;
		this.shapes = new ArrayList<>();
	}

	void addShape(Shape shape){
		if(shape != null) { //null checks!
			this.shapes.add(shape);
		}
	}

	void removeShape(Shape shape){
		if(shape != null) { // more null checks!
			this.shapes.remove(shape);
		}
	}

	void removeAllShapes(){
		this.shapes.clear();    //remove shapes
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int index = 0;
		for (Shape userShape : this.shapes) {   //for each shape
			g.setColor(this.colors[index++]);   //set color based on index, and increment index
			switch (userShape.getID()) {
				case 0: {   //triangles
					g.drawPolygon(userShape.getX(), userShape.getY(), 3);   //draw polygon
					g.fillPolygon(userShape.getX(), userShape.getY(), 3);   //fill polygon
					break;
				}
				case 1: //square / rectangle
				case 2: {
					g.drawRect(userShape.getLocX() - userShape.getWidth() / 2, userShape.getLocY() - userShape.getHeight() / 2, userShape.getWidth(), userShape.getHeight());
					g.fillRect(userShape.getLocX() - userShape.getWidth() / 2, userShape.getLocY() - userShape.getHeight() / 2, userShape.getWidth(), userShape.getHeight());
					break;
				}
				case 3: {   //circles
					g.drawOval(userShape.getLocX() - userShape.getRadius() / 2, userShape.getLocY() - userShape.getRadius() / 2, userShape.getRadius(), userShape.getRadius());
					g.fillOval(userShape.getLocX() - userShape.getRadius() / 2, userShape.getLocY() - userShape.getRadius() / 2, userShape.getRadius(), userShape.getRadius());
				}
			}
			if(index == colors.length) index = 0;   //reset index if end of colors array
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
}
