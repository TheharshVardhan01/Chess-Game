package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{

	public int x,y;
	public boolean pressed;
	
	@Override
	public void mousePressed(MouseEvent e) {
		pressed = true;  //when true then is pressed
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}
	@Override
	public void mouseDragged(MouseEvent e) {    //track player's mouse movement
		x = e.getX();   //gives current position of mouse
		y = e.getY();
	}
	@Override
	public void mouseMoved(MouseEvent e) {      //track player's mouse movement
		x = e.getX();
		y = e.getY();
	}
}
