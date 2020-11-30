/*
 * EL OUALI
 * TAFANEL
*/

package model;
import java.util.ArrayList;
import java.util.Observable;

public class Road extends Observable{
	private City a, b;
	private double distance, poids, tp;
	private static ArrayList<Road> roads = new ArrayList<Road>();
	private static ArrayList<Road> shortestPath = new ArrayList<Road>();
	public static double Q=1, A=1, B=1, C=.7;

	// constructeurs
	public Road(City a, City b){
		this.a = a;
		this.b = b;
		poids = 1;
		tp = 0;
		distance = a.distance(b);
		roads.add(this);
		a.addRoad(this);
		b.addRoad(this);
	}

	// methodes
	public void augmentePoids(double lk){
		if(lk > 0)
			tp += Q/lk;
	}

	public void update(){
		poids = C*poids+tp;
	}

	public static void updateRoads(){
		for(Road r: roads)
			r.update();
	}

	public boolean isSmaller(Road r){
		return distance < r.distance;
	}

	// getters
	public double getPoids(){
		return poids;
	}

	public double getDistance(){
		return distance;
	}

	public City getDestination(City d){
		if(d == a){
			return b;
		}
		else if(d == b){
			return a;
		}
		else{
			return null;
		}
	}

	public City getA(){
		return a;
	}

	public City getB(){
		return b;
	}

	public static ArrayList<Road> get_shortestPath(){
		return shortestPath;
	}

	public static ArrayList<Road> get_roads(){
		return roads;
	}

	// setters
	public static void set_shortestPath(ArrayList<Road> shortestPath){
		Road.shortestPath = shortestPath;
	}

	public String toString(){
		return "form ["+a.toString()+"] to ["+b.toString()+"]";
	}

	public static void resetRoads(){
		for(Road r: roads){
			r.reset();
		}
	}

	public void reset(){
		tp = 0;
		poids = 1;
		this.setChanged();
		this.notifyObservers();
	}
}
