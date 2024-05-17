import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Toolkit;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.Random;



public class GameScreen extends JPanel implements Runnable
{
	int GameW=1920;
	int GameH=1080;
	
	int asteroidRate = 15;
	int BonusRate = 15;
	
	Thread gameThread;
	
	KeyBM keyM = new KeyBM();
	Player p = new Player(1);
	Player p2 = new Player(2);
	Asteroid A = new Asteroid();
	StarField star = new StarField();
	Random random = new Random();
	
	Kreep k2=new Kreep(2);
	Kreep k1=new Kreep(1);
		
	public List<Asteroid> AsteroidList =new ArrayList<Asteroid>();
	public List<Bonus> BonusList =new ArrayList<Bonus>();
	
	public GameScreen(){
		this.setPreferredSize(new Dimension(GameW,GameH));
		this.setBackground(Color.CYAN);
		this.setForeground(Color.WHITE);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyM);
		this.setFocusable(true);
		
	}
	public void StartGameThread(){
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void StopGameThread(){
		gameThread=null;
	}
	@Override
	public void run(){
		double drawInterwal = 1000000000/60;
		double nextDrawTime=System.nanoTime()+drawInterwal;
		while(gameThread != null)
		{
			Update();
			repaint();
			Toolkit.getDefaultToolkit().sync();
			try{
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				if(remainingTime<0)
				{
				 remainingTime=0;
				}
				gameThread.sleep((long)remainingTime);
				nextDrawTime+=drawInterwal;
			} catch (InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
		}
	}
	
	public void Update(){
	
		try{
	
		star.Update();
		p.Update(keyM.keyUp,keyM.keyDown,keyM.keyLeft,keyM.keyRight,keyM.keySpace,keyM.turnLeft,keyM.turnRight);
		p2.Update(keyM.keyUp2,keyM.keyDown2,keyM.keyLeft2,keyM.keyRight2,keyM.keySpace2,keyM.turnLeft2,keyM.turnRight2);

		k2.Update();
		k1.Update();
		
		asteroidRate--;
		BonusRate--;
		
		if(asteroidRate < 0)
		{
			int ch1 =random.nextInt(100);
			if(ch1<10){
			asteroidRate = 15;
			AsteroidSp();}
		}
		
		for(int i = 0; i<p2.bulletList.size();i++){
		if(p.boundingBox.intersects(p2.bulletList.get(i).boundingBox)){
				
			p2.bulletList.get(i).isAlive=false;
			p.health-=10;
					if(p.health<0){
						p.isAlive=false;
						System.out.println("PLYER 2 WIN");
						StopGameThread();
					}
		}}
		
		for(int i = 0; i<p.bulletList.size();i++){
		if(p2.boundingBox.intersects(p.bulletList.get(i).boundingBox)){
				
			p.bulletList.get(i).isAlive=false;
			p2.health-=10;
					if(p2.health<0){
						p2.isAlive=false;
						System.out.println("PLYER 1 WIN");
						StopGameThread();
					}
		}}
		
		
		for(Asteroid A : AsteroidList)
		{
			for(int i = 0; i<p.bulletList.size();i++){
			
				if(A.boundingBox.intersects(p.bulletList.get(i).boundingBox)){
				
					A.isAlive=false;
					p.bulletList.get(i).isAlive=false;
				
				}
			
			}
			if(A.boundingBox.intersects(p.boundingBox)){
				
					A.isAlive=false;
					p.health-=10;
					if(p.health<0){
						p.isAlive=false;
						System.out.println("PLYER 2 WIN");
						StopGameThread();
					}
				
				}
		}
		
		if(BonusRate < 0)
		{
			int ch =random.nextInt(100);
			if(ch<50){
			int ch1 =random.nextInt(100);
			if(ch1==1){
			BonusRate = 50;	
			BonusSp();}}
		}
		
		for(Bonus A : BonusList)
		{	
			
			for(int i = 0; i<p.bulletList.size();i++){
			
				if(A.boundingBox.intersects(p.bulletList.get(i).boundingBox)){
				
					A.isAlive=false;
					p.bulletList.get(i).isAlive=false;
				
				}
			
			}
			if(A.boundingBox.intersects(p.boundingBox)){
				
					A.isAlive=false;
				 	p.bulletSpeed+=5;
				 	if(p.health<500)
				 	p.health+=100;
				 	if(p.turn<3)p.turn++;
				}
		}
		
		
		
		
		for(Asteroid A : AsteroidList)
		{
			for(int i = 0; i<p2.bulletList.size();i++){
			
				if(A.boundingBox.intersects(p2.bulletList.get(i).boundingBox)){
				
					A.isAlive=false;
					p2.bulletList.get(i).isAlive=false;
				
				}
			
			}
			if(A.boundingBox.intersects(p2.boundingBox)){
				
					A.isAlive=false;
					p2.health-=10;
					if(p2.health<0){
						p2.isAlive=false;
						System.out.println("PLYER 1 WIN");
						StopGameThread();
					}
				
				}
		}
		
		for(Bonus A : BonusList)
		{	
			
			for(int i = 0; i<p2.bulletList.size();i++){
			
				if(A.boundingBox.intersects(p2.bulletList.get(i).boundingBox)){
				
					A.isAlive=false;
					p2.bulletList.get(i).isAlive=false;
				
				}
			
			}
			if(A.boundingBox.intersects(p2.boundingBox)){
				
					A.isAlive=false;
				 	p2.bulletSpeed+=5;
				 	if(p2.health<500)
				 	p2.health+=100;
				 	if(p2.turn<2)p2.turn++;
				}
		}
		
		
		
		
		for(Asteroid A : AsteroidList)
		{if(A.boundingBox.intersects(k1.boundingBox)){
				
					A.isAlive=false;
					k1.health-=10;
					if(k1.health<0){
						k1.isAlive=false;
					}
				
				}
			for(int i = 0; i<k1.bulletList.size();i++){
			
				if(A.boundingBox.intersects(k1.bulletList.get(i).boundingBox)){
				
					A.isAlive=false;
					k1.bulletList.get(i).isAlive=false;
				
				}
			
			}
			
		}
		
		for(Asteroid A : AsteroidList)
		{
			
			if(A.boundingBox.intersects(k2.boundingBox)){
				
					A.isAlive=false;
					k2.health-=10;
					if(k2.health<0){
						k2.isAlive=false;
					}
				
				}
				for(int i = 0; i<k2.bulletList.size();i++){
				if(A.boundingBox.intersects(k2.bulletList.get(i).boundingBox)){
				
					A.isAlive=false;
					k2.bulletList.get(i).isAlive=false;
				
				}
			
			}
		}
		
		
		for(int i = 0; i<p2.bulletList.size();i++){
		if(k1.boundingBox.intersects(p2.bulletList.get(i).boundingBox)){
				
			p2.bulletList.get(i).isAlive=false;
			k1.health-=10;
					if(k1.health<0){
						k1.isAlive=false;;
					}
		}}
		
		for(int i = 0; i<p.bulletList.size();i++){
		if(k2.boundingBox.intersects(p.bulletList.get(i).boundingBox)){
				
			p.bulletList.get(i).isAlive=false;
			k2.health-=10;
					if(k2.health<0){
						k2.isAlive=false;
					}
		}}
		
		
		
		for(int i = 0; i<k2.bulletList.size();i++){
		if(k1.boundingBox.intersects(k2.bulletList.get(i).boundingBox)){
				
			k2.bulletList.get(i).isAlive=false;
			k1.health-=10;
					if(k1.health<0){
						k1.isAlive=false;;
					}
		}}
		
		for(int i = 0; i<k1.bulletList.size();i++){
		if(k2.boundingBox.intersects(k1.bulletList.get(i).boundingBox)){
				
			p.bulletList.get(i).isAlive=false;
			k2.health-=10;
					if(k2.health<0){
						k2.isAlive=false;
					}
		}}
		
		for(int i = 0; i<k2.bulletList.size();i++){
		if(p.boundingBox.intersects(k2.bulletList.get(i).boundingBox)){
				
			k2.bulletList.get(i).isAlive=false;
			p.health-=10;
					if(p.health<0){
						p.isAlive=false;
						System.out.println("PLYER 2 WIN");
						StopGameThread();
					}
		}}
		
		for(int i = 0; i<k1.bulletList.size();i++){
		if(p2.boundingBox.intersects(k1.bulletList.get(i).boundingBox)){
				
			k1.bulletList.get(i).isAlive=false;
			p2.health-=10;
					if(p2.health<0){
						p2.isAlive=false;
						System.out.println("PLYER 1 WIN");
						StopGameThread();
					}
		}}
		}catch(Exception e){e.printStackTrace();}
		

	}

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);

		g.setFont(new Font("Arial",Font.PLAIN,30));
		g.setColor(Color.red);	
		Graphics2D g2 = (Graphics2D)g;
		star.Draw(g2);
		p.Draw(g2);
		p2.Draw(g2);
		if(k2.isAlive)
		 k2.Draw(g2);
		if(k1.isAlive)
		 k1.Draw(g2);
		 
		updateAsteroid();
		updateBonus();
		for(Asteroid A : AsteroidList)
		{
			A.Draw(g2);
		}
		for(Bonus A : BonusList)
		{
			A.Draw(g2);
		}
		g.drawString("plyer 1 health:"+p.health,100,100);
		g.drawString("plyer 2 health:"+p2.health,1600,100);
		if(!p.isAlive){g.setFont(new Font("Arial",Font.PLAIN,60)); g.drawString("plyer 2 win",600,600);}
		if(!p2.isAlive){g.setFont(new Font("Arial",Font.PLAIN,60)); g.drawString("plyer 1 win",600,600);}
	}
	
	public void BonusSp()
	{
		
			Bonus newBonus=new Bonus();		
			if(AsteroidList.size()<20)
			{
				
				BonusList.add(newBonus);
			}
			
		
	}
	
	
	
	public void AsteroidSp()
	{
		
			Asteroid newAsteroid=new Asteroid();		
			if(AsteroidList.size()<25)
			{
			
				int ch =random.nextInt(1000);
				int ch1 =random.nextInt(1920);
				if(ch<50)newAsteroid.speed=20;
				newAsteroid.randPosX=ch1;
				AsteroidList.add(newAsteroid);
				
			}
			
		
	}
	
	public void updateAsteroid()
	{
		try
		{
			for(Asteroid A: AsteroidList)
			{
				A.Update();
				if(A.posY>=1080) A.isAlive=false;
			}
			for(int i = 0; i<AsteroidList.size();i++){
			 if(AsteroidList.get(i).isAlive==false)
			 {
			 AsteroidList.remove(i);
			  i--;
			 }
			}	
		}
		catch(Exception e)
		{
		
		}
	}
	public void updateBonus()
	{
		try
		{
			for(Bonus A: BonusList)
			{
				A.Update();
				if(A.posY>=1080) A.isAlive=false;
			}
			for(int i = 0; i<BonusList.size();i++){
			 if(BonusList.get(i).isAlive==false)
			 {
			 BonusList.remove(i);
			  i--;
			 }
			}	
		}
		catch(Exception e)
		{
		
		}
	}

}
