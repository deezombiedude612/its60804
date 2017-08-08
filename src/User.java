package quizBankLy;


public abstract class User 
{
	private String userName; // initialize user name
	private String userStatus; // initialize userStatus for active, barred, etc.
	private String dateJoined; // initialize dateJoined for the date the user has registered himself/herself
	
	// default constructors
	public User() 
	{
		userName = "";
		userStatus = "Active";
		dateJoined = "01/01/1970";
	}
	//overloaded constructor
	public User(String userName, String userStatus, String dateJoined) 
	{
		this.userName = userName;
		this.userStatus = userStatus;
		this.dateJoined = dateJoined;
	}
	
	// accessor and mutator (get and set) methods
	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}
	public String getUserStatus() 
	{
		return userStatus;
	}
	public void setUserStatus(String userStatus) 
	{
		this.userStatus = userStatus;
	}
	public String getDateJoined()
	{
		return dateJoined;
	}
	public void setDateJoined(String dateJoined)
	{
		this.dateJoined = dateJoined;
	}
	// print out general information
	public void displayUserInfo() 
	{
		System.out.println("Username: " + userName);
		System.out.println("User Status: " + userStatus);
		System.out.println("Date Joined: " + dateJoined);
	}
}
