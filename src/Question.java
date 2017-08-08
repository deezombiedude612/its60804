package quizBankLy;

import java.util.ArrayList;

public class Question 
{
	private String quesDesc; // what the question is asking, basically.
	private int numAnswers; // number of answers to go along with the question
	private ArrayList<Answer> answerList = new ArrayList<Answer>(); // to store in all the answers belonging to 1 question
	
	// default constructor
	public Question()
	{
		quesDesc = "";
		numAnswers = 0;
		for(Answer ans: answerList)
			ans.setAnsDesc("");
	}
	//overloaded constructor
	public Question(String quesDesc, int numAnswers, ArrayList<Answer> answerList)
	{
		this.quesDesc = quesDesc;
		this.numAnswers = numAnswers;
		this.answerList = answerList;
	}
	
	// accessor and mutator (get and set) methods
	public String getQuesDesc() 
	{
		return quesDesc;
	}
	public void setQuesDesc(String quesDesc) 
	{
		this.quesDesc = quesDesc;
	}
	public int getNumAnswers() 
	{
		return numAnswers;
	}
	public void setNumAnswers(int numAnswers) 
	{
		this.numAnswers = numAnswers;
	}
	public ArrayList<Answer> getAnswerList()
	{
		return answerList;
	}
	public void setAnswerList(int answerIndex, ArrayList<Answer> answerList)
	{
		this.answerList = answerList;
	}
	//print out questions
	public void displayQuestion() 
	{
		System.out.println(quesDesc);
	}
}
