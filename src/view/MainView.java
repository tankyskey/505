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

	public MainView(ArrayList<Road> roads, ArrayList<City> cities, int LARGEUR, int HAUTEUR){
		super();
		this.cities = cities;
		this.roads = roads;

		create_IHM();
		this.setVisible(true);
		this.setSize(LARGEUR, HAUTEUR);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public MainView(ArrayList<Road> roads, ArrayList<City> cities){
		this(roads, cities, 500, 500);
	}

	public void create_IHM(){
		JPanel c1=new JPanel(new BorderLayout());
		JPanel c2=new JPanel();
		JPanel c3=new JPanel();
		JPanel c4=new JPanel();

		JLabel l1=new JLabel("nombre de villes:");
	   	JLabel l2=new JLabel("nombre de fourmis:");
	   	JLabel l3=new JLabel("A:");
	   	JLabel l4=new JLabel("B:");
	   	JLabel l5=new JLabel("C:");
	   	JLabel l6=new JLabel("Q:");

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
				Road.clearRoads();
				drawMap.repaint();
			}
		});
		b2.addActionListener(new ActionListener(){
			// New Map
			@Override
			public void actionPerformed(ActionEvent e){
				City.createMap(Integer.parseInt(f1.getText()));
				drawMap.repaint();
			}
		});
		b3.addActionListener(new ActionListener(){
			// Launch
			@Override
			public void actionPerformed(ActionEvent e){
				Road.clearRoads();
				Road.setA(Double.parseDouble(f3.getText()));
				Road.setB(Double.parseDouble(f4.getText()));
				Road.setC(Double.parseDouble(f5.getText()));
				Road.setQ(Double.parseDouble(f6.getText()));
				drawMap.repaint();

				int n = 500;
				while((n--) != 0){
					for(int i=1; i<City.get_nbCities(); i++){
						Ant.patrouille();
					}
					Ant.resetAnt();
					Road.updateRoads();
					System.out.println("");
				}

				for(Road r: Road.get_shortestPath()){
					System.out.println(r);
				}
			}
		});

		c2.setBackground(Color.GRAY);
		c4.setBackground(Color.GRAY);

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
