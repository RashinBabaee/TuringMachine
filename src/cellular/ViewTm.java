package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * this is the class for interface of TM window
 */
public class ViewTm {

	private int tape[];

	private Stage stage5 = new Stage();
	private ControllerTm controllerTm = new ControllerTm();
	// ControllerClient controllerClient = new ControllerClient();
	private TextArea textArea = new TextArea();
	private TextField tmTextField = new TextField();
	private TextField tapeTextField = new TextField();

	/**
	 * this function is to set the tm
	 * 
	 * @param tm is String
	 */
	public void setTm(String tm) {
		// this.tm = modelTm.getTm();
		tmTextField.setText(tm);
	}

	/**
	 * this function is to get the tape
	 * 
	 * @return tape
	 */
	public String GetTape() {
		return tapeTextField.getText();
	}

	/**
	 * this function is for showing tm window
	 */
	public void show() {
		stage5.show();
	}

	/**
	 * this function is for showing turning machine window
	 */
	public ViewTm() {

		BorderPane borderMain = new BorderPane();
		Scene scene4 = new Scene(borderMain, 600, 240);
		stage5.setScene(scene4);
		stage5.setTitle("Turning Machine");

		FlowPane topFlow = new FlowPane();
		borderMain.setTop(topFlow);

		Image image = new Image("tm.png");
		ImageView imageView = new ImageView(image);
		topFlow.getChildren().add(imageView);
		topFlow.setAlignment(Pos.CENTER);

		FlowPane centerFlow = new FlowPane();
		borderMain.setCenter(centerFlow);

		Label tmLabel = new Label("TM");

		Label TapeLabel = new Label("Tape");

		Button runBut = new Button("Run");
		Button clearBut = new Button("Clear");

		centerFlow.getChildren().add(tmLabel);
		FlowPane.setMargin(tmLabel, new Insets(10));

		centerFlow.getChildren().add(tmTextField);
		FlowPane.setMargin(tmTextField, new Insets(10));

		centerFlow.getChildren().add(TapeLabel);
		FlowPane.setMargin(TapeLabel, new Insets(10));

		centerFlow.getChildren().add(tapeTextField);
		FlowPane.setMargin(tapeTextField, new Insets(10));

		centerFlow.getChildren().add(runBut);
		FlowPane.setMargin(runBut, new Insets(10));
		runBut.setTooltip(new Tooltip("Run"));
		runBut.setOnAction(controllerTm);

		centerFlow.getChildren().add(clearBut);
		FlowPane.setMargin(clearBut, new Insets(10));
		clearBut.setTooltip(new Tooltip("Clear"));
		clearBut.setOnAction(controllerTm);

		FlowPane bottomFlow = new FlowPane();
		borderMain.setBottom(bottomFlow);
		bottomFlow.getChildren().add(textArea);
	}

}
