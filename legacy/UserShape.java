import java.io.PrintStream;
import java.util.Arrays;

public class UserShape {
	private int id;
	private static int globalID;
	private int name;
	private int locY;
	private int locX;
	private int width;
	private int height;
	private int radius;
	private int[] pointsX;
	private int[] pointsY;
	private int panelSizeY;
	private int panelSizeX;

	UserShape(int name, int panelSizeY, int panelSizeX, int locY, int locX, int width, int height, int id) {
		this.id = id;
		this.name = name;
		this.locY = locY;
		this.locX = locX;
		this.width = width;
		this.height = height;
		this.radius = width < height ? width / 2 : height / 2;
		this.panelSizeY = panelSizeY;
		this.panelSizeX = panelSizeX;
		this.setPoints();
		this.setTrianglePoints();
	}

	UserShape(int name, int locY, int locX, int width, int height) {
		this.id = globalID++;
		this.name = name;
		this.locY = locY;
		this.locX = locX;
		this.width = width;
		this.height = height;
		this.radius = width < height ? width / 2 : height / 2;
		this.setPoints();
		this.setTrianglePoints();
	}

	UserShape(int name, int locY, int locX, int radius) {
		this.id = globalID++;
		this.name = name;
		this.width = radius * 2;
		this.height = radius * 2;
		this.radius = radius;
		this.locY = locY;
		this.locX = locX;
		this.setPoints();
		this.setTrianglePoints();
	}

	UserShape(int name, int panelSizeY, int panelSizeX, int locY, int locX, int radius) {
		this.id = globalID++;
		this.name = name;
		this.width = radius * 2;
		this.height = radius * 2;
		this.radius = radius;
		this.locY = locY;
		this.locX = locX;
		this.panelSizeY = panelSizeY;
		this.panelSizeX = panelSizeX;
		this.setPoints();
		this.setTrianglePoints();
	}

	UserShape(int name, int panelSizeY, int panelSizeX, int locY, int locX, int width, int height) {
		this.id = globalID++;
		this.name = name;
		this.locY = locY;
		this.locX = locX;
		this.width = width;
		this.height = height;
		this.radius = width < height ? width / 2 : height / 2;
		this.panelSizeY = panelSizeY;
		this.panelSizeX = panelSizeX;
		this.setPoints();
		this.setTrianglePoints();
	}

	public void setTrianglePoints() {
		System.out.println("PanelSizeY: " + this.panelSizeY + " and PanelSizeX: " + this.panelSizeX);
		if (this.name == 0) {
			int[] x = new int[3];
			int[] y = new int[3];
			x[0] = this.locX - this.width / 2 < 0 ? 0 : this.locX - this.width / 2;
			y[0] = this.locY - this.height / 2 < 0 ? 0 : this.locY - this.height / 2;
			if (this.locX + this.width / 2 > this.panelSizeX) {
				x[2] = this.panelSizeX;
				x[1] = this.panelSizeX;
			} else {
				x[2] = this.locX + this.width / 2;
				x[1] = this.locX;
			}
			if (this.locY + this.height / 2 > this.panelSizeY) {
				y[2] = this.panelSizeY;
				y[1] = this.panelSizeY;
			} else {
				y[2] = y[0];
				y[1] = this.locY;
			}
			this.pointsX = x;
			this.pointsY = y;
			for (int i = 0; i < this.pointsY.length; ++i) {
			}
		}
	}

	public void setPoints() {
	}

	public int getId() {
		return this.id;
	}

	public int getPanelSizeY() {
		return this.panelSizeY;
	}

	public void setPanelSizeY(int panelSizeY) {
		this.panelSizeY = panelSizeY;
		this.setTrianglePoints();
	}

	public int getPanelSizeX() {
		return this.panelSizeX;
	}

	public void setPanelSizeX(int panelSizeX) {
		this.panelSizeX = panelSizeX;
		this.setTrianglePoints();
	}

	boolean isPointInShape(int x, int y) {
		if (this.getName() == 3) {
			double distanceFromCentroid = Math.sqrt(Math.pow(this.locX - x, 2.0) + Math.pow(this.locY - y, 2.0));
			if (distanceFromCentroid < (double)this.radius) {
				return true;
			}
		} else if (this.getName() == 0) {
			for (int i = 0; i < 3; ++i) {
			}
			double[][] T = new double[2][2];
			T[0][0] = this.pointsX[0] - this.pointsX[2];
			T[0][1] = this.pointsX[1] - this.pointsX[2];
			T[1][0] = this.pointsY[0] - this.pointsY[2];
			T[1][1] = this.pointsY[1] - this.pointsY[2];
			double detT = T[0][0] * T[1][1] - T[1][0] * T[0][1];
			double alpha1 = (this.pointsY[1] - this.pointsY[2]) * (x - this.pointsX[2]) + (this.pointsX[2] - this.pointsX[1]) * (y - this.pointsY[2]);
			double alpha2 = (this.pointsY[2] - this.pointsY[0]) * (x - this.pointsX[2]) + (this.pointsX[0] - this.pointsX[2]) * (y - this.pointsY[2]);
			double alpha3 = 1.0 - (alpha1 /= detT) - (alpha2 /= detT);
			if (alpha1 >= 0.0 && alpha2 >= 0.0 && alpha3 >= 0.0) {
				System.out.println("In");
				return true;
			}
			System.out.println("Not in");
		} else if ((this.getName() == 1 || this.getName() == 2) && x > this.locX - this.width / 2 && x < this.locX + this.width / 2 && y > this.locY - this.height / 2 && y < this.locY + this.height / 2) {
			return true;
		}
		return false;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		UserShape userShape = (UserShape)o;
		return this.id == userShape.getId();
	}

	public boolean isShapeInShape(UserShape userShape) {
		if (userShape.name == 2 || userShape.name == 1) {
			// empty if block
		}
		return false;
	}

	public String toString() {
		return "UserShape{id=" + this.id + ", name=" + this.name + ", locY=" + this.locY + ", locX=" + this.locX + ", width=" + this.width + ", height=" + this.height + ", radius=" + this.radius + ", pointsX=" + Arrays.toString(this.pointsX) + ", pointsY=" + Arrays.toString(this.pointsY) + '}';
	}

	public int hashCode() {
		int result = this.name;
		result = 31 * result + this.locY;
		result = 31 * result + this.locX;
		result = 31 * result + this.width;
		result = 31 * result + this.height;
		result = 31 * result + this.radius;
		result = 31 * result + Arrays.hashCode(this.pointsX);
		result = 31 * result + Arrays.hashCode(this.pointsY);
		return result;
	}

	protected void setName(int name) {
		this.name = name;
	}

	int getName() {
		return this.name;
	}

	protected int getLocY() {
		return this.locY;
	}

	protected int getLocX() {
		return this.locX;
	}

	int getWidth() {
		return this.width;
	}

	int getHeight() {
		return this.height;
	}

	int[] getPointsX() {
		return this.pointsX;
	}

	protected void setLocY(int locY) {
		this.locY = locY;
	}

	protected void setLocX(int locX) {
		this.locX = locX;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	protected void setPointsX(int[] pointsX) {
		this.pointsX = pointsX;
	}

	int[] getPointsY() {
		return this.pointsY;
	}

	protected void setPointsY(int[] pointsY) {
		this.pointsY = pointsY;
	}

	protected int getRadius() {
		return this.radius;
	}

	protected void setRadius(int radius) {
		this.radius = radius;
	}
}
