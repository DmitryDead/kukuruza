import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Bonus 
{


	public Rectangle boundingBox;
	public BufferedImage sprite;
	public int posX, posY;
	public boolean isAlive;
	public int speed;
	private int randPosX;
	public int bonusName;
	
	Random random = new Random();
	
	public Bonus()
	{
		try{
			switch (bonusName){
				case (1):
					sprite=ImageIO.read(new FileInputStream("Graphis/spped.jpeg"));
					break;
				case (2):
					sprite=ImageIO.read(new FileInputStream("Graphis/spped.jpeg"));
					break;
				case (3):
					sprite=ImageIO.read(new FileInputStream("Graphis/spped.jpeg"));
					break;
				default:
					sprite=ImageIO.read(new FileInputStream("Graphis/spped.jpeg"));
					break;	
			}	
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		speed = 10;
		isAlive = true;
		//int gameWidth = 280;
		//int gameHeight = 400;
		randPosX = random.nextInt(1920);
		posY = -10;
		boundingBox = new Rectangle(randPosX, posY, 16, 16);
	}
	
	public void Update()
	{
		posY = posY + speed;
		
		boundingBox = new Rectangle(randPosX, posY, 16, 16);
		
		if(posY > 1080)
		{
			posY = -30;
		}
	}
	
	public void Draw(Graphics2D g)
	{
		
		g.drawImage(sprite, randPosX,posY, 16, 16, null);
		
	}
	
}
