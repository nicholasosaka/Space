/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class Shape {
	private int id, panelSizeY, panelSizeX, width, height, locX, locY, radius;
	private int[] x = new int[4];
	private int[] y = new int[4];

	Shape(int id, int panelSizeX, int panelSizeY, int locX, int locY, int width, int height) {
		this(id, locX, locY, width, height);
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

	private void populatePointData(){
		if(this.id == 0){
			setTrianglePoints();
		} else if (this.id == 1 || this.id == 2){
			setRectangularPoints();
		}
	}

	private void setRectangularPoints() {

	}

	private void setTrianglePoints(){
		x[0] = Math.max(this.locX - this.width / 2, 0);
		y[0] = Math.max(this.locY - this.height / 2, 0);

		if(this.locX + this.width / 2 > this.panelSizeX){
			x[1] = this.panelSizeX;
			x[2] = this.panelSizeX;
		} else {
			x[1] = this.locX;
			x[2] = this.locX + this.width / 2;
		}

		if(this.locY + this.height / 2 > this.panelSizeY){
			y[1] = this.panelSizeY;
			y[2] = this.panelSizeY;
		} else {
			y[1] = this.locY;
			y[2] = y[0];
		}
	}

	public boolean isPointInShape(int x, int y) {
		//TODO implement checkers
		if (this.getID() == 3) {
			if (Math.sqrt(Math.pow(this.locX - x, 2.0) + Math.pow(this.locY - y, 2.0)) < (double) this.radius) {
				return true;
			}
		} else {
			//TODO finish other IDs
		}
		return false;
	}

	public int getID(){
		return this.id;
	}
}
