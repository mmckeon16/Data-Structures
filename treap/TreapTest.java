package treap;

import static org.junit.Assert.*;
import org.junit.Test;
/**
 * 
 * @author Mary McKeon
 * Test class for Treap.java
 *
 */

public class TreapTest {

	@Test
	public void testAdd() {
		Treap<Integer> test = new Treap<Integer>();
		assertEquals(Boolean.toString(test.add(4,19)),"true");
		assertEquals(Boolean.toString(test.add(2,31)),"true");
		assertEquals(Boolean.toString(test.add(6,70)),"true");
		assertEquals(Boolean.toString(test.add(1,84)),"true");
		assertEquals(Boolean.toString(test.add(3,12)),"true");
		assertEquals(Boolean.toString(test.add(5,83)),"true");
		assertEquals(Boolean.toString(test.add(7,26)),"true");
	}
	
	@Test
	public void testFind() {
		Treap<Integer> test = new Treap<Integer>();
		test.add(4,19);
		test.add(2,31);
		test.add(6,70);
		test.add(1,84);
		test.add(3,12);
		test.add(5,83);
		test.add(7,26);
		
		assertEquals(Boolean.toString(test.find(4)),"true");
		assertEquals(Boolean.toString(test.find(8)),"false");
	}
	
	@Test
	public void testDelete() {
		Treap<Integer> test = new Treap<Integer>();
		test.add(4,19);
		test.add(2,31);
		test.add(6,70);
		test.add(1,84);
		test.add(3,12);
		test.add(5,83);
		test.add(7,26);
		
		assertEquals(Boolean.toString(test.delete(4)), "true");
		assertEquals(Boolean.toString(test.delete(4)), "false");
	}
	
	@Test
	public void testExistingPriority() {
		Treap<Integer> test = new Treap<Integer>();
		test.add(4,19);
		test.add(2,31);
		test.add(6,70);
		test.add(1,84);
		test.add(3,12);
		test.add(5,83);
		test.add(7,26);
		
		assertEquals(Boolean.toString(test.add(9,19)), "false");
	}
}
