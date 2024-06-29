package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 5 Nov 2023
 */

import static java.lang.System.out;

import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

/**
 * this class in controller class for user interface
 */
public class Controller implements EventHandler<ActionEvent>{
	/**
	 * reference to the game view
	 */
	GameView game;
	/**
	 * this function is for when the user presses the random button
	 */
	private void random() {
		game.random();
	}
	/**
	 * this function is for when the user presses the manual button 
	 */
	private void manual() {
		game.manual();
	}
	/**
	 * this function is for when the user presses the start button 
	 */
	private void start() {
		timer.start();
	}
	/**
	 * this function is for when the user presses the stop button 
	 */
	private void stop() {
		timer.stop();
	}
	/**
	 * this function is for when the user presses the color button 
	 */
	private void color() {
		game.color();
	}
	/**
	 * this function is for when the user selects the multicolor checkbox
	 */
	private void multiColor() {
		game.multiColor();
	}
	
	private Timer timer = new Timer(1000, e -> {
		game.next();
	});
	


	/**
	 * this is the constructor for the user interface controller
	 * @param game is game
	 */
	public Controller(GameView game) {
		this.game = game;
	}

	@Override
	/**
	 * this is the function for when the user presses buttons
	 */
	public void handle(ActionEvent e) {


		Object o = e.getSource();
		String actionString[];
		if(o instanceof MenuItem) {
			actionString = ((MenuItem)o).getId().split(" ");;
			
		}else {
			actionString=((Control) e.getSource()).getTooltip().getText().split(" ");
		}
		
		String action = actionString[0];
		out.println(action);

		 if (action.equals("Language")) {
			ComboBox<String> language = (ComboBox<String>) e.getSource();
			String selectlanguage = (String) language.getValue();
			game.updateInterface(selectlanguage);
			out.println(selectlanguage);
		 }
		 else if (action.equals("English")) {
			game.updateInterface("English");

		}
		 else if (action.equals("French")) {
			game.updateInterface("French");

		}
		 else if (action.equals("Random")) {
			random();

		} else if (action.equals("Manual")) {
			manual();

		} else if (action.equals("Start")) {
			start();
		} else if (action.equals("Stop")) {
			stop();
		} else if (action.equals("Color")) {
			color();
		} else if (action.equals("multiColor")) {
			multiColor();
		}
		else if (action.equals("Button")) {
			int x = Integer.parseInt(actionString[1]);
			int y = Integer.parseInt(actionString[2]);
			game.pressButton(x, y);
		}

	}

}
