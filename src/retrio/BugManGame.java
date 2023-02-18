package retrio;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class BugManGame extends JPanel implements ActionListener {

	RetrioMain retrioMain;
	
	//Game
	boolean purpleIn = false; //This tells us if a power-up orb is on screen
	boolean purpleQ = false;
	boolean purpleCorrect = false;
	int blockNum = 15; //This tells us how many blocks there are in each row and column
	int gameSpeed = 5;
	int ghostNum = 0;
	int score = 0;
	int seconds;
	int spriteSize = 30; //This tells us the size of each block/sprite
	int[] bugLoc = new int[2]; //This stores the current location of Bugman
	int[][] ghostLoc = new int[20][2]; //This stores the location of the ghost in the maze
	int[][] yellowLoc = new int[4][2]; //This stores the location of the yellow orbs in the maze
	int[] purpleLoc = new int[2]; //This stores the location of the purple orb in the maze
	int yellowNum; //This tells us how many normal orbs are on screen
	int[][] mapLayout = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
			{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
			{1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1},
			{1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
			{1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
			{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
			{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
			};
	
	Random random = new Random();
	Timer t = new Timer(100, this);
	
	//Graphics
	Image bugFront = new ImageIcon("images/bugFront.gif").getImage();
	Image ghost = new ImageIcon("images/ghost.gif").getImage();
	Image mazeBlock = new ImageIcon("images/mazeBlock.png").getImage();
	Image mazeHall = new ImageIcon("images/mazeHall.png").getImage();
	Image purpleOrb = new ImageIcon("images/purpleOrb.gif").getImage();
	Image yellowOrb = new ImageIcon("images/yellowOrb.gif").getImage();
	
	//Audio
	URL gameOverMusicPath = getClass().getClassLoader().getResource("Stage Clear.wav");
	
	public BugManGame(RetrioMain retrioMain) {
		
		this.retrioMain = retrioMain;
		startBugMan();
		
	}
	
	//This runs the Bugman game logic
	public void startBugMan() {
		
		setPreferredSize(new Dimension(450, 450));
		t.start(); //This starts the timer
		bugLoc[0] = bugLoc[1] = 30; //This initiates Bugman's starting x and y location
		yellowRand(); //This sets the location of the yellow orbs
		addGhost();
		
	}
	
	public void yellowRand() {
		
		yellowNum = 4;
		
		int i, j, k;
		
		for(int x = 0; x < 4; x++) { //This goes through each of the four orbs
			
			boolean possibleLoc = false; //This tells us if the location can be placed with an orb
			boolean noSameLoc = true; //This tells us if the location is already used
			
			while(!possibleLoc) { //This loops until a possible location is found
				
				i = random.nextInt(15);
				j = random.nextInt(15);
				k = 0;
				
				if(x > 0) { //This avoids having two orbs with the same location
					
					noSameLoc = true;
					
					while(k < x) { //This loops until a new location is found
						
						if(yellowLoc[k][0] == yellowLoc[x][0]) {

							if(yellowLoc[k][1] == yellowLoc[x][1]) {
								
								noSameLoc = false;
								
							}
							
						}
						
						k++;
						
					}
					
				}
				
				if(noSameLoc) {
					
					if(mapLayout[j][i] == 0){ //This runs if the location is a hall
						
						yellowLoc[x][0] = i;
						yellowLoc[x][1] = j;
						possibleLoc = true;
						
					}
					
				}
				
			}
			
		}
				
	}
	
	public void purpleRand() {
		
		if(!purpleIn) {
			
			int i, j, k;
			
			boolean possibleLoc = false; //This tells us if the location can be placed with an orb
			boolean noSameLoc = true; //This tells us if the location is already used
			
			while(!possibleLoc) { //This loops until a possible location is found
				
				i = random.nextInt(15);
				j = random.nextInt(15);
				k = 0;
				
				while(k < 4) { //This loops until a new location is found
					
					noSameLoc = true;
					
					if(yellowLoc[k][0] == purpleLoc[0]) {

						if(yellowLoc[k][1] == purpleLoc[1]) {
							
							noSameLoc = false;
							
						}
						
					}
					
					k++;
					
				}
				
				if(noSameLoc) {
					
					if(mapLayout[j][i] == 0){ //This runs if the location is a hall
						
						purpleLoc[0] = i;
						purpleLoc[1] = j;
						possibleLoc = true;
						
					}
					
				}
				
			}
			
			purpleIn = true;
			
		}
		
	}
	
	public void checkGet() {
		
		int x; //This tells us the number of the orbs
		
		for(x = 0; x < 4; x++) {
			
			//Case for yellow orbs
			if(bugLoc[0] == (yellowLoc[x][0] * 30)) {
				
				if(bugLoc[1] == (yellowLoc[x][1] * 30)) {

					retrioMain.mainMenu.gameSelec.bugman.showQuestion();
					t.stop();
					
					yellowNum--;
					yellowLoc[x][0] = 1500;
					yellowLoc[x][1] = 1500;
					
				}
				
			}
			
		}
		
		if(purpleIn) {
			
			//Case for purple orb
			if(bugLoc[0] == (purpleLoc[0] * 30)) {
				
				if(bugLoc[1] == (purpleLoc[1] * 30)) {
					
					purpleQ = true;
					retrioMain.mainMenu.gameSelec.bugman.showQuestion();
					t.stop();
					
					purpleIn = false;
					purpleLoc[0] = purpleLoc[1] = 1000;
					
				}
				
			}
			
		}
		
		for(x = 0; x < ghostNum; x++) {
			
			//Case for ghosts
			if(bugLoc[0] == (ghostLoc[x][0] * 30)) {
				
				if(bugLoc[1] == (ghostLoc[x][1] * 30)) {
					
					t.stop();
					gameOverScreen();
					
				}
				
			}
			
		}
		
	}
	
	public void gameOverScreen() {
		
		retrioMain.sound.backgroundMusic(null, 1);
		retrioMain.sound.backgroundMusic(gameOverMusicPath, 0);
		JOptionPane.showMessageDialog(null, "You Are NOT Smarter than Mac, Your Score is " + score, "Game Over", JOptionPane.ERROR_MESSAGE);
		
		retrioMain.mainMenu.gameSelec.bugman.gamePanel.removeAll();
		retrioMain.mainMenu.gameSelec.bugman.questionPanel.removeAll();
		retrioMain.removeContents();
		retrioMain.mainMenu.gameSelec.gameSelectionScreen();
		
	}
	
	public void addGhost() {
		
		ghostLoc[ghostNum][0] = 13;
		ghostLoc[ghostNum][1] = 13;
		ghostNum++;
		
	}
	
	public void moveGhost() {
		
		int i, j;
		
		for(int x = 0; x < ghostNum; x++) { //This goes through each of the ghosts
			
			boolean possibleLoc = false; //This tells us if the location can be placed with an orb
			
			while(!possibleLoc) { //This loops until a possible location is found
				
				i = ghostLoc[x][0] + ghostMoveWhere();
				j = ghostLoc[x][1] + ghostMoveWhere();
				
				if(mapLayout[j][i] == 0){ //This runs if the location is a hall
					
					ghostLoc[x][0] = i;
					ghostLoc[x][1] = j;
					possibleLoc = true;
					
				}
				
			}
			
		}
		
	}
	
	public int ghostMoveWhere() {
		
		int loc;
		
		loc = random.nextInt(3);
		
		if(loc == 2) {
			
			loc = -1;
			
		}
		
		return loc;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		for(int j = 0; j < 15; j++) { //Goes through each row
			
			for(int i = 0; i < 15; i++) {
				
				if(mapLayout[j][i] == 1) {
					
					g.drawImage(mazeBlock, i * 30, j * 30, null);
					
				}

				if(mapLayout[j][i] == 0){
					
					g.drawImage(mazeHall, i * 30, j * 30, null);
					
				}
				
			}
			
		}
		
		int x = 0;
		
		while(x < 4) {
			
			g.drawImage(yellowOrb, yellowLoc[x][0] * 30, yellowLoc[x][1] * 30, null);
			x++;
			
		}
		
		if(purpleIn) {
			
			g.drawImage(purpleOrb, purpleLoc[0] * 30, purpleLoc[1] * 30, null);
		}
		
		g.drawImage(bugFront, bugLoc[0], bugLoc[1], null);
		
		x = 0;
		
		while(x < ghostNum) {
			
			g.drawImage(ghost, ghostLoc[x][0] * 30, ghostLoc[x][1] * 30, null);
			x++;
			
		}
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//String theCommand = e.getActionCommand();

		seconds++;
		
		if((seconds % 2) == 0) {
			
			moveGhost();
			
		}
		
		if(seconds == 100) {
			
			seconds = 0;
			
			purpleRand();
			
		}
		
		checkGet();
		
		if(yellowNum == 0) {
			
			yellowRand();
			
		}
		
		retrioMain.mainFrame.validate();
		validate();
		retrioMain.mainFrame.repaint();
		repaint();
		
	}

}