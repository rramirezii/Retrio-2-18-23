package retrio;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

public class GameSelection implements ActionListener {
	
	//Audio
    URL gameSelectionBGM = getClass().getClassLoader().getResource("Mario Paint Music Maker Song 3.wav");
    
	//Classes
	RetrioMain main;
	BugMan bugman;
	AreYouSmarterThanMac aystm;
	
	//Graphics
	JPanel aystmPanel, bugmanPanel, pvzPanel, titlePanel, titlePanel2, exitPanel;
	JButton aystmButton, bugmanButton, pvzButton, exitButton;
	JLabel titleLabel;
	
    public GameSelection(RetrioMain main) {
    	
    	this.main = main;
    	bugman = new BugMan(main);
    	aystm = new AreYouSmarterThanMac(main);
    	
    }
    
	public void gameSelectionScreen() {
		
		main.mainFrame.getContentPane().setBackground(Color.white);		
		main.createBanner(" Retrio Game Selection ");
		
		aystmPanel = new JPanel();
		aystmPanel.setBounds(30, 200, 290, 290);
		aystmPanel.setOpaque(false);
		
		aystmButton = new JButton("AYSTM");
		aystmButton.setPreferredSize(new Dimension(280, 280));
		aystmButton.setIcon(new ImageIcon("images/aystm.png"));
		aystmButton.setBorder(BorderFactory.createLineBorder(Color.black,5));
		aystmButton.setActionCommand("AYSTM");
		aystmButton.addActionListener(this);
		
		aystmPanel.add(aystmButton);
		
		bugmanPanel = new JPanel();
		bugmanPanel.setBounds(330, 200, 290, 290);
		bugmanPanel.setOpaque(false);
		
		bugmanButton = new JButton("BUGMAN");
		bugmanButton.setPreferredSize(new Dimension(280, 280));
		bugmanButton.setIcon(new ImageIcon("images/BugMan.gif"));
		bugmanButton.setBorder(BorderFactory.createLineBorder(Color.black,5));
		bugmanButton.setActionCommand("BUGMAN");
		bugmanButton.addActionListener(this);
		
		bugmanPanel.add(bugmanButton);
		
		pvzPanel = new JPanel();
		pvzPanel.setBounds(630, 200, 290, 290);
		pvzPanel.setOpaque(false);
		
		pvzButton = new JButton("PVZ");
		pvzButton.setPreferredSize(new Dimension(280, 280));
		pvzButton.setBorder(BorderFactory.createLineBorder(Color.black,5));
		pvzButton.setActionCommand("PVZ");
		pvzButton.addActionListener(this);
		pvzPanel.add(pvzButton);
		
		//
		exitPanel = new JPanel();
		exitPanel.setBounds(900, 0, 45, 50);
		exitPanel.setOpaque(false);
		
		exitButton = new JButton("X");
		//exitButton.setBounds(900, 0, 45, 50);
        exitButton.setFont(main.retrioFont.deriveFont(Font.BOLD, 30f));
        exitButton.setBackground(Color.white);
        exitButton.setForeground(Color.black);
        exitButton.setBorderPainted(true);
        exitButton.setActionCommand("Exit");
        exitButton.addActionListener(this);
		exitButton.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		
		exitPanel.add(exitButton);//
		
		main.sound.backgroundMusic(gameSelectionBGM, -1);
		
		main.mainFrame.add(aystmPanel);
		main.mainFrame.add(bugmanPanel);
		main.mainFrame.add(pvzPanel);
		main.mainFrame.add(exitPanel); //
		main.mainFrame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent action) {

		String theCommand = action.getActionCommand();
		main.removeContents();
		
		switch(theCommand) {
		
			case "AYSTM":
				main.sound.backgroundMusic(null, 1);
				main.mainFrame.getContentPane().setBackground(Color.white);
				aystm.areYouSmarterThanMacGraphics();
				aystm.gamePlay(main.questionReader.getQuestionSize());
				break;
				
			case "BUGMAN":
				main.sound.backgroundMusic(null, 1);
				main.mainFrame.getContentPane().setBackground(new Color(255, 85, 85));
				bugman.gamePlay();
				break;
				
			case "PVZ":
				main.sound.backgroundMusic(null, 1);
				main.mainFrame.getContentPane().setBackground(new Color(85, 169, 255));
				//main.penguinsVsZombies.gamePlay();
				break;
				
			case "Exit":
				main.sound.backgroundMusic(null, 1);
				main.mainMenu.mainMenuScreen();
				
		}
		
	}

}
