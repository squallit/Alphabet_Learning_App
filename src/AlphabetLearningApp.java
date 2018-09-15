/*************************
 * Author: Son Luu
 * Class: CS 170 - 02
 * Date: 12/07/2016
 * Assignment: Final Project
 * Description: Interactive Alphabet Learning Program For Kids
 */




import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.InputStream;
import java.util.Random;
import sun.audio.*;

import javax.swing.*;

public class AlphabetLearningApp {
	
	
	//Variables
	private int currentScore = 0; //keep track of current scores
	private String currentPlayer = ""; //keep track of the current player
	private int[] highestScores = new int[]{40, 35, 25, 10, 5}; //keep track of the best scores
	private String[] bestPlayers = new String[]{"Sam Lee","Mark Johnson","Huy Chen","Jason Nguyen","Marc Jacobs"}; //keep track of the best Players
	private String currentLetter; //keep track of current letter shown by the picture
	private int challengeCounter = 0; //count the number of pictures to end the game, 10 challenge picture will be shown
	private final int HIGHEST_SCORE_SLOTS = 5;
	
	
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlphabetLearningApp window = new AlphabetLearningApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AlphabetLearningApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setFont(new Font("Ayuthaya", Font.BOLD, 20));
		frame.setBounds(0, 0, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); 
		
				
		
