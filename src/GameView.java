import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The class GameView has been created for the purpose of storing and displaying all of the UI elements for an application which is
 * being passed to the GameController class in order to manage the UI elements.  
 * @author Navin Bajgai, BSc Computer Science, Second Year Group 1
 * @version 14/11/2016
 */

public class GameView  
{
	PlayerModel playerModel;
	
	/**
	 * the fields for all of the UI elements that are to be implemented and displayed in an application
	 * including labels, buttons, text-fields, image views, panes, audio-clips and so on.
	 */
	Pane leftPane, gamePane;
	Label gameTitleLbl, welcomeLbl, playerNameLbl, playerAgeLbl, instructionLbl, gameInstructionsLbl, nameLbl, pointsLbl, streaksLbl, bestStreakLbl, rightAnswerLbl, wrongAnswerLbl, lifeLineLbl, congratulationsLbl, gameOverLbl, goodByeLbl;
	Button confirmBtn, lifeLine1Btn, lifeLine2Btn, lifeLine3Btn;
	TextField playerNameTxt, playerAgeTxt;
	ImageView multiplyImage, congrats, star, winner, angryBoy;
	AudioClip yesSound, noSound, backgroundMusic, startMusic, clappingSound, congratsSound, gameOver;
	String answerGiven, correctAnswer;
	QuestionPaneView questionPaneView;
	SidePaneView sidePaneView;
		
	private int noOfQuestion;
	private Random random;
	
