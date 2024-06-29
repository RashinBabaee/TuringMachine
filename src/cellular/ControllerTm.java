package cellular;

/*
 * Name: Rashin Babaee
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 3 Dec 2023
 */

import static java.lang.System.out;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Control;

/**
 * this class is controller class for user interface for turning machine
 */
public class ControllerTm implements EventHandler<ActionEvent> {
	/**
	 * this is a function for run button in turning machine
	 */
	public void run() {

	}

	/**
	 * this function is for clearing textField
	 */
	public void clear() {

	}

	/**
	 * this is the function for when the user presses buttons
	 */
	public void handle(ActionEvent e) {

		String action = ((Control) e.getSource()).getTooltip().getText();
		
		out.println(action);

		if (action.equals("Run")) {
			run();
		} else if (action.equals("Clear")) {
			clear();
		}
	}
	/**
	 * this is a constructor
	 */
	public ControllerTm() {
		
	}

}
