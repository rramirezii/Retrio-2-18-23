package retrio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainMenu implements ActionListener {
	
    //Audio
    Sound sound = new Sound();
	URL creditsBGM = getClass().getClassLoader().getResource("Mario Paint Letter P.wav");
    URL buttonClickSound = getClass().getClassLoader().getResource("Global Button Click.wav");
	URL howToPlayBGM = getClass().getClassLoader().getResource("Mario Paint Letter P.wav");
	
	//Classes
	RetrioMain main;
	GameSelection gameSelec;
	
	//Counter
	int pageNum;
	
	//Graphics
	JPanel titlePanel, titlePanel2, creditPanel, namePanel, instructionsPanel,
		   playPanel, howToPlayPanel, settingsPanel, creditsPanel, exitPanel;
    JLabel titleLabel;
    JButton playButton, howToPlayButton, settingsButton, creditsButton, exitButton, backButton,
    		prevButton, nextButton;
	JTextArea creditText, nameText, instructionsText;

    public MainMenu(RetrioMain main) {
    	
    	this.main = main;
    	gameSelec = new GameSelection(main);
    	mainMenuScreen();
    	
    }
    
	public void mainMenuScreen() {
		
		main.mainFrame.getContentPane().setBackground(new Color(85, 255, 128));

        titlePanel = new JPanel();
        titlePanel.setBounds(120, 20, 700, 200);
        titlePanel.setBackground(new Color(85, 255, 128));
        
        titleLabel = new JLabel("retrio");
        titleLabel.setForeground(Color.black);
        titleLabel.setFont(main.retrioFont.deriveFont(Font.BOLD, 250f));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel);
        
        playPanel = new JPanel();
        playPanel.setBounds(350, 260, 240, 60);
        playPanel.setBackground(new Color(85, 255, 128));
        
        playButton = new JButton("Play");
        playButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 45f));
        playButton.setBackground(new Color(85, 255, 128));
        playButton.setForeground(Color.black);
        playButton.setBorderPainted(false);
        playButton.setActionCommand("Play");
        playButton.addActionListener(this);
        playPanel.add(playButton);
        
        howToPlayPanel = new JPanel();
        howToPlayPanel.setBounds(350, 315, 240, 60);
        howToPlayPanel.setBackground(new Color(85, 255, 128));
        
        howToPlayButton = new JButton("How To Play");
        howToPlayButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 45f));
        howToPlayButton.setBackground(new Color(85, 255, 128));
        howToPlayButton.setForeground(Color.black);
        howToPlayButton.setBorderPainted(false);
        howToPlayButton.setActionCommand("How To Play");
        howToPlayButton.addActionListener(this);
        howToPlayPanel.add(howToPlayButton);
        
        settingsPanel = new JPanel();
        settingsPanel.setBounds(350, 370, 240, 60);
        settingsPanel.setBackground(new Color(85, 255, 128));
        
        settingsButton = new JButton("Settings");
        settingsButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 45f));
        settingsButton.setBackground(new Color(85, 255, 128));
        settingsButton.setForeground(Color.black);
        settingsButton.setBorderPainted(false);
        settingsButton.setActionCommand("Settings");
        settingsButton.addActionListener(this);
        settingsPanel.add(settingsButton);
        
        creditsPanel = new JPanel();
        creditsPanel.setBounds(350, 425, 240, 60);
        creditsPanel.setBackground(new Color(85, 255, 128));
        
        creditsButton = new JButton("Credits");
        creditsButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 45f));
        creditsButton.setBackground(new Color(85, 255, 128));
        creditsButton.setForeground(Color.black);
        creditsButton.setBorderPainted(false);
        creditsButton.setActionCommand("Credits");
        creditsButton.addActionListener(this);
        creditsPanel.add(creditsButton);
        
        exitPanel = new JPanel();
        exitPanel.setBounds(350, 480, 240, 60);
        exitPanel.setBackground(new Color(85, 255, 128));
        
        exitButton = new JButton("Exit");
        exitButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 45f));
        exitButton.setBackground(new Color(85, 255, 128));
        exitButton.setForeground(Color.black);
        exitButton.setBorderPainted(false);
        exitButton.setActionCommand("Exit");
        exitButton.addActionListener(this);
        exitPanel.add(exitButton);

        main.mainFrame.add(titlePanel);
        main.mainFrame.add(playPanel);
        main.mainFrame.add(howToPlayPanel);
        main.mainFrame.add(settingsPanel);
        main.mainFrame.add(creditsPanel);
        main.mainFrame.add(exitPanel);
        main.mainFrame.setVisible(true);
        
	}
	
	//This runs the credits screen
	public void creditsScreen() {
		
		main.sound.backgroundMusic(creditsBGM,-1);
		
		main.createBanner(" Credits ");
	
        namePanel = new JPanel();
        namePanel.setBounds(100, 200, 760, 200);
        namePanel.setBackground(new Color(85, 255, 128));
        
        nameText = new JTextArea();
        nameText.setOpaque(false);
        nameText.setFont(main.retrioFont.deriveFont(Font.BOLD, 40f));
        nameText.setText("Code: Angela Mendoza and Ronnie Ramirez II\n\n     Illustrations: Ronnie Ramirez II"
        		+ "\n\n          Music: Mario Paint (1992)");
        nameText.setEditable(false);
        nameText.setForeground(Color.black);
        namePanel.add(nameText);
        
		backButton = new JButton("Back To Main Menu");
		backButton.setBounds(0, 500, 960, 100);
		backButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 40f));
		backButton.setBackground(new Color(85, 255, 128));
		backButton.setForeground(Color.black);
		backButton.setBorderPainted(false);
		backButton.setActionCommand("Back");
        backButton.addActionListener(this);
		
		main.mainFrame.add(namePanel);
		main.mainFrame.add(backButton);
		main.mainFrame.setVisible(true);
		
	}

	//This runs the how to play screen
	public void howToPlayScreen() {
		
		pageNum = 1;
		
		main.sound.backgroundMusic(howToPlayBGM,-1);
		
		main.createBanner(" How To Play: Retrio ");
	
        instructionsPanel = new JPanel();
        instructionsPanel.setBounds(50, 125, 800, 300);
        instructionsPanel.setBackground(new Color(85, 255, 128));
        
        instructionsText = new JTextArea();
        instructionsText.setOpaque(false);
        instructionsText.setFont(main.retrioFont.deriveFont(Font.PLAIN, 25f));
        instructionsText.setText("Retrio is an interactive game bundle that is fun game-wise as it contains three various games, "
        		+ "“Are You Smarter than Mac?”, “Bugman”, and “Penguins vs. Zombies”, all have quiz-related themes in them but are presented creatively "
        		+ "in a unique way from each other containing ideas, questions, and information within the scope of topics such as Overview of Operating Systems, "
        		+ "Process Management, Memory Management, Storage Management, and Security and Protection which are all found in the course CMSC 125 - Operating Systems. "
        		+ "The game’s interface is inspired by the Windows 1.01 operating system and the name is a portmanteau of the words “Retro”, referring to the games and "
        		+ "style employed, and “Trio”, referring to the number of games included in the bundle.");
        instructionsText.setEditable(false);
        instructionsText.setSize(800, 300);
        instructionsText.setForeground(Color.black);
		instructionsText.setLineWrap(true);
		instructionsText.setWrapStyleWord(true);
        instructionsPanel.add(instructionsText);
        
		prevButton = new JButton("Previous");
		prevButton.setBounds(100, 450, 200, 30);
		prevButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 20f));
		prevButton.setBackground(new Color(85, 255, 128));
		prevButton.setForeground(Color.black);
		prevButton.setBorderPainted(false);
		prevButton.setActionCommand("Prev");
		prevButton.setEnabled(false);
		prevButton.addActionListener(this);
        
		nextButton = new JButton("Next");
		nextButton.setBounds(660, 450, 200, 30);
		nextButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 20f));
		nextButton.setBackground(new Color(85, 255, 128));
		nextButton.setForeground(Color.black);
		nextButton.setBorderPainted(false);
		nextButton.setActionCommand("Next");
		nextButton.addActionListener(this);
        
		backButton = new JButton("Back To Main Menu");
		backButton.setBounds(0, 500, 960, 100);
		backButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 40f));
		backButton.setBackground(new Color(85, 255, 128));
		backButton.setForeground(Color.black);
		backButton.setBorderPainted(false);
		backButton.setActionCommand("Back");
        backButton.addActionListener(this);
		
		main.mainFrame.add(instructionsPanel);
		main.mainFrame.add(prevButton);
		main.mainFrame.add(nextButton);
		main.mainFrame.add(backButton);
		main.mainFrame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		
		String theCommand = action.getActionCommand();
		
		switch(theCommand) {
		
			case "Play":
				main.removeContents();
				main.sound.backgroundMusic(null, 1);
				gameSelec.gameSelectionScreen();
				break;
				
			case "How To Play": 
				main.removeContents();
				main.sound.backgroundMusic(null, 1);
				howToPlayScreen();
				break;
				
			case "Settings":
				main.removeContents();
				main.sound.backgroundMusic(null, 1);
				//main.settings.settingsScreen();
				break;
				
			case "Credits":
				main.removeContents();
				main.sound.backgroundMusic(null, 1);
				creditsScreen();
				break;
				
			case "Exit":
				System.exit(0);
				break;
				
			case "Prev":
				
				pageNum--;

				if(pageNum != 4) {
					
					nextButton.setEnabled(true);
					
					switch(pageNum) {
					
						case 1:
							main.renameBanner(" How To Play: Retrio ");
							prevButton.setEnabled(false);
					        instructionsText.setText("Retrio is an interactive game bundle that is fun game-wise as it contains three various games, "
					        		+ "“Are You Smarter than Mac?”, “Bugman”, and “Penguins vs. Zombies”, all have quiz-related themes in them but are presented creatively "
					        		+ "in a unique way from each other containing ideas, questions, and information within the scope of topics such as Overview of Operating Systems, "
					        		+ "Process Management, Memory Management, Storage Management, and Security and Protection which are all found in the course CMSC 125 - Operating Systems. "
					        		+ "The game’s interface is inspired by the Windows 1.01 operating system and the name is a portmanteau of the words “Retro”, referring to the games and "
					        		+ "style employed, and “Trio”, referring to the number of games included in the bundle.");
							break;
							
						case 2:
							main.renameBanner(" How To Play: Are You Smarter than Mac? ");
					        instructionsText.setText("“Are You Smarter than Mac?” is a quiz game in which the player and his challenger Mac (a non-player character), will "
					        		+ "simultaneously answer multiple-choice questions related to Operating Systems. To win and officially declare themselves “Smarter than Mac”, "
					        		+ "the player must have more correct answers than Mac by the end of the game.");
							break;
							
						case 3:
							main.renameBanner(" How To Play: Bugman ");
							instructionsText.setText("Bugman is an arcade game based on the famous Pacman franchise with a quiz game mechanic blended in. It focuses on Bugman, the mascot of "
									+ "the Android OS, as the main character of the game as he goes around the maze collecting orbs and avoiding ghosts to gain more points resulting in "
									+ "a higher score.");
							break;
					
					}
					
				}
				
				break;
			
			case "Next":
				
				pageNum++;
				
				if(pageNum != 1) {
					
					prevButton.setEnabled(true);
						
						switch(pageNum) {
									
						case 2:
							main.renameBanner(" How To Play: Are You Smarter than Mac? ");
					        instructionsText.setText("“Are You Smarter than Mac?” is a quiz game in which the player and his challenger Mac (a non-player character), will "
					        		+ "simultaneously answer multiple-choice questions related to Operating Systems. To win and officially declare themselves “Smarter than Mac”, "
					        		+ "the player must have more correct answers than Mac by the end of the game.");
							break;
							
						case 3:
							main.renameBanner(" How To Play: BugMan ");
							instructionsText.setText("BugMan is an arcade game based on the famous Pacman franchise with a quiz game mechanic blended in. It focuses on BugMan, the mascot of "
									+ "the Android OS, as the main character of the game as he goes around the maze collecting orbs and avoiding ghosts to gain more points resulting in "
									+ "a higher score. Use the mouse and its left button to click and interact with the game. Arrow keys for controlling BugMan will be shown on-screen and are clickable.");
							break;
							
						case 4:
							main.renameBanner(" How To Play: Penguins Vs Zombies ");
							nextButton.setEnabled(false);
							instructionsText.setText("A variant of the popular video game franchise, Plants vs. Zombie, the Penguins vs Zombies is a tower defense video game with integrated "
									+ "quiz game mechanics. Use the mouse and its left button to click and interact with the game.");
							break;
					
						}
						
					main.mainFrame.setVisible(true);
					
				}
				
				break;
				
			case "Back": 
				main.sound.backgroundMusic(null, 1);
				main.removeContents();
				main.sound.backgroundMusic(main.splashMusicPath, -1);
				mainMenuScreen();
				break;
				
		}
		
		sound.soundEffect(buttonClickSound, 0);
		
	}

}
