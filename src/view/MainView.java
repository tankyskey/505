/*
 * EL OUALI
 * TAFANEL
 */

package view;

import java.util.ArrayList;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainView extends JFrame{
	private int LARGEUR, HAUTEUR;
	private ArrayList<City> cities;
	private ArrayList<Road> roads;
	private Color bckg_cl, font_cl;

	public MainView(int LARGEUR, int HAUTEUR){
		super();
		this.cities = City.getMap();
		this.roads = Road.get_roads();
		this.bckg_cl = Palette.black;
		this.font_cl = Palette.green;

		create_IHM();
		this.setVisible(true);
		this.setSize(LARGEUR, HAUTEUR);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public MainView(){
		this(500, 500);
	}

	public void create_IHM(){
		JPanel c1=new JPanel(new BorderLayout());
		JPanel c2=new JPanel();
		JPanel c3=new JPanel();
		JPanel c4=new JPanel();

		JLabel l1=new JLabel("nombre de villes:");	l1.setForeground(font_cl);
	   	JLabel l2=new JLabel("nombre de fourmis:");	l2.setForeground(font_cl);
	   	JLabel l3=new JLabel("A:");	l3.setForeground(font_cl);
	   	JLabel l4=new JLabel("B:");	l4.setForeground(font_cl);
	   	JLabel l5=new JLabel("C:");	l5.setForeground(font_cl);
	   	JLabel l6=new JLabel("Q:");	l6.setForeground(font_cl);

		JTextField f1=new JTextField("4"); // nbVilles
		JTextField f2=new JTextField("4"); // nbFourmis
		JTextField f3=new JTextField("1.0"); // A
		JTextField f4=new JTextField("1.0"); // B
		JTextField f5=new JTextField("0.7"); // C
		JTextField f6=new JTextField("1.0"); // Q

		JButton b1=new JButton("Clear");
		JButton b2=new JButton("New map");
		JButton b3=new JButton("Launch");

		DrawAera drawMap = new DrawAera(roads, cities);

		b1.addActionListener(new ActionListener(){
			// Clear
			@Override
			public void actionPerformed(ActionEvent e){
				Road.resetRoads();
				drawMap.repaint();
			}
		});
		b2.addActionListener(new ActionListener(){
			// New Map
			@Override
			public void actionPerformed(ActionEvent e){
				City.setDim(drawMap.getWidth(), drawMap.getHeight());
				City.createMap(Integer.parseInt(f1.getText()));
				drawMap.repaint();
			}
		});
		b3.addActionListener(new ActionListener(){
			// Launch
			@Override
			public void actionPerformed(ActionEvent e){
				Road.resetRoads();
				Ant.setAnts(Integer.parseInt(f2.getText()));
				Road.A=Double.parseDouble(f3.getText());
				Road.B=Double.parseDouble(f4.getText());
				Road.C=Double.parseDouble(f5.getText());
				Road.Q=Double.parseDouble(f6.getText());
				drawMap.repaint();

				System.out.println("====] Simulation [====");
				int n = 1000;
				while((n--) != 0){
					for(int i=1; i<City.get_nbCities(); i++){
						Ant.patrouille();
					}
					Ant.resetAnt();
					Road.updateRoads();
					drawMap.repaint();
				}

				System.out.println("short path:");
				for(Road r: Road.get_shortestPath()){
					System.out.println(r);
				}
				System.out.println("====] Simulation [====");
			}
		});

		c2.setBackground(bckg_cl);
		c4.setBackground(bckg_cl);
		drawMap.setBackground(bckg_cl);

		c3.setLayout(new BoxLayout(c3, BoxLayout.PAGE_AXIS));
		c3.add(l1); c3.add(f1);
		c3.add(l2); c3.add(f2);

		c4.setLayout(new BoxLayout(c4, BoxLayout.PAGE_AXIS));
		c4.add(l1); c4.add(f1);
		c4.add(l2); c4.add(f2);
		c4.add(l3); c4.add(f3);
		c4.add(l4); c4.add(f4);
		c4.add(l5); c4.add(f5);
		c4.add(l6); c4.add(f6);
		c4.add(b1);
		c4.add(b2);
		c4.add(b3);

		c1.add(drawMap, BorderLayout.CENTER);
		c1.add(c2, BorderLayout.EAST);
		c2.add(c4);
		this.add(c1);
		this.setSize(LARGEUR, HAUTEUR);
	}
}
