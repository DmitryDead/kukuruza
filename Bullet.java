import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet
{
	
	public Rectangle boundingBox;
	public BufferedImage sprite;
	public int posX,posY,side;
	public boolean isAlive;
	public int speed;
	public int speedTurn;
	
	public Bullet(BufferedImage newSprite,int s){
	
		side=s;
		speed=10;
		sprite=newSprite;
		isAlive=true;
		
	}
	
	public void Draw(Graphics2D g2)
	{
		g2.drawImage(sprite,posX,posY,30,50,null);
	}


}
