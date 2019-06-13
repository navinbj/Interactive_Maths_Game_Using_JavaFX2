import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The class SidePaneView has been created for allowing a player to see the progress of the questions in the pane. 
 * Every time a player answer the question correctly, the field current will increase and stays on the next question. 
 * @author Navin Bajgai, BSc Computer Science, Second Year Group 1
 * @version 14/11/2016
 */
public class SidePaneView extends VBox {
	private int current = 1;
	
	/**
	 * Constructor for the objects of class SidePaneView. 
	 */
	public SidePaneView() {
		for (int i = 15; i > 0; i--) {
			Text text = new Text("Question " + i);
			text.setFill(i == current ? Color.BLACK : Color.GRAY);
			text.setFont(Font.font(15));	
			getChildren().add(text);
		}
	}
	
	/**
	 * The method for climbing over to the next question label once answered the current question correctly. 
	 */
	public void selectNext()
	{
		if (current == 15) {
			return;
		}
		
		Text text = (Text) getChildren().get(15 - current);
		text.setFill(Color.DARKGREEN);
		current++;
		
		text = (Text) getChildren().get(15 - current);
		text.setFill(Color.DARKRED);
	}
}