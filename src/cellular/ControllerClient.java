package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */
import static java.lang.System.out;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;

/**
 * this class is controller class for user interface
 */
public class ControllerClient implements EventHandler<ActionEvent> {

	/**
	 * variable hostName
	 */
	String hostName;
	/**
	 * object of clientSocket
	 */
	Socket clientSocket;
	private ViewClient viewClient = new ViewClient(this);
	private ViewTm viewTm = new ViewTm();
	private ModelTm modelTm = new ModelTm();
	private BufferedReader inputFromServer;
	private PrintWriter outputToServer;

	/**
	 * it is a constructor
	 */
	public ControllerClient() {

	}

	/**
	 * this function shows the function show in viewClient
	 */
	public void show() {
		viewClient.show();
	}

	/**
	 * this function is for sending request from client to server
	 */
	public void sendRequest() {
		outputToServer.println(Configuration.PROTOCOL_RECVMODEL + Configuration.PROTOCOL_SEPARATOR
				+ viewClient.getUser() + Configuration.PROTOCOL_SEPARATOR + "request");
		outputToServer.flush();
	}

	/**
	 * this function is for sending data from client to server
	 * 
	 * @param data is String
	 */
	public void sendData(String data) {

		outputToServer.println(Configuration.PROTOCOL_SENDMODEL + Configuration.PROTOCOL_SEPARATOR
				+ viewClient.getUser() + Configuration.PROTOCOL_SEPARATOR + data);
		outputToServer.flush();
	}

	/**
	 * this function is for receiving data from server to client
	 * 
	 * @return receivedData that is String
	 * @throws IOException is transferring information
	 */
	public String receiveData() throws IOException {
		String receivedData = inputFromServer.readLine();
		return receivedData;
	}

	/**
	 * this function is for splitting information with protocol separator
	 * 
	 * @return data with splitting them
	 * @throws IOException is transferring information
	 */
	public String[] split() throws IOException {
		String[] splitData = receiveData().split(Configuration.PROTOCOL_SEPARATOR);
		return splitData;

	}

	/**
	 * this function is for connecting client to server
	 * 
	 * @throws IOException is transferring information
	 */
	public void connectToServer() throws IOException {
		viewClient.displayText("Starting the program\n");

		try {
			int port = Integer.parseInt(viewClient.getPort());
			if (port < 10000 && port > 65535) {
				System.out.println("port number should be between 10000 and 65535 ");
			} else {
				clientSocket = new Socket(hostName, port);
				inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);

				// Send a message to the server
				// outputToServer.println("Hello from the client!");
				sendData("Hello from the client!");
				viewClient.displayText("Hello from the client!\n");

				// Read the server's response
				String[] serverResponse = split();
				System.out.println("Server: " + serverResponse[2]);
				viewClient.displayText("Server: " + serverResponse[2]+"\n");

				String[] serverResponse1 = split();
				System.out.println("Server: " + serverResponse1[2]);
				viewClient.displayText("Server: " + serverResponse1[2]+"\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this function is for disconnecting client from server
	 */
	public void disconnectFromServer() {
		viewClient.displayText("Ending the program\n");

		// Closing the client socket
		try {
			outputToServer.flush();
			outputToServer.close();
			inputFromServer.close();
			clientSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this is a function for creating a new game
	 */
	public void createNewGame() {
		viewClient.displayText("pressed New game\n");
	}

	/**
	 * this is a function for validation of model
	 */
	public void validate() {
		viewClient.displayText("pressed Validate button\n");
	}

	/**
	 * this function is for sending tm
	 */
	public void sendTm() {

		viewClient.displayText("Sendig TM to server\n");
		String text = viewClient.getTm();
		boolean validBinary;

		try {

			if (text.length() == 20) {
				validBinary = true;
			} else {
				validBinary = false;
			}

		} catch (NumberFormatException e) {
			validBinary = false;

		}

		System.out.println(text);
		sendData(text);
	}

	/**
	 * this function is for receiving tm from server
	 */
	public void receiveTm() {
		viewClient.displayText("Receiving TM from server\n");
		String[] result;
		try {
			result = split();
			System.out.println("Result" + result[2]);
			viewClient.displayText("Result" + result[2]+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * this function is for running game in client window
	 */
	public void runGame() {
		viewClient.displayText("Pressed run button\n");
	}

	/**
	 * this is the function for when the user presses buttons
	 */
	public void handle(ActionEvent e) {

		String action = ((Control) e.getSource()).getTooltip().getText();

		out.println(action);

		if (action.equals("Connect")) {
			try {

				connectToServer();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (action.equals("End")) {
			disconnectFromServer();
		} else if (action.equals("New")) {
			createNewGame();
		} else if (action.equals("Validate")) {
			validate();
		} else if (action.equals("Send")) {
			sendTm();
		} else if (action.equals("Receive")) {
			sendRequest();
			receiveTm();

		} else if (action.equals("Run")) {
			runGame();

			viewTm.show();
			viewTm.setTm(viewClient.getTm());
		}
	}

}
