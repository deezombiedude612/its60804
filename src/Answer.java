package quizBankLy;


public class Answer 
{
	private String ansDesc; // answer description
	private boolean correctAnswer; // false - wrong answer; true - correct answer
	
	// default constructor
	public Answer()
	{
		ansDesc = "";
		correctAnswer = false;
	}
	//overloaded constructor
	public Answer(String ansDesc, boolean correctAnswer)
	{
		this.ansDesc = ansDesc;
		this.correctAnswer = correctAnswer;
	}
	
	// accessor and mutator (get and set) methods
	public String getAnsDesc() 
	{
		return ansDesc;
	}
	public void setAnsDesc(String ansDesc) 
	{
		this.ansDesc = ansDesc;
	}
	public boolean getIsCorrectAnswer() 
	{
		return correctAnswer;
	}
	public void setCorrectAnswer(boolean correctAnswer) 
	{
		this.correctAnswer = correctAnswer;
	}
	//print out the answer description
	public void displayAnswer()
	{
		System.out.println(ansDesc);
	}
}
