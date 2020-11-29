package view;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import java.awt.*;
import model.*;

public class DrawAera extends Canvas implements Observer {
	private ArrayList<City> cities;
	private ArrayList<Road> roads;
	private int radius;
	private int updates;

	public DrawAera(ArrayList<Road> roads, ArrayList<City> cities){
		super();
		this.cities = cities;
		this.roads = roads;
		this.radius = 15;
		this.updates = 0;
		for(Road r: roads){
			r.addObserver(this);
		}
	}
	public void paint(Graphics g){
		for(Road r: roads){
			City a = r.getA();
			City b = r.getB();
			Color bkg = this.getBackground();
			double poids = (r.getPoids()*1000-1000)*10;
			System.out.println(r.toString()+": "+poids);
			if(poids>=255)
				poids = 255;
			if(poids < 0)
				poids = 0;
			g.setColor(new Color(255, 50, 50, (int)poids));
			g.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
		}

		for(City c: cities){
			g.setColor(Color.ORANGE);
			g.fillOval(c.getX()-radius/2, c.getY()-radius/2, radius, radius);
			g.setColor(Color.BLUE);
			g.drawString(c.toString(), c.getX()-radius/2, c.getY()-radius/2);
		}

	}

	public void update(Observable obs, Object obj){
		updates++;
		this.repaint();
	}
}