		/*********************************
		 * MAIN PANEL
		 * This Part is for Main Panel
		 * The main panel will contains all children panels
		 ********************************/
		//Create upper JPanel For Controls
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.window);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		frame.getContentPane().add(mainPanel);
		
		
		
		
		
		
		/*********************************
		 * UPPER PANEL
		 * This Part is for Upper Panel
		 * The upper panel will contains controls such as Play Button, High Score, Score, Status...
		 ********************************/
		//Create upper JPanel For Controls
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground(SystemColor.window);
		mainPanel.add(upperPanel);
		
		//Click to Play button
		JButton playBtn = new JButton("");
		playBtn.setHorizontalAlignment(SwingConstants.LEFT);
		upperPanel.add(playBtn);
		Image buttonImage = new ImageIcon(this.getClass().getResource("/play.png")).getImage();
		playBtn.setIcon(new ImageIcon(buttonImage));
		
		//High score button********
		JButton highscoreBtn = new JButton("");
		highscoreBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		upperPanel.add(highscoreBtn);
		Image hsBtnImg = new ImageIcon(this.getClass().getResource("/highscore.png")).getImage();
		highscoreBtn.setIcon(new ImageIcon(hsBtnImg));
		
		
		/*********************************
		 * CREDIT PANEL
		 * This Part is for Credit Panel, an introduction to the game
		 * This Panel only shows up at the beginning of the game before user click Play
		 ********************************/
		//Intro panels
		JLabel introLabel = new JLabel("WELCOME TO ALPHABEL MATCHING GAME"); //Wait for user to enter name after he clicks Play
		introLabel.setForeground(new Color(51, 102, 153));
		introLabel.setFont(new Font("Noteworthy", Font.BOLD, 30));
		introLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(introLabel);
		
		JLabel courseLabel = new JLabel("Class: CS-170 Java Programing"); //Wait for user to enter name after he clicks Play
		courseLabel.setForeground(new Color(153, 51, 51));
		courseLabel.setFont(new Font("Noteworthy", Font.BOLD, 20));
		courseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(courseLabel);
		
		JLabel authorLabel = new JLabel("Author: Son Luu"); //Wait for user to enter name after he clicks Play
		authorLabel.setForeground(new Color(51, 153, 102));
		authorLabel.setFont(new Font("Noteworthy", Font.BOLD, 20));
		authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(authorLabel);
		
		
		
		
		
		/*********************************
		 * MIDDLE PANEL
		 * This Part is for Middle Panel
		 * The middle panel will display challenges
		 ********************************/
		//Create middle JPanel for challenge display and information
		JPanel middlePanel = new JPanel();
		middlePanel.setBackground(SystemColor.window);
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
		mainPanel.add(middlePanel);
		
		
		
		//1st Middle JPanel is the most left middle panel containing your name
		//*******************************************************************
		JPanel firstMiddlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		firstMiddlePanel.setBackground(SystemColor.window);
		firstMiddlePanel.setLayout(new BoxLayout(firstMiddlePanel, BoxLayout.Y_AXIS));
		middlePanel.add(firstMiddlePanel);
		firstMiddlePanel.setVisible(false); //not show up until press Play
		
		//Your name
		JLabel nameLabel = new JLabel(""); //Wait for user to enter name after he clicks Play
		nameLabel.setForeground(new Color(51, 102, 0));
		nameLabel.setFont(new Font("Noteworthy", Font.BOLD, 40));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstMiddlePanel.add(nameLabel);
		
		
		//Simple Instruction
		JLabel instructLabel = new JLabel("Let's match the picture with the letter!              ");
		instructLabel.setForeground(new Color(102, 51, 51));
		instructLabel.setFont(new Font("Noteworthy", Font.BOLD, 25));
		instructLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstMiddlePanel.add(instructLabel);
		
		//Simple Instruction
		JLabel instructLabel2 = new JLabel("10 pictures will be shown each game!");
		instructLabel2.setForeground(new Color(153, 153, 0));
		instructLabel2.setFont(new Font("Noteworthy", Font.BOLD, 25));
		instructLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		firstMiddlePanel.add(instructLabel2);
		
		JLabel instructLabel3 = new JLabel("Score 5 points for each picture matched!");
		instructLabel3.setForeground(new Color(153, 153, 0));
		instructLabel3.setFont(new Font("Noteworthy", Font.BOLD, 25));
		instructLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		firstMiddlePanel.add(instructLabel3);
	
		
	
		//2nd Middle JPanel is the center challenge label
		//*******************************************************************
		JLabel secondMiddleLabel = new JLabel("");
		secondMiddleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image challengeImg = new ImageIcon(this.getClass().getResource("/intro.png")).getImage();
		secondMiddleLabel.setIcon(new ImageIcon(challengeImg));
		middlePanel.add(secondMiddleLabel);
		
		
		
		
		//3rd Middle JPanel is the most right JPanel contain score
		//*******************************************************************
		JPanel thirdMiddlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		thirdMiddlePanel.setBackground(SystemColor.window);
		thirdMiddlePanel.setLayout(new BoxLayout(thirdMiddlePanel, BoxLayout.X_AXIS));
		middlePanel.add(thirdMiddlePanel);
		thirdMiddlePanel.setVisible(false); //not show up until press Play
		
		JLabel emptyRightLabel = new JLabel("      ");
		thirdMiddlePanel.add(emptyRightLabel);
		
		JLabel scoreLabel = new JLabel("");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		Image scoreImg = new ImageIcon(this.getClass().getResource("/score.png")).getImage();
		scoreLabel.setIcon(new ImageIcon(scoreImg));
		thirdMiddlePanel.add(scoreLabel);
			
		JLabel scoreActiveLable = new JLabel(""); //Wait for user to click Play to set score
		scoreActiveLable.setForeground(new Color(51, 102, 153));
		scoreActiveLable.setHorizontalAlignment(SwingConstants.CENTER);
		scoreActiveLable.setFont(new Font("Kefa", Font.BOLD, 60));
		thirdMiddlePanel.add(scoreActiveLable);
		
		
		
		
		
		
		
		
		
		/*********************************
		 * LOWER PANEL
		 * This Part is for Lower Panel
		 * The lower panel will contains alphabet buttons
		 * This part will process the game
		 ********************************/
		//Create lower JPanel for Alphabet letter buttons
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(SystemColor.window);
		//lowerPanel.setBounds(0, 300, 1000, 100);
		mainPanel.add(lowerPanel);
		lowerPanel.setLayout(new GridLayout(0,13));
		lowerPanel.setVisible(false); //not show up until press Play
		
		//Initialize the alphabet buttons
		JButton[] letterButtons = new JButton[26];
		String[] letters = new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		for (int i = 0; i < letters.length; i++)
	    {
	    	letterButtons[i] = new JButton();
	    	letterButtons[i].setName(letters[i]); //set name of the buttons array corresponding to the letter
			Image letterImg = new ImageIcon(this.getClass().getResource("/" + letters[i] + ".png")).getImage();
			letterButtons[i].setIcon(new ImageIcon(letterImg));
			lowerPanel.add(letterButtons[i]);
		
	    }
		
		
		
		
		
		
		/*********************************
		 * HANDLING EVENTS
		 * This Part will handle all button events
		 ********************************/
		
		//*****************
		//Handle Letter Buttons
		//*****************
		for (int i = 0; i < letters.length; i++)
		{
			
			letterButtons[i].addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e)
			  {
				  //If the picture shown is less than 10 times, continue 
				  if (challengeCounter <= 10)
				  {
					  //****Generate a random picture*****//
					  //Get name of the button clicked
					  String buttonClicked = ((JButton) e.getSource()).getName(); 
					  
					  //Compare if the button clicked is equal to the current letter
					  if (currentLetter.equals(buttonClicked))
					  {
						  currentScore += 5; //add 5 to current score for each right match 
						  scoreActiveLable.setText(" " + currentScore);
					  }
					  
					  //get a random number from 0 to 25, according to alphabet order
					  int letterIndex = randomNumber(letters.length);
					  
					  //create a label with the corresponding character 
					  Image challengeImg = new ImageIcon(this.getClass().getResource("/" + letters[letterIndex] + "_Picture.png")).getImage();
					  secondMiddleLabel.setIcon(new ImageIcon(challengeImg));
					  
					  //update the current letter with new picture
					  currentLetter = letters[letterIndex];
					  
					  //Increase challenge counter by 1 as another picture is shown
					  challengeCounter++;
				  }
				  else //if the picture shown is equal 10 times, stop, game over, start a new game
				  {
					//Inform that game is over
					JOptionPane.showMessageDialog (frame, "Game is over. Please Start a new game!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
					  
					//Hide name & score & alphabet board
					firstMiddlePanel.setVisible(false);
					thirdMiddlePanel.setVisible(false);
					lowerPanel.setVisible(false);
					//Show credit panels
					introLabel.setVisible(true);
					authorLabel.setVisible(true);
					courseLabel.setVisible(true);
					
					//Set back intro image
					Image challengeImg = new ImageIcon(this.getClass().getResource("/intro.png")).getImage();
					secondMiddleLabel.setIcon(new ImageIcon(challengeImg));
					
					//Compare with highest scores table
					for (int i = 0; i < HIGHEST_SCORE_SLOTS; i++)
					{
						int tempScore = 0;
						String tempPlayer = "";
						if (currentScore > highestScores[i])
						{
							tempScore = highestScores[i];
							tempPlayer = bestPlayers[i];
							
							highestScores[i] = currentScore;
							bestPlayers[i] = currentPlayer;
							
							currentScore = tempScore;
							currentPlayer = tempPlayer;
						}
					}
				  }
			  }
			});
		}

		
		//*****************
		//Handle Play Button
		//*****************
		playBtn.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			   
			  //Ask user for name
			 JFrame popUpFrame = new JFrame("Your Name");
			 popUpFrame.setLocationRelativeTo(frame);
			 // prompt the user to enter their name
			 String name = JOptionPane.showInputDialog(popUpFrame, "What Is Your Name?");
			 
			 if (name != null)
			 {
				 //Set the name label to show user's name
				 nameLabel.setText("Hi " + name);
				 //Set the score to be zero
				 currentScore = 0;
				 scoreActiveLable.setText(" " + currentScore);
				  
				  
				 //Set name & score & alphabet board to visible
				 firstMiddlePanel.setVisible(true);
				 thirdMiddlePanel.setVisible(true);
				 lowerPanel.setVisible(true);
				 //Hide credit panels
				 introLabel.setVisible(false);
				 authorLabel.setVisible(false);
				 courseLabel.setVisible(false);
			  
				  
				 //****Generate a random picture*****//
				  
				 //get a random number from 0 to 25, according to alphabet order
				 int letterIndex = randomNumber(letters.length);
				 
				 //create a label with the corresponding character 
				 Image challengeImg = new ImageIcon(this.getClass().getResource("/" + letters[letterIndex] + "_Picture.png")).getImage();
				 secondMiddleLabel.setIcon(new ImageIcon(challengeImg));
				  
				 //assign the current letter
				 currentLetter = letters[letterIndex];
				 
				 //Set challenge counter to 1 as first picture is shown
				 challengeCounter = 1;
				 
				 //Set current player
				 currentPlayer = name;
			 }
		  }
		});
		
		
		
		//Handle High Score Button
		highscoreBtn.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			   
			  	 //Ask user for name
				 JFrame popUpFrame = new JFrame("Highest Scores");
				 popUpFrame.getContentPane().setBackground(SystemColor.window);
				 popUpFrame.setFont(new Font("Ayuthaya", Font.BOLD, 20));
				 popUpFrame.setBounds(0, 0, 400, 300);
				 popUpFrame.setLocationRelativeTo(frame); 
				 popUpFrame.setVisible(true);
			 
	
			
				 //Create upper JPanel For Display
				JPanel mainPopUpPanel = new JPanel();
				mainPopUpPanel.setBackground(SystemColor.window);
				mainPopUpPanel.setLayout(new GridLayout(0,2));
				popUpFrame.getContentPane().add(mainPopUpPanel);
				
				//Add high scores
				JLabel[] highestScoreLabel = new JLabel[HIGHEST_SCORE_SLOTS]; 
				JLabel[] bestPlayerLabel = new JLabel[HIGHEST_SCORE_SLOTS]; 
				for (int i = 0; i < HIGHEST_SCORE_SLOTS; i++)
			    {	
					bestPlayerLabel[i] = new JLabel();
					bestPlayerLabel[i].setText(bestPlayers[i]);
					bestPlayerLabel[i].setForeground(new Color(51, 102, 153));
					bestPlayerLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
					bestPlayerLabel[i].setFont(new Font("Kefa", Font.BOLD, 20));
					mainPopUpPanel.add(bestPlayerLabel[i]);
					
					
					highestScoreLabel[i] = new JLabel();
					highestScoreLabel[i].setText(""+highestScores[i]);
					highestScoreLabel[i].setForeground(new Color(51, 102, 153));
					highestScoreLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
					highestScoreLabel[i].setFont(new Font("Kefa", Font.BOLD, 20));
					mainPopUpPanel.add(highestScoreLabel[i]);
					
			    }
				
				
		  }
		});
		
	}
	
	
	
	
	
	
	/*************************
	 * HELPER FUNCTIONS
	*************************/
	
	//Return a random number
	public int randomNumber(int range)
	{
		Random rand = new Random();
		int  n = rand.nextInt(range);
		return n;
	}

}
