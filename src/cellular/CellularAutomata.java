package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 5 Nov 2023
 */

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is for showing cellular automata window
 */
public class CellularAutomata {

	private Label language = new Label("Language"); // language label for cellular automata window
	private Label modelLabel = new Label("Model: "); // model label for cellular automata window
	private Button setButton = new Button("Set"); // set button for cellular automata window
	private Stage stage2 = new Stage(); // stage for cellular automata window

	private Locale currentLocale;
	private static ResourceBundle texts;

	/**
	 * this function updates languages
	 * 
	 * @param lang is String
	 */
	public void updateInterface(String lang) {
		String languages = null;
		String country = null;

		switch (lang) {
		case "English": // if it is English
			languages = "en";
			country = "CA";

			break;
		case "French": // if it is french
			languages = "fr";
			country = "CA";

			break;

		}
		try {

			currentLocale = new Locale.Builder().setLanguage(languages).setRegion(country).build();
			texts = ResourceBundle.getBundle("resources/texts", currentLocale);
			language.setText(texts.getString("LANGUAGE")); // get String and change it to text for LANGUAGE
			modelLabel.setText(texts.getString("MODELLABEL")); // get String and change it to text for MODELLABEL
			setButton.setText(texts.getString("SETBUTTON")); // get String and change it to text for SETBUTTON

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int[] pattern = new int[] { 0, 0, 0, 1, 0, 0, 1, 0 };
	private int width = 370;
	private int height = 33;
	private int[][] model = new int[width][height];
	private Label[][] view = new Label[width][height];
	// background color for top and bottom of window
	private BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY);
	private Background background = new Background(backgroundFill);
	// background color when the grid is 1
	private BackgroundFill backgroundFillBlack = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
	private Background backgroundBlack = new Background(backgroundFillBlack);
	// background color when the grid is 0
	private BackgroundFill backgroundFillWhite = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
	private Background backgroundWhite = new Background(backgroundFillWhite);

	private GridPane grid = new GridPane();
	private BorderPane border2 = new BorderPane(); // border for cellular automata window
	private int[][][] rules = new int[2][2][2];

	/**
	 * generates numeric diagram by applying the rule to the previous line
	 */
	void makeModel() {

		rules[1][1][1] = pattern[0];
		rules[1][1][0] = pattern[1];
		rules[1][0][1] = pattern[2];
		rules[1][0][0] = pattern[3];
		rules[0][1][1] = pattern[4];
		rules[0][1][0] = pattern[5];
		rules[0][0][1] = pattern[6];
		rules[0][0][0] = pattern[7];

		model[width / 2][0] = 1;
		for (int y = 1; y < height; y++) {
			for (int x = 1; x < width - 1; x++) { // generate next lines of pattern
				int l = model[x - 1][y - 1];
				int c = model[x][y - 1];
				int r = model[x + 1][y - 1];
				model[x][y] = rules[l][c][r];

			}
		}

	}

	/**
	 * make board as a label and add it in the grid
	 */
	void makeBoard() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < model.length; x++) {
				Label square = new Label();
				square.setPrefSize(3, 1);

				view[x][y] = square;

				square.setBackground(backgroundWhite);

				grid.add(square, x, y);

			}
		}
		border2.setCenter(grid);
	}

	/**
	 * this function can draw the model
	 */
	void drawModel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < model.length; x++) {

				if (model[x][y] == 0) {
					view[x][y].setBackground(backgroundWhite); // if the grid is 0, the color is white

				} else {

					view[x][y].setBackground(backgroundBlack); // if the grid is 1, the color is black
				}

			}
		}

	}

	/**
	 * this function can show the stage
	 */
	void show() {
		stage2.show();
	}

	/**
	 * it is the interface of cellular automata window
	 */
	public CellularAutomata() {

		Scene scene2 = new Scene(border2, 1100, 750); // scene for cellular automata window
		stage2.setScene(scene2);
		stage2.setTitle("Cellular Automata"); // title for cellular automata window

		FlowPane topFlow2 = new FlowPane(); // top flow for cellular automata window
		border2.setTop(topFlow2);

		Image image2 = new Image("ca.png"); // image for cellular automata window
		ImageView imageView2 = new ImageView(image2);
		topFlow2.getChildren().add(imageView2);
		topFlow2.setAlignment(Pos.CENTER);

		FlowPane bottomFlow2 = new FlowPane(); // bottom flow for cellular automata window
		border2.setBottom(bottomFlow2);

		bottomFlow2.getChildren().add(language);

		String array2[] = new String[] { "English", "French" };
		ComboBox<String> comboBox2 = new ComboBox<>(FXCollections.observableArrayList(array2)); // combox for cellular
																								// automata window
		comboBox2.setValue(array2[0]);
		comboBox2.setOnAction(s -> {
			String currentLanguage = comboBox2.getValue();
			System.out.println(currentLanguage);
			updateInterface(currentLanguage);
		});
		bottomFlow2.getChildren().add(comboBox2);

		bottomFlow2.getChildren().add(modelLabel);

		TextField textField = new TextField(); // text field for cellular automata window
		bottomFlow2.getChildren().add(textField);

		bottomFlow2.getChildren().add(setButton);

		setButton.setOnMouseClicked(s -> { // if user clicks set button, it should show cellular automata
			String text = textField.getText();
			boolean validBinary;
			int number;

			try {
				number = Integer.parseInt(text, 2);
				if (text.length() == 8) {
					validBinary = true;
				} else {
					validBinary = false;
				}

			} catch (NumberFormatException e) {
				validBinary = false;
				number = 0;
			}
			System.out.println(number);
			if (validBinary) {
				char[] charArray = text.toCharArray();
				int[] intArray = new int[charArray.length]; // change to int array
				for (int i = 0; i < charArray.length; i++) {
					if (charArray[i] == '1') {
						intArray[i] = 1;
					}

				}
				pattern = intArray;
				makeModel(); // call make model
				drawModel(); // call draw model
			}

		});
		bottomFlow2.setAlignment(Pos.CENTER); // align bottom flow

		bottomFlow2.setBackground(background);
		topFlow2.setBackground(background);

		FlowPane.setMargin(comboBox2, new Insets(10));
		FlowPane.setMargin(modelLabel, new Insets(10));
		FlowPane.setMargin(setButton, new Insets(15));

		makeBoard(); // call make board

	}

}
