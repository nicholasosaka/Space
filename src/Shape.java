/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class Shape {
	private int id, panelSizeY, panelSizeX, width, height, locX, locY, radius;
	private int[] x, y;

	Shape(int id, int panelSizeX, int panelSizeY, int locX, int locY, int width, int height) {
		this(id, locY, locX, width, height);
		this.panelSizeX = panelSizeX;
		this.panelSizeY = panelSizeY;
	}

	Shape(int id, int locX, int locY, int width, int height){
		this.id = id;
		this.locY = locY;
		this.locX = locX;
		this.width = width;
		this.height = height;
	}

	Shape(int id, int panelSizeX, int panelSizeY, int locX, int locY, int radius){
		this.id = id;
		this.locY = locY;
		this.locX = locX;
		this.panelSizeX = panelSizeX;
		this.panelSizeY = panelSizeY;
		this.radius = radius;
	}
}
