import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The class Assignment is an educational game application template which has been designed interactively for the 
 * primary school children to learn things in a fun and online environment. 
 * The class Assignment stores the references to several other classes such as, GameView, PlayerModel, GameController
 * and LeaderboardView in order to make an application designed and run using a MVC approach. 
 * @author Navin Bajgai, BSc Computer Science, Second Year Group 1
 * @version 14/11/2016
 */

public class Assignment1 extends Application
{	
	GameView gameView;
	PlayerModel playerModel;
	GameController gameController;
	LeaderboardView leaderboardView;
	
	Scene scene;
	TabPane root;
	Tab tab1, tab2;
	Image stageIcon;
	
	public static void main(String[] args) {
		launch(args);	
	}	
	
	@Override
	public void start(Stage stage)
	{			
		stage.setTitle("Software Architectures - Navin Bajgai");
		root = new TabPane();
		scene = new Scene(root, 800, 600);
		stage.setScene(scene);
		stage.getIcons().add(new Image(Assignment1.class.getResource("res/icon.png").toExternalForm()));
				
		tab1 = new Tab();
		tab1.setText("Learn-to-multiply");
		tab1.setClosable(false);
		root.getTabs().add(tab1);
		
		tab2 = new Tab();
		tab2.setText("Leaderboards");
		tab2.setClosable(false);
		root.getTabs().add(tab2);
		stage.show();
		
		stage.setResizable(false);
		stage.setMaximized(false);
		
		gameView = new GameView();
		playerModel = new PlayerModel();
		gameController = new GameController(playerModel, gameView, leaderboardView);
		leaderboardView = new LeaderboardView();
		
		tab1.setContent(gameView.gamePane);		//setting the contents of class GameView into the first tab.
		tab2.setContent(leaderboardView.leaderboardPane);	//setting the contents of class LeaderboardView into the second tab.
	}
}