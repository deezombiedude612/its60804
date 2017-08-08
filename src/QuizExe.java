import java.util.Scanner;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class QuizExe 
{
	private static Scanner input = new Scanner(System.in); // instantiate scanner object named as "input" - to be used in all methods in QuizExe
	
	// array lists (used to store many objects in place of a database)
	private static ArrayList<QuizCreator> quizCreator = new ArrayList<QuizCreator>();
	private static ArrayList<QuizPlayer> quizPlayer = new ArrayList<QuizPlayer>();
	private static ArrayList<Quiz> quizList = new ArrayList<Quiz>();
	
	public static void main(String[] args) 
	{	
		Date date = new Date(); // instantiate date object named as "date"
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy"); // date format to be used
		
		initial(); // for main purpose of demo - preset users and quizzes
		
		System.out.println("-----WELCOME TO OUR QUIZ BANK!!-----"); // welcome message
		
		// enter user name field
		String userName;
		do
		{
			System.out.print("\nEnter your username >> "); // prompt for user name
			userName = input.nextLine();
			if(userName.length() < 1) // if nothing is entered
				System.out.println("Enter at least a character long!");
		} 
		while(userName.length() < 1); // end do-while loop
		
		// check if userName already exists in array lists (quizCreator and quizPlayer)
		int noMatch = 0; // number of mismatches
		int numUsers = 0; // number of registered users
		String role = ""; // if userName is found, immediately record existing role over here
		for(User user: quizCreator)
		{
			numUsers++; // add 1 for every quiz creator in quizCreator array list
			if(!(userName.equals(user.getUserName())))
				noMatch++; // for every non-matching account
			else
				role = "quizCreator";
		} // end enhanced for loop
		for(User user: quizPlayer)
		{
			numUsers++; // add 1 for every quiz player in quizPlayer array list
			if(!(userName.equals(user.getUserName())))
				noMatch++; // for every non-matching account
			else
				role = "quizPlayer";
		} // end enhanced for loop
		
		if(noMatch == numUsers) // if no such account exists
		{
			System.out.println("\nWelcome, new user.\n"); // welcome message for new users
			int option = 3; // option to choose between quiz creator and quiz player
			while(option != 1 && option != 2)
			{
				// display roles available only if sentinel value 3 is entered
				if(option == 3) 
				{
					System.out.println("1. Quiz Creator");
					System.out.println("2. Quiz Player");
					System.out.println("3. Redisplay Options");
					System.out.println("");
				} // end if statement
				System.out.print("Enter desired role >> "); // prompt user to register as desired role
				
				// check if the user input is valid 
				do
				{
					if(input.hasNextInt() == true)
					{
						option = input.nextInt();
						input.nextLine();
						break;
					} // end if statement
					else
					{
						System.out.print("Invalid input, enter again >>");
						input.nextLine();
					} // end else statement
				}
				while(input.hasNextInt() != true); // end do-while loop
			
				switch(option) 
				{
					case 1:
						QuizCreator tempCreator = new QuizCreator(); // create new creator account
						tempCreator.setUserName(userName); // set userName as name for new account
						tempCreator.setDateJoined(dateFormat.format(date).toString()); // set current date joined
						quizCreator.add(tempCreator); // append quizCreator array list
						role = "quizCreator";
						break;
					case 2:
						QuizPlayer tempPlayer = new QuizPlayer(); // create new player account
						tempPlayer.setUserName(userName); // set userName as name for new account
						tempPlayer.setDateJoined(dateFormat.format(date).toString()); // set current date joined
						quizPlayer.add(tempPlayer); // append quizPlayer array list
						role = "quizPlayer";
						break;
					case 3: // if user wants program to redisplay options
						System.out.println("\nRedisplaying options...\n");
						break;
					default: // if invalid choice was input
						System.out.println("\nInvalid choice. Try again!");
						System.out.println("Enter 3 to redisplay options.\n");
				} // end switch case
				if(option == 1 || option == 2)
					System.out.println("\nWelcome, " + userName); // greeting for newly registered users
			} // end while loop
		} // end if statement
		else
		{
			if(role == "quizCreator") // if quiz creator is chosen
			{
				// use a temporary creator account similar to existing record; when game is exited, override existing record
				QuizCreator tempCreator = new QuizCreator();
				tempCreator.setUserName(userName);
				for(QuizCreator creator: quizCreator)
					if(userName.equals(creator.getUserName())) // transferring existing user details into temporary account
					{
						tempCreator.setNumQuizCreated(creator.getNumQuizCreated()); // retrieve number of quizzes created by creator
						tempCreator.setDateJoined(creator.getDateJoined()); // retrieve creator's join date
					} // end if statement
			} // end if statement
			else
			{
				// use a temporary player account similar to existing record; when game is exited, override existing record
				QuizPlayer tempPlayer = new QuizPlayer();
				tempPlayer.setUserName(userName);
				for(QuizPlayer player: quizPlayer)
					if(userName.equals(player.getUserName())) // transferring existing user details into temporary account
					{
						tempPlayer.setNumQuizPlayed(player.getNumQuizPlayed()); // retrieve number of quizzes played by player
						tempPlayer.setCumScore(player.getCumScore()); // retrieve player's cumulative score
						tempPlayer.setDateJoined(player.getDateJoined()); // retrieve player's join date
					} // end if statement
			} // end else statement
			System.out.println("\nWelcome back, " + userName); // greeting for returning users
		} // end else statement
		
		// display main menu interface; different for creators and players
		int menuOpt = 0; // main menu option choice, preset to 0
		if(role == "quizCreator") // display main menu interface for quiz creators
			while(menuOpt != -1) // while exit sentinel value -1 is not entered
			{
				if(menuOpt == 0) // display options only when needed (users will need to key in 0 for menuOpt at that time)
				{
					System.out.println("------------------------------");
					System.out.println("1. Add Quiz");
					System.out.println("2. Modify Quiz");
					System.out.println("3. Delete Quiz");
					System.out.println("4. Display User Information");
					System.out.println("5. Edit Account Information");
					System.out.println("");
				} // end if statement
				
				System.out.println("Enter 0 to redisplay options. Enter -1 to quit."); // constant reminder on how to display options; sentinel value '-1' to exit
				System.out.print("Enter option >> "); // prompt user to enter option
				
				//check if the user input is valid 
				do
				{
					if(input.hasNextInt() == true)
					{
						menuOpt = input.nextInt();
						input.nextLine();
						break;
					} // end if statement
					else
					{
						System.out.print("Invalid input, enter again >>");
						input.nextLine();
					} // end else statement
				}
				while(input.hasNextInt() != true); // end do-while loop
				
				// switch menuOpt to access other functionalities in quiz bank
				switch(menuOpt)
				{
					case 0:
						System.out.println("\nRedisplaying options...\n");
						break;
					case 1:
						addQuiz(userName);
						break;
					case 2:
						modifyQuiz(userName);
						break;
					case 3:
						deleteQuiz(role, userName);
						break;
					case 4:
						System.out.println("");
						int count = -1; // counter to find index of creator
						for(QuizCreator creator: quizCreator)
						{
							count++; // if not found, count increments to search in next index
							if(userName.equals(creator.getUserName()))
								break; // if found, the count is at current index of user
						} // end enhanced for loop
						quizCreator.get(count).displayCreatorInfo(); // display creator info
						System.out.println("");
						break;
					case 5:
						editUserInfo(role, userName);
						break;
					case -1: // exits while loop instantly
						break;	
					default:
						System.out.println("\nInvalid option. Try again!\n"); // invalid option has been entered
				} // end switch case
			} // end while loop
		
		else if(role == "quizPlayer") // display main menu interface for quiz players
			while(menuOpt != -1) // while exit sentinel value -1 is not entered
			{
				if(menuOpt == 0) // display options only when needed (users will need to key in 0 for menuOpt at that time)
				{
					System.out.println("------------------------------");
					System.out.println("1. Play Quiz");
					System.out.println("2. Display User Information");
					System.out.println("3. Edit User Information");
					System.out.println("");
				} // end if statement
				
				System.out.println("Enter 0 to redisplay options. Enter -1 to exit."); // constant reminder on how to display options
				System.out.print("Enter option >> "); // prompt user to enter option
				
				//check if the user input is valid 
				do
				{
					if(input.hasNextInt() == true)
					{
						menuOpt = input.nextInt();
						input.nextLine(); // to cater for any skip line cases
						break;
					} // end if statement
					else
					{
						System.out.print("Invalid input, enter again >>");
						input.nextLine(); // to cater for any skip line cases
					} // end else statement
				} 
				while(input.hasNextInt() != true); // end do-while loop
				
				// switch menuOpt to access other functionalities in quiz bank
				switch(menuOpt)
				{
					case 0:
						System.out.println("\nRedisplaying options...\n");
						break;
					case 1:
						playQuiz(role, userName);
						break;
					case 2:
						System.out.println("");
						int count = 0; // counter to find index of player
						for(QuizPlayer player: quizPlayer)
						{
							if(userName.equals(player.getUserName()))
								break; // if found, the count is at current index of user
							count++; // if not found, count increments to search in next index
						} // end enhanced for loop
						quizPlayer.get(count).displayPlayerInfo();
						System.out.println("");
						break;
					case 3:
						editUserInfo(role, userName);
						break;
					case -1: // sentinel value: exits program immediately
						break;	
					default:
						System.out.println("\nInvalid option. Try again!\n"); // invalid choice has been entered
				} // end switch case
			} // end while loop
		
		System.out.println("\nGOODBYE!! :3"); // goodbye message, end program
	} // end main()
	
	/* --- external methods for quiz creators only --- */
	
	/*
	 * This method allows quiz creators to add quizzes into the program for quiz players to play with.
	 */
	private static void addQuiz(String creatorName)
	{
		
		input.nextLine();
		char continueAgain = 'x'; // used after a quiz is created (Y - create another quiz, N - exit)
		String tempQuizId = ""; // to store in quiz id to be used by players
		do 
		{
			do
			{
				System.out.print("Enter custom quiz code (must be alphanumeric; e.g. MRI1) >> "); // prompt creator for quiz code
				tempQuizId = input.nextLine();
			      
				// prompt creator if there are errors - not 4 characters long or quiz code contains non-alphanumeric characters
				if(tempQuizId.length() != 4) // check if quiz code is 4 characters long
					System.out.println("Unable to accept input. Quiz code must be 4 characters long!\n");
				if(!(tempQuizId.matches("[a-zA-Z0-9 ]+"))) // check if quiz code has non-alphanumeric characters
					System.out.println("Quiz code must be alphanumeric. Try again.\n");
			} 
			while(tempQuizId.length() != 4 || !(tempQuizId.matches("[a-zA-Z0-9 ]+"))); // end do-while loop
			
			// set quiz type
			System.out.print("Enter quiz type >> "); // prompt creator to enter quiz type
			String tempQuizType = input.nextLine();
			if(tempQuizType.equals("")) // if quiz type is left empty
				tempQuizType = "<undefined>";
			
			// set quiz name
			String tempQuizName = ""; // to store quiz name
			do
			{
				System.out.print("Enter quiz name >> "); // prompt creator to enter name for quiz
				tempQuizName = input.nextLine();
				
				if(tempQuizName.length() == 0)
					System.out.println("You must give your quiz a name!!!\n"); // quiz name cannot be left empty
			}
			while(tempQuizName.length() == 0); // end do-while loop
			
			// set number of questions for quiz
			int numQuestions = 0; // temporary holder for number of questions intended to be made for this quiz
		
			do
			{
				System.out.print("Enter number of questions for this quiz >> "); // prompt creator to enter number of questions for quiz
				
				// check if the user input is valid 
				do
				{
					if(input.hasNextInt() == true)
					{
						numQuestions = input.nextInt();
						input.nextLine();
						break;
					} // end else statement
					else
					{
						System.out.print("Invalid input, enter again >>");
						input.nextLine();
					} // end else statement
				} 
				while(input.hasNextInt() != true); // end do-while loop
			
				if(numQuestions < 0) // if negative value is input
					System.out.println("Number of questions cannot be negative!\n");
				else if(numQuestions == 0) // if creator wants to create no questions for quiz, this is if creator only wants to create templates
				{
					System.out.println("WARNING: You are about to create a quiz with no questions."); // prompt warning message
					System.out.print("Do you wish to continue? ('Y' to continue, 'N' to change the number of questions.) >> "); // prompt for creator confirmation
					char decide = input.next().charAt(0);
					input.nextLine();
					if(decide == 'Y' || decide == 'y') // if creator still wants to create quiz without questions
						break;
				} // end else-if statement
			} 
			while(numQuestions <= 0); // end do-while loop
			
			ArrayList <Question> tempQuestionHolder = new ArrayList<Question>(); // temporary holder to contain questions before saving them into a new Quiz object
			
			// create questions
			for(int i = 0; i < numQuestions; i++)
			{
				Question tempQuestion = new Question(); // create a new question for every entry in temporary array
				do
				{
					System.out.println("Enter question #" + (i + 1) + " description"); // prompt creator for question description
					System.out.print(">> ");
					tempQuestion.setQuesDesc(input.nextLine());
						
					if (tempQuestion.getQuesDesc().length() == 0) // if question description is left empty
						System.out.println("You cannot leave a question empty!\n"); // display error message
				}
				while(tempQuestion.getQuesDesc().length() == 0); // end do-while loop
					
				int numAnswers = 0;
				while(numAnswers <= 1)
				{
					System.out.print("Enter number of answers for this question >> "); // prompt creator for number of answers for the question
					
					// check if the user input is valid 
					do
					{
						if(input.hasNextInt() == true)
						{
							numAnswers = input.nextInt();
							input.nextLine();
							break;
						} // end if statement
						else
						{
							System.out.print("Invalid input, enter again >>");
							input.nextLine();
						} // end else statement
					} while(input.hasNextInt() != true); // end do-while loop
					
					if(numAnswers < 1) // if creator inputs 0 or any negative number
						System.out.println("This quiz creator only creates MCQ quizzes, please enter number of answers to be put together with the question.\n"); // display error message
					else if(numAnswers == 1) // if creator wants only one answer available
					{
						System.out.println("You are about to create a quiz with only 1 answer choice."); // display warning message
						System.out.print("Do you wish to continue? ('Y' to continue, 'N' to change the number of answers.) >> "); // prompt creator for confirmation
						char decide = input.next().charAt(0);
							input.nextLine();
						if(decide == 'y' || decide == 'Y') // if creator still decides on letting only 1 answer available
							break;
					} // end else-if statement
				} // end while loop
				
				// set answers
				Answer[] answer = new Answer[numAnswers]; // create answer array to save the answers 
				int numWrongAnswers = 0; // for situation where all answers entered are unintentionally put as false 
				for(int j = 0; j < numAnswers; j++)
				{
					answer[j] = new Answer(); // create new Answer object for every array entry
					do
					{
						System.out.println("Enter answer #" + (j + 1) + "'s description"); // prompt for answer #j's description
						System.out.print(">> ");
						answer[j].setAnsDesc(input.nextLine());
						
						if(answer[j].getAnsDesc().length() == 0) // if answer description is left empty
							System.out.println("You cannot leave an answer empty!\n");
					}
					while(answer[j].getAnsDesc().length() == 0); // end do-while loop
					
					// set if the answer is correct 
					char correct = ' ';
                    while((correct != 'Y' && correct != 'y') && (correct != 'N' && correct != 'n'))
					{
						System.out.print("Is this answer correct? (Y/N) >> "); // ask creator whether answer entered is to be set as correct or wrong
						correct = input.next().charAt(0);
						input.nextLine(); // to cater for any skipline cases
						
						if((correct != 'Y' && correct != 'y') && (correct != 'N' && correct != 'n')) // if invalid input is received
							System.out.println("Invalid input. Try again.\n");
						else if(correct == 'N' || correct == 'n') // if answer is set to be wrong
						{
							numWrongAnswers++; // increase counter of number of wrong answers by 1
							answer[j].setCorrectAnswer(false); // set that particular answer to be wrong
						} // end else-if statement
						else if(correct == 'y'||correct == 'Y') // if answer is set to be correct
							answer[j].setCorrectAnswer(true); // set that particular answer to be correct
					} // end while loop
					
					// if more than 1 answer is set as correct or no answers are correct at all
                    if((j + 1) == numAnswers) // only check this if all answers have been entered
						if((numWrongAnswers == numAnswers) || (numWrongAnswers <= numAnswers - 2)) // normal case is when only one answer is correct; if abnormal cases are found
						{
							char confirm = ' ';
							while((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n')) // until user gives proper response
							{
								if(numWrongAnswers == numAnswers) // if all answers are set to be incorrect
									System.out.println("\nWARNING: You have set all answers to be incorrect."); // warning message stating that all questions are wrong
								else if(numWrongAnswers <= numAnswers - 2) // if more than 1 answer is set to be correct
									System.out.println("\nWARNING: You have set more than 1 correct answer for this question."); // warning message stating that more than 1 answer is correct
								System.out.print("Would you like to edit the answers? (Y/N) >> "); // prompt for creator confirmation
								confirm = input.next().charAt(0);
								input.nextLine(); // to cater for any skipline cases
								
								if((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n')) // if invalid input is received
									System.out.println("Invalid input. Try again.");
							} // end while loop
							
							if(confirm == 'Y' || confirm == 'y') // if creator wants to edit answers
							{
								j = -1; // roll back to recreate answer for same question number
								numWrongAnswers = 0; // reset number of wrong answers for the question
							} // end if statement
						} // end if statement
				} // end for loop
				
				// display question and answers to ask the user whether to save it or not
				char confirmSave = 'x';
				while((confirmSave != 'Y' && confirmSave != 'y') && (confirmSave != 'N' && confirmSave != 'n')) // until user enters a proper response
				{
					if(confirmSave == 'x' || confirmSave == 'X') // preview question if 'x' or 'X' is entered for confirmSave variable
					{
						System.out.println("\nDEMO QUESTION #" + (i + 1)); // display question number
						System.out.println(tempQuestion.getQuesDesc()); // display question
						for(int j = 0; j < numAnswers; j++) // display all answers within question
							System.out.println("[" + (j + 1) + "] " + answer[j].getAnsDesc() + " <" + answer[j].getIsCorrectAnswer() + ">");
					} // end if statement
					
					System.out.println("\nEnter 'x' to redisplay question preview."); // sentinel value 'x' or 'X' to redisplay question preview
					System.out.print("Accept question created? (Y/N) >> "); // prompt creator on whether to accept question or not
					confirmSave = input.next().charAt(0);
					
					if((confirmSave != 'N' && confirmSave != 'n') && (confirmSave != 'Y' && confirmSave != 'y')) 
						if(confirmSave != 'x' && confirmSave != 'X') // if invalid input is received
							System.out.println("Invalid input. Try again.\n"); // display error message
				} // end while loop
				
				// if creator confirms to save question
				if(confirmSave == 'Y' || confirmSave == 'y') // save question into temporary answer array list
				{
					for(int j = 0; j < numAnswers; j++) // add answers to answer arraylist of answers in question
						tempQuestion.getAnswerList().add(answer[j]);
					tempQuestionHolder.add(tempQuestion); // add question to arraylist of questions
					System.out.println("Question saved.\n"); // notify creator that question is saved
				} // end if-statement
				else // discard question, backtrack count by 1
				{
					System.out.println("Question discarded.\n"); // notify creator that question has been discarded
					i--; // backtrack to recreate question
				} // end else-statement
					
				// if creator wants to continue making questions later, if all questions have not yet been made up yet; for template purposes
				char choice = ' ';
				if(i < numQuestions - 1)
				{
					do
					{
						System.out.println("Do you wish to continue? (Y/N)"); // ask creator whether he\she wants to continue making questions
						System.out.print(">>");
						choice = input.next().charAt(0);
						
						if((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n')) // if invalid input is received
							System.out.println("Invalid choice. Try again.\n");
					}
					while((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n')); // end do-while loop
				} // end if statement
				if(choice == 'N' || choice == 'n') // if creator decides to stop making questions
					break;
			} // end for loop
			
			// set score awarded per question correctly answered
			int scorePerQ = 0;
			while(scorePerQ < 1)
			{
				System.out.println("Enter score awarded per correctly-answered question >> ");
				
				// check if the user input is valid 
				do
				{
					if(input.hasNextInt() == true) // user input does not contain anything else but integer values
					{
						scorePerQ = input.nextInt();
						input.nextLine(); // to cater for skipline cases
						break;
					} // end if statement
					else
					{
						System.out.print("Invalid input, enter again >>"); // error message, prompt user to enter value again
						input.nextLine(); 
					} // end else statement
				} 
				while(input.hasNextInt() != true);
				
				if(scorePerQ < 1) // if score less than 1 is entered
					System.out.println("You must allocate scores for correctly-answered questions!\n");
			} // end while loop
			
			// set score deducted per question wrongly answered (optional - if 0 is entered, it will not show in quiz summary)
			int scoreDeductPerQ = -1;
			System.out.println("Enter score deducted per wrongly-answered question >> ");
			
			//check if the user input is valid 
			do
			{
				if(input.hasNextInt()==true)
				{
					scoreDeductPerQ = input.nextInt();
					input.nextLine(); // to cater for skipline cases
					break;
				} // end if statement
				else
				{
					System.out.print("Invalid input, enter again >>");
					input.nextLine(); // to cater for skipline cases
				} // end else statement
			} 
			while(input.hasNextInt() != true); // end do-while loop
			
			if(scoreDeductPerQ < 0) // if negative numbers were entered (possibly by mistake)
				scoreDeductPerQ *= -1; // convert negative number to positive
			
			// set number of attempts allowed per try in the quiz
			int numAttempts = 0; // temporary placeholder to store number of attempts allowed in the quiz
			do
			{
				System.out.print("Enter maximum number of attempts in the quiz >> "); // prompt number of attempts allocated for quiz
				do
				{
					if(input.hasNextInt() == true)
					{
						numAttempts = input.nextInt();
						input.nextLine(); // to cater for skipline cases
						break;
					} // end if statement
					else
					{
						System.out.print("Invalid input, enter again >>");
						input.nextLine(); // to cater for skipline cases
					} // end else statement
				} 
				while(input.hasNextInt() != true); // end do-while loop
				
				if(numAttempts < 1) // if creator enters less than 1 attempt to be allocated
					System.out.println("Thou shall not alloweth less than 1 attempt for any quiz!\n");
			}
			while(numAttempts < 1); // end do-while loop
			
			// prompt confirmation of saving quiz from creator
			char confirmSave = 'x'; // used by creators to immediately discard their work or not
			do
			{
				if(confirmSave == 'x' || confirmSave == 'X') // display quiz details before saving quiz
				{
					System.out.println("\nYour quiz details are as follows: ");
					System.out.println("Quiz Code: " + tempQuizId);
					System.out.println("Quiz Type: " + tempQuizType);
					System.out.println("Quiz Name: " + tempQuizName);
					System.out.println("Number of questions: " + numQuestions);
					System.out.println("Attempts allowed per try: " + numAttempts);
					System.out.println("Score awarded per correct answer: " + scorePerQ);
					if(scoreDeductPerQ != 0)
						System.out.println("Score deducted per wrong answer: " + scoreDeductPerQ);
					System.out.println("\nIf there are any minor mistakes made during the creation of this quiz and you wish to edit them,"
							+ "\nyou are encouraged to save this quiz first and then select \"Edit Quiz\" from the main menu to amend it.");
				} // end if-statement
				System.out.println("Enter 'x' to display quiz details again."); // sentinel character 'x' or 'X' to redisplay quiz details
				System.out.print("Save this quiz? (Y/N) >> "); // ask creator whether this quiz is to be saved or not
				confirmSave = input.next().charAt(0);
				
				if((confirmSave != 'N' && confirmSave != 'n') && (confirmSave != 'Y' && confirmSave != 'y'))
					if(confirmSave != 'x' && confirmSave != 'X') // if invalid input is entered
						System.out.println("Invalid input. Try again.\n"); // display error message
			}
			while((confirmSave != 'N' && confirmSave != 'n') && (confirmSave != 'Y' && confirmSave != 'y')); // end do-while loop
			
			// confirmation - saving quiz
			if(confirmSave == 'n' || confirmSave == 'N') // if user chooses not to save quiz
				System.out.println("Quiz has been discarded.\n"); // alert message that quiz has been discarded
			else // if user chooses to save quiz
			{
				// add quiz entry to quizList
				Quiz newEntry = new Attempts(); // create new Attempts object containing quiz details
				newEntry.setQuizId(tempQuizId);
				newEntry.setQuizName(tempQuizName);
				newEntry.setQuizType(tempQuizType);
				newEntry.setNumQuestions(numQuestions);
				newEntry.setQuestionList(tempQuestionHolder);
				newEntry.setQuizCreatedBy(creatorName);
				newEntry.setAttemptsAllowed(numAttempts);
				newEntry.setScorePerQ(scorePerQ);
				newEntry.setScoreDeductPerQ(scoreDeductPerQ);
				quizList.add(newEntry); // add newEntry to arraylist of quizzes
				
				System.out.println("Quiz has been saved.\n"); // alert message that quiz has been saved
			} // end else statement
			
			do
			{
				System.out.print("Do you wish to continue? (Y/N) >> "); // prompt user whether to continue creating quizzes or not
				continueAgain = input.next().charAt(0);
				input.nextLine(); // to cater for skipline cases
				if((continueAgain != 'N' && continueAgain != 'n') && (continueAgain != 'Y' && continueAgain != 'y')) // if invalid input is entered
					System.out.println("Invalid input. Try again.\n"); // display error message
			} 
			while((continueAgain != 'N' && continueAgain != 'n') && (continueAgain != 'Y' && continueAgain != 'y')); // end do-while loop
			
		} 
		while(continueAgain != 'N' && continueAgain != 'n'); // end do-while loop
		
		System.out.println("Returning to main menu...\n"); // message displayed before displaying main menu
	} // end addQuiz()
	
	/*
	 * This method allows quiz creators to modify quizzes into the program for quiz players to play with.
	 * This quiz modifier's functionalities are limited.
	 */
	private static void modifyQuiz(String userName)
	{
		String id = "0"; // to initialize id as "0"
		int numQuizzes = 0; // to initialize numQuizzes as 0
		char confirmContinue = ' '; // initialize confirmContinue as a blank space
		
		while(confirmContinue != 'N' && confirmContinue != 'n') // while quiz creator still wants to continue modifying quiz
		{
			confirmContinue = ' '; // to reset confirmContinue as a blank space
			id = "0"; // to reset id as 0 
			while(id.length() != 4 && !(id.equals("-1"))) // while the length of the id is not 4 and not "-1" keep looping
			{
				if(id.equals("0")) // if id is 0 enter if statement
				{
					numQuizzes = 0; // set numQuizzes as 0
					System.out.println("\nWhich quiz would you like to modify?"); // display message to prompt quiz creator to choose what to modify
					for(Quiz quizToModify: quizList) // enhance loop for quizList array list
					{
						numQuizzes++; // increment +1 to count the total number of quizes
						System.out.println("Quiz #" + numQuizzes + ": " + quizToModify.getQuizName() + " (ID: " + quizToModify.getQuizId() + ")"); 
						// display quiz details
					} // end enhanced for loop
				} // end if statement
				System.out.println("\nEnter 0 to display all the quizzes again. Enter -1 to exit."); 
				// display message to prompt quiz creator to choose redisplay quiz or exit
				System.out.print("Enter the ID of the quiz to be modified. >> "); // display message to prompt quiz creator to enter id
				id = input.next(); // quiz creator enters id
				
				if(id.length() != 4 && !((id.equals("-1") || id.equals("0")))) // if id lenght is not 4 and id is not -1 or id is 0
					System.out.println("Unable to accept input. Quiz code must be 4 characters long!"); // display error message
			} // end while loop
			
			if(id.equals("-1")) // if id is -1 break from the loop
				break;
			
			int noMatch = 0; // initialize noMatch as 0
			for(Quiz quizToModify: quizList) // enhanced loop for quizList
			{
				if(!(id.equals(quizToModify.getQuizId()))) // if id is not equals to quiz id in arrayList enter if statement
					noMatch++; // increment +1 after every non-matching quiz in the quizList 
				else
				{
					System.out.println("\nQUIZ FOUND!"); // display success message if quiz is found
					if(!(userName.equals(quizToModify.getQuizCreatedBy()))) // if the quiz creatorname entered is not the owner of the quiz id
						System.out.println("WARNING: You are not authorized to modify this quiz."); // display error message
					else
					{
						int choice = 0; // initialize choice to 0
						do
						{
							if(choice == 0) // display options
							{
								System.out.println("\nWhat would you like to modify?"); // ask user on what to be modified in quiz
								System.out.println("-------------------------------");
								System.out.println("1. Quiz Name");
								System.out.println("2. Score Per Questions");
								System.out.println("3. Attempts allowed");
							} // end if statement
							System.out.println("(Enter 0 to redisplay options.)"); // remind users of syntax value 0 to redisplay options
							System.out.print(">> ");
							
							//check if the quiz creator input is valid 
							do
							{ //the do while loop here to to ensure that the quiz creator only enters an integer 
								if(input.hasNextInt()==true)
								{
									choice = input.nextInt();
									input.nextLine(); // to cater for skipline cases
									break;
								} // end if-statement
								else
								{ //if the quiz creator enters not an integer
									System.out.print("Invalid input, enter again >>"); // display error message
									input.nextLine(); // to cater for skipline cases
								} // end else-statement
							}
							while(input.hasNextInt() != true); // end do while-loop
							
							
							switch(choice) //switch case for the options
							{
								case 0: //in case quiz creator enters 0, it will break 
									break;
									
								case 1:
									input.nextLine(); // to solve skipping issue
									String newName = ""; //initialize newName as blank space
									while(newName.length() == 0)//while length of newName is 0 enter while loop
									{
										System.out.print("\nPlease enter new quiz name >> "); //display message to prompt quiz creator to enter new name
										newName = input.nextLine(); 
										
										if(newName.length() == 0) //if the quiz creator did not enter anything
											System.out.println("Quiz name cannot be left empty!!");//display error message
									} // end while loop
									char confirm = ' ';
									while((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n'))
									//ensure the quiz creator enters only the 4 specific characters
									{
										System.out.print("\nAre you sure you want to modify?(Y/N) >> "); //display message for final modifications
										confirm = input.next().charAt(0);
										
										if((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n')) 
										//if quiz creator enters not the 4 specific characters
											System.out.println("Invalid input. Try again."); //display error message
									} // end while loop
									
									if(confirm == 'Y' || confirm == 'y')//if quiz creator chooses to finalize modification
									{
										quizToModify.setQuizName(newName); //new name is set as the new quiz name
										System.out.println("This quiz's new name: " + quizToModify.getQuizName());
									} // end if statement
									else
										System.out.println("Aborted changes to quiz name."); //else display abort message
									break; //break case 1
									
								case 2:
									System.out.println("\nThis is the current score awarded per question: " + quizToModify.getScorePerQ()); // display current score awarded per question
									int newScore=0; // initialize newScore to 0
									do
									{
										System.out.print("Enter new score for every question (enter same score if no changes are wanted) >> "); // prompt user for new score per question
										
										//check if the quiz creator input is valid 
										do
										{
											if(input.hasNextInt() == true)
											{
												newScore = input.nextInt();
												input.nextLine();
												break;
											} // end if statement
											else
											{
												System.out.print("Invalid input, enter again >>");
												input.nextLine();
											} // end else statement
										}
										while(input.hasNextInt() != true); // end do-while loop
										
										if(newScore < 1) //if score is negative error message is displayed
											System.out.println("Score awarded cannot be negative!\n");
									} 
									while(newScore < 1); // end do-while loop
									
									if(newScore != quizToModify.getScorePerQ()) //check if the newScore is different than the previous score
									{ 
										confirm = ' '; 
										while((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n')) // to confirm final modifications
										{ 
											System.out.print("\nAre you sure you want to modify?(Y/N) >> "); // prompt user for confirmation
											confirm = input.next().charAt(0);
											
											if((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n')) // if invalid input is entered
												System.out.println("Invalid input. Try again."); // display error message
										} // end while loop
										
										if(confirm == 'Y' || confirm == 'y') // if user finalizes modification
										{ 
											quizToModify.setScorePerQ(newScore); //newScore will replace previous score
											System.out.println("This quiz's new score per question: " + quizToModify.getScorePerQ()); // display new score per question
										} // end if statement
										else // if user aborts changes
											System.out.println("Aborted changes to score per question."); //display abort message
									} // end if statement
									
									System.out.println("\nThis is the current score deducted per question: " + quizToModify.getScoreDeductPerQ()); // display current score deducted per question answered wrongy
									System.out.print("Enter new deduct score for every question (enter same score if no changes are wanted) >> "); // prompt user for new score deducted per question answered wrongly
									//to prompt quiz creator to enter score for deduction
									
									int newDeductScore = 0;
									//check if the quiz creator input is valid 
									do
									{
										if(input.hasNextInt() == true)
										{
											newDeductScore = input.nextInt();
											input.nextLine();
											break;
										} // end if statement
										else
										{
											System.out.print("Invalid input, enter again >>");
											input.nextLine(); // to cater for skipline cases
										} // end else statement
									} 
									while(input.hasNextInt() != true); // end do-while loop
								
									if(newDeductScore < 0) // if user sets deduct score to be negative
										newDeductScore *= -1; // make entered deduct score positive
									
									if(newDeductScore != quizToModify.getScoreDeductPerQ()) // if new deduct score is not equal to previous one
									{ //confirm final modifications
										confirm = ' '; // reset confirmation value to blank space
										while((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n')) // until a proper input is entered
										{
											System.out.print("\nAre you sure you want to modify?(Y/N) >> "); // prompt user for confirmation
											confirm = input.next().charAt(0);
											
											if((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n')) // if invalid input is entered
												System.out.println("Invalid input. Try again."); // display error message
										} // end while loop
										
										if(confirm == 'Y' || confirm == 'y') // if user confirms changes
										{
											quizToModify.setScoreDeductPerQ(newDeductScore); //previous score deduction is replaced
											System.out.println("This quiz's new score deducted per question: " + quizToModify.getScoreDeductPerQ()); // display new score deducted per question
										} // end if statement
										else // if user aborts changes
											System.out.println("Aborted changes to score deducted per question."); // display abort message
									} // end if statement 
									break; //break case 2
									
								case 3:
									System.out.println("\nThis is the current number of attempts allowed: " + quizToModify.getAttemptsAllowed()); // display current number of attempts allowed in quiz
									int newAttemptsAllowed = 0; //initialize newAttemptsAllowed to 0
									while(newAttemptsAllowed < 1) //to ensure quiz creator do not enter a negative or 0 value
									{
										System.out.print("Enter new number of attempts allowed (enter same number of attempts if no changes are wanted) >> "); // prompt user for number of attempts allowed
										
										//check if the quiz creator input is valid 
										do
										{
											if(input.hasNextInt() == true)
											{
												newAttemptsAllowed = input.nextInt();
												input.nextLine();
												break;
											} // end if-statement
											else
											{
												System.out.print("Invalid input, enter again >>");
												input.nextLine();
											} // end else statement
										}
										while(input.hasNextInt() != true); // end do-while loop
										
										if(newAttemptsAllowed < 1) // if less than 1 attempt was allocated
											System.out.println("Number of attempts cannot be negative!\n"); // display error message
									} // end while loop
									
									if(newAttemptsAllowed != quizToModify.getAttemptsAllowed()) // to check if new attempts is different than previous attempts
									{ 
										confirm = ' '; // reinitialize confirmation value to empty space
										while((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n'))
										{
											System.out.print("\nAre you sure you want to modify?(Y/N) >> "); // prompt user for confirmation
											confirm = input.next().charAt(0);
											
											if((confirm != 'Y' && confirm != 'y') && (confirm != 'N' && confirm != 'n')) // if invalid input is entered
												System.out.println("Invalid input. Try again.");
										} // end while loop
										
										if(confirm == 'Y' || confirm == 'y') // if quiz creator make final modifications
										{ 
											quizToModify.setAttemptsAllowed(newAttemptsAllowed);; // new attempts will replace previous attempts
											System.out.println("This quiz's new score deducted per question: " + quizToModify.getAttemptsAllowed()); // notify that changes have been made
										} // end if statement
										else // if user cancels changes
											System.out.println("Aborted changes to attempts allowed."); // notify user that changes have been aborted
									} // end if statement 
									break;
									
								default: // if invalid input is entered
									System.out.println("Invalid choice. Try again!\n");
							} // end switch case
						}
						while(choice < 1 || choice > 3); // end do-while loop
					} // end else statement
				} // end statement statement
			} // end enhanced for loop
			
			if(noMatch == numQuizzes) //to cross check to see if the quiz id is valid
				System.out.println("Quiz not found. Quiz codes are CaSe SeNsItItIvE.");
			
			while((confirmContinue != 'N' && confirmContinue != 'n') && (confirmContinue != 'Y' && confirmContinue != 'y')) // ask quiz creator if they would like to continue editting
			{  
				System.out.print("\nDo you wish to continue? (Y/N) >> ");
				confirmContinue = input.next().charAt(0);
				
				if((confirmContinue != 'N' && confirmContinue != 'n') && (confirmContinue != 'Y' && confirmContinue != 'y')) // if invalid input is entered
					System.out.println("Invalid input. Try again!");
			} // end while loop
		} // end while loop
		
		System.out.println("Returning to main menu...\n"); // message displayed before displaying main menu
	} // end modifyQuiz()
	
	/*
	 * This method allows quiz creators to delete their own quizzes if they so desire.
	 */
	private static void deleteQuiz(String role, String userName)
	{
		String quizId = ""; // initialize ID of quizzes to be deleted as null
		
		//display all quiz id  info 
		System.out.println("Please choose which quiz you want to delete"); // prompt user to enter ID of quiz to be deleted
		for(Quiz quiz:quizList) // list all quizzes in program
			System.out.print(quiz.getQuizId());
		System.out.println("");
		
		do
		{
			System.out.println("Enter '-1' to go back to the main menu."); // reminder: sentinel value to exit is '-1'
			System.out.print("Enter Quiz ID >> "); // prompt creator for quiz code
			quizId = input.nextLine();
			if(quizId.equals("-1")) // if sentinel value -1 is entered
				break; // exit to main method
			else if(quizId.length() != 4) // check if quiz code is 4 characters long
				System.out.println("Unable to accept input. Quiz code must be 4 characters long!\n"); // display error message
			else
			{
				int noMatch = 0; // counter to cross check how many quizzes do not have same ID as user input ID
				int i; // counter
				for(i = 0; i < quizList.size(); i++) // search for quiz with same ID
				{
					if(quizId.equals(quizList.get(i).getQuizId())) // if quiz is found
					{
						System.out.println("QUIZ FOUND!\n"); // notify user that quiz is found
						if(userName.equals(quizList.get(i).getQuizCreatedBy())) // quizzes can only be deleted by their own creators
						{
							quizList.get(i).displayQuizDetails(role); // display quiz details
							char userConfirm = ' '; // initialize choice to become blank space
							do
							{
								System.out.print("\nAre you sure you want to delete this quiz? (Y/N) >> "); // prompt user for confirmation
								userConfirm = input.next().charAt(0);
							
								if((userConfirm != 'Y' && userConfirm != 'y') && (userConfirm != 'N' && userConfirm != 'n')) // if invalid input is entered
									System.out.println("Invalid input. try again."); // display error message
							}
							while((userConfirm != 'Y' && userConfirm != 'y') && (userConfirm != 'N' && userConfirm != 'n')); // end do-while loop
						
							if(userConfirm == 'Y' || userConfirm == 'y') // if user wishes to delete quiz
							{
								quizList.remove(i); // delete if confirmed by creator
								System.out.println("Quiz has been successfully removed.\n"); // notify user that quiz is deleted
							} // end if statement
							else // if user aborts deletion process
								System.out.println("Quiz removal aborted.\n"); // notify user that quiz deletion is aborted
						} // end if statement
						else // if user is trying to delete quiz that does not belong to him/her
							System.out.println("WARNING: You are not authorized to delete this quiz.\n"); // notify user that he/she is not authorised to maek changes or delete that quiz
					} // end if statement
				} // end for loop
		
				
				if(noMatch == i) // if all quizzes do not match quizId
					System.out.println("Quiz not found. Quiz codes are CaSe SeNsItItIvE. Try again.\n"); // notify player that quiz does not exist
			} // end else statement
		} 
		while(!(quizId.equals("-1"))); // end do-while loop
		
		System.out.println("Returning to main menu...\n"); // message displayed before displaying main menu
	} // end deleteQuiz()
	
	/* for quiz players only */
	private static void playQuiz(String role, String userName)
	{
		String quizId = ""; // to be matched with a quiz Id
		System.out.println("");
		
		// prompt user for quiz code
		do
		{
			System.out.println("Enter '-1' to go back to the main menu."); // reminder: sentinel value to exit is '-1'
			System.out.print("Enter Quiz ID >> "); // prompt for quiz code
			quizId = input.next();
			if(quizId.equals("-1")) // if sentinel value '-1' is entered
				break; // exit to main method
			else if(quizId.toString().length() != 4) // if user input is not exactly 4 characters long
				System.out.println("Unable to accept input. Quiz code must be 4 characters long!\n");
			else // if user input contains exactly 4 characters
			{
				int noMatch = 0, numQuizzes = 0; // counters; noMatch to count how many quizzes do not have same ID as user input ID, numQuizzes to count number of quizzes in Quiz arraylist; both initialized to 0
				for(Quiz quizMatch: quizList) // search every quiz in quiz arraylist
				{
					numQuizzes++; // increment by 1 for every quiz found in the list
					if(!(quizId.equals(quizMatch.getQuizId()))) // for every non-matching entry
						noMatch++; // add 1 to noMatch
					else // quiz found, launch quiz
					{
						System.out.println("QUIZ FOUND!"); // notify user that quiz is found
						System.out.println("\n==================================================");
						quizMatch.displayQuizDetails(role); // display quiz details
						char choice = ' '; // choice to proceed and continue in the quiz
						do 
						{
							if(choice == 'X' || choice == 'x') // display instructions
								System.out.println("\n-----INSTRUCTIONS-----\n"
										+ "Players will be asked a series of questions.\n"
										+ "You need to answer them by typing in the correct answer.\n"
										+ "For each question answered correctly, " + quizMatch.getScorePerQ() + " point(s) will be awarded.\n"
										+ "Beware, you are only allowed " + quizMatch.getAttemptsAllowed() + " wrong answers before you lose.");
							
							System.out.println("\nEnter 'x' to display instructions."); // syntax 'x' for displaying instructions
							System.out.print("Proceed? (Y/N) >> "); // prompt user whether to continue or not
							choice = input.next().charAt(0);
							if((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n')) // if invalid input is entered
								if(choice != 'X' && choice != 'x') // if instructions syntax 'x' is not entered
									System.out.println("Invalid character input."); // display error message
						}
						while((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n')); // end do-while loop
						
						// play quiz only if 'Y' or 'y' is chosen
						if(choice == 'Y' || choice == 'y') 
						{
							do 
							{
								System.out.println("");
								//(quizType, quizId, quizName, numQuestions, scorePerQ, scoreDeductPerQ, score,highScore,highScoreHolder, 
								// attemptsAllowed, quizCreatedBy, ArrayList<Question> questionList,numAttempts)
								Quiz thisAttempt = new Attempts();
								thisAttempt.setQuizType(quizMatch.getQuizType());
								thisAttempt.setQuizId(quizMatch.getQuizId());
								thisAttempt.setQuizName(quizMatch.getQuizName());
								thisAttempt.setNumQuestions(quizMatch.getNumQuestions());
								thisAttempt.setScorePerQ(quizMatch.getScorePerQ());
								thisAttempt.setScoreDeductPerQ(quizMatch.getScoreDeductPerQ());
								thisAttempt.setScore(quizMatch.getScore());
								thisAttempt.setHighScore(quizMatch.getHighScore());
								thisAttempt.setHighScoreHolder(quizMatch.getHighScoreHolder());
								thisAttempt.setAttemptsAllowed(quizMatch.getAttemptsAllowed());
								thisAttempt.setQuizCreatedBy(quizMatch.getQuizCreatedBy());
								thisAttempt.setQuestionList(quizMatch.getQuestionList());
								Attempts thisRun = (Attempts)thisAttempt;
								thisRun.setNumAttempts(thisRun.getAttemptsAllowed());
								thisRun.displayNumAttempts();
								
								int quesNo = 1; // question number, starts from 1
								for(Question questionAsk: thisRun.getQuestionList())
								{
									int correctAnswerIndex = 0;
									for(Answer answer: questionAsk.getAnswerList()) // search for the correct answer for the question
									{
										if(answer.getIsCorrectAnswer())
											break;
										else
											correctAnswerIndex++;
									} // end enhanced-for loop
									correctAnswerIndex++; // to match answer options [1], [2], [3], etc.
									int answer = -1;
									while(answer != correctAnswerIndex)
									{
										System.out.println("\nQUESTION " + quesNo);
										questionAsk.displayQuestion();
										int i = 1;
										for(Answer showAnswer: questionAsk.getAnswerList()) 
										{
											System.out.print("[" + i + "] ");
											showAnswer.displayAnswer();
											i++;
										} // end enhanced-for loop
										System.out.print("\nAnswer: ");
										answer = input.nextInt();
										
										if(answer == correctAnswerIndex) // if answer input is correct
										{
											System.out.println("\nCORRECT!");
											thisRun.setScore(thisRun.getScore() + thisRun.getScorePerQ());
											System.out.println("Your current score: " + thisRun.getScore());
										} // end if statement
										else if(answer < 1 || answer >= i) // if out-of-range answer was input
											System.out.println("Please enter your answer from the given answer choices.");
										else
											thisRun.wrongAnswer(thisRun);
										if(thisRun.getNumAttempts() == 0) // if no attempts remain, end quiz immediately
											break;
									} // end while loop
									quesNo++; // incremenet question number for next question
									
									if(thisRun.getNumAttempts() == 0) // if no attempts remain
									{
										quizMatch.displayQuizOver(thisRun.getScore());
										break;
									} // end if statement
								} // end enhanced-for loop
								
								// if player finishes quiz with remaining attempts
								if(thisRun.getNumAttempts() != 0)
									thisRun.displayQuizCompleted(thisRun.getNumAttempts());
								
								// add score to player's cumulative score and +1 to number of quizzes played
								System.out.println("Saving progress...");
								int notFound = 0, numPlayers = 0;
								for(QuizPlayer player: quizPlayer)
								{
									numPlayers++; // add to total number of players
									if(userName.equals(player.getUserName()))
									{
										player.setNumQuizPlayed(player.getNumQuizPlayed() + 1);
										player.setCumScore(player.getCumScore() + thisRun.getScore());
										break;
									} // end if statement
									else
										notFound++;
								} // end enhanced-for loop
								if(notFound == numPlayers) // error that is not to happen - sudden deletion of player account
									System.out.println("\nWARNING: User cannot be found. Scores will not be saved.");
								else
									System.out.println("Progress saved.");
								
								System.out.print("\nContinue? (Y/N) >> "); // prompt player to whether he/she wants to continue playing this quiz
								choice = input.next().charAt(0);
								if((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n')) // if invalid choice is input
									System.out.println("Invalid character input.\n");
							}
							while(choice != 'N' && choice != 'n'); // end do-while loop
						} // end if-statement
						System.out.println("\nEnding quiz session..."); // message displayed before returning to prompt for quiz code
						System.out.println("==================================================\n");	
					} // end else statement
				} // end enhanced-for loop
				
				if(noMatch == numQuizzes) // if quiz is not found
					System.out.println("Quiz not found. Quiz codes are CaSe SeNsItItIvE. Try again.\n"); // notify player that quiz does not exist
			} // end else-if statement
		}
		while(quizId != "-1"); // end do-while loop
		
		System.out.println("Returning to main menu...\n"); // message displayed before displaying main menu
	} // end playQuiz()
	
	/* for all users to utilize */
	private static void editUserInfo(String role, String userName)
	{
		if(role == "quizCreator")
		{
			String changeName=" ";
			System.out.println("===========Edit User Name==========");
			System.out.println("This is your username: " + userName);

			for(QuizCreator quizCreatorNew: quizCreator) // loop to get creator's array index
			{ 
				if(quizCreatorNew.getUserName().equals(userName)) //once the compiler finds that the array list has what the username typed
				{ 
					System.out.println("Please enter your desired username"); // prompt user to change name
					do 
					{
						changeName = input.nextLine();
						break;
					} 
					while (input.nextLine() != null); // while username input is not empty
						 
					System.out.println("Are you sure you want to change your name to "+changeName+"?(Y/N)");
					char choice = input.nextLine().charAt(0);
						 
					while((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n'))
					{	
						if((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n'))
							System.out.println("Invalid input. Try again.");
						System.out.println("Are you sure you want to change your name to " + changeName + "?(Y/N)");
						choice = input.nextLine().charAt(0);
					} // end while loop
							
					if(choice == 'Y' || choice == 'y')
					{
						quizCreatorNew.setUserName(changeName);
						System.out.println("Your new username is: "+changeName);
						userName = changeName;
					} // end if statement
					else
						System.out.println("Aborted changes to username.");
				} // end while
			} // end loop for quizCreator
	
			System.out.println("Returning to main menu...\n"); // message displayed before displaying main menu
		} // end if
		
		else if(role == "quizPlayer")
		{
			String changeName=" ";
			System.out.println("===========Edit User Name==========");
			System.out.println("This is your username: " + userName);
			
			for(QuizPlayer quizPlayerNew: quizPlayer) // loop to get player's array index
			{ 
				if(quizPlayerNew.getUserName().equals(userName)) // once the compiler finds that the array list has what the username typed
				{
					System.out.println("Please enter your desired username"); // prompt user to change name
					do 
					{
						changeName = input.nextLine();
						break;
					} 
					while (input.nextLine() != null); // end do-while loop
					 
					System.out.println("Are you sure you want to change your name to " + changeName+" (Y/N)?");
					char choice = input.nextLine().charAt(0);
						
					while((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n'))
					{	
						if((choice != 'Y' && choice != 'y') && (choice != 'N' && choice != 'n'))
							System.out.println("Invalid input. Try again.");
						
						System.out.println("Are you sure you want to change your name to " + changeName + "?(Y/N)");
						choice = input.nextLine().charAt(0);
					} // end while loop
						
					if (choice =='Y' || choice == 'y')
					{
						quizPlayerNew.setUserName(changeName);
					    
					    System.out.println("Your new username is: " + changeName);
					    userName = changeName;
					}
					else
						System.out.println("Aborted changes to username.");
				} // end if statement for creator choice
			} // end loop for quizPlayer
			
			System.out.println("Returning to main menu...\n"); // message displayed before displaying main menu
		} // end if statement		
	} // end editUserInfo()
	
	/* for demo purposes only - preset users and quizzes */
	private static void initial()
	{
		// add quiz creators with preset fields
		QuizCreator creator01 = new QuizCreator();
		creator01.setUserName("dzombiedude612");
		creator01.setNumQuizCreated(8);
		quizCreator.add(creator01);
		QuizCreator creator02 = new QuizCreator();
		creator02.setUserName("aoiYuki");
		creator02.setNumQuizCreated(5);
		quizCreator.add(creator02);
		QuizCreator creator03 = new QuizCreator();
		creator03.setUserName("Crazy Dave"); // to be used to illustrate deleteQuiz
		creator03.setNumQuizCreated(1);
		quizCreator.add(creator03);
		
		// add quiz players
		QuizPlayer player01 = new QuizPlayer();
		player01.setUserName("Christina_Kang");
		player01.setNumQuizPlayed(5);
		player01.setCumScore(75);
		quizPlayer.add(player01);
		QuizPlayer player02 = new QuizPlayer();
		player02.setUserName("Melody");
		player02.setNumQuizPlayed(4);
		player02.setCumScore(50);
		quizPlayer.add(player02);
		
		// add sample quiz
		Quiz demoQuiz = new Quiz();
		demoQuiz.setQuizId("ARI1");
		demoQuiz.setQuizType("Mathematics");
		demoQuiz.setQuizName("Basic Mathematical Operations");
		demoQuiz.setQuizCreatedBy("Crazy Dave");
		demoQuiz.setHighScore(4);
		demoQuiz.setHighScoreHolder("Peashooter");
		demoQuiz.setNumQuestions(5);
		demoQuiz.setScorePerQ(5);
		
		Question demoQuizQ1 = new Question(); // adding question 1
		demoQuizQ1.setQuesDesc("What is 2 + 2?"); // set question 1 description
		demoQuizQ1.setNumAnswers(4); // set number of answers for question 1
		demoQuiz.getQuestionList().add(demoQuizQ1); // add question 1 to question array list
		Answer demoQuizQ1A1 = new Answer(); // adding answer 1 to question 1
		demoQuizQ1A1.setAnsDesc("4"); // set answer 1 description
		demoQuizQ1A1.setCorrectAnswer(true); // decide whether answer is correct or not
		demoQuizQ1.getAnswerList().add(demoQuizQ1A1); // add answer 1 to question 1's answer array list
		Answer demoQuizQ1A2 = new Answer();
		demoQuizQ1A2.setAnsDesc("5");
		demoQuizQ1A2.setCorrectAnswer(false);
		demoQuizQ1.getAnswerList().add(demoQuizQ1A2);
		Answer demoQuizQ1A3 = new Answer();
		demoQuizQ1A3.setAnsDesc("7");
		demoQuizQ1A3.setCorrectAnswer(false);
		demoQuizQ1.getAnswerList().add(demoQuizQ1A3);
		Answer demoQuizQ1A4 = new Answer();
		demoQuizQ1A4.setAnsDesc("All of the above");
		demoQuizQ1A4.setCorrectAnswer(false);
		demoQuizQ1.getAnswerList().add(demoQuizQ1A4);
		
		Question demoQuizQ2 = new Question();
		demoQuizQ2.setQuesDesc("Given a number x, what is x multiplied by 0?");
		demoQuizQ2.setNumAnswers(3);
		demoQuiz.getQuestionList().add(demoQuizQ2);
		Answer demoQuizQ2A1 = new Answer("-1", false); // alternative way of setting an answer
		demoQuizQ2.getAnswerList().add(demoQuizQ2A1);
		Answer demoQuizQ2A2 = new Answer("1", false);
		demoQuizQ2.getAnswerList().add(demoQuizQ2A2);
		Answer demoQuizQ2A3 = new Answer("0", true);
		demoQuizQ2.getAnswerList().add(demoQuizQ2A3);
		
		Question demoQuizQ3 = new Question();
		demoQuizQ3.setQuesDesc("Adam has two apples. If each of 30 of his classmates have two apples as well, how many apples do they have altogether?");
		demoQuizQ3.setNumAnswers(4);
		demoQuiz.getQuestionList().add(demoQuizQ3);
		Answer demoQuizQ3A1 = new Answer("52", false);
		demoQuizQ3.getAnswerList().add(demoQuizQ3A1);
		Answer demoQuizQ3A2 = new Answer("62", true);
		demoQuizQ3.getAnswerList().add(demoQuizQ3A2);
		Answer demoQuizQ3A3 = new Answer("60", false);
		demoQuizQ3.getAnswerList().add(demoQuizQ3A3);
		Answer demoQuizQ3A4 = new Answer("70", false);
		demoQuizQ3.getAnswerList().add(demoQuizQ3A4);
		
		Question demoQuizQ4 = new Question();
		demoQuizQ4.setQuesDesc("Any even number multiplied by an odd number will alway be equal to an even number.");
		demoQuizQ4.setNumAnswers(2);
		demoQuiz.getQuestionList().add(demoQuizQ4);
		Answer demoQuizQ4A1 = new Answer("True", true);
		demoQuizQ4.getAnswerList().add(demoQuizQ4A1);
		Answer demoQuizQ4A2 = new Answer("False", false);
		demoQuizQ4.getAnswerList().add(demoQuizQ4A2);
		
		Question demoQuizQ5 = new Question();
		demoQuizQ5.setQuesDesc("2(2) + 2(3) = 2(2 + 3)");
		demoQuizQ5.setNumAnswers(4);
		demoQuiz.getQuestionList().add(demoQuizQ5);
		Answer demoQuizQ5A1 = new Answer("Gah, this is depressing.", false);
		demoQuizQ5.getAnswerList().add(demoQuizQ5A1);
		Answer demoQuizQ5A2 = new Answer("I'm not sure.. I'll need my mom for this.", false);
		demoQuizQ5.getAnswerList().add(demoQuizQ5A2);
		Answer demoQuizQ5A3 = new Answer("Correct.", true);
		demoQuizQ5.getAnswerList().add(demoQuizQ5A3);
		Answer demoQuizQ5A4 = new Answer("Wrong.. this is the work of.. FAIRIES!!!", false);
		demoQuizQ5.getAnswerList().add(demoQuizQ5A4);
		
		quizList.add(demoQuiz); // add quiz to quizList
	} // end initial()
} // end QuizExe class
