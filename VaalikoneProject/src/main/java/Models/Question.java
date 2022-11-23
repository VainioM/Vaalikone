package Models;

public class Question {
	
	private int questionID;
	private String question;
	
	/**
	 * Constructor for question object
	 * @param questionID
	 * @param question
	 */
	public Question(String questionID, String question) {
		setQuestionID(questionID);
		this.question=question;
	}
	
	/**
	 * question empty constructor
	 */
	public Question(){
		
	}
	
	/**
	 * Get question ID
	 * @return
	 */
	public int getQuestionID(){
		return questionID;
	}
	
	/**
	 * Set question ID
	 * @param questionID
	 */
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	
	/**
	 * Set question ID if parameter is String
	 * @param questionID
	 */
	public void setQuestionID(String questionID) {
		try {
			this.questionID = Integer.parseInt(questionID);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
		
	/**
	 * Get question
	 * @return question
	 */
	}
	public String getQuestion() {
		return question;
	}
	/**
	 * Set question
	 * @param question
	 */
	public void setQuestion(String question) {
		this.question=question;
	}
	

}