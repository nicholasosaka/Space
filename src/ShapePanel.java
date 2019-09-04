import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShapePanel
		extends JPanel {
	private Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.ORANGE, Color.YELLOW};
	private JFrame context;
	public ArrayList<UserShape> userShapes;
	private boolean isAdding;

	ShapePanel(JFrame context) {
		this.context = context;
		this.userShapes = new ArrayList();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int index = 0;
		for (UserShape userShape : this.userShapes) {
			g.setColor(this.colors[index++]);
			switch (userShape.getName()) {
				case 0: {
					g.drawPolygon(userShape.getPointsX(), userShape.getPointsY(), 3);
					g.fillPolygon(userShape.getPointsX(), userShape.getPointsY(), 3);
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
			if (index != this.colors.length) continue;
			index = 0;
		}
	}

	void addUserShape(UserShape userShape) {
		this.userShapes.add(userShape);
	}

	ArrayList<UserShape> getUserShapes() {
		return this.userShapes;
	}

	void removeAllShapes() {
		this.userShapes.clear();
	}

	@Override
	public Dimension getPreferredSize() {
		return this.context.getSize();
	}
}
