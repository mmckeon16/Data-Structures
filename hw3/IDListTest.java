package hw3;

import static org.junit.Assert.*;
import org.junit.Test;

public class IDListTest {
	
	@Test
	public void testAdd() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 10; i++){
			dll.add(i);
			assertEquals(Integer.toString(dll.getHead()), Integer.toString(i));
		}
		dll.add(null);
		assertEquals(dll.toString(), "[null,9,8,7,6,5,4,3,2,1,0]");
		
	}
	
	@Test
	public void testAddAt() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 10; i++){
			dll.add(i);
		}
		dll.add(5, 10);
		dll.add(11,11);
		System.out.println(dll.toString());
		assertEquals(dll.toString(), "[9,8,7,6,5,10,4,3,2,1,0,11]");
	}
	
	@Test
	public void testAppend() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 10; i++){
			dll.append(i);
			assertEquals(Integer.toString(dll.getLast()), Integer.toString(i));
		}
		assertEquals(dll.toString(), "[0,1,2,3,4,5,6,7,8,9]");
	}
	
	@Test
	public void testGet() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 10; i++){
			dll.append(i);
		}
		assertEquals(Integer.toString(dll.get(5)), "5");
	}
	
	@Test
	public void testSize() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 10; i++){
			dll.append(i);
		}
		assertEquals(Integer.toString(dll.size()), "10");
	}
	
	@Test
	public void testSizeOnEmptyList() {
		IDList<Integer> dll = new IDList<Integer>();
		assertEquals(Integer.toString(dll.size()), "0");
	}
	
	@Test
	public void testRemove() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 10; i++){
			dll.append(i);
		}
		assertEquals(Integer.toString(dll.remove()), "0");
		assertEquals(dll.toString(), "[1,2,3,4,5,6,7,8,9]");
		assertEquals(Integer.toString(dll.getHead()), "1");
	}
	
	@Test
	public void testRemoveLast() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 10; i++){
			dll.append(i);
		}
		assertEquals(Integer.toString(dll.removeLast()), "9");
		assertEquals(dll.toString(), "[0,1,2,3,4,5,6,7,8]");
		assertEquals(Integer.toString(dll.getLast()), "8");
	}
	
	@Test
	public void testRemoveAt() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 3; i++){
			dll.append(i);
		}
		assertEquals(Integer.toString(dll.removeAt(2)), "2");
		assertEquals(dll.toString(), "[0,1]");
		assertEquals(Integer.toString(dll.removeAt(0)), "0");
		assertEquals(Integer.toString(dll.removeAt(0)), "1");
		assertNull(dll.removeAt(0));
		
	}
	
	@Test
	public void testRemoveElem() {
		IDList<Integer> dll = new IDList<Integer>();
		for (int i = 0; i < 3; i++){
			dll.append(i);
		}
		assertTrue(dll.remove(2));
		assertEquals(dll.toString(), "[0,1]");
		assertTrue(dll.remove(1));
		assertEquals(dll.toString(), "[0]");
		assertFalse(dll.remove(1));
	}

}
