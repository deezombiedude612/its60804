package quizBankLy;


public class QuizCreator extends User
{
	private int numQuizCreated; // number of quizzes created by creator
	
	// default constructor
	public QuizCreator() 
	{
		super();
		numQuizCreated = 0;
	}
	//overloaded constructor
	public QuizCreator(String userName, String dateJoined, String userStatus, int numQuizCreated) 
	{
		super(userName, dateJoined, userStatus);
		this.numQuizCreated = numQuizCreated;
	}
	
	// accessor and mutator (get and set) methods
	public int getNumQuizCreated() 
	{
		return numQuizCreated;
	}
	public void setNumQuizCreated(int numQuizCreated) 
	{
		this.numQuizCreated = numQuizCreated;
	}
	
	// overriding display method
	public void displayCreatorInfo() 
	{
		super.displayUserInfo();
		System.out.println("Number of quizzes created: " + numQuizCreated);
	}
}
