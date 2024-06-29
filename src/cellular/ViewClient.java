package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 * this is the class for interface of client window
 */
public class ViewClient {

	/**
	 * object of clientSocket
	 */
	Socket clientSocket;

	private Stage stage4 = new Stage();
	private TextField userTextField = new TextField();
	private TextField serverTextField = new TextField();
	private TextField portTextField = new TextField();
	private TextArea textArea = new TextArea();
	private ScrollPane scrollPane = new ScrollPane();
	private TextField tmTextField = new TextField();

	private ControllerClient controllerClient;

	/**
	 * this function is for when userTextField is empty
	 * 
	 * @return userTextField.getText()
	 */
	public String getUser() {
		if (userTextField.getText() == null || userTextField.getText().equals("")) {
			return Configuration.DEFAULT_USER;
		}
		return userTextField.getText();
	}

	/**
	 * this function is for when portTextField is empty
	 * 
	 * @return portTextField.getText()
	 */
	public String getPort() {
		if (portTextField.getText() == null || portTextField.getText().equals("")) {
			return Configuration.DEFAULT_PORT;
		}

		return portTextField.getText();
	}

	/**
	 * this function is for when serverTextField is empty
	 * 
	 * @return serverTextField.getText()
	 */
	public String getServer() {
		if (serverTextField.getText() == null || serverTextField.getText().equals("")) {
			return Configuration.DEFAULT_ADDR;
		}
		return serverTextField.getText();
	}

	/**
	 * this function is for showing text in textArea
	 * 
	 * @param text is String
	 */
	public void displayText(String text) {
		textArea.appendText(text);

	}

	/**
	 * this function is for getting tm as a text
	 * 
	 * @return tmTextField.getText(
	 */
	public String getTm() {
		return tmTextField.getText();
	}

	/**
	 * this function is for showing client window
	 */
	void show() {
		stage4.show();
	}

	/**
	 * it is the constructor for the graphical user interface of client window
	 * 
	 * @param controllerClient is a parameter for viewClient
	 */
	public ViewClient(ControllerClient controllerClient) {
		this.controllerClient = controllerClient;

		BorderPane borderMain = new BorderPane();
		Scene scene4 = new Scene(borderMain, 600, 440);
		stage4.setScene(scene4);
		stage4.setTitle("Game Client - JAP(fall 2023");

		FlowPane topFlow = new FlowPane();
		borderMain.setTop(topFlow);

		Image image = new Image("tm-client.png");
		ImageView imageView = new ImageView(image);
		topFlow.getChildren().add(imageView);
		topFlow.setAlignment(Pos.CENTER);

		FlowPane centerFlow = new FlowPane();
		borderMain.setCenter(centerFlow);

		Label user = new Label("User: ");

		Label serverLabel = new Label("Server: ");

		Label portLabel = new Label("Port: ");

		Button connectBut = new Button("Connect");
		Button endBut = new Button("End");
		Button newBut = new Button("New");
		Button validateBut = new Button("Validate");

		Label tmLabel = new Label("TM: ");

		Button sendBut = new Button("Send");
		Button receiveBut = new Button("Receive");
		Button runBut = new Button("Run");

		centerFlow.getChildren().add(user);
		FlowPane.setMargin(user, new Insets(10));

		centerFlow.getChildren().add(userTextField);
		FlowPane.setMargin(userTextField, new Insets(10));

		centerFlow.getChildren().add(serverLabel);
		FlowPane.setMargin(serverLabel, new Insets(10));

		centerFlow.getChildren().add(serverTextField);
		FlowPane.setMargin(serverTextField, new Insets(10));

		centerFlow.getChildren().add(portLabel);
		FlowPane.setMargin(portLabel, new Insets(10));

		centerFlow.getChildren().add(portTextField);
		FlowPane.setMargin(portTextField, new Insets(10));

		centerFlow.getChildren().add(connectBut);
		FlowPane.setMargin(connectBut, new Insets(10));
		connectBut.setTooltip(new Tooltip("Connect"));
		connectBut.setOnAction(controllerClient);

		centerFlow.getChildren().add(endBut);
		FlowPane.setMargin(endBut, new Insets(10));
		endBut.setTooltip(new Tooltip("End"));
		endBut.setOnAction(controllerClient);

		centerFlow.getChildren().add(newBut);
		FlowPane.setMargin(newBut, new Insets(10));
		newBut.setTooltip(new Tooltip("New"));
		newBut.setOnAction(controllerClient);

		centerFlow.getChildren().add(validateBut);
		FlowPane.setMargin(validateBut, new Insets(10));
		validateBut.setTooltip(new Tooltip("Validate"));
		validateBut.setOnAction(controllerClient);

		centerFlow.getChildren().add(tmLabel);
		FlowPane.setMargin(tmLabel, new Insets(10));

		centerFlow.getChildren().add(tmTextField);
		FlowPane.setMargin(tmTextField, new Insets(10));

		centerFlow.getChildren().add(sendBut);
		FlowPane.setMargin(sendBut, new Insets(10));
		sendBut.setTooltip(new Tooltip("Send"));
		sendBut.setOnAction(controllerClient);

		centerFlow.getChildren().add(receiveBut);
		FlowPane.setMargin(receiveBut, new Insets(10));
		receiveBut.setTooltip(new Tooltip("Receive"));
		receiveBut.setOnAction(controllerClient);

		centerFlow.getChildren().add(runBut);
		FlowPane.setMargin(runBut, new Insets(10));
		runBut.setTooltip(new Tooltip("Run"));
		runBut.setOnAction(controllerClient);

		FlowPane bottomFlow = new FlowPane();
		borderMain.setBottom(bottomFlow);
		bottomFlow.getChildren().add(textArea);
		bottomFlow.getChildren().add(scrollPane);
	}

}
