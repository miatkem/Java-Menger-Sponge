import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MengerSpongeRunner implements ActionListener
{

	int width = 1000;
	int height = 1000;
	
	
	Timer ticker;
	Panel panel;
	JButton button;
	
	boolean RAINBOW_MODE=false;
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new MengerSpongeRunner();
	}
	public MengerSpongeRunner()
	{		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(width,height);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		
		
		panel = new Panel(width, height, RAINBOW_MODE);
		window.add(panel);
		
		//JPanel butPan = new JPanel();
		button=new JButton("Split Sponge");
		button.addActionListener(this);
		//butPan.add(button);
		//window.add(butPan);
		ticker = new Timer(1000, this);
	    ticker.start();
		
	
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==ticker)
		{
			panel.updateDisplay();
		}
	}
}
class Panel extends JPanel
{
	int iteration;
	int width, height;
	boolean rainbowMode;
	
	
	public Panel(int width, int height, boolean rainbowMode)
	{
		iteration=0;
		this.width=width-100;
		this.height=height-100;
		this.rainbowMode=rainbowMode;
	}
	
	public void paintSponge(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		int rep=iteration;
		while(rep>0)
		{
			double size=width/(Math.pow(3, rep));
			
			for(int i=0; i<Math.pow(3, rep); i++)
			{
				for(int j=0; j<Math.pow(3, rep); j++)
				{
					if(i%3==1&&j%3==1)
					{
						if(rainbowMode)
			        		g.setColor(new Color((int)(Math.random()*256),(int) (Math.random()*226),(int)(Math.random()*256)));
						g2.fill(new Rectangle2D.Double(50+size*i,50+size*j, size, size));
					
						
						if(rep==1)
						{
							Font mainFont = new Font("Serif",1,width/25);
							String finalStr = "Menger Sponge";
							String str = finalStr.substring(0,3+iteration*2);
							for(int t=str.length();t<finalStr.length(); t++)
								str+=" ";
							g.setColor(Color.WHITE);
							drawCenteredString(g, str, new Rectangle((int)(50.0+size*i),(int)(50.0+size*j), (int)size,(int) size), mainFont);
						}
						 
					}
					g.setColor(Color.BLACK);
					g2.draw(new Rectangle2D.Double(50+size*i,50+size*j, size, size));
				}
			}
			rep--;
		}
		iteration++;
		
	}
	
	public void updateDisplay()
	{
		this.revalidate();
		this.repaint();
		
	}
	public void paintComponent(Graphics g) 
	{
		
		g.setColor(Color.BLACK);
		if(iteration<6)
			paintSponge(g);
		
	}
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) 
	{
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y);
	}
}