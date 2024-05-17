import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Asteroid 
{


	public Rectangle boundingBox;
	public BufferedImage sprite;
	public int posX, posY;
	public boolean isAlive;
	public int speed;
	public int randPosX;
	
	Random random = new Random();
	
	public Asteroid()
	{
		try{
			sprite=ImageIO.read(new FileInputStream("Graphis/met.png"));
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		speed = 2;
		isAlive = true;
		//int gameWidth = 280;
		//int gameHeight = 400;
		randPosX = random.nextInt(1920);
		posY = -30;
		boundingBox = new Rectangle(randPosX, posY, 40, 40);
	}
	
	public void Update()
	{
		posY = posY + speed;
		
		boundingBox = new Rectangle(randPosX, posY, 40, 40);
		
		if(posY > 1080)
		{
			posY = -30;
		}
	}
	
	public void Draw(Graphics2D g)
	{
		
		g.drawImage(sprite, randPosX,posY, 40, 40, null);
		
	}
	
}
