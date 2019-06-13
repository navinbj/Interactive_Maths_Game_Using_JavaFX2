import java.util.Arrays;
import java.util.List;

/**
 * The class QuestionModel models how the question and the multiple choices answers for the question is set up
 * to display on the question pane view. .
 * @author Navin Bajgai, BSc Computer Science, Second Year Group 1
 * @version 14/11/2016
 */
public class QuestionModel {
	
	private String name;
	private List<String> answers;
	
	/**
	 * Constructor for the objects of class QuestionModel.
	 * @param name
	 * @param firstNum
	 * @param secondNum
	 * @param answers
	 */
	public QuestionModel(String name, int firstNum, int secondNum, String... answers)
	{
		this.name = name + firstNum + " x " + secondNum + " ?";
		this.answers = Arrays.asList(answers);	
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
	 * @return the answers
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}