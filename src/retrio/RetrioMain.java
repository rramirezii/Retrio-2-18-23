package retrio;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import javax.swing.*;

public class RetrioMain implements KeyListener {
	
	//Classes
	MainMenu mainMenu;
	QuestionReader questionReader = new QuestionReader();
    Sound sound = new Sound();
	
	//Graphics
	JFrame mainFrame;
	JLabel titleLabel, anyKeyLabel;
	JPanel titlePanel, versionPanel, anyKeyPanel, copyrightPanel, bannerPanel, bannerPanel2;
    JTextArea versionTextArea, copyrightTextArea;
    Font retrioFont;
    
    //Audio
    URL splashMusicPath = getClass().getClassLoader().getResource("Mario Paint Main Theme.wav");
    URL keyboardSound = getClass().getClassLoader().getResource("Keyboard Press.wav");

	
	public RetrioMain() {
		
		mainFrame();
		createFont();
		splashScreen();
		mainFrame.revalidate();
		questionReader.questionReader();
		
	}
	
	//This creates the main frame for the program
	public void mainFrame() {
		
		mainFrame = new JFrame("Retrio"); //Main Frame
		mainFrame.setSize(new Dimension(960, 680));
		mainFrame.setLayout(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.addKeyListener(this);
		
	}
	
	//This creates a custom font for the program
	public void createFont() {
		
		try {
			this.retrioFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/RetrioFont.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(retrioFont);
		}
		catch(IOException | FontFormatException e){
			 e.printStackTrace();
		}
		
	}
	
	//This runs the splash screen
	public void splashScreen() {
		
		mainFrame.getContentPane().setBackground(new Color(0, 0, 134));
		
        titlePanel = new JPanel();
        titlePanel.setBounds(120, 20, 700, 200);
        titlePanel.setBackground(new Color(0, 0, 134));
        
        titleLabel = new JLabel("retrio");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(retrioFont.deriveFont(Font.BOLD, 250f));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel);
        
        versionPanel = new JPanel();
        versionPanel.setBounds(400, 260, 150, 100);
        versionPanel.setBackground(new Color(0, 0, 134));
        
        versionTextArea = new JTextArea(2, 12);
        versionTextArea.setForeground(Color.white);
        versionTextArea.setBackground(new Color(0, 0, 134));
        versionTextArea.setFont(retrioFont.deriveFont(30f));
        versionTextArea.setText("Retrio Game\nVersion 1.01");
        versionTextArea.setEditable(false);
        versionTextArea.setLayout(new GridBagLayout());
        versionPanel.add(versionTextArea);
        
        anyKeyPanel = new JPanel();
        anyKeyPanel.setBounds(130, 380, 690, 180);
        anyKeyPanel.setBackground(new Color(0, 0, 134));
        
        anyKeyLabel = new JLabel("Press any key to continue...");
        anyKeyLabel.setForeground(Color.white);
        anyKeyLabel.setFont(retrioFont.deriveFont(Font.BOLD, 45f));
        anyKeyLabel.setHorizontalAlignment(JLabel.CENTER);
        anyKeyPanel.add(anyKeyLabel);
        
        copyrightPanel = new JPanel();
        copyrightPanel.setBounds(110, 480, 730, 200);
        copyrightPanel.setBackground(new Color(0, 0, 134));
        
        copyrightTextArea = new JTextArea(2, 60);
        copyrightTextArea.setForeground(Color.white);
        copyrightTextArea.setBackground(new Color(0, 0, 134));
        copyrightTextArea.setFont(retrioFont.deriveFont(30f));
        copyrightTextArea.setText("Copyright (c) Retrio Corporation, 2022. All Rights Reserved."
        		                  + "\n       Retrio is a registered trademark of Retrio Corp.");
        copyrightTextArea.setEditable(false);
        copyrightTextArea.setLayout(new GridBagLayout());
        copyrightPanel.add(copyrightTextArea);
        
        sound.backgroundMusic(splashMusicPath, -1);
        
        mainFrame.add(titlePanel);
        mainFrame.add(versionPanel);
        mainFrame.add(anyKeyPanel);
        mainFrame.add(copyrightPanel);
		
	}
	
	//This removes all contents from the frame
	public void removeContents() {
		
		mainFrame.getContentPane().removeAll();
		mainFrame.revalidate();
		mainFrame.repaint();
		
	}
	
	public void createBanner(String title) {
		
		bannerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		bannerPanel.setBounds(0, 0, 945, 50);
		bannerPanel.setBackground(new Color(0, 0, 134));
		bannerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		
		bannerPanel2 = new JPanel();
		bannerPanel2.setBounds(0, 45, 945, 50);
		bannerPanel2.setBackground(new Color(255, 255, 85));
		bannerPanel2.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		
		titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.white);
        titleLabel.setBackground(Color.black);
        titleLabel.setOpaque(true);
        titleLabel.setFont(retrioFont.deriveFont(Font.BOLD, 40f));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        bannerPanel.add(titleLabel);
        
        mainFrame.add(bannerPanel);
		mainFrame.add(bannerPanel2);
		mainFrame.setVisible(true);
        
	}
	
	public void renameBanner(String title) {
		
		titleLabel.setText(title);
		bannerPanel.add(titleLabel);
		mainFrame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		new RetrioMain();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		sound.soundEffect(keyboardSound, 0);
		mainFrame.removeKeyListener(this);
		
		removeContents();
		
		mainMenu = new MainMenu(this);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
