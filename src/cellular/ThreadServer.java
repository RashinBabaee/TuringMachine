package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * this class is for connecting to client with socket
 */
public class ThreadServer implements Runnable {

	/**
	 * Socket variable.
	 */
	Socket clientSocket;
	/**
	 * Variables for number clients.
	 */
	int nclients = 0;

	/**
	 * Number of port.
	 */
	int portNumber = 0;

	/**
	 * Server socket.
	 */
	private ServerSocket serversocket;
	private ViewServer viewServer;
	private String tm;

	/**
	 * this is the function for connecting server to client
	 * 
	 * @param port is int
	 */
	public void startServer(int port) {
		portNumber = port;
		viewServer.displayText("Starting Server Thread on port " + portNumber+"\n");
		System.out.println("Starting Server Thread on port " + portNumber);
		try {
			serversocket = new ServerSocket(portNumber);
			Thread serverThread = new Thread(this);
			serverThread.start();
			System.out.println("Server running on " + InetAddress.getLocalHost() + " at port " + portNumber + "!");
			viewServer
					.displayText("Server running on " + InetAddress.getLocalHost() + " at port " + portNumber + "!\n");
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * the type of running is boolean
	 */
	boolean running = true;

	/**
	 * this function is for disconnecting server from client
	 */
	public void disconnect() {

		try {
			serversocket.close();
			running = false;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * this is a run function for server
	 */
	@Override
	public void run() {

		while (running) {
			try {
				clientSocket = serversocket.accept();
				nclients += 1;
				System.out.println(
						"Connecting " + clientSocket.getInetAddress() + " at port " + clientSocket.getPort() + ".");
				viewServer.displayText(
						"Connecting " + clientSocket.getInetAddress() + " at port " + clientSocket.getPort() + ".\n");
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
			Connection connection = new Connection();
			connection.start();
		}
	}

	/**
	 * this class is for sending and receiving information
	 */
	class Connection extends Thread {

		private PrintStream outputToClient = null;
		private BufferedReader inputFromClient;

		/**
		 * this function is for sending data from server to client
		 * 
		 * @param data is String
		 */
		public void sendData(String data) {

			outputToClient.println(Configuration.PROTOCOL_SENDMODEL + Configuration.PROTOCOL_SEPARATOR + "server"
					+ Configuration.PROTOCOL_SEPARATOR + data);
			outputToClient.flush();
		}

		/**
		 * this function is for receiving data from client
		 * 
		 * @return receivedDatais String
		 * @throws IOException is transferring information
		 */
		public String receiveData() throws IOException {
			String receivedData = inputFromClient.readLine();

			return receivedData;

		}

		/**
		 * his function is for splitting information with protocol separator
		 * 
		 * @return splitData is array of String
		 * @throws IOException is transferring information
		 */
		public String[] split() throws IOException {
			String data = receiveData();
			if (data != null) {
				String[] splitData = data.split(Configuration.PROTOCOL_SEPARATOR);
				return splitData;

			}
			return null;
		}

		/**
		 * Run method.
		 */
		public void run() {
			if (clientSocket != null) {
				try {
					outputToClient = new PrintStream(clientSocket.getOutputStream());
					inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

					sendData("Welcome! You are now connected.");
					viewServer.displayText("Welcome! You are now connected.\n");
					outputToClient.flush();

					String[] clientMessage;
					while ((clientMessage = split()) != null) {
						if (clientSocket != null && !clientSocket.isClosed()) {
							// Handle incoming messages from the client
							System.out.println(
									"Received from client " + clientSocket.getInetAddress() + ": " + clientMessage[2]);
							viewServer.displayText(
									"Received from client " + clientSocket.getInetAddress() + ": " + clientMessage[2]+"\n");

							viewServer.displayText("Server received: " + clientMessage[2]+"\n");
						}else {
							System.out.println("Socket is closed.");
		                break; // Exit the loop if the socket is closed
			            }


						
						if (clientMessage[0].equals(Configuration.PROTOCOL_RECVMODEL)) {

							sendData(tm);
							outputToClient.flush();
						} else if (clientMessage[0].equals(Configuration.PROTOCOL_SENDMODEL)) {
							tm = clientMessage[2];
							sendData("Server received: " + clientMessage[2]);

						}

					}
					// Client disconnected
					System.out.println("Client " + clientSocket.getInetAddress() + " disconnected!");
					viewServer.displayText("Client " + clientSocket.getInetAddress() + " disconnected!\n");
					nclients -= 1;
					System.out.println("Current client number: " + nclients);
					viewServer.displayText("Current client number: " + nclients);

					if (nclients == 0) {
						System.out.println("No active clients. Ending server...");
						viewServer.displayText("No active clients. Ending server...\n");
						serversocket.close();
					}

				}catch (IndexOutOfBoundsException e) {
				    // Handle IndexOutOfBoundsException
				    // Log the error and handle it appropriately
				    e.printStackTrace();
				}catch (SocketException e) {
					clientSocket = null;

				} catch (IOException e) {
					e.printStackTrace();
				}catch (Exception e) {
				    // Handle other exceptions
				    // Log the error and handle it appropriately
				    e.printStackTrace();
				}finally {
					if (clientSocket != null) {
						// clientSocket.close();
					}
				}
			}
		}
		/**
		 * this is a constructor
		 */
		public Connection() {
			
		}
	}

	/**
	 * this is a constructor for thread server class
	 * 
	 * @param viewServer is a parameter
	 */
	public ThreadServer(ViewServer viewServer) {
		this.viewServer = viewServer;
	}

}
