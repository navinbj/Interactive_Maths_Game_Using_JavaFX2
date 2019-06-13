import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * The class LeaderboardView has been created for the purpose of storing and displaying all of the UI elements for an application which is
 * being passed to the GameController class in order to manage the UI elements.  
 * @author Navin Bajgai, BSc Computer Science, Second Year Group 1
 * @version 14/11/2016
 */

public class LeaderboardView
{
	/**
	 * the fields for all of the UI elements that are to be implemented and displayed in an application
	 * including labels, buttons, text-fields, image views, panes, audio-clips and so on.
	 */
	Pane leaderboardPane;
	Label titleLbl, topTenLbl, playerNameLbl, pointsLbl, streaksLbl, playersNameLbl;
	int playerPos, pointPos, streakPos;
	PlayerModel playerModel;
	
	/**
	 * Constructor for the objects of class LeaderboardView. 
	 */
	public LeaderboardView()
	{
		/**
		 * Positioning and styling the UI elements into appropriate location within the game-screen by applying certain 
		 * JavaFX properties. 
		 */
		
		playerModel = new PlayerModel();
		
		leaderboardPane = new Pane();
		leaderboardPane.setPrefSize(800,600);
		leaderboardPane.setLayoutX(0);
		leaderboardPane.setStyle("-fx-background-color: #000000;");		//cyanBlue
		
		titleLbl = new Label();
		titleLbl = new Label();
		titleLbl.setText("See Where You Rank Amongst Others");
		titleLbl.setFont(Font.font(40));
		titleLbl.setTextFill(Color.LIGHTCYAN);
		titleLbl.setLayoutX(65);
		titleLbl.setLayoutY(20);
		
		topTenLbl = new Label();
		topTenLbl = new Label();
		topTenLbl.setText("Top 10 Scores");
		topTenLbl.setTextFill(Color.WHEAT);
		topTenLbl.setStyle("-fx-font-style: italic; -fx-font-size: 20;");
		topTenLbl.setLayoutX(65);
		topTenLbl.setLayoutY(75);
		
		playerNameLbl = new Label();
		playerNameLbl.setText("PLAYERS");
		playerNameLbl.setFont(Font.font(35));
		playerNameLbl.setTextFill(Color.RED);
		playerNameLbl.setLayoutX(100);
		playerNameLbl.setLayoutY(120);
		playerPos = 150;
		
		pointsLbl = new Label();
		pointsLbl.setText("POINTS");
		pointsLbl.setFont(Font.font(35));
		pointsLbl.setTextFill(Color.RED);
		pointsLbl.setLayoutX(350);
		pointsLbl.setLayoutY(120);
		pointPos = 150;
		
		streaksLbl = new Label();
		streaksLbl.setText("STREAKS");
		streaksLbl.setFont(Font.font(35));
		streaksLbl.setTextFill(Color.RED);
		streaksLbl.setLayoutX(520);
		streaksLbl.setLayoutY(120);
		streakPos = 150;	
		
		/**
		 * adding the UI elements into a game pane so that all the elements will be displayed on the screen as necessary.
		 */
		leaderboardPane.getChildren().addAll(titleLbl, topTenLbl, playerNameLbl, pointsLbl, streaksLbl);
		
		try {
			showAllPlayerStats();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * The method for querying all of the top ten players' statistics from the database including their names, points earned 
	 * and the best streak achieved from the game.
	 */
	public void showAllPlayerStats() throws ClassNotFoundException, SQLException
	{		
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://helios.csesalford.com:3306/stc392";
		Connection connection = null;
		
		try {
			Class.forName(DRIVER_CLASS);
			connection = DriverManager.getConnection(url, "stc392", "milans/12");
			Statement statement = connection.createStatement();
			
			//the SQL select statement for outputting top ten players ordered by the points in a descending order.
			ResultSet results = statement.executeQuery("select * from SWA_Assignment1 order by points desc limit 10");			
			
			System.out.println("\n");
			System.out.println("TABLE: SWA_Assignment1");
			System.out.println("CLASS:\t" + this.getClass());
			System.out.println("===============================================");
			
			while (results.next()) 
			{			
				String playerName = results.getString("name");
				int pointsEarned = results.getInt("points");
				int streakAchieved = results.getInt("streaks");	
				
				Label testLbl = new Label();
				testLbl.setFont(Font.font(20));
				testLbl.setTextFill(Color.YELLOW);
				testLbl.setLayoutX(100);
				testLbl.setLayoutY(playerPos + 20);
				playerPos += 30;
				testLbl.setText(playerName);
				testLbl.setVisible(true);
				leaderboardPane.getChildren().add(testLbl);
				
				Label pointsEarnedLbl = new Label();
				pointsEarnedLbl.setFont(Font.font(20));
				pointsEarnedLbl.setTextFill(Color.YELLOW);
				pointsEarnedLbl.setLayoutX(380);
				pointsEarnedLbl.setLayoutY(pointPos + 20);
				pointPos += 30;
				pointsEarnedLbl.setText(pointsEarned + "");
				pointsEarnedLbl.setVisible(true);
				leaderboardPane.getChildren().add(pointsEarnedLbl);

				Label streaksAchievedLbl = new Label();
				streaksAchievedLbl.setFont(Font.font(20));
				streaksAchievedLbl.setTextFill(Color.YELLOW);
				streaksAchievedLbl.setLayoutX(580);
				streaksAchievedLbl.setLayoutY(streakPos + 20);
				streakPos += 30;
				
				streaksAchievedLbl.setTextAlignment(TextAlignment.CENTER);
				streaksAchievedLbl.setText(streakAchieved + "");
				
				streaksAchievedLbl.setVisible(true);
				leaderboardPane.getChildren().add(streaksAchievedLbl);
												
				System.out.println("Player Name:\t" + playerName);
				System.out.println("Points Earned:\t" + pointsEarned);
				System.out.println("Streaks:\t" + streakAchieved);
				System.out.println("-----------------------------------------------------------------------------------------");
				System.out.println("");
			}	
			
			results.close();
			statement.close();
		}
		catch (Exception exception) {
			System.err.println("***ERROR...cannot load " + DRIVER_CLASS);
			System.exit(1);
		}
		finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}