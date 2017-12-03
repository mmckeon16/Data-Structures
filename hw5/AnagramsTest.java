package hw5;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

public class AnagramsTest {

	@Test
	public void testBuildLetterTable() {
		Anagrams a = new Anagrams();
		System.out.println(a.letterTable);
		assertEquals(a.letterTable.toString(),"{a=2, b=3, c=5, d=7, e=11, f=13, g=17, h=19, i=23, j=29, k=31, l=37, m=41, n=43, o=47, p=53, q=59, r=61, s=67, t=71, u=73, v=79, w=83, x=89, y=97, z=101}");

	}
	
	@Test
	public void testAddWord() {
		Anagrams a = new Anagrams();
		
		a.addWord("mary");
		a.addWord("mckeon");
		
		boolean me = false;
		for(Map.Entry<Long, ArrayList<String>> entry : a.anagramTable.entrySet()) {
			if(entry.getValue().get(0).equals("mary") || entry.getValue().get(0).equals("mckeon")){
				me = true;
			}
		}
		assertEquals(Boolean.toString(me),"true");
	}
	
	@Test
	public void testMyHashCode() {
		Anagrams a = new Anagrams();
		boolean correct = false;
		if(a.myHashCode("mary") == (long) 485194){
			correct = true;
		}
		assertEquals(Boolean.toString(correct), "true");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMyHashCodeIllegalArgumetException(){
		Anagrams a = new Anagrams();
		
		boolean correct = false;
		a.myHashCode(null);
	}
	
	@Test
	public void testgetMaxEntriesWithTwoMaxEntries() {
		Anagrams a = new Anagrams ();
		
		a.addWord("mary");
		a.addWord("nick");
		a.addWord("kinc");
		a.addWord("amyr");
		
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		assertEquals(maxEntries.get(0).getValue().size(), 2);
		assertEquals(maxEntries.get(1).getValue().size(), 2);
		assertEquals(maxEntries.size(), 2);
	}
	
	@Test
	public void testCompleteProcess() {
		Anagrams a = new Anagrams ();
		
		final long startTime = System.nanoTime();
		try {
		a.processFile("words_alpha.txt");
		} catch (IOException e1){
		e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double)estimatedTime/1000000000);
		System.out.println("Time : "+ seconds);
		assertEquals(maxEntries.get(0).getKey().toString(), "236204078");
		assertEquals(maxEntries.get(0).getValue().size(), 15);
		assertEquals(maxEntries.size(), 1);
		assertEquals(maxEntries.get(0).getValue().get(0), "alerts");
		assertEquals(maxEntries.get(0).getValue().get(maxEntries.get(0).getValue().size()-1), "talers");
		System.out.println(maxEntries.get(0).getKey());
		System.out.println("List of max anagrams : "+ maxEntries);
		System.out.println(maxEntries.get(0).getValue().size());
	}	
}
