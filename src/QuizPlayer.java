package quizBankLy;


public class QuizPlayer extends User 
{
	private int numQuizPlayed; // number of quizzes played by player
	private long cumScore; // cumulative score of all quizzes played so far
	
	// default constructors
	public QuizPlayer() 
	{
		super();
		numQuizPlayed = 0;
		cumScore = 0;
	}
	//overloaded constructor
	public QuizPlayer(String userName, String dateJoined, String userStatus, int numQuizPlayed, long cumScore) 
	{
		super(userName, dateJoined, userStatus);
		this.numQuizPlayed = numQuizPlayed;
		this.cumScore = cumScore;
	}
	
	// accessor and mutator (get and set) methods
	public int getNumQuizPlayed() 
	{
		return numQuizPlayed;
	}
	public void setNumQuizPlayed(int numQuizPlayed) 
	{
		this.numQuizPlayed = numQuizPlayed;
	}
	public long getCumScore() 
	{
		return cumScore;
	}
	public void setCumScore(long cumScore) 
	{
		this.cumScore = cumScore;
	}
	//overidding display method
	public void displayPlayerInfo() 
	{
		super.displayUserInfo();
		System.out.println("Number of quizzes played: " + numQuizPlayed);
		System.out.println("Cumulative Score: " + cumScore);
	}
}
