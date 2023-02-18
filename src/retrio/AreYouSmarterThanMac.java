package retrio;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class AreYouSmarterThanMac implements ActionListener {
	
	RetrioMain retrioMain;
	GameSelection gameSelection;
	
	private JPanel questionPanel, choicesButtonPanel, choicesAreaPanel, cheatsPanel, macIconPanel, macLivesPanel, playerIconPanel, playerLivesPanel;
	private JButton choiceAButton, choiceBButton, choiceCButton, choiceDButton, copyButton, fiftyfiftyButton, switchButton;
	private JTextArea questionArea, choiceAArea, choiceBArea, choiceCArea, choiceDArea;
	private JLabel quesNumLabel, cheatsLabel, macIconLabel, playerIconLabel, macLabel, playerLabel, macLivesLabel, playerLivesLabel, macAnsLabel, playerAnsLabel;

	//Stores the location of the sound effects
    URL correctAnswerSound = getClass().getClassLoader().getResource("Correct Answer Sound.wav");
    URL wrongAnswerSound = getClass().getClassLoader().getResource("Wrong Answer Sound.wav");
    URL cheatsButtonSound = getClass().getClassLoader().getResource("Cheats Button Click.wav");
    URL gameOverMusicPath = getClass().getClassLoader().getResource("Stage Clear.wav");
    URL backgroundMusicPath = getClass().getClassLoader().getResource("Mario Paint Gnat Attack 2.wav");
    URL choicesButtonSound = getClass().getClassLoader().getResource("Global Button Click.wav");
	private String correctAnswer;   //Stores the correct answer
	private String macLetterAnswer; //Stores the letter of Mac's answer
	
	private int quesNum = 1;        //Question number begins at 1
	private int macLives = 3;    	//Mac has 3 lives when the game begins
	private int playerLives = 3;    //Player has 3 lives when the game begins

	private Color customYellow = new Color(255, 255, 85);
	private Cursor buttonCursor = new Cursor(Cursor.HAND_CURSOR);
	private ArrayList <Integer> chosenItems = new ArrayList<>(); //Storing the items that have already been chosen
	private Timer timer = new Timer(); 
	
	public AreYouSmarterThanMac(RetrioMain retrioMain) {
		this.retrioMain = retrioMain;
	}
	
	public void gamePlay(int itemRange) { 
		
		int chosenItem = itemRandomizer(itemRange); //a random item number is generated and stored
		questionPrinter(chosenItem);                //print the question of the randomly chosen item number
		choicesPrinter(chosenItem);                 //print the choices of the randomly chosen item number
		setCorrectAnswer(chosenItem);               //stores the answer of the randomly chosen item number into the answerHolder String variable
		
		macAnsLabel.setText(null);                  //removes Mac's displayed answer
		playerAnsLabel.setText(null);               //removes Player's displayed answer
		
		choiceAButton.setEnabled(true);             //enables the choice buttons
		choiceBButton.setEnabled(true); 
		choiceCButton.setEnabled(true); 
		choiceDButton.setEnabled(true); 
		
		endGame();                                  //checks if the game is over
		
	}
	
	//CHOOSES A RANDOM ITEM NUMBER
	public int itemRandomizer(int itemRange) {             
	
		Random itemRandomizer = new Random();
		System.out.println(itemRange);
		int randomNum = itemRandomizer.nextInt(itemRange); //generates a random number
		
		if(chosenItems.contains(randomNum) == false) {     //if the number has not been chosen,
			chosenItems.add(randomNum);                    //store the number into the chosenItems ArrayList
		}
		else {                                             //a new random number will be generated if the original number was already chosen
			while(chosenItems.contains(randomNum)) {       //will keep generating a random number until it receives a value that has not yet been chosen
				randomNum = itemRandomizer.nextInt(itemRange);
			}	
			chosenItems.add(randomNum);                    //store the new, generated number into the chosenItems ArrayList
		}	
		return randomNum;
	}
	
	//PRINTS THE QUESTION ONTO THE TEXT AREA
	public void questionPrinter(int itemNum) {      
		questionArea.setText(retrioMain.questionReader.question.get(itemNum));
	}
	
	//RANDOMLY PRINTS THE CHOICES ONTO THEIR RESPECTIVE TEXT AREAS
	public void choicesPrinter(int itemNum) {       
		
		//store each choice into the choices array
		String[] choices = {retrioMain.questionReader.choiceA.get(itemNum), retrioMain.questionReader.choiceB.get(itemNum), 
					        retrioMain.questionReader.choiceC.get(itemNum), retrioMain.questionReader.choiceD.get(itemNum)};
		Random shuffle = new Random();
		
		//shuffles the arrangement of the choices within the array
		for(int i = 0; i < choices.length; i++) {   
			int shuffleSwap = shuffle.nextInt(choices.length);
			String temp = choices[shuffleSwap];
			choices[shuffleSwap] = choices[i];
			choices[i] = temp;
		}
		
		//the final arrangement of the choices will be printed accordingly
		choiceAArea.setText(choices[0]);          
		choiceBArea.setText(choices[1]);
		choiceCArea.setText(choices[2]);
		choiceDArea.setText(choices[3]);
	}
	
	public void setCorrectAnswer(int chosenItem) {
		this.correctAnswer = retrioMain.questionReader.answer.get(chosenItem);
	}
	
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	public void setQuesNum(int quesNum) {
		this.quesNum = quesNum;
	}
	
	public int getQuesNum() {
		return this.quesNum;
	}
	
	public void setMacLives(int macLives) {
		this.macLives = macLives;
	}
	
	public int getMacLives() {
		return this.macLives;
	}
	
	public void setPlayerLives(int playerLives) {
		this.playerLives = playerLives;
	}
	
	public int getPlayerLives() {
		return this.playerLives;
	}
	
	//RETURNS MAC'S ANSWER
	public String macAnswer(String correctAnswer) {
		
		Random shuffle = new Random(); 
		int random = shuffle.nextInt(100); //the random number will be used to determine if Mac will get the correct answer or not
		String macAnswer = " ";            //holds the correct answer
		
		//Mac has a 90% chance of getting the correct answer
		if(random % 10 == 0) { 
			//choose one of the 3 wrong answers as Mac's final answer
			if(choiceAArea.getText().equals(correctAnswer)) {
				macAnswer = setWrongAnswers(choiceBArea.getText(), choiceCArea.getText(), choiceDArea.getText());
			}
			else if(choiceBArea.getText().equals(correctAnswer)) {
				macAnswer = setWrongAnswers(choiceAArea.getText(), choiceCArea.getText(), choiceDArea.getText());
			}
			else if(choiceCArea.getText().equals(correctAnswer)) {
				macAnswer = setWrongAnswers(choiceAArea.getText(), choiceBArea.getText(), choiceDArea.getText());
			}
			else if(choiceDArea.getText().equals(correctAnswer)) {
				macAnswer = setWrongAnswers(choiceAArea.getText(), choiceBArea.getText(), choiceCArea.getText());
			}
		}
		else {
			macAnswer = correctAnswer; //Mac gets the correct answer
		}
			
		//determine which letter corresponds to Mac's answer
		if(macAnswer.equals(choiceAArea.getText())) {
			this.macLetterAnswer = "A";
		}
		else if(macAnswer.equals(choiceBArea.getText())) {
			this.macLetterAnswer = "B";
		}
		else if(macAnswer.equals(choiceCArea.getText())) {
			this.macLetterAnswer = "C";
		}
		else if(macAnswer.equals(choiceDArea.getText())) {
			this.macLetterAnswer = "D";
		}
		
		return macAnswer;
	}
	
	//CHOOSES WHICH OF THE WRONG ANSWERS WILL BE MAC'S ANSWER
	public String setWrongAnswers(String wrongAns1, String wrongAns2, String wrongAns3) {
		
		Random shuffle = new Random(); 
		String[] wrongAns = new String[3]; //array for storing the 3 wrong answers
		String macWrongAnswer = " ";       //holds the final wrong answer
		
		wrongAns[0] = wrongAns1;
		wrongAns[1] = wrongAns2;
		wrongAns[2] = wrongAns3;
		
		macWrongAnswer = wrongAns[shuffle.nextInt(3)]; //chooses which one of the wrong answers is Mac's final answer
		
		return macWrongAnswer;
	}

	//CHECKS THE PLAYER AND MAC'S ANSWERS
	public void checkAnswer(String correctAnswer, String playerAnswer, String macAnswer) {
		
		TimerTask timeForNewItem = new TimerTask() {

			@Override
			public void run() {
				
				if(correctAnswer.equals(playerAnswer)) {      //if Player got the correct answer
					retrioMain.sound.soundEffect(correctAnswerSound, 0); //play the correct answer sound effect
				}
				
				if(!correctAnswer.equals(playerAnswer)) {     //if Player got the wrong answer
					retrioMain.sound.soundEffect(wrongAnswerSound, 0);   //play the wrong answer sound effect
					setPlayerLives(getPlayerLives() - 1);     //deduct 1 life from Player	
					if(getPlayerLives() == 2) {
						playerLivesLabel.setIcon(new ImageIcon("images/TwoLives.JPG"));
					}
					else if(getPlayerLives() == 1) {
						playerLivesLabel.setIcon(new ImageIcon("images/OneLife.JPG"));
					}
					else if(getPlayerLives() == 0) {
						playerLivesLabel.setIcon(new ImageIcon("images/NoLives.JPG"));
					}
				}

				if(!correctAnswer.equals(macAnswer)) {        //if Mac got the wrong answer
					setMacLives(getMacLives() - 1);           //deduct 1 life from Mac
					if(getMacLives() == 2) {
						macLivesLabel.setIcon(new ImageIcon("images/TwoLives.JPG"));
					}
					else if(getMacLives() == 1) {
						macLivesLabel.setIcon(new ImageIcon("images/OneLife.JPG"));
					}
					else if(getMacLives() == 0) {
						macLivesLabel.setIcon(new ImageIcon("images/NoLives.JPG"));
					}
				}
				
				setQuesNum(getQuesNum() + 1);                           //increment the Question number
				quesNumLabel.setText("Question no." + getQuesNum());    //display the new Question number
				gamePlay(retrioMain.questionReader.getQuestionSize());  //call gamePlay method to print new question and choices
				
			}	
		};
		
		timer.schedule(timeForNewItem, 3000);
		
	}
	
	//COPY CHEAT
	public void copyCheat(String macAnswer) {	
		checkAnswer(getCorrectAnswer(), macAnswer, macAnswer); //Player's answer parameter is the same as Mac's answer
	}
	
	//50:50 CHEAT
	public void fiftyFiftyCheat(String correctAnswer) { 
		
		boolean[] trueOrFalse = {true, false, false};
		Random shuffle = new Random(); 	
		
		//shuffles the order of the true and false values 
		for(int i = 0; i < trueOrFalse.length; i++) { 
			int shuffleSwap = shuffle.nextInt(trueOrFalse.length);
			boolean temp = trueOrFalse[shuffleSwap];
			trueOrFalse[shuffleSwap] = trueOrFalse[i];
			trueOrFalse[i] = temp;
		}
		
		//Assigns 1 true and 2 false values to the 3 choice buttons that do not contain the correct answer
		//The button with the correct answer and true value will remain enabled, while the 2 buttons with the false values will be disabled
		if(correctAnswer.equals(choiceAArea.getText())) { 
			choiceBButton.setEnabled(trueOrFalse[0]);
			choiceCButton.setEnabled(trueOrFalse[1]);
			choiceDButton.setEnabled(trueOrFalse[2]);
		}
		else if(correctAnswer.equals(choiceBArea.getText())) { 
			choiceAButton.setEnabled(trueOrFalse[0]);
			choiceCButton.setEnabled(trueOrFalse[1]);
			choiceDButton.setEnabled(trueOrFalse[2]);
		}
		else if(correctAnswer.equals(choiceCArea.getText())) {
			choiceAButton.setEnabled(trueOrFalse[0]);
			choiceBButton.setEnabled(trueOrFalse[1]);
			choiceDButton.setEnabled(trueOrFalse[2]);
		}
		else if(correctAnswer.equals(choiceDArea.getText())) {
			choiceAButton.setEnabled(trueOrFalse[0]);
			choiceBButton.setEnabled(trueOrFalse[1]);
			choiceCButton.setEnabled(trueOrFalse[2]);
		}
	}
	
	//DISABLES CHOICE BUTTONS
	public void disableChoiceButtons() {
		choiceAButton.setEnabled(false);
		choiceBButton.setEnabled(false);
		choiceCButton.setEnabled(false);
		choiceDButton.setEnabled(false);
	}
	
	//END OF THE GAME
	public void endGame() {
		
		if(getPlayerLives() == 0 && getMacLives() > 0) {      //if Player's lives reach 0
			retrioMain.sound.backgroundMusic(null, 1);
			retrioMain.sound.backgroundMusic(gameOverMusicPath, 0);
			loseMessage();
		}
		else if(getPlayerLives() > 0 && getMacLives() == 0) { //if Mac's lives reach 0
			winMessage();
		}
		else if(getQuesNum() == 11) {	//31				  //if 30 items have been answered
			if(getPlayerLives() < getMacLives()) {            //if Player has less lives than Mac's lives
				loseMessage();
			}
			else if(getPlayerLives() > getMacLives()) {       //if Mac has less lives than Player's lives
				winMessage();
			}
			else if(getPlayerLives() == getMacLives()) {      //if Player and Mac have equal number of lives
				if(!copyButton.isEnabled() || !fiftyfiftyButton.isEnabled() || !switchButton.isEnabled()) {
					loseMessage();
				}
				else {
					winMessage();
				}
			}
		}
		
	}
	
	//DISPLAYS THE LOSE MESSAGE
	public void loseMessage() {	
		resetValues();
		JOptionPane.showMessageDialog(null, "You Are NOT Smarter than Mac", "Game Over", JOptionPane.ERROR_MESSAGE);
		retrioMain.sound.backgroundMusic(null, 1);
		retrioMain.removeContents();
		retrioMain.mainMenu.gameSelec.gameSelectionScreen();
	}
	
	//DISPLAYS THE WINNING MESSAGE
	public void winMessage() {
		resetValues();
		JOptionPane.showMessageDialog(null, "YOU ARE SMARTER THAN MAC!!!", "Game Over", JOptionPane.PLAIN_MESSAGE);
		retrioMain.sound.backgroundMusic(null, 1);
		retrioMain.removeContents();
		retrioMain.mainMenu.gameSelec.gameSelectionScreen();
	}
	
	public void resetValues() {
		
		quesNum = 1;        //Question number begins at 1
		macLives = 3;    	//Mac has 3 lives when the game begins
		playerLives = 3;    //Player has 3 lives when the game begins
		
	}

	//ACTION LISTENER
	@Override
	public void actionPerformed(ActionEvent action) {
		
		String theCommand = action.getActionCommand(); 
		String correctAnswer = getCorrectAnswer();
		
		switch(theCommand) { 
			case "A":
				retrioMain.sound.soundEffect(choicesButtonSound, 0);
				checkAnswer(correctAnswer, choiceAArea.getText(), macAnswer(correctAnswer));
				playerAnsLabel.setText("A");
				macAnsLabel.setText(this.macLetterAnswer);
				disableChoiceButtons();
				break;
			case "B":
				retrioMain.sound.soundEffect(choicesButtonSound, 0);
				checkAnswer(correctAnswer, choiceBArea.getText(), macAnswer(correctAnswer));
				playerAnsLabel.setText("B");
				macAnsLabel.setText(this.macLetterAnswer);
				disableChoiceButtons();
				break;
			case "C":
				retrioMain.sound.soundEffect(choicesButtonSound, 0);
				checkAnswer(correctAnswer, choiceCArea.getText(), macAnswer(correctAnswer));
				playerAnsLabel.setText("C");
				macAnsLabel.setText(this.macLetterAnswer);
				disableChoiceButtons();
				break;
			case "D":
				retrioMain.sound.soundEffect(choicesButtonSound, 0);
				checkAnswer(correctAnswer, choiceDArea.getText(), macAnswer(correctAnswer));
				playerAnsLabel.setText("D");
				macAnsLabel.setText(this.macLetterAnswer);
				disableChoiceButtons();
				break;
			case "COPY":
				retrioMain.sound.soundEffect(cheatsButtonSound, 0);
				copyCheat(macAnswer(correctAnswer));
				playerAnsLabel.setText(this.macLetterAnswer);
				macAnsLabel.setText(this.macLetterAnswer);
				disableChoiceButtons();
				copyButton.setEnabled(false);
				break;
			case "FIFTYFIFTY":
				retrioMain.sound.soundEffect(cheatsButtonSound, 0);
				fiftyFiftyCheat(correctAnswer);
				fiftyfiftyButton.setEnabled(false);
				break;
			case "SWITCH":
				retrioMain.sound.soundEffect(cheatsButtonSound, 0);
				gamePlay(retrioMain.questionReader.getQuestionSize());
				switchButton.setEnabled(false);
				break;
		}
		
	}
	
	//GRAPHICS
	public void areYouSmarterThanMacGraphics() {
		
		retrioMain.createBanner(" Are You Smarter Than Mac? ");
		
		//DISPLAY MAC'S ICON
		macIconPanel = new JPanel();
		macIconPanel.setBounds(50, 130, 100, 80);
		macIconPanel.setBackground(Color.white);
		macIconLabel = new JLabel();
		macIconLabel.setIcon(new ImageIcon("images/maclogo.png"));
		macIconPanel.add(macIconLabel);
		
		//DISPLAY MAC'S LIVES
		macLivesPanel = new JPanel(new GridLayout(2, 1));
		macLivesPanel.setBounds(150, 125, 90, 90);
		macLivesPanel.setBackground(Color.white);
		macLabel = new JLabel("Mac");
		macLabel.setFont(retrioMain.retrioFont.deriveFont(25f));
		macLivesLabel = new JLabel();
		macLivesLabel.setIcon(new ImageIcon("images/ThreeLives.JPG"));
		macLivesPanel.add(macLabel);
		macLivesPanel.add(macLivesLabel);
		
		//DISPLAY MAC'S ANSWER
		macAnsLabel = new JLabel();
		macAnsLabel.setBounds(250, 180, 50, 50);
		macAnsLabel.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 30f));
		
		//DISPLAY THE PLAYER'S ICON
		playerIconPanel = new JPanel();
		playerIconPanel.setBounds(50, 250, 100, 80);
		playerIconPanel.setBackground(Color.white);
		playerIconLabel = new JLabel();
		playerIconLabel.setIcon(new ImageIcon("images/macman.png"));
		playerIconPanel.add(playerIconLabel);
		
		//DISPLAY THE PLAYER'S LIVES
		playerLivesPanel = new JPanel(new GridLayout(2, 1));
		playerLivesPanel.setBounds(150, 245, 90, 90);
		playerLivesPanel.setBackground(Color.white);
		playerLabel = new JLabel("You");
		playerLabel.setFont(retrioMain.retrioFont.deriveFont(25f));
		playerLivesLabel = new JLabel();
		playerLivesLabel.setIcon(new ImageIcon("images/ThreeLives.JPG"));
		playerLivesPanel.add(playerLabel);
		playerLivesPanel.add(playerLivesLabel);
		
		//DISPLAY THE PLAYER'S ANSWER
		playerAnsLabel = new JLabel();
		playerAnsLabel.setBounds(250, 300, 50, 50);
		playerAnsLabel.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 30f));
		
		//QUESTION AREA
		questionPanel = new JPanel(); 
		questionPanel.setBounds(350, 100, 570, 290);
		questionPanel.setBackground(Color.white);
		questionPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		//DISPLAY QUESTION NUMBER
		quesNumLabel = new JLabel("Question no." + getQuesNum(), JLabel.CENTER);
		quesNumLabel.setForeground(Color.black);
		quesNumLabel.setOpaque(true);
		quesNumLabel.setBackground(customYellow);
		quesNumLabel.setPreferredSize(new Dimension(560, 30));
		quesNumLabel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		quesNumLabel.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		questionPanel.add(quesNumLabel);
		
		//QUESTION AREA
		questionArea = new JTextArea(); 
		questionArea.setForeground(Color.black);
		questionArea.setEditable(false);
		questionArea.setFont(retrioMain.retrioFont.deriveFont(20f));
		questionArea.setLineWrap(true);
		questionArea.setWrapStyleWord(true);
		questionArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		JScrollPane scrollBar = new JScrollPane(questionArea); 
		scrollBar.setPreferredSize(new Dimension(560, 240));
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		questionPanel.add(scrollBar);
		
		//CHOICES BUTTON PANEL
		choicesButtonPanel = new JPanel(new GridLayout(4, 1, 0, 5));
		choicesButtonPanel.setBounds(350, 410, 45, 200);
		choicesButtonPanel.setBackground(Color.white);
		
		//CHOICE A BUTTON
		choiceAButton = new JButton("A"); 
		choiceAButton.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		choiceAButton.setBorderPainted(false);
		choiceAButton.setBackground(Color.white);
		choiceAButton.setActionCommand("A");
		choiceAButton.addActionListener(this);
		choiceAButton.setCursor(buttonCursor);
		choicesButtonPanel.add(choiceAButton);
		
		//CHOICE B BUTTON
		choiceBButton = new JButton("B"); 
		choiceBButton.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		choiceBButton.setBorderPainted(false);
		choiceBButton.setBackground(Color.white);
		choiceBButton.setActionCommand("B");
		choiceBButton.addActionListener(this);
		choiceBButton.setCursor(buttonCursor);
		choicesButtonPanel.add(choiceBButton);
		
		//CHOICE C BUTTON
		choiceCButton = new JButton("C"); 
		choiceCButton.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		choiceCButton.setBorderPainted(false);
		choiceCButton.setBackground(Color.white);
		choiceCButton.setActionCommand("C");
		choiceCButton.addActionListener(this);
		choiceCButton.setCursor(buttonCursor);
		choicesButtonPanel.add(choiceCButton);
		
		//CHOICE D BUTTON
		choiceDButton = new JButton("D"); 
		choiceDButton.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		choiceDButton.setBorderPainted(false);
		choiceDButton.setBackground(Color.white);
		choiceDButton.setActionCommand("D");
		choiceDButton.addActionListener(this);
		choiceDButton.setCursor(buttonCursor);
		choicesButtonPanel.add(choiceDButton);
		
		//CHOICES TEXT AREA PANEL
		choicesAreaPanel = new JPanel(new GridLayout(4, 1, 0, 5)); 
		choicesAreaPanel.setBounds(400, 410, 510, 200);
		choicesAreaPanel.setBackground(Color.white);
		
		//CHOICE A TEXT AREA
		choiceAArea = new JTextArea();
		choiceAArea.setForeground(Color.BLACK);
		choiceAArea.setFont(retrioMain.retrioFont.deriveFont(15f));
		choiceAArea.setEditable(false);
		choiceAArea.setLineWrap(true);
		choiceAArea.setWrapStyleWord(true);
		choiceAArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		choicesAreaPanel.add(choiceAArea);
		
		//CHOICE B TEXT AREA
		choiceBArea = new JTextArea();
		choiceBArea.setForeground(Color.BLACK);
		choiceBArea.setFont(retrioMain.retrioFont.deriveFont(15f));
		choiceBArea.setEditable(false);
		choiceBArea.setLineWrap(true);
		choiceBArea.setWrapStyleWord(true);
		choiceBArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		choicesAreaPanel.add(choiceBArea);
		
		//CHOICE C TEXT AREA
		choiceCArea = new JTextArea();
		choiceCArea.setForeground(Color.BLACK);
		choiceCArea.setFont(retrioMain.retrioFont.deriveFont(15f));
		choiceCArea.setEditable(false);
		choiceCArea.setLineWrap(true);
		choiceCArea.setWrapStyleWord(true);
		choiceCArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		choicesAreaPanel.add(choiceCArea);
		
		//CHOICE D TEXT AREA
		choiceDArea = new JTextArea();
		choiceDArea.setForeground(Color.BLACK);
		choiceDArea.setFont(retrioMain.retrioFont.deriveFont(15f));
		choiceDArea.setEditable(false);
		choiceDArea.setLineWrap(true);
		choiceDArea.setWrapStyleWord(true);
		choiceDArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		choicesAreaPanel.add(choiceDArea);
		
		//CHEATS PANEL
		cheatsPanel = new JPanel(new GridLayout(4, 1));
		cheatsPanel.setBounds(50, 410, 200, 160);
		cheatsPanel.setBackground(customYellow);
		cheatsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		cheatsLabel = new JLabel("Cheats");
		cheatsLabel.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		cheatsLabel.setHorizontalAlignment(JLabel.CENTER);
		cheatsPanel.add(cheatsLabel);
		
		//COPY BUTTON
		copyButton = new JButton("Copy");
		copyButton.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		copyButton.setBorderPainted(false);
		copyButton.setBackground(Color.white);
		copyButton.setActionCommand("COPY");
		copyButton.addActionListener(this);
		copyButton.setCursor(buttonCursor);
		cheatsPanel.add(copyButton);
		
		//50:50 BUTTON
		fiftyfiftyButton = new JButton("50:50");
		fiftyfiftyButton.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		fiftyfiftyButton.setBorderPainted(false);
		fiftyfiftyButton.setBackground(Color.white);
		fiftyfiftyButton.setActionCommand("FIFTYFIFTY");
		fiftyfiftyButton.addActionListener(this);
		fiftyfiftyButton.setCursor(buttonCursor);
		cheatsPanel.add(fiftyfiftyButton);
		
		//SWITCH BUTTON
		switchButton = new JButton("Switch");
		switchButton.setFont(retrioMain.retrioFont.deriveFont(Font.BOLD, 20f));
		switchButton.setBorderPainted(false);
		switchButton.setBackground(Color.white);
		switchButton.setActionCommand("SWITCH");
		switchButton.addActionListener(this);
		switchButton.setCursor(buttonCursor);
		cheatsPanel.add(switchButton);
		
		//BACKGROUND MUSIC
		retrioMain.sound.backgroundMusic(backgroundMusicPath, -1);
		
		//ADD PANELS TO THE MAIN CONTAINER
		retrioMain.mainFrame.add(macIconPanel);
		retrioMain.mainFrame.add(macLivesPanel);
		retrioMain.mainFrame.add(macAnsLabel);
		retrioMain.mainFrame.add(playerIconPanel);
		retrioMain.mainFrame.add(playerLivesPanel);
		retrioMain.mainFrame.add(playerAnsLabel);
		retrioMain.mainFrame.add(questionPanel);
		retrioMain.mainFrame.add(choicesButtonPanel);
		retrioMain.mainFrame.add(choicesAreaPanel);
		retrioMain.mainFrame.add(cheatsPanel);
		retrioMain.mainFrame.setVisible(true);
		
	}

}
