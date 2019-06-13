import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The class PlayerModel holds all kind of information about a particular player who is or has played the game. 
 * The information stored about the players are name, age, points scored and the best streak achieved from the game.
 * @author Navin Bajgai, BSc Computer Science, Second Year Group 1
 * @version 14/11/2016
 */
public class PlayerModel 
{
	private String name;
	private int age;
	private int points;
	private int currentStreak;
	private int bestStreak;
	private int noOfIncorrectAttempts;
	
	/**
	 * Constructor for the objects of class PlayerModel.
	 */
	public PlayerModel() {
		age = 0;
		points = 0;
		currentStreak = 0;
		bestStreak = 0;
		noOfIncorrectAttempts = 0;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the currentStreak
	 */
	public int getCurrentStreak() {
		return currentStreak;
	}

	/**
	 * @param currentStreak the currentStreak to set
	 */
	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}

	/**
	 * @return the bestStreak
	 */
	public int getBestStreak() {
		return bestStreak;
	}

	/**
	 * @param bestStreak the bestStreak to set
	 */
	public void setBestStreak(int bestStreak) {
		this.bestStreak = bestStreak;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the noOfIncorrectAttempts
	 */
	public int getNoOfIncorrectAttempts() {
		return noOfIncorrectAttempts;
	}

	/**
	 * @param noOfIncorrectAttempts the noOfIncorrectAttempts to set
	 */
	public void setNoOfIncorrectAttempts(int noOfIncorrectAttempts) {
		this.noOfIncorrectAttempts = noOfIncorrectAttempts;
	}
	
	/**
	 * @param playerName
	 * @param points
	 * @param streaks
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * The method for populating the player's game's results including name, points earned and the best streak achieved into the database. 
	 */
	public void insertPlayerStats(String playerName, int points, int streaks) throws SQLException 
	{
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";	//the driver for loading the database. 
		String url = "jdbc:mysql://helios.csesalford.com:3306/stc392";	//the server where the database is set up to.
		
		Connection connection = null;
		try {
			Class.forName(DRIVER_CLASS);
			connection = DriverManager.getConnection(url, "stc392", "milans/12");
			
			Statement statement = connection.createStatement();
			
			//the SQL insert statement for inserting the player's record/ results into the database.
			String insertQuery = "INSERT INTO SWA_Assignment1 (name, points, streaks)" + "VALUES('" + playerName + "', " + points + ", " + streaks + ")";
			
			int result = statement.executeUpdate(insertQuery);
			
			if (result > 0) {
				System.out.println("\nINSERTED!!");
			} else {
				System.err.println("****ERROR..couldn't insert!");
			}
			statement.close();
		}
		catch (Exception e) {
			System.err.println("***ERROR...cannot load " + DRIVER_CLASS);
			System.exit(1);
		}
		finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
		
	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * The method for querying all of the top ten players' statistics from the database including their names, points earned 
	 * and the best streak achieved from the game.
	 */
	public void queryPlayerStats() throws ClassNotFoundException, SQLException
	{	
		String DRIVER_CLASS = "com.mysql.jdbc.Driver";	//the driver for loading the database. 
		String url = "jdbc:mysql://helios.csesalford.com:3306/stc392";		//the server where the database is set up to.
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