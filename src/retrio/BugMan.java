package retrio;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class BugMan implements ActionListener{
	
	RetrioMain main;
	BugManGame bugmanGame;
	
	//Graphics
	JButton upButton, downButton, leftButton, rightButton, aButton, bButton, cButton, dButton;
	JPanel buttonPanel = new JPanel(new GridBagLayout());
	JPanel gamePanel = new JPanel();
	JPanel questionPanel = new JPanel();
	JLabel questionLabel;
	JTextArea questionText, aText, bText, cText, dText;
	GridBagConstraints c = new GridBagConstraints();
	
	URL backgroundMusicPath = getClass().getClassLoader().getResource("Mario Paint Gnat Attack Boss.wav");
	URL keyboardSound = getClass().getClassLoader().getResource("Keyboard Press.wav");	
    URL correctAnswerSound = getClass().getClassLoader().getResource("Correct Answer Sound");
    URL wrongAnswerSound = getClass().getClassLoader().getResource("Wrong Answer Sound.wav");
	
	ArrayList <Integer> chosenItems = new ArrayList<>(); //Storing the items that have already been chosen
	String correctAnswer;
	
	public BugMan(RetrioMain main) {
		
		this.main = main;
		
	}
	
	public void gamePlay() {
	
		bugmanGame = new BugManGame(main);
		bugManGraphics();
		main.mainFrame.validate();
		main.mainFrame.setVisible(true);
	
	}
	
	public void bugManGraphics() {
		
		main.sound.backgroundMusic(backgroundMusicPath, -1);
		main.createBanner(" BugMan ");
		
		buttonPanel.setBounds(20, 300, 140, 140);
		buttonPanel.setBackground(Color.white);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		
		//Up Button
		upButton = new JButton("^"); 
		upButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 25f));
		upButton.setBorderPainted(false);
		upButton.setBackground(Color.white);
		upButton.addActionListener(this);
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.PAGE_START;
		buttonPanel.add(upButton, c);
		
		//Left Button
		leftButton = new JButton("<");
		leftButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 18f));
		leftButton.setBorderPainted(false);
		leftButton.setBackground(Color.white);
		leftButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.LINE_START;
		buttonPanel.add(leftButton, c);
		
		//Right Button
		rightButton = new JButton(">");
		rightButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 18f));
		rightButton.setBorderPainted(false);
		rightButton.setBackground(Color.white);
		rightButton.addActionListener(this);
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.LINE_END;
		buttonPanel.add(rightButton, c);
		
		//Down Button
		downButton = new JButton("v"); 
		downButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 25f));
		downButton.setBorderPainted(false);
		downButton.setBackground(Color.white);
		downButton.addActionListener(this);
		c.gridx = 1;
		c.gridy = 2;
		c.fill = GridBagConstraints.PAGE_END;
		buttonPanel.add(downButton, c);
		
		//Maze Panel
		gamePanel.setBounds(175, 115, 475, 475);
		gamePanel.setBackground(new Color(68, 114, 196));
		gamePanel.setBorder(BorderFactory.createLineBorder(new Color(38, 67, 120)));
		gamePanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		
		gamePanel.add(bugmanGame);
		gamePanel.setVisible(true);
		
		main.mainFrame.add(buttonPanel);
		main.mainFrame.add(gamePanel);
		main.mainFrame.add(questionPanel);
		main.mainFrame.setPreferredSize(new Dimension(960, 680));
		main.mainFrame.pack();
		main.mainFrame.setVisible(true);
		
	}
	
	public void showQuestion() {
		
		//Question Panel
		questionPanel.setVisible(true);
		questionPanel.setBounds(670, 115, 260, 475);
		questionPanel.setBackground(Color.white);
		questionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		questionPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		
		questionLabel = new JLabel(" Question ");
		questionLabel.setBackground(Color.black);
		questionLabel.setForeground(Color.white);
		questionLabel.setOpaque(true);
		questionPanel.add(questionLabel);
        	
        questionText = new JTextArea();
        questionText.setFont(main.retrioFont.deriveFont(Font.PLAIN, 20f));
        questionText.setForeground(Color.black);
		questionText.setEditable(false);
		questionText.setLineWrap(true);
		questionText.setWrapStyleWord(true);
		JScrollPane scrollBar = new JScrollPane(questionText); 
		scrollBar.setPreferredSize(new Dimension(230, 200));
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		questionPanel.add(scrollBar);
		
		//Choices
		aButton = new JButton("A");
		aButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 25f));
		aButton.setBackground(Color.gray);
		aButton.addActionListener(this);
		questionPanel.add(aButton);
		
		aText = new JTextArea("a");
		aText.setFont(main.retrioFont.deriveFont(Font.PLAIN, 13.5f));
		aText.setForeground(Color.BLACK);
		aText.setEditable(false);
		aText.setLineWrap(true);
		aText.setWrapStyleWord(true);
		aText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		aText.setPreferredSize(new Dimension(180, 50));
		questionPanel.add(aText);
		
		bButton = new JButton("B");
		bButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 25f));
		bButton.setBackground(Color.gray);
		bButton.addActionListener(this);
		questionPanel.add(bButton);
		
		bText = new JTextArea("a");
		bText.setFont(main.retrioFont.deriveFont(Font.PLAIN, 13.5f));
		bText.setForeground(Color.BLACK);
		bText.setEditable(false);
		bText.setLineWrap(true);
		bText.setWrapStyleWord(true);
		bText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		bText.setPreferredSize(new Dimension(180, 50));
		questionPanel.add(bText);
		
		cButton = new JButton("C");
		cButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 25f));
		cButton.setBackground(Color.gray);
		cButton.addActionListener(this);
		questionPanel.add(cButton);
		
		cText = new JTextArea("a");
		cText.setFont(main.retrioFont.deriveFont(Font.PLAIN, 13.5f));
		cText.setForeground(Color.BLACK);
		cText.setEditable(false);
		cText.setLineWrap(true);
		cText.setWrapStyleWord(true);
		cText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		cText.setPreferredSize(new Dimension(180, 50));
		questionPanel.add(cText);
		
		dButton = new JButton("D");
		dButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 25f));
		dButton.setBackground(Color.gray);
		dButton.addActionListener(this);
		questionPanel.add(dButton);
		
		dText = new JTextArea("a");
		dText.setFont(main.retrioFont.deriveFont(Font.PLAIN, 13.5f));
		dText.setForeground(Color.BLACK);
		dText.setEditable(false);
		dText.setLineWrap(true);
		dText.setWrapStyleWord(true);
		dText.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		dText.setPreferredSize(new Dimension(180, 50));
		questionPanel.add(dText);
		
		displayQuestion(main.questionReader.getQuestionSize());
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
		questionText.setText(main.questionReader.question.get(itemNum));
	}
	
	//RANDOMLY PRINTS THE CHOICES ONTO THEIR RESPECTIVE TEXT AREAS
	public void choicesPrinter(int itemNum) {       
		
		//store each choice into the choices array
		String[] choices = {main.questionReader.choiceA.get(itemNum), main.questionReader.choiceB.get(itemNum), 
					        main.questionReader.choiceC.get(itemNum), main.questionReader.choiceD.get(itemNum)};
		Random shuffle = new Random();
		
		//shuffles the arrangement of the choices within the array
		for(int i = 0; i < choices.length; i++) {   
			int shuffleSwap = shuffle.nextInt(choices.length);
			String temp = choices[shuffleSwap];
			choices[shuffleSwap] = choices[i];
			choices[i] = temp;
		}
		
		//the final arrangement of the choices will be printed accordingly
		aText.setText(choices[0]);          
		bText.setText(choices[1]);
		cText.setText(choices[2]);
		dText.setText(choices[3]);
	}
	
	//CHECKS THE PLAYER'S ANSWERS
	public void checkAnswer(String correctAnswer, String playerAnswer) {
				
		if(correctAnswer.equals(playerAnswer)) {      //if Player got the correct answer
			main.sound.soundEffect(correctAnswerSound, 0); //play the correct answer sound effect
			bugmanGame.score += 10;
			
			if(bugmanGame.purpleQ) {
				
				if(bugmanGame.ghostNum != 0) {
					
					bugmanGame.purpleQ = false;
					bugmanGame.ghostNum--;
					bugmanGame.ghostLoc[bugmanGame.ghostNum][0] = bugmanGame.ghostLoc[bugmanGame.ghostNum][1] = 1000;
					
				}
				
			}
			
		}
				
		if(!correctAnswer.equals(playerAnswer)) {     //if Player got the wrong answer
			main.sound.soundEffect(wrongAnswerSound, 0);   //play the wrong answer sound effect
			bugmanGame.addGhost();
		}
		
		displayQuestion(main.questionReader.getQuestionSize());  //call gamePlay method to print new question and choices
		
	}
	
	public void displayQuestion(int itemRange) {
		
		int chosenItem = itemRandomizer(itemRange); //a random item number is generated and stored
		questionPrinter(chosenItem);                //print the question of the randomly chosen item number
		choicesPrinter(chosenItem);                 //print the choices of the randomly chosen item number
		setCorrectAnswer(chosenItem);               //stores the answer of the randomly chosen item number into the answerHolder String variable
		
	}
		
	public void setCorrectAnswer(int chosenItem) {
		this.correctAnswer = main.questionReader.answer.get(chosenItem);
	}
	
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		main.sound.soundEffect(keyboardSound, 0);
		String theCommand = e.getActionCommand();

		switch(theCommand) {
		
		case "^":
			
			if(bugmanGame.mapLayout[(bugmanGame.bugLoc[1]/30) - 1][(bugmanGame.bugLoc[0]/30)] != 1) { //First row
				
				bugmanGame.bugLoc[1] = bugmanGame.bugLoc[1] - 30;
				
			}
			
			break;
			
		case "v":

			if(bugmanGame.mapLayout[(bugmanGame.bugLoc[1]/30) + 1][(bugmanGame.bugLoc[0]/30)] != 1) {
				
				bugmanGame.bugLoc[1] = bugmanGame.bugLoc[1] + 30;
				
			}
			
			break;
			
		case "<":
			
			if(bugmanGame.mapLayout[(bugmanGame.bugLoc[1]/30)][(bugmanGame.bugLoc[0]/30) - 1] != 1) {
				
				bugmanGame.bugLoc[0] = bugmanGame.bugLoc[0] - 30;

			}
			
			break;
			
		case ">":
			
			if(bugmanGame.mapLayout[(bugmanGame.bugLoc[1]/30)][(bugmanGame.bugLoc[0]/30) + 1] != 1) {
				
				bugmanGame.bugLoc[0] = bugmanGame.bugLoc[0] + 30;
								
			}
			
			break;
		
		case "A":
			System.out.println("a");
			main.sound.soundEffect(keyboardSound, 0);
			checkAnswer(correctAnswer, aText.getText());
			bugmanGame.t.start();
			questionPanel.removeAll();
			questionPanel.setVisible(false);
			break;
			
		case "B":
			System.out.println("b");
			main.sound.soundEffect(keyboardSound, 0);
			checkAnswer(correctAnswer, bText.getText());
			bugmanGame.t.start();
			questionPanel.removeAll();
			questionPanel.setVisible(false);
			break;
			
		case "C":
			System.out.println("c");
			main.sound.soundEffect(keyboardSound, 0);
			checkAnswer(correctAnswer, cText.getText());
			bugmanGame.t.start();
			questionPanel.removeAll();
			questionPanel.setVisible(false);
			break;
			
		case "D":
			System.out.println("d");
			main.sound.soundEffect(keyboardSound, 0);
			checkAnswer(correctAnswer, dText.getText());
			bugmanGame.t.start();
			questionPanel.removeAll();
			questionPanel.setVisible(false);
			break;
			
		}
		
	}

}