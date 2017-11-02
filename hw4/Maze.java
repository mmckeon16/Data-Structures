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
        } else if (x == maze.getNCols()-1 && y == maze.getNRows()-1){ // it reached the winning block
        	maze.recolor(x,y,PATH);
    		return true;
        } else if(maze.getColor(x,y).equals(BACKGROUND) || maze.getColor(x,y).equals(TEMPORARY)){ //this block has already been visited
        	return false;
        }else if(maze.getColor(x, y).equals(NON_BACKGROUND)){ //the block has not been visited
        	maze.recolor(x,y,TEMPORARY);
        	boolean foundPath = findMazePath(x+1, y) || findMazePath(x, y+1) || findMazePath(x-1, y) || findMazePath(x, y-1);
        	if(foundPath == true){ //recoloring the path green
        		maze.recolor(x,y,PATH);
        	}
        	return foundPath;
        } 
        return false;
    }
    
    public boolean isCycle(int x, int y, Stack<PairInt> trace, Stack<PairInt> temp){
    	if(x < 0 || y < 0 || x > maze.getNCols()-1 || y > maze.getNRows()-1){ // block is out of bounds
        	return false;
        }
    	if(maze.getColor(x, y).equals(NON_BACKGROUND)){ //check in bounds!!
	    	if(trace.search(new PairInt(x,y)) < 1){ //not already in the stack
	    		temp.push(new PairInt(x,y));
	    		int count = 0;
	    		if(x+1 < maze.getNCols() && maze.getColor(x+1, y).equals(NON_BACKGROUND)){
	        		count++;
	        	}
	        	if(x-1 > -1 && maze.getColor(x-1, y).equals(NON_BACKGROUND)){
	        		count++;
	        	}
	        	if(y-1 > -1 && maze.getColor(x, y-1).equals(NON_BACKGROUND)){
	        		count++;
	        	}
	        	if(y+1 < maze.getNRows() && maze.getColor(x, y+1).equals(NON_BACKGROUND)){
	        		count++;
	        	}
	        	if(count >= 2){
	        		return false;
	        	}
	        	if(x == maze.getNCols()-1 && y == maze.getNRows()-1){
	        		return false;
	        	}
	    		return isCycle(x+1,y,trace,temp) || isCycle(x-1,y,trace,temp)
	    				||isCycle(x,y-1,trace,temp) || isCycle(x,y+1,trace,temp);
	    	} else{
	    		return true;
	    	}
    	} else {
    		trace.addAll(temp);
    		return false;
    	}
    }
    
    /**
     * test without first method!!!
     * @param x
     * @param y
     * @param result
     * @param trace
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
        if(x < 0 || y < 0 || x > maze.getNCols()-1 || y > maze.getNRows()-1){ // block is out of bounds
        	return;
        } else if (x == maze.getNCols()-1 && y == maze.getNRows()-1){ // it reached the winning block
        	trace.add(new PairInt(x,y));
        	ArrayList<PairInt> p = new ArrayList<PairInt>();
        	p.addAll(trace);
        	result.add(p);
    		return;
        } else if(maze.getColor(x,y).equals(PATH) || maze.getColor(x, y).equals(BACKGROUND)){ //this block has already been visited
        	return;
        }else if(maze.getColor(x, y).equals(NON_BACKGROUND)){ //the block has not been visited
        	int count = 0;
        	boolean icr = false;
        	boolean icl = false;
        	boolean icu = false;
        	boolean icd = false;
        	maze.recolor(x,y,PATH);
        	if(x+1 < maze.getNCols() && maze.getColor(x+1, y).equals(NON_BACKGROUND)){
        		count++;
        	}
        	if(x-1 > -1 && maze.getColor(x-1, y).equals(NON_BACKGROUND)){
        		count++;
        	}
        	if(y-1 > -1 && maze.getColor(x, y-1).equals(NON_BACKGROUND)){
        		count++;
        	}
        	if(y+1 < maze.getNRows() && maze.getColor(x, y+1).equals(NON_BACKGROUND)){
        		count++;
        	}
        	if(count >= 3){
        		Stack<PairInt> temp = new Stack<PairInt>();
        		icr = isCycle(x+1, y, trace, temp);
        		icl = isCycle(x-1, y, trace, temp);	
        		icd = isCycle(x, y-1, trace, temp);
        		icu = isCycle(x, y+1, trace, temp);
        		if(icr){ //cycle to the right
        			trace.push(new PairInt(x,y));
    	        	findMazePathStackBased(x-1,y,result,trace);
    	        	findMazePathStackBased(x,y+1,result,trace);
    	        	findMazePathStackBased(x,y-1,result,trace);
        		}else if(icl){ //cycle to the left
        			trace.push(new PairInt(x,y));
    	        	findMazePathStackBased(x+1,y,result,trace);
    	        	findMazePathStackBased(x,y+1,result,trace);
    	        	findMazePathStackBased(x,y-1,result,trace);
        		} else if(icd){ // cycle below
        			trace.push(new PairInt(x,y));
    	        	findMazePathStackBased(x+1,y,result,trace);
    	        	findMazePathStackBased(x-1,y,result,trace);
    	        	findMazePathStackBased(x,y+1,result,trace);
        		}else if(icu){ //cycle above
        			trace.push(new PairInt(x,y));
    	        	findMazePathStackBased(x+1,y,result,trace);
    	        	findMazePathStackBased(x-1,y,result,trace);
    	        	findMazePathStackBased(x,y-1,result,trace);
        		} else{
        			trace.push(new PairInt(x,y));
        			findMazePathStackBased(x+1,y,result,trace);
        	        findMazePathStackBased(x-1,y,result,trace);
        	        findMazePathStackBased(x,y-1,result,trace);
        	        findMazePathStackBased(x,y+1,result,trace);
        		}
        	} else { //TODO consider multiple cycles
        		trace.push(new PairInt(x,y));
        		findMazePathStackBased(x+1,y,result,trace);
    	        findMazePathStackBased(x-1,y,result,trace);
    	        findMazePathStackBased(x,y-1,result,trace);
    	        findMazePathStackBased(x,y+1,result,trace);
        	}
        	trace.pop();
	        maze.recolor(x,y,NON_BACKGROUND);
        	}
        	if(!trace.isEmpty()){
            	trace.pop();       		
        	}

        	return;
      } 


    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0,0,result,trace);
    	return result;
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    

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
