import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBM implements KeyListener
{
	
	public boolean keyLeft;
	public boolean keyRight;
	public boolean keyUp;
	public boolean keyDown;
	public boolean keySpace;
	public boolean keyLeft2;
	public boolean keyRight2;
	public boolean keyUp2;
	public boolean keyDown2;
	public boolean keySpace2;
	public boolean turnRight;
	public boolean turnLeft;
	public boolean turnRight2;
	public boolean turnLeft2;
	
	public KeyBM(){};
	
	@Override
	public void keyTyped(KeyEvent e){
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int KeyCode = e.getKeyCode();
		
		if(KeyCode==KeyEvent.VK_A){keyLeft=true;}
		if(KeyCode==KeyEvent.VK_D){keyRight=true;}
		if(KeyCode==KeyEvent.VK_W){keyUp=true;}
		if(KeyCode==KeyEvent.VK_S){keyDown=true;}
		if(KeyCode==KeyEvent.VK_SPACE){keySpace=true;}
		if(KeyCode==KeyEvent.VK_NUMPAD4){keyLeft2=true;}
		if(KeyCode==KeyEvent.VK_NUMPAD6){keyRight2=true;}
		if(KeyCode==KeyEvent.VK_NUMPAD8){keyUp2=true;}
		if(KeyCode==KeyEvent.VK_NUMPAD5){keyDown2=true;}
		if(KeyCode==KeyEvent.VK_NUMPAD0){keySpace2=true;}
		if(KeyCode==KeyEvent.VK_E){turnRight=true;}
		if(KeyCode==KeyEvent.VK_Q){turnLeft=true;}
		if(KeyCode==KeyEvent.VK_NUMPAD9){turnRight2=true;}
		if(KeyCode==KeyEvent.VK_NUMPAD7){turnLeft2=true;}
	}
	
	@Override
	public void  keyReleased(KeyEvent e)
	{
		int KeyCode = e.getKeyCode();
	
		if(KeyCode==KeyEvent.VK_A){keyLeft=false;}
		if(KeyCode==KeyEvent.VK_D){keyRight=false;}
		if(KeyCode==KeyEvent.VK_W){keyUp=false;}
		if(KeyCode==KeyEvent.VK_S){keyDown=false;}
		if(KeyCode==KeyEvent.VK_SPACE){keySpace=false;}
		if(KeyCode==KeyEvent.VK_NUMPAD4){keyLeft2=false;}
		if(KeyCode==KeyEvent.VK_NUMPAD6){keyRight2=false;}
		if(KeyCode==KeyEvent.VK_NUMPAD8){keyUp2=false;}
		if(KeyCode==KeyEvent.VK_NUMPAD5){keyDown2=false;}
		if(KeyCode==KeyEvent.VK_NUMPAD0){keySpace2=false;}
		if(KeyCode==KeyEvent.VK_E){turnRight=false;}
		if(KeyCode==KeyEvent.VK_Q){turnLeft=false;}
		if(KeyCode==KeyEvent.VK_NUMPAD9){turnRight2=false;}
		if(KeyCode==KeyEvent.VK_NUMPAD7){turnLeft2=false;}
	}
}
