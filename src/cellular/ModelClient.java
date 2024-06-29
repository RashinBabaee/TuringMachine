package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */
/**
 * this class is for setters and getters for clients
 */
public class ModelClient {
	private String userName;
	private String serverAddress;
	private int port;
	private String tmVar;
	private int tm[];
	private int tape[];

	/**
	 * this function is to set the username
	 * 
	 * @param userName is String
	 */
	public void setUsernames(String userName) {
		this.userName = userName;
	}

	/**
	 * this function is to set the serveraddress
	 * 
	 * @param serverAddress is String
	 */
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;

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
	 * this function is to set the tm
	 * 
	 * @param tm is array of integer
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
	 * it is the constructor
	 */
	public ModelClient() {

	}

}
