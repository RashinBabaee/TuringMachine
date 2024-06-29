package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 5 Nov 2023
 */

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JColorChooser;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is for showing game of life window
 */
public class GameView {

	private Stage stage3 = new Stage();
	private BorderPane border3 = new BorderPane();
	private BorderPane borderMain = new BorderPane();
	private FlowPane centerFlow3 = new FlowPane();
	private Color colorBlue = Color.rgb(0, 0, 255); // color of board game
	private Color colorGreen = Color.rgb(0, 255, 0); // color of board game
	private Color colorRed = Color.rgb(255, 0, 0); // color of board game
	private Color colorYellow = Color.rgb(255, 255, 0); // color of board game
	private Color colorNavyblue = Color.rgb(0, 30, 255); // color of board game
	private Color colorPink = Color.rgb(255, 0, 234); // color of board game
	private Color colorLightblue = Color.rgb(0, 255, 238); // color of board game
	private Color colorOrange = Color.rgb(255, 123, 0); // color of board game
	private Color colorPurple = Color.rgb(255, 0, 247); // color of board game
	private Color arrayColor[] = { colorRed, colorGreen, colorNavyblue, colorYellow, colorPink, colorLightblue,
			colorBlue, colorOrange, colorPurple };
	private ColorPicker colorPicker = new ColorPicker(colorBlue);
	private Button random = new Button("Random");
	private Button manual = new Button("Manual");
	private Button colorButton = new Button("Color");
	private CheckBox cb = new CheckBox("Multicolor");
	private Button start = new Button("Start");
	private Button stop = new Button("Stop");
	private TextField textFieldModelGame = new TextField();
	private Controller controller = new Controller(this);
	private Model model = new Model();
	private TextField textFieldSteps = new TextField();
	private Label stepsLabel = new Label("Steps: ");

	private Menu gameMenu = new Menu("Game");
	private MenuItem randomMenuItem = new MenuItem("Random");
	private MenuItem manualMenuItem = new MenuItem("Manual");
	private MenuItem colorMenuItem = new MenuItem("Color");
	private MenuItem startMenuItem = new MenuItem("Start");
	private MenuItem stopMenuItem = new MenuItem("Stop");
	private Menu languageMenu = new Menu("Language");
	private MenuItem englishMenuItem = new MenuItem("English");
	private MenuItem frenchMenuItem = new MenuItem("French");
	private Menu helpMenu = new Menu("Help");
	private MenuItem aboutMenuItem = new MenuItem("About");
	private Label modelLabelGame = new Label("Model: ");

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
			modelLabelGame.setText(texts.getString("MODELLABEL")); // get String and change it to text for MODELLABEL
			random.setText(texts.getString("RANDOM"));
			manual.setText(texts.getString("MANUAL"));
			start.setText(texts.getString("START"));
			stop.setText(texts.getString("STOP"));

			colorButton.setText(texts.getString("COLOR"));
			cb.setText(texts.getString("MULTICOLOR"));
			stepsLabel.setText(texts.getString("STEPS"));

			gameMenu.setText(texts.getString("GAME"));
			randomMenuItem.setText(texts.getString("RANDOM"));
			manualMenuItem.setText(texts.getString("MANUAL"));
			colorMenuItem.setText(texts.getString("COLOR"));
			startMenuItem.setText(texts.getString("START"));
			stopMenuItem.setText(texts.getString("STOP"));
			languageMenu.setText(texts.getString("LANGUAGE"));
			englishMenuItem.setText(texts.getString("ENGLISH"));
			frenchMenuItem.setText(texts.getString("FRENCH"));
			helpMenu.setText(texts.getString("HELP"));
			aboutMenuItem.setText(texts.getString("ABOUT"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int[] pattern = new int[18];
	private int width = 30;
	private int height = 30;


	private Button[][] viewGame = new Button[width][height];

	private GridPane grid = new GridPane();


	/**
	 * this function is for getting random model
	 */
	void randomModel() {
		int originalModel[][] = model.getModel();
		Random random = new Random();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < originalModel.length; x++) {

				if (random.nextInt() % 3 == 0) {
					originalModel[x][y] = 1;
				} else {
					originalModel[x][y] = 0;
				}

			}
		}

	}

