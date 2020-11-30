/*
 * EL OUALI
 * TAFANEL
 */

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
	private Color city_cl, road_cl, font_cl;

	public DrawAera(ArrayList<Road> roads, ArrayList<City> cities){
		super();
		this.cities = cities;
		this.roads = roads;
		this.radius = 25;
		this.updates = 0;
		this.road_cl = Palette.red;
		this.city_cl = Palette.orange;
		this.font_cl = Palette.green;
		for(Road r: roads){
			r.addObserver(this);
		}
	}

	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3));
		for(Road r: roads){
			City a = r.getA();
			City b = r.getB();
			Color bkg = this.getBackground();
			double poids = (r.getPoids());
			System.out.println(r.toString()+": "+poids);
			if(poids>=255)
				poids = 255;
			if(poids <= 20)
				poids = 0;

			Color cl = new Color(road_cl.getRed(), road_cl.getGreen(), road_cl.getBlue(), (int)poids);
			g.setColor(cl);
			g2.drawLine(a.getX(), a.getY(), b.getX(), b.getY());
		}

		for(City c: cities){
			g.setColor(city_cl);
			g.fillOval(c.getX()-radius/2, c.getY()-radius/2, radius, radius);
			g.setColor(font_cl);
			g2.drawString(c.toString(), c.getX()-radius/2, c.getY()-radius/2);
		}

	}

	public void update(Observable obs, Object obj){
		updates++;
		this.repaint();
	}
}
