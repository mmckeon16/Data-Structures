package hw5;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Mary McKeon
 * CS 284 Section B
 *
 */

public class Anagrams {
	final Integer[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};;
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	//Constructor
	public Anagrams (){
		anagramTable = new HashMap<Long,ArrayList<String>>();
		buildLetterTable();
	}
	
	/**
	 * Called by construction to populate the letter table with it's associated prime numbers
	 */
	public void buildLetterTable(){
		letterTable = new HashMap<Character,Integer>()
		{{
		     put('a', 2);
		     put('b', 3);
		     put('c', 5);
		     put('d', 7);
		     put('e', 11);
		     put('f', 13);
		     put('g', 17);
		     put('h', 19);
		     put('i', 23);
		     put('j', 29);
		     put('k', 31);
		     put('l', 37);
		     put('m', 41);
		     put('n', 43);
		     put('o', 47);
		     put('p', 53);
		     put('q', 59);
		     put('r', 61);
		     put('s', 67);
		     put('t', 71);
		     put('u', 73);
		     put('v', 79);
		     put('w', 83);
		     put('x', 89);
		     put('y', 97);
		     put('z', 101);    
		}};
	}
	
	/**
	 * Function that calls the myHashCode, and places the word in the anagram table using the code as the key
	 * @param s String to be added to the anagram table
	 */
	public void addWord(String s){
		long code = myHashCode(s);
		
		if(anagramTable.get(code) == null){
			anagramTable.put(code, new ArrayList<String>());
		}
		anagramTable.get(code).add(s);
	}
	
	/**
	 * Uses the letter table of prime numbers to calculate a unique value for the 
	 * hash code by multiplying them together
	 * @param s String word to calculate the value of hash code for
	 * @return Long which is the hash code
	 */
	public Long myHashCode(String s){
		if(s!=null){
			long code = 1;
			for(int i = 0; i < s.length(); i++){
				code = code*(long)letterTable.get(s.charAt(i));
			}
			return code;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Reads in the file, and takes every word on each line, and calls the addWord function
	 * to add it to the anagram table
	 * 
	 * @param s String the name of the file to be read
	 * @throws IOException if file is not in directory
	 */
	public void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while(( strLine = br.readLine()) != null) {
		this.addWord(strLine);
		}
		
		br.close();
	}
	
	/**
	 * Goes through the anagram table and finds the ArrayList of words sharing the same hash
	 * code and creates an ArrayList of those with the maximum number of anagrams.
	 * 
	 * @return ArrayList<Map.Entry<Long,ArrayList<String>>> maxAnagrams which is the list
	 * of entries with the most anagrams
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries(){
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxAnagrams = new ArrayList<HashMap.Entry<Long,ArrayList<String>>>();
		if(anagramTable.isEmpty()){
			return null;
		}else{
			maxAnagrams.add(anagramTable.entrySet().iterator().next());
		}
		for(Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()) { //goes over each entry in the hashmap
			boolean inTable = false;
			for (int i = 0; i < maxAnagrams.size(); i++){ // checks if current entry is already in the table
				if(((entry.getValue()).equals(maxAnagrams.get(i).getValue()))){
					inTable = true;
				}
			}
			if(maxAnagrams.get(0).getValue().size() == entry.getValue().size() && !inTable){ // if the amount of anagrams is the same as the current max, insert into table
				maxAnagrams.add(entry);
			} else if(!inTable && maxAnagrams.get(0).getValue().size() < entry.getValue().size()){ //if current entry has more anagrams, get rid of current max and add entry
				maxAnagrams.clear();
				maxAnagrams.add(entry);
			}
		}
		return maxAnagrams;
	}
	
	public static void main(String[] args){
		
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
			System.out.println("Key of max anagrams : " + maxEntries.get(0).getKey());
			System.out.println("List of max anagrams : "+ maxEntries);
			System.out.println("Length of list of max anagrams : " + maxEntries.get(0).getValue().size());

	}

}
