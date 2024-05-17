import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class StarField{
	
	int x,y,x2,y2;
	BufferedImage image;
	
	public StarField()
	{
		try 
		{
			image=ImageIO.read(new FileInputStream("Graphis/bg.jpeg"));
		}catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Update()
	{	
		if(y >= 1080) y=0;
		if(x >= 1920) x=0;
		y++;
		x++;;
	}
	
	public void Draw(Graphics2D g2)
	{
		g2.drawImage(image,x,y-1080,1920,1080,null);
		g2.drawImage(image,x-1920,y,1920,1080,null);
		g2.drawImage(image,x,y,1920,1080,null);
		g2.drawImage(image,x-1920,y-1080,1920,1080,null);
	}
	
	
	
}