	/**
	 * this function is for resetting the board
	 */
	void clearModel() {
		int originalModel[][] = model.getModel();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < originalModel.length; x++) {

				originalModel[x][y] = 0;

			}
		}

	}

	/**
	 * this function is for manually setting the model
	 * 
	 * @param x is int
	 * @param y is int
	 */
	public void pressButton(int x, int y) {
		int originalModel[][] = model.getModel();
		if (originalModel[x][y] == 0) {
			originalModel[x][y] = 1;
		} else {
			originalModel[x][y] = 0;
		}
		drawColor();
	}

	private Font font = new Font(1);

	/**
	 * this function is for creating visual board
	 */
	void makeBoardGame() {
		for (int y = 0; y < 30; y++) {
			for (int x = 0; x < 30; x++) {
				Button square = new Button();
				square.setFont(font);
				square.setPrefSize(15, 15);
				square.setTooltip(new Tooltip("Button " + x + " " + y));
				square.setOnAction(controller);;

				grid.add(square, x, y);
				viewGame[x][y] = square;

			}
		}
		border3.setCenter(grid);
	}

	/**
	 * this function is for counting neighbors of a cell with the coordinates x and y
	 * 
	 * @param x is x coordinate
	 * @param y is y coordinate
	 * @return the number of neighbors
	 */
	public int neighbor(int x, int y) {
		int originalModel[][] = model.getModel();
		int xp = x - 1;
		int yp = y - 1;
		int sum = 0;

		for (xp = x - 1; xp <= x + 1; xp++) {
			for (yp = y - 1; yp <= y + 1; yp++) {
				if ((0 <= xp && xp < originalModel.length) && (0 <= yp && yp < height)) {
					sum = sum + originalModel[xp][yp];
				}
			}
		}
		return sum - originalModel[x][y];

	}

	/**
	 * this function is for calculating next model using the pattern provided by user
	 */
	void calculateNextModel() {
		
		
		int originalModel[][] = model.getModel();
		int[][] nextModel = new int[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < originalModel.length; x++) {
				int neighbors = neighbor(x, y);
				if (originalModel[x][y] == 0) {
					nextModel[x][y] = pattern[neighbors];

				} else {
					nextModel[x][y] = pattern[neighbors + 9];

				}

			}
		}
		model.setModel(nextModel);
		

	}

	/**
	 * this function is for showing the window
	 */
	void showGame() {
		stage3.show();
	}

	/**
	 * this function is for generating background from color
	 * 
	 * @param color is color of background
	 * @return background object
	 */
	private static Background background(Color color) {
		return new Background(new BackgroundFill(color, null, null));
	}

	/**
	 * this function is for setting the color of cells to the color selected by the user
	 * 
	 * @param color is the color to set the cells to
	 */
	public void chooseColor(Color color) {
		int originalModel[][] = model.getModel();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (originalModel[x][y] == 1) {
					viewGame[x][y].setBackground(background(color));

				} else {
					viewGame[x][y].setBackground(background(Color.WHITE));
				}

			}
		}
	}

	/**
	 * this function is for displaying multiple colors based on the number of neighbors
	 */
	public void multiColorMethod() {
		int originalModel[][] = model.getModel();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (originalModel[x][y] == 1) {
					int n = neighbor(x, y);
					Color neighborColor = arrayColor[n];
					viewGame[x][y].setBackground(background(neighborColor));

				} else {
					viewGame[x][y].setBackground(background(Color.WHITE));
				}

			}
		}

	}

	/**
	 * this method is for displaying single color selected by user
	 */
	public void singleColor() {
		int originalModel[][] = model.getModel();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (originalModel[x][y] == 1) {

					viewGame[x][y].setBackground(background(colorPicker.getValue()));

				} else {
					viewGame[x][y].setBackground(background(Color.WHITE));
				}

			}
		}
	}

	/**
	 * this method is for generating and displaying a random board when the user presses the random 
	 * button
	 */
	public void random() {

		randomModel();
		drawColor();
	}

	/**
	 * this function is for displaying an empty board so the user can manually select
	 * the model
	 */
	public void manual() {
		clearModel();
		drawColor();

	}

	/**
	 * this function is for generating next model using the pattern typed by user
	 */
	public void next() {
		int step = model.getSteps();
		step++;
		textFieldSteps.setText("" + step);
		model.setSteps(step);
		String text = textFieldModelGame.getText();
		boolean validBinary;
		int number;

		try {
			number = Integer.parseInt(text, 2);
			if (text.length() == 18) {
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
			calculateNextModel(); // call make model
			drawColor();
		}
	}

	/**
	 * this function is for coloring all the cells with the single color
	 */
	public void color() {
		chooseColor(colorPicker.getValue());
	}

	/**
	 * this function is for coloring all the cells with multiple colors
	 */
	public void multiColor() {

		drawColor();
	}

	/**
	 * this function is for coloring the cells
	 */
	public void drawColor() {
		if (cb.isSelected()) {

			multiColorMethod();

		} else {
			singleColor();
		}

	}

	/**
	 * it is the constructor for the graphical user interface of game of life window
	 */
	public GameView() {

		model.setModel(new int[width][height]);
		Scene scene3 = new Scene(borderMain, 600, 745);
		borderMain.setCenter(border3);
		stage3.setScene(scene3);
		stage3.setTitle("GameOfLife");

		FlowPane topFlow3 = new FlowPane();
		border3.setTop(topFlow3);

		Image image3 = new Image("gl.png");
		ImageView imageView3 = new ImageView(image3);
		topFlow3.getChildren().add(imageView3);
		topFlow3.setAlignment(Pos.CENTER);

		border3.setCenter(centerFlow3);

		FlowPane bottomFlow3 = new FlowPane();
		border3.setBottom(bottomFlow3);

		bottomFlow3.getChildren().add(random);
		FlowPane.setMargin(random, new Insets(10));
		random.setTooltip(new Tooltip("Random"));
		random.setOnAction(controller);

		bottomFlow3.getChildren().add(manual);
		FlowPane.setMargin(manual, new Insets(10));
		manual.setTooltip(new Tooltip("Manual"));
		manual.setOnAction(controller);

		bottomFlow3.getChildren().add(modelLabelGame);

		bottomFlow3.getChildren().add(textFieldModelGame);
		FlowPane.setMargin(textFieldModelGame, new Insets(10));

		bottomFlow3.getChildren().add(colorPicker);
		FlowPane.setMargin(colorPicker, new Insets(10));

		bottomFlow3.getChildren().add(colorButton);
		FlowPane.setMargin(colorButton, new Insets(10));
		colorButton.setTooltip(new Tooltip("Color"));
		colorButton.setOnAction(controller);

		bottomFlow3.getChildren().add(cb);
		FlowPane.setMargin(cb, new Insets(10));
		cb.setTooltip(new Tooltip("multiColor"));
		cb.setOnAction(controller);

		bottomFlow3.getChildren().add(start);
		FlowPane.setMargin(start, new Insets(10));
		start.setTooltip(new Tooltip("Start"));
		start.setOnAction(controller);

		bottomFlow3.getChildren().add(stepsLabel);

		bottomFlow3.getChildren().add(textFieldSteps);
		FlowPane.setMargin(textFieldSteps, new Insets(10));

		bottomFlow3.getChildren().add(stop);
		FlowPane.setMargin(stop, new Insets(10));
		stop.setTooltip(new Tooltip("Stop"));
		stop.setOnAction(controller);

		makeBoardGame();
		randomModel();
		drawColor();

		gameMenu.getItems().add(randomMenuItem);

		randomMenuItem.setOnAction(controller);
		randomMenuItem.setId("Random");
		gameMenu.getItems().add(manualMenuItem);
		manualMenuItem.setId("Manual");
		manualMenuItem.setOnAction(controller);
		gameMenu.getItems().add(colorMenuItem);
		colorMenuItem.setId("Color");
		colorMenuItem.setOnAction(controller);
		gameMenu.getItems().add(startMenuItem);
		startMenuItem.setId("Start");
		startMenuItem.setOnAction(controller);
		gameMenu.getItems().add(stopMenuItem);
		stopMenuItem.setId("Stop");
		stopMenuItem.setOnAction(controller);

		englishMenuItem.setOnAction(controller);
		englishMenuItem.setId("English");

		frenchMenuItem.setOnAction(controller);
		frenchMenuItem.setId("French");
		languageMenu.getItems().add(englishMenuItem);
		languageMenu.getItems().add(frenchMenuItem);

		helpMenu.getItems().add(aboutMenuItem);
		helpMenu.setId("Help");
		aboutMenuItem.setOnAction(controller);
		aboutMenuItem.setId("About");

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(gameMenu);
		menuBar.getMenus().add(languageMenu);
		menuBar.getMenus().add(helpMenu);

		borderMain.setTop(menuBar);

	}

}
