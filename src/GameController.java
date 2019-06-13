import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * The class GameController has been created for the purpose of controlling and managing the UI materials 
 * obtained from the views such as GameView and LeaderboardView.
 * @author Navin Bajgai, BSc Computer Science, Second Year Group 1
 * @version 14/11/2016
 */
public class GameController {
	PlayerModel playerModel;
	GameView gameView;
	LeaderboardView leaderboardView;
	private String answerGiven, correctAnswer;
	
	//the field for handling the mouse or click events. 
	private EventHandler<ActionEvent> aEventHandler = new EventHandler<ActionEvent>() {

		@Override
		/**
		 * The method for handling the click events depending on the source of an event. 
		 */
		public void handle(ActionEvent event) {
			if (event.getSource() == gameView.confirmBtn)
			{
				String playerName = gameView.playerNameTxt.getText();
				
				if (playerName.isEmpty()) {
					System.out.println("Please provide your name first!");
				}
				else {			
					gameView.startMusic.stop();
					gameView.backgroundMusic.play();
					
					gameView.lifeLineLbl.setVisible(true);
					gameView.lifeLine1Btn.setVisible(true);
					gameView.lifeLine2Btn.setVisible(true);
					gameView.lifeLine3Btn.setVisible(true);
					
					playerModel.setName(playerName);
					String pointsEarned = "" + playerModel.getPoints();
					String currentStreak = "" + playerModel.getCurrentStreak();
					String highestStreak = "" + playerModel.getBestStreak();
					
					gameView.playerAgeLbl.setVisible(false);
					gameView.playerNameLbl.setVisible(false);
					gameView.playerAgeTxt.setVisible(false);
					gameView.playerNameTxt.setVisible(false);
					gameView.confirmBtn.setVisible(false);
					
					gameView.nameLbl.setVisible(true);
					gameView.nameLbl.setText("PLAYER NAME:\t" + playerName.toUpperCase());
					
					gameView.pointsLbl.setVisible(true);
					gameView.pointsLbl.setText("POINTS EARNED:\t" + pointsEarned+ " /1500");
					
					gameView.streaksLbl.setVisible(true);
					gameView.streaksLbl.setText("CURRENT STREAK:\t" + currentStreak);
					
					gameView.bestStreakLbl.setVisible(true);
					gameView.bestStreakLbl.setText("BEST STREAK:\t\t" + highestStreak);
					
					gameView.questionPaneView.setVisible(true);
					gameView.generateQuestions();
					gameView.sidePaneView.setVisible(true);					
				}
			}
		}
	};
		
	/**
	 * Constructor for the objects of class GameController.
	 * @param playerModel
	 * @param gameView
	 * @param leaderboardView
	 */
	public GameController(PlayerModel playerModel, GameView gameView, LeaderboardView leaderboardView) {
		super();
		this.playerModel = playerModel;
		this.gameView = gameView;
		this.leaderboardView = leaderboardView;
		this.gameView.confirmBtn.setOnAction(aEventHandler);
	}

	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * @return the answerGiven
	 */
	public String getAnswerGiven() {
		return answerGiven;
	}

	/**
	 * @param answerGiven the answerGiven to set
	 */
	public void setAnswerGiven(String answerGiven) {
		this.answerGiven = answerGiven;
	}
}