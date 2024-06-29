package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 5 Nov 2023
 */

/**
 * This class is model class
 */
public class Model {
	
	private int model[][];
	private int steps;
	
	/**
	 * this function is to set the model
	 * @param model is int[][]
	 */
	public void setModel(int model[][]) {
		this.model = model;
	}
	
	/**
	 * this function is to get the model
	 * @return model
	 */
	public int[][] getModel() {
		return model;
		
	}
	/**
	 * this function is to set the steps
	 * @param steps is equal to steps
	 */
	public void setSteps(int steps) {
		this.steps = steps;
	}
	/**
	 * this function is to get the steps
	 * @return steps
	 */
	public int getSteps() {
		return steps;
		
	}
	/**
	 * this is the constructor
	 */
	public Model() {
		
	}

}
