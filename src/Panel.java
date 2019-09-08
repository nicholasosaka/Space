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
	private Color[] colors = new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA};

	public Panel(JFrame frame) {
		this.frame = frame;
		this.shapes = new ArrayList<>();
	}

	void addShape(Shape shape){
		if(shape != null) {
			this.shapes.add(shape);
		}
	}

	void removeShape(Shape shape){
		if(shape != null) {
			this.shapes.remove(shape);
		}
	}

	void removeAllShapes(){
		this.shapes.clear();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int index = 0;
		for (Shape userShape : this.shapes) {
			g.setColor(this.colors[index++]);
			switch (userShape.getID()) {
				case 0: {
					g.drawPolygon(userShape.getX(), userShape.getY(), 3);
					g.fillPolygon(userShape.getX(), userShape.getY(), 3);
					break;
				}
				case 1:
				case 2: {
					g.drawRect(userShape.getLocX() - userShape.getWidth() / 2, userShape.getLocY() - userShape.getHeight() / 2, userShape.getWidth(), userShape.getHeight());
					g.fillRect(userShape.getLocX() - userShape.getWidth() / 2, userShape.getLocY() - userShape.getHeight() / 2, userShape.getWidth(), userShape.getHeight());
					break;
				}
				case 3: {
					g.drawOval(userShape.getLocX() - userShape.getWidth() / 2, userShape.getLocY() - userShape.getHeight() / 2, userShape.getWidth(), userShape.getHeight());
					g.fillOval(userShape.getLocX() - userShape.getWidth() / 2, userShape.getLocY() - userShape.getHeight() / 2, userShape.getWidth(), userShape.getHeight());
				}
			}
			if(index == colors.length){
				index = 0;
			}
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
}
