import javax.swing.*;

public class Main
{
	public static void main(String[] args){
	
	JFrame window = new JFrame();
	
	
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	window.setTitle("Spase Shooter");
	
	//
	GameScreen gameScreen = new GameScreen();
	
	//
	window.add(gameScreen);
	
	window.pack();
	window.setLocationRelativeTo(null);
	window.setVisible(true);
	
	//
	try{
	gameScreen.StartGameThread();
	}catch(Exception e){
	
	
	
	};
	
	}
}
