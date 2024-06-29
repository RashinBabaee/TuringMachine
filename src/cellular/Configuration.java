package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */

/**
 * This class is for protocols
 */
public class Configuration {
	/**
	 * it is a protocol for separating
	 */
	static final String PROTOCOL_SEPARATOR = "#";
	/**
	 * it is a protocol for ending
	 */
	static final String PROTOCOL_END = "P0";
	/**
	 * it is a protocol for sending model
	 */
	static final String PROTOCOL_SENDMODEL = "P1";
	/**
	 * it is a protocol for receiving model
	 */
	static final String PROTOCOL_RECVMODEL = "P2";
	/**
	 * it is a default user that is String
	 */
	static String DEFAULT_USER = "User1";
	/**
	 * it is a default address that is String
	 */
	static String DEFAULT_ADDR = "localhost";
	/**
	 * it is a default port that is String
	 */
	static String DEFAULT_PORT = "12345";

	/**
	 * it is a constructor for configuration class
	 */
	public Configuration() {

	}

}
