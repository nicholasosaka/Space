import java.util.Arrays;

/*
 * @author Nikko Osaka
 * @date 9/7/2019
 */
public class Shape {
	private int id, width, height, locX, locY, radius;
	private int[] x = new int[4];
	private int[] y = new int[4];

	Shape(int id,int locX, int locY, int width, int height) {
		this.id = id;
		this.locY = locY;
		this.locX = locX;
		this.width = width;
		this.height = height;
		populatePointData();
	}

	Shape(int id, int locX, int locY, int radius){
		this.id = id;
		this.locY = locY;
		this.locX = locX;
		this.radius = radius;
		populatePointData();
	}

	protected void populatePointData(){
		if(this.id == 0){
			setTrianglePoints();
		} else if (this.id == 1 || this.id == 2){
			setRectangularPoints();
		} else {
			setCircularPoints();
		}
	}

	private void setCircularPoints() {
		x[0] = this.locX - this.radius/2;
		x[1] = this.locX + this.radius/2;

		y[0] = this.locY - this.radius/2;
		y[1] = y[0];

		x[2] = x[0];
		x[3] = x[1];

		y[2] = this.locY + this.radius/2;
		y[3] = x[2];
	}

	private void setRectangularPoints() {
		x[0] = this.locX - this.width/2;
		x[1] = this.locX + this.width/2;

		y[0] = this.locY - this.height/2;
		y[1] = y[0];

		x[2] = x[0];
		x[3] = x[1];

		y[2] = this.locY + this.height/2;
		y[3] = x[2];
	}

	private void setTrianglePoints(){
		x[0] = this.locX - this.width/2;
		y[0] = this.locY;

		x[1] = this.locX;
		y[1] = this.locY - this.height/2;

		x[2] = this.locX + this.width/2;
		y[2] = this.locY;
	}

	public boolean isPointInShape(int x, int y) {
		if (this.getID() == 3) {
			return Math.sqrt(Math.pow(this.locX - x, 2.0) + Math.pow(this.locY - y, 2.0)) < (double) this.radius;
		} else if (this.getID() == 0){

			double[][] T = new double[2][2];

			T[0][0] = this.x[0] - this.x[2];
			T[0][1] = this.x[1] - this.x[2];
			T[1][0] = this.y[0] - this.y[2];
			T[1][1] = this.y[1] - this.y[2];

			double detT = T[0][0] * T[1][1] - T[1][0] * T[0][1];

			double alpha1 = (this.y[1] - this.y[2]) * (x - this.x[2]) + (this.x[2] - this.x[1]) * (y - this.y[2]);
			double alpha2 = (this.y[2] - this.y[0]) * (x - this.x[2]) + (this.x[0] - this.x[2]) * (y - this.y[2]);
			double alpha3 = 1.0 - (alpha1 /= detT) - (alpha2 /= detT);

			return alpha1 >= 0.0 && alpha2 >= 0.0 && alpha3 >= 0.0;

		} else return (this.getID() == 1 || this.getID() == 2) && x > this.locX - this.width / 2 && x < this.locX + this.width / 2 && y > this.locY - this.height / 2 && y < this.locY + this.height / 2;
	}

	/**
	 * Method to check if two shapes intersect
	 * @param shape shape to check this shape against
	 * @return true if intersection, false otherwise
	 */
	public boolean intersect(Shape shape){
		//TODO finish intersection logic

		int verticies = (shape.getID() == 0) ? 3 : (((shape.getID() == 2) || (shape.getID() == 3)) ? 4 : 0);

		for(int i = 0; i < verticies; i++){
			if(isPointInShape(shape.getX()[i], shape.getY()[i])) return true;   //checks intersection of other shape

			if(shape.isPointInShape(getX()[i], getY()[i])) return true; //checks against self
		}

		return false;
	}

	@Override
	public boolean equals(Object o){
		if(o == null || this.getClass() != o.getClass()){
			return false;
		}

		if(this == o){
			return true;
		}

		return this.hashCode() == o.hashCode();

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + getWidth();
		result = 31 * result + getHeight();
		result = 31 * result + getLocX();
		result = 31 * result + getLocY();
		result = 31 * result + getRadius();
		result = 31 * result + Arrays.hashCode(x);
		result = 31 * result + Arrays.hashCode(y);
		return result;
	}

	@Override
	public String toString(){
		return "Shape{id=" + this.id + ", locY=" + this.locY + ", locX=" + this.locX + ", width=" + this.width + ", height=" + this.height + ", radius=" + this.radius + ", x[]=" + Arrays.toString(this.x) + ", y[]=" + Arrays.toString(this.y) + '}';
	}

	public int getID(){
		return this.id;
	}

	public int[] getX() {
		return x;
	}

	public int[] getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getLocX() {
		return locX;
	}

	public int getLocY() {
		return locY;
	}

	public int getRadius() {
		return radius;
	}

	public void setLocY(int y) {
		this.locY = y;
	}

	public void setLocX(int x){
		this.locX = x;
	}
}