	/**
	 * Constructor for the objects of class GameView.
	 */
	public GameView()
	{
		super();	
				
		/**
		 * Positioning and styling the UI elements into appropriate location within the game-screen by applying certain 
		 * JavaFX properties. 
		 */
		sidePaneView = new SidePaneView();
		playerModel = new PlayerModel();
		random = new Random();
		questionPaneView = new QuestionPaneView();
		gamePane = new Pane();
		gamePane.setStyle("-fx-background-color: #F0F8FF;");		//cyanBlue
		
		leftPane = new Pane();
		leftPane.setPrefSize(300, 600);
		leftPane.setStyle("-fx-background-color: #000000;"); 	//black
		
		welcomeLbl = new Label();
		welcomeLbl.setText("Welcome To");
		welcomeLbl.setFont(Font.font(40));
		welcomeLbl.setTextFill(Color.WHITE);
		welcomeLbl.setLayoutX(25);
		welcomeLbl.setLayoutY(10);
		
		gameTitleLbl = new Label();
		gameTitleLbl.setText("Learn To Multiply");
		gameTitleLbl.setFont(Font.font(30));
		gameTitleLbl.setTextFill(Color.GREEN);
		gameTitleLbl.setLayoutX(55);
		gameTitleLbl.setLayoutY(60);
		
		multiplyImage = new ImageView(GameView.class.getResource("res/multiply_image.GIF").toExternalForm());
		multiplyImage.setLayoutX(100);
		multiplyImage.setLayoutY(115);
		multiplyImage.setFitWidth(110);
		multiplyImage.setFitHeight(75);
		
		yesSound = new AudioClip(GameView.class.getResource("res/yes.mp3").toExternalForm());
		noSound = new AudioClip(GameView.class.getResource("res/no.mp3").toExternalForm());
		backgroundMusic = new AudioClip(GameView.class.getResource("res/background_music.mp3").toExternalForm());
		startMusic = new AudioClip(GameView.class.getResource("res/startmusic.mp3").toExternalForm());
		congratsSound = new AudioClip(GameView.class.getResource("res/congratsSound.mp3").toExternalForm());
		clappingSound = new AudioClip(GameView.class.getResource("res/clapping.mp3").toExternalForm());
		gameOver = new AudioClip(GameView.class.getResource("res/gameOver.mp3").toExternalForm());
		
		playerNameLbl = new Label();
		playerNameLbl.setText("NAME (*)");
		playerNameLbl.setFont(Font.font(20));
		playerNameLbl.setTextFill(Color.RED);
		playerNameLbl.setLayoutX(55);
		playerNameLbl.setLayoutY(210);	
		
		playerNameTxt = new TextField();
		playerNameTxt.setLayoutX(55);
		playerNameTxt.setLayoutY(240);
		
		playerAgeLbl = new Label();
		playerAgeLbl.setText("AGE (!)");
		playerAgeLbl.setFont(Font.font(20));
		playerAgeLbl.setTextFill(Color.RED);
		playerAgeLbl.setLayoutX(55);
		playerAgeLbl.setLayoutY(270);	
		
		playerAgeTxt = new TextField();
		playerAgeTxt.setLayoutX(55);
		playerAgeTxt.setLayoutY(300);
		
		confirmBtn = new Button();
		confirmBtn.setText("Start");
		confirmBtn.setFont(Font.font(15));
		confirmBtn.setTextFill(Color.WHITE);
		confirmBtn.setStyle("-fx-background-color:#006400; -fx-font-weight:BOLD;");
		confirmBtn.setLayoutX(55);
		confirmBtn.setLayoutY(340);
		
		instructionLbl = new Label();
		instructionLbl.setText("Instructions:");
		instructionLbl.setFont(Font.font(15));
		instructionLbl.setTextFill(Color.DEEPPINK);
		instructionLbl.setLayoutX(12);
		instructionLbl.setLayoutY(390);
		
		gameInstructionsLbl = new Label();
		String instructions = "There are 15 questions altogether.\n"
				+ "You need to answer each question correctly to win a game.\n"
				+ "Please choose only 1 correct answer from the choices.\n"
				+ "Each correct answer is worth 100 points out of possible 1500.\n"
				+ "You have 3 lifelines that can be used anytime you like.\n"
				+ "Every time you answer incorrectly, you lose a lifeline.\n"
				+ "You can use all 3 lifelines on one question if you want to.\n"
				+ "If you used all 3 lifelines, then you can not proceed further.\n"
				+ "To win a game, you need to score 1500 points";
		gameInstructionsLbl.setText(instructions);
		gameInstructionsLbl.setFont(Font.font(10));
		gameInstructionsLbl.setTextFill(Color.LIGHTCORAL);
		gameInstructionsLbl.setLayoutX(12);
		gameInstructionsLbl.setLayoutY(415);
		gameInstructionsLbl.setStyle("-fx-border-color: #0000FF; -fx-padding:3");
		
		nameLbl = new Label();
		nameLbl.setText("");
		nameLbl.setFont(Font.font(15));
		nameLbl.setTextFill(Color.YELLOW);
		nameLbl.setLayoutX(55);
		nameLbl.setLayoutY(220);	
		
		pointsLbl = new Label();
		pointsLbl.setText("");
		pointsLbl.setFont(Font.font(15));
		pointsLbl.setTextFill(Color.YELLOW);
		pointsLbl.setLayoutX(55);
		pointsLbl.setLayoutY(245);
		
		streaksLbl = new Label();
		streaksLbl.setText("");
		streaksLbl.setFont(Font.font(15));
		streaksLbl.setTextFill(Color.YELLOW);
		streaksLbl.setLayoutX(55);
		streaksLbl.setLayoutY(270);
		
		bestStreakLbl = new Label();
		bestStreakLbl.setText("");
		bestStreakLbl.setFont(Font.font(15));
		bestStreakLbl.setTextFill(Color.YELLOW);
		bestStreakLbl.setLayoutX(55);
		bestStreakLbl.setLayoutY(295);
		
		rightAnswerLbl = new Label();
		rightAnswerLbl.setFont(Font.font(45));
		rightAnswerLbl.setTextFill(Color.GREEN);
		rightAnswerLbl.setLayoutX(380);
		rightAnswerLbl.setLayoutY(470);
		
		wrongAnswerLbl = new Label();
		wrongAnswerLbl.setFont(Font.font(45));
		wrongAnswerLbl.setTextFill(Color.RED);
		wrongAnswerLbl.setLayoutX(380);
		wrongAnswerLbl.setLayoutY(470);
		
		lifeLineLbl = new Label();
		lifeLineLbl.setText("Life Lines:");
		lifeLineLbl.setLayoutX(590);
		lifeLineLbl.setLayoutY(25);
		lifeLineLbl.setFont(Font.font(20));
		lifeLineLbl.setTextFill(Color.PURPLE);
		lifeLineLbl.setVisible(false);	
		
		lifeLine1Btn = new Button();
		lifeLine1Btn.setText("1");
		lifeLine1Btn.setFont(Font.font(15));
		lifeLine1Btn.setTextFill(Color.WHITE);
		lifeLine1Btn.setStyle("-fx-background-color:#006400;");
		lifeLine1Btn.setLayoutX(680);
		lifeLine1Btn.setLayoutY(25);
		lifeLine1Btn.setVisible(false);
		
		lifeLine2Btn = new Button();
		lifeLine2Btn.setText("2");
		lifeLine2Btn.setFont(Font.font(15));
		lifeLine2Btn.setTextFill(Color.WHITE);
		lifeLine2Btn.setStyle("-fx-background-color:#006400;");
		lifeLine2Btn.setLayoutX(715);
		lifeLine2Btn.setLayoutY(25);
		lifeLine2Btn.setVisible(false);
		
		lifeLine3Btn = new Button();
		lifeLine3Btn.setText("3");
		lifeLine3Btn.setFont(Font.font(15));
		lifeLine3Btn.setTextFill(Color.WHITE);
		lifeLine3Btn.setStyle("-fx-background-color:#006400;");
		lifeLine3Btn.setLayoutX(750);
		lifeLine3Btn.setLayoutY(25);
		lifeLine3Btn.setVisible(false);
		
		congratulationsLbl = new Label();
		congratulationsLbl.setLayoutX(320);
		congratulationsLbl.setLayoutY(470);
		congratulationsLbl.setFont(Font.font(45));
		congratulationsLbl.setTextFill(Color.GREEN);
		congratulationsLbl.setVisible(false);
		
		congrats = new ImageView(GameView.class.getResource("res/congrats.jpg").toExternalForm());
		congrats.setLayoutX(300);
		congrats.setLayoutY(0);
		congrats.setFitWidth(510);
		congrats.setFitHeight(120);
		congrats.setVisible(false);
		
		star = new ImageView(GameView.class.getResource("res/star.gif").toExternalForm());
		star.setLayoutX(680);
		star.setLayoutY(445);
		star.setFitWidth(100);
		star.setFitHeight(100);
		star.setVisible(false);
		
		winner = new ImageView(GameView.class.getResource("res/winner.gif").toExternalForm());
		winner.setLayoutX(350);
		winner.setLayoutY(150);
		winner.setFitWidth(280);
		winner.setFitHeight(280);
		winner.setVisible(false);
		
		angryBoy = new ImageView(GameView.class.getResource("res/horrid_angry.GIF").toExternalForm());
		angryBoy.setLayoutX(360);
		angryBoy.setLayoutY(200);
		angryBoy.setVisible(false);
		
		gameOverLbl = new Label();
		gameOverLbl.setText("GAME OVER!");
		gameOverLbl.setLayoutX(350);
		gameOverLbl.setLayoutY(80);
		gameOverLbl.setFont(Font.font(70));
		gameOverLbl.setTextFill(Color.RED);
		gameOverLbl.setVisible(false);
		
		goodByeLbl = new Label();
		goodByeLbl.setLayoutX(320);
		goodByeLbl.setLayoutY(500);
		goodByeLbl.setFont(Font.font(30));
		goodByeLbl.setTextFill(Color.RED);
		goodByeLbl.toBack();
		goodByeLbl.setVisible(false);	
		
		questionPaneView.setLayoutX(380);
		questionPaneView.setLayoutY(80);
		questionPaneView.setVisible(false);		
		
		sidePaneView.setLayoutX(700);
		sidePaneView.setLayoutY(130);
		sidePaneView.setVisible(false);
		
		startMusic.play();
		
		/**
		 * adding the UI elements into a game pane so that all the elements will be displayed on the screen as necessary.
		 */
		gamePane.getChildren().addAll(leftPane, gameTitleLbl, welcomeLbl, multiplyImage, playerNameLbl, playerNameTxt, playerAgeLbl, playerAgeTxt,
				confirmBtn, instructionLbl, gameInstructionsLbl, nameLbl, pointsLbl, streaksLbl, bestStreakLbl, questionPaneView, rightAnswerLbl, wrongAnswerLbl,
				lifeLineLbl, lifeLine1Btn, lifeLine2Btn, lifeLine3Btn, congratulationsLbl, congrats, star, winner, angryBoy, gameOverLbl, goodByeLbl, sidePaneView);			
	}
	
