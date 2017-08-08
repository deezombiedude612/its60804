package quizBankLy;

import java.util.ArrayList;

public class Attempts extends Quiz
{
	private int numAttempts; // number of attempts left
	
	// default constructors
	public Attempts()
	{
		super();
		numAttempts = 0;
	}
	//overloaded constructor
	public Attempts(String quizType, String quizId, String quizName, int numQuestions, int scorePerQ, int scoreDeductPerQ, int score, int highScore, String highScoreHolder, int attemptsAllowed, String quizCreatedBy, ArrayList<Question> questionList, int numAttempts)
	{
		super(quizType, quizId, quizName, numQuestions, scorePerQ, scoreDeductPerQ, score, highScore, highScoreHolder, attemptsAllowed, quizCreatedBy,  questionList);
		this.numAttempts = numAttempts;
	}
	
	// accessor and mutator (get and set) methods
	public int getNumAttempts() 
	{
		return numAttempts;
	}
	public void setNumAttempts(int numAttempts) 
	{
		this.numAttempts = numAttempts;
	}
	//to print out attempts
	public void displayNumAttempts() 
	{
		System.out.println("Attempts Left: " + numAttempts);
	}
}
