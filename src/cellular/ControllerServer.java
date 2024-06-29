package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */
import static java.lang.System.out;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;

/**
 * this class is controller class for user interface for server
 */
public class ControllerServer implements EventHandler<ActionEvent> {

	/**
	 * this is an object
	 */
	ThreadServer threadServer;

	private ViewServer viewServer = new ViewServer(this);
	private ServerSocket serverSocket;
	private Socket clientSocket;
	/**
	 * model is String
	 */
	String model;

	/**
	 * this function shows the function show in viewServer
	 */
	public void show() {
		viewServer.show();
	}

	/**
	 * this function is for connecting server to clients
	 */
	public void acceptConnections() {
		viewServer.displayText("Start the connection\n");
		try {

			int port = Integer.parseInt(viewServer.getPort());
			if (port < 10000 || port > 65000) {
				System.out.println("port number should be between 10000 and 65000");

			} else {

				threadServer = new ThreadServer(viewServer);
				threadServer.startServer(port);
			}

		} catch (NumberFormatException e) {
			System.out.println("invalid port number");
			viewServer.displayText("invalid port number\n");
		}
	}

	/**
	 * this function is for disconnecting server from client
	 */
	public void disconnectClients() {
		viewServer.displayText("Pressed disconnect in server\n");

		if (threadServer != null) {
			threadServer.disconnect();
		}
	}

	/**
	 * this function is for accepting one connection
	 */
	public void acceptOneConnection() {

	}

	/**
	 * this function is for storing tm in server
	 */
	public void storeTm() {

	}

	/**
	 * this function
	 */
	public void model() {
		viewServer.displayText("Pressed model button\n");

	}

	/**
	 * this function is for finalizing
	 */
	public void finalizes() {
		viewServer.displayText("Pressed finalizes button\n");

	}

	/**
	 * this is the function for when the user presses buttons
	 */
	public void handle(ActionEvent e) {
		String action = ((Control) e.getSource()).getTooltip().getText();
		out.println(action);

		if (action.equals("Start")) {
			acceptConnections();
		} else if (action.equals("Model")) {
			model();
		} else if (action.equals("Finalizes")) {
			finalizes();
		} else if (action.equals("End")) {
			disconnectClients();
		}
	}
	/**
	 * this is a constructor
	 */
	public ControllerServer() {
		
	}

}
