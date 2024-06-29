package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */
/**
 * This class is model class for server
 */
public class ModelServer {

	private int tm[];
	private int tape[];
	private int port;

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
	 * this function is to set the port
	 * 
	 * @param port is int
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * this is the constructor
	 */
	public ModelServer() {

	}

}
