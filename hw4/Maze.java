package hw4;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        if(x < 0 || y < 0 || x > maze.getNCols()-1 || y > maze.getNRows()-1){ // block is out of bounds
        	return false;
        } else if(maze.getColor(x,y).equals(BACKGROUND) || maze.getColor(x,y).equals(TEMPORARY)){ //this block has already been visited
        	return false;
        }else if (x == maze.getNCols()-1 && y == maze.getNRows()-1){ // it reached the winning block
        	maze.recolor(x,y,PATH);
    		return true;
        } else if(maze.getColor(x, y).equals(NON_BACKGROUND)){ //the block has not been visited
        	maze.recolor(x,y,TEMPORARY);
        	boolean foundPath = findMazePath(x+1, y) || findMazePath(x, y+1) || findMazePath(x-1, y) || findMazePath(x, y-1);
        	if(foundPath == true){ //recoloring the path green
        		maze.recolor(x,y,PATH);
        	}
        	return foundPath;
        } 
        return false;
    }
    
    /**
     * The recursive helper method for findAllMazePaths which goes through all possible paths and adds them to the Arraylist Result
     * @param x current x coordinate
     * @param y current y coordinate
     * @param result ArrayList<ArrayList<PairInt>> that will be returned in findAllMazePaths of all possible paths
     * @param trace Stack<PairInt> which keeps track of current path
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
        if(x < 0 || y < 0 || x > maze.getNCols()-1 || y > maze.getNRows()-1){ // block is out of bounds
        	return;
        } else if (x == maze.getNCols()-1 && y == maze.getNRows()-1){ // it reached the winning block
        	trace.add(new PairInt(x,y));
        	ArrayList<PairInt> p = new ArrayList<PairInt>();
        	p.addAll(trace);
        	result.add(p);
        	trace.pop();
    		return;
        } else if(maze.getColor(x,y).equals(PATH) || maze.getColor(x, y).equals(BACKGROUND)){ //this block has already been visited
        	return;
        }else if(maze.getColor(x, y).equals(NON_BACKGROUND)){ //the block has not been visited
        	maze.recolor(x,y,PATH);
        	 //TODO consider multiple cycles
        		trace.push(new PairInt(x,y));
        		findMazePathStackBased(x+1,y,result,trace);
    	        findMazePathStackBased(x-1,y,result,trace);
    	        findMazePathStackBased(x,y-1,result,trace);
    	        findMazePathStackBased(x,y+1,result,trace);
        	if(!trace.isEmpty()){
            	trace.pop();
        	}
	        maze.recolor(x,y,NON_BACKGROUND);
        	}
        	return;
      } 

    /**
     * Method that returns an ArrayList<ArrayList<PairInt>> of all possible paths to the exit
     * @param x current x coordinate
     * @param y current y coordinate
     * @return ArrayList<ArrayList<PairInt>> of all possible paths
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0,0,result,trace);
    	return result;
    }
    
    /**
     * Finds all possible paths, and then finds the minimum path and returns it, will return the empty
     * @param x current x coordinate
     * @param y current y coordinate
     * @return ArrayList<PairInt> minimum path to exit from (0,0)
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList<ArrayList<PairInt>> result = findAllMazePaths(0,0);
    	if(result.size() == 0){
    		return new ArrayList<PairInt>(); 
    	}
    	ArrayList<PairInt> minSize = result.get(0);
    	for(int i = 0; i < result.size(); i++){
    		if(minSize.size() > result.get(i).size()){
    			minSize = result.get(i);
    		}
    	}
    	return minSize;
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
