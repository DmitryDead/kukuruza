import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.util.Random;
public class Kreep
{
	int x,y;
	Random random = new Random();
	int velocity;
	int health;
	int bulletSpeed;
	int heath;
	float bulletDelay;
	public Rectangle boundingBox;
	int plyerSide;
	public boolean isAlive=false;
	int turn;
	BufferedImage sprite=null;
	BufferedImage bulletSprite=null;
	
	public List<Bullet> bulletList;
	
	public Kreep(int side)
	{
		x=50;
		plyerSide=side;
		bulletSpeed=10;
		y=300;
		velocity=4;
		health=150;
		heath=0;
		bulletDelay=8;
		isAlive=true;
		turn=0;
		try{
			switch (side){
				case (1):
					sprite=ImageIO.read(new FileInputStream("Graphis/k1.png"));
					bulletSprite=ImageIO.read(new FileInputStream("Graphis/bullet.png"));
					break;
				case (2):
					sprite=ImageIO.read(new FileInputStream("Graphis/k2.png"));
					bulletSprite=ImageIO.read(new FileInputStream("Graphis/bullet2.png"));
					x=1920-200;
					break;
				default:
					isAlive=false;
					break;}
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bulletList= new ArrayList<Bullet>();
		boundingBox = new Rectangle(x, y, 100, 70);
	}
	public void Update()
	{
		if(isAlive==true){
		int ch1 =random.nextInt(5);
		
		if(ch1==5)y-=velocity;
		if(y<0)y=1080;
		if(ch1==4)y+=velocity;
		if(y>1080)y=1;
		if(ch1==3)x-=velocity;
		if(x<0)x=1920;
		if(ch1==1)x+=velocity;
		if(x>1920)x=1;
		
		
		if(ch1==2)shoot();else bulletDelay=1;
		updateBullets();
		boundingBox.x=x;
		boundingBox.y=y;}else{}
	}
	
	public void Draw(Graphics2D g2)
	{
		
		g2.drawImage(sprite,x,y,100,70,null);
	

		for(Bullet b : bulletList)
		{
			b.Draw(g2);
		}
	}
	
	public void shoot()
	{
		if(bulletDelay>0)
		{bulletDelay--;}
		else{
			Bullet newBullet=new Bullet(bulletSprite,plyerSide);
			newBullet.speed=bulletSpeed;
			newBullet.posX=x+16;
			newBullet.posY=y+12;
			
			if(bulletList.size()<20)
			{
				bulletList.add(newBullet);
			}
			bulletDelay=20;	
		}
		
	}
	
	public void updateBullets()
	{
		try
		{
			for(Bullet b : bulletList)
			{

				switch (plyerSide){
				case (1):
					b.posX+=b.speed;
		
					break;
				case (2):
					b.posX-=b.speed;
		
					break;
				default:
					break;}
				b.boundingBox=new Rectangle(b.posX,b.posY,30,30);
				
				if(b.posY<=0) b.isAlive=false;
				if(b.posY>=1080) b.isAlive=false;
				if(b.posX<=0) b.isAlive=false;
				if(b.posX>=1920) b.isAlive=false;
			}
			for(int i = 0; i<bulletList.size();i++){
			 if(bulletList.get(i).isAlive==false)
			 {
			  bulletList.remove(i);
			  i--;
			 }
			}	
		}
		catch(Exception e)
		{
		
		}
	}
	
	
	
	
	
	
}
