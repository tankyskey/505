package model;
import java.util.ArrayList;

public class Road{
	private static double Q=1, A=1, B=1, C=.7;
	private City a, b;
	private double distance, poids;
	private static ArrayList<Road> roads = new ArrayList<Road>();

	public Road(City a, City b){
		this.a = a;
		this.b = b;
		poids = 1;
		distance = a.distance(b);
		roads.add(this);
		a.addRoad(this);
		b.addRoad(this);
	}

	public void augmentePoids(double lk){
		poids += (1-C)*Q/lk;
	}

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

	public static void setQ(double n){
		Q = n;
	}

	public static void setA(double n){
		A = n;
	}

	public static void setB(double n){
		B=n;
	}

	public static void setC(double n){
		C=n;
	}

}
