import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class SpaceGameTest {
	SpaceGame spaceGame;

	@Before
	public void initialize(){
		spaceGame = new SpaceGame("Unit Testing", 500, 500);
	}

	@Test
	public void addShapeShouldAddShape() {
		Shape s = new Shape(1,100,100,100,100);
		spaceGame.addShape(s);
		assertEquals(s, spaceGame.getShapes().get(0));

		spaceGame.addShape(1,100,100,100,100);
		assertEquals(2, spaceGame.getShapes().size());
	}

	@Test
	public void addCircle() {
		Shape s = new Shape(4,500,250,250,100);
		spaceGame.addShape(s);
		assertEquals(4, spaceGame.getShapes().get(0).getID());
	}

	@Test
	public void addTriangle() {
		Shape s = new Shape(0,500,250,250,100);
		spaceGame.addShape(s);
		assertEquals(0, spaceGame.getShapes().get(0).getID());
	}

	@Test
	public void addRectangle() {
		Shape s = new Shape(2,500,250,250,100);
		spaceGame.addShape(s);
		assertEquals(2, spaceGame.getShapes().get(0).getID());
	}

	@Test
	public void multipleShapesShouldPopulateProperly() {
		for(int i = 0; i < 5; i++) {
			spaceGame.addShape(i % 4, 100, 100, 100, 100);
		}

		assertEquals(5, spaceGame.getShapes().size());
	}

	@Test
	public void pointCheckForTriangle(){
		spaceGame.addShape(0,250,250,100,100);

		assertTrue(spaceGame.isPointInSpace(250, 250));

		assertFalse(spaceGame.isPointInSpace(100, 100));
	}

	@Test
	public void pointCheckForCircle(){
		spaceGame.addShape(250, 250, 100);

		assertTrue(spaceGame.isPointInSpace(250, 250));

		assertFalse(spaceGame.isPointInSpace(100, 100));
	}

	@Test
	public void pointCheckForRectangle(){
		spaceGame.addShape(2,250, 250, 100,100);

		assertTrue(spaceGame.isPointInSpace(250, 250));

		assertFalse(spaceGame.isPointInSpace(100, 100));
	}

	@Test
	public void pointInSpaceTrue(){
		Point p = new Point(250, 250);
		spaceGame.addShape(250, 250, 100);

		assertTrue(spaceGame.isPointInSpace(250, 250));

		assertTrue(spaceGame.isPointInSpace(p));
	}

	@Test
	public void pointInSpaceFalse(){
		Point p = new Point(3, 3);
		spaceGame.addShape(250, 250, 50);

		assertFalse(spaceGame.isPointInSpace(455, 455));

		assertFalse(spaceGame.isPointInSpace(p));
	}

	@Test
	public void checkSpaceContents(){
		Shape s = new Shape(250, 250, 100);
		Shape q = new Shape(250, 250, 100);
		spaceGame.addShape(s);

		assertTrue(spaceGame.isShapeInSpace(s));
		assertTrue(spaceGame.isShapeInSpace(q));
	}

	@Test
	public void removeByObject() {
		Triangle t = new Triangle(250, 250, 100, 100);
		spaceGame.addShape(t);
		spaceGame.remove(t);

		assertEquals(0, spaceGame.getShapes().size());
	}


	@Test
	public void removeAll() {
		Random rand = new Random(System.currentTimeMillis());
		for(int i = 0; i < 10; i++){
			int x = rand.nextInt(500);
			int y = rand.nextInt(500);
			spaceGame.addPoint(x, y);
		}

		spaceGame.removeAll();
		assertEquals(0, spaceGame.getShapes().size());
	}

	@Test
	public void getShapes() {
		ArrayList<Shape> list = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			Shape s = new Shape(0, 250, 250, (i*10)+1, (i*10)+1);
			spaceGame.addShape(s);
			list.add(s);
		}

		assertEquals(10, spaceGame.getShapes().size());
		assertEquals(list, spaceGame.getShapes());

	}
}