	/**
	 * The method for generating the questions with random integers each time for the game.  
	 * This will generate 15 multiplication questions with random numbers each time. 
	 */
	public void generateQuestions() 
	{
		noOfQuestion++;		//each time the question is generated, the field noOfQuestion increases by 1. 
		
		if (noOfQuestion < 16) {
			int number1 = random.nextInt(20);
			int number2 = random.nextInt(12);
			int answer = number1 * number2;
			
			/**
			 * A hash set for storing and displaying the random numbers generated in order to prevent numbers duplication.
			 */
			HashSet<Integer> numbersSet = new HashSet<>();
			numbersSet.add(answer);
			
			//adding 4 random integers ranging from 1 to 100, into an array. 
			int[] incorrectAnswers = new Random().ints(1, 100).distinct().limit(4).toArray();
			
			for (int i : incorrectAnswers) {
				numbersSet.add(i);
			}
			numbersSet.remove(answer); //removing the answer previously added in order to avoid duplication.
			
			Object[] integerNumbers = numbersSet.toArray();
			
			//getting the integers from the hash set. 
			int incorrectAns1 = (int) integerNumbers[0];
			int incorrectAns2 = (int) integerNumbers[1];
			int incorrectAns3 = (int) integerNumbers[2];
		
			//creating and setting up a question to display on a question pane view with the multiple choices answers. 
			questionPaneView.setQuestion(new QuestionModel("What is ", number1, number2, answer+"" , incorrectAns1+"", incorrectAns2+"", incorrectAns3+""));
		}
		else{
			System.err.println("***ERROR: Question limit reached!");
		}	
	}
	
