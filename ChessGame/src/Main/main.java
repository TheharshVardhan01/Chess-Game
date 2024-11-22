package Main;

import javax.swing.JFrame;

public class main {
	public static void main(String[] args)
	{
		JFrame window  = new JFrame("Simple Chess");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		GamePanel gp = new GamePanel();
		window.add(gp); // Adding it to window
		window.pack(); // Than packing it and adjusted the size to the game Panel
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gp.launchGame(); // Once the window will get created the thread will start
	}
}
