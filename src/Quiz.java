package quizBankLy;

import java.util.ArrayList;

public class Quiz 
{
	private String quizType; // type of quiz (i.e. mathematics, science, logic, language)
	private String quizId; // unique id for each quiz
	private String quizName; // name given to quiz
	private int numQuestions; // number of questions to be answered in a quiz
	private int scorePerQ; // score allocated per question (generally.. but if quizCreators want to make exceptions to certain questions, they can fix special values in main)
	private int scoreDeductPerQ; // score deducted if wrong answer is detected (optional)
	private int score; // total score for current attempt in quiz
	private int highScore; // high score recorded in game
	private String highScoreHolder; // high score achiever's user name
	private int attemptsAllowed; // maximum number of attempts allowed IN quiz (not attempts to try!)
	private String quizCreatedBy; // quiz's creator
	private ArrayList<Question> questionList = new ArrayList<Question>(); // to store in the questions belonging to one quiz
	
	// default constructors
	public Quiz()
	{
		quizType = "<undefined>";
		quizId = "0000";
		quizName = "";
		numQuestions = 0;
		scorePerQ = 1;
		scoreDeductPerQ = 0;
		score = 0;
		highScore = 0;
		highScoreHolder = "<NaN>";
		attemptsAllowed = 5; // default is 5; quizCreators can choose to allow different number of attempts
		quizCreatedBy = "anonymous";
		for(Question ques: questionList)
			ques.setQuesDesc("");
	}
	public Quiz(String quizType, String quizId, String quizName, int numQuestions, int scorePerQ, int scoreDeductPerQ, int score, int highScore, String highScoreHolder, int attemptsAllowed, String quizCreatedBy, ArrayList<Question> questionList)
	{
		this.quizType = quizType;
		this.quizId = quizId;
		this.quizName = quizName;
		this.numQuestions = numQuestions;
		this.scorePerQ = scorePerQ;
		this.scoreDeductPerQ = scoreDeductPerQ;
		this.score = score;
		this.highScore = highScore;
		this.highScoreHolder = highScoreHolder;
		this.attemptsAllowed = attemptsAllowed;
		this.quizCreatedBy = quizCreatedBy;
		this.questionList = questionList;
	}
	
	// accessor and mutator (get and set) methods
	public String getQuizType() 
	{
		return quizType;
	}
	public void setQuizType(String quizType) 
	{
		this.quizType = quizType;
	}
	public String getQuizId() 
	{
		return quizId;
	}
	public void setQuizId(String quizId) 
	{
		this.quizId = quizId;
	}
	public String getQuizName() 
	{
		return quizName;
	}
	public void setQuizName(String quizName) 
	{
		this.quizName = quizName;
	}
	public int getNumQuestions() 
	{
		return numQuestions;
	}
	public void setNumQuestions(int numQuestions) 
	{
		this.numQuestions = numQuestions;
	}
	public int getScorePerQ()
	{
		return scorePerQ;
	}
	public void setScorePerQ(int scorePerQ)
	{
		this.scorePerQ = scorePerQ;
	}
	public int getScoreDeductPerQ()
	{
		return scoreDeductPerQ;
	}
	public void setScoreDeductPerQ(int scoreDeductPerQ)
	{
		this.scoreDeductPerQ = scoreDeductPerQ;
	}
	public int getScore()
	{
		return score;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
	public int getHighScore() 
	{
		return highScore;
	}
	public void setHighScore(int highScore) 
	{
		this.highScore = highScore;
	}
	public String getHighScoreHolder() 
	{
		return highScoreHolder;
	}
	public void setHighScoreHolder(String highScoreHolder) 
	{
		this.highScoreHolder = highScoreHolder;
	}
	public int getAttemptsAllowed()
	{
		return attemptsAllowed;
	}
	public void setAttemptsAllowed(int attemptsAllowed)
	{
		this.attemptsAllowed = attemptsAllowed;
	}
	public String getQuizCreatedBy() 
	{
		return quizCreatedBy;
	}
	public void setQuizCreatedBy(String quizCreatedBy)
	{
		this.quizCreatedBy = quizCreatedBy;
	}
	public ArrayList<Question> getQuestionList()
	{
		return questionList;
	}
	public void setQuestionList(ArrayList<Question> questionList)
	{
		this.questionList = questionList;
	}
	
	public void displayQuizDetails(String role) 
	{
		if(role == "quizCreator")
			System.out.println("Quiz ID: " + quizId);
		if(role == "quizPlayer")
			System.out.println("Quiz Created By: " + quizCreatedBy);
		System.out.println("Quiz Type: " + quizType);
		System.out.println("Quiz Name: " + quizName);
		
		System.out.println("\nNumber of Questions: " + numQuestions);
		System.out.println("High Score: " + highScore);
		System.out.println("High Scorer: " + highScoreHolder);
	}
	
	public void wrongAnswer(Attempts attempt)
	{
		System.out.println("\nWRONG!!");
		attempt.setNumAttempts(attempt.getNumAttempts() - 1);
		attempt.displayNumAttempts();
		if(scoreDeductPerQ != 0)
		{
			score -= scoreDeductPerQ;
			if(score < 0)
				score = 0;
			System.out.println("Score: " + score);
		}
	}
	
	public void displayQuizOver(int score)
	{
		System.out.println("No more attempts left. Quiz over.");
		System.out.println("Final Score: " + score);
		newHighScore();
		System.out.println("High Score: " + highScore);
	}
	
	public void displayQuizCompleted(int attemptsAllowed)
	{
		System.out.println("Congratulations, you completed this quiz!");
		System.out.println("Final Score: " + score);
		newHighScore();
		System.out.println("High Score: " + highScore);
		System.out.println("Number of attempts remaining: " + attemptsAllowed);
	}
	
	public void displayNumAttempts() 
	{
		System.out.println("Attempts Left: " + attemptsAllowed);
	}
	
	public void newHighScore()
	{
		if(score > highScore) 
		{
			System.out.println("\nA NEW HIGH SCORE!!!");
			this.highScore = score;
		}
	}
}