	/*
	 * The class QuestionPaneView has been created for displaying the question into the screen along the the list of answer buttons. 
	 */
	class QuestionPaneView extends VBox
	{
		private Text text = new Text();
		private List<Button> answerButtons;
		
		/**
		 * Constructor for the objects of class QuestionPaneView.
		 */
		public QuestionPaneView() 
		{
			super(20);	//this extends the superclass Vertical Box and adds some spacing between the question and the answer buttons.
			text.setFont(Font.font(40));
			answerButtons = new ArrayList<>();
			
			HBox answersHBox = new HBox();
			for (int i = 0; i < 4; i++) {
				Button button = new Button();
				button.setFont(Font.font(25));
				button.setPrefWidth(120);
				
				button.setOnAction(event -> {		
					answerGiven = button.getText();
					correctAnswer = answerButtons.get(0).getText();	
									
					if (answerGiven.equals(correctAnswer)) 
					{
						System.out.println("CORRECT:\t" + answerGiven);
						yesSound.play();
						
						playerModel.setPoints(playerModel.getPoints() + 100);
						String pointsEarned = playerModel.getPoints() + "";
						System.out.println(pointsEarned);
						
						playerModel.setCurrentStreak(playerModel.getCurrentStreak() + 1);
						String currentStreak = playerModel.getCurrentStreak() + "";
						System.out.println(currentStreak);
						
						pointsLbl.setText("POINTS EARNED:\t" + pointsEarned + " /1500");
						streaksLbl.setText("CURRENT STREAK:\t" + currentStreak);
						
						wrongAnswerLbl.setVisible(false);
						rightAnswerLbl.setVisible(true);
						rightAnswerLbl.setText("CORRECT!: " + correctAnswer);
						
						generateQuestions();
						sidePaneView.selectNext();
						
						if (playerModel.getPoints() == 1500) {
							questionPaneView.setVisible(false);
							rightAnswerLbl.setVisible(false);
							lifeLineLbl.setVisible(false);
							lifeLine1Btn.setVisible(false);
							lifeLine2Btn.setVisible(false);
							lifeLine3Btn.setVisible(false);
							
							congrats.setVisible(true);
							star.setVisible(true);
							winner.setVisible(true);
							congratulationsLbl.setVisible(true);
							
							String playerName = playerNameTxt.getText();
							System.out.println("player name:\t" + playerName);

							String congratsMessage = "And it is...\t"+ playerName.toUpperCase();		
							congratulationsLbl.setText(congratsMessage);
							congratulationsLbl.toFront();
							
							backgroundMusic.stop();
							clappingSound.play();
							congratsSound.play();
						}
					}
					else {
						int noOfIncorrectAttempt = playerModel.getNoOfIncorrectAttempts();
						int currentStreak = playerModel.getCurrentStreak();
						int bestStreak = playerModel.getBestStreak();
												
						playerModel.setNoOfIncorrectAttempts(noOfIncorrectAttempt + 1);
						
						if (currentStreak >= bestStreak) {
							playerModel.setBestStreak(currentStreak);
							bestStreakLbl.setText("BEST STREAK:\t\t" + playerModel.getBestStreak());
						}
						playerModel.setCurrentStreak(0);
						
						streaksLbl.setText("CURRENT STREAK:\t" + playerModel.getCurrentStreak());
						noSound.play();
						rightAnswerLbl.setVisible(false);
						wrongAnswerLbl.setVisible(true);
						wrongAnswerLbl.setText("**INCORRECT!: " + answerGiven);
						
						if (playerModel.getNoOfIncorrectAttempts() == 1) {
							lifeLine1Btn.setStyle("-fx-background-color:#A9A9A9;");
						}
						else if (playerModel.getNoOfIncorrectAttempts() == 2)
						{
							lifeLine2Btn.setStyle("-fx-background-color:#A9A9A9;");
						}
						else if (playerModel.getNoOfIncorrectAttempts() == 3)
						{	
							backgroundMusic.stop();
							gameOver.play();
							
							lifeLine3Btn.setStyle("-fx-background-color:#A9A9A9;");

							questionPaneView.setVisible(false);
							sidePaneView.setVisible(false);
							wrongAnswerLbl.setVisible(false);
							
							gameOverLbl.setVisible(true);
							angryBoy.setVisible(true);
							goodByeLbl.setText("Sorry to see you go..." + playerNameTxt.getText().toUpperCase());
							goodByeLbl.setVisible(true);
						}				
					}
					
					//this means that the game is finished. 
					if (playerModel.getPoints() == 1500 || playerModel.getNoOfIncorrectAttempts() == 3)
					{
						String playerName = playerNameTxt.getText();
						int pointsEarned = playerModel.getPoints();
						
						if (playerModel.getCurrentStreak() >= playerModel.getBestStreak()) 
						{
							int bestStreak = playerModel.getCurrentStreak();
							playerModel.setBestStreak(bestStreak);
							bestStreakLbl.setText("BEST STREAK:\t\t" + playerModel.getBestStreak());
						}
						int streakAchieved = playerModel.getBestStreak();
						try {
							playerModel.insertPlayerStats(playerName, pointsEarned, streakAchieved);
							playerModel.queryPlayerStats();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				answerButtons.add(button);	//adding the buttons into an array list. 
				answersHBox.getChildren().add(button);	//adding the button into a Horizontal Box for displaying them horizontally. 
			}
			getChildren().addAll(text);
			getChildren().addAll(answerButtons);
		}
		
		/**
		 * The method for setting up a question. Gets the field name from class Question. 
		 * @param question
		 */
		public void setQuestion(QuestionModel question) {
			text.setText(question.getName());
			Collections.shuffle(answerButtons);	//method for shuffling the answer buttons in the pane. 
			
			for (int i = 0; i < 4; i++) {
				answerButtons.get(i).setText(question.getAnswers().get(i));
			}
		}
	}
}