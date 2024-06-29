package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */
/**
 * this class is for setters and getters for turning machine
 */
public class ModelTm {

	private int tm[];
	private int tape[];

	/**
	 * this function is for changing array of integers to String
	 * 
	 * @return tmString
	 */
	public String tmString() {
		String tmString = "";
		for (int i = 0; i < tm.length; i++) {
			tmString += tm[i];

		}
		return tmString;
	}

	/**
	 * this function is to set the tm
	 * 
	 * @param tm is array of integers
	 */
	public void setTm(int tm[]) {
		this.tm = tm;
	}

	/**
	 * this function is to get the tm
	 * 
	 * @return tm
	 */
	public int[] getTm() {
		return tm;

	}

	/**
	 * this function is to set the tape
	 * 
	 * @param tape is array of integers
	 */
	public void setTape(int tape[]) {
		this.tape = tape;
	}

	/**
	 * this function is to get the tape
	 * 
	 * @return tape
	 */
	public int[] getTape() {
		return tape;

	}

	/**
	 * this is the constructor
	 */
	public ModelTm() {

	}
}
