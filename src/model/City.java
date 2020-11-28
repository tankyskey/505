package model;
import java.util.ArrayList;
import java.lang.Math;

public class City{
	private int x, y;
	private static ArrayList<City> map = new ArrayList<City>();
	private ArrayList<Road> roads;

	public City(int x, int y){
		this.x = x;
		this.y = y;
		roads = new ArrayList<Road>();
	}

	public City(){
		this((int)(Math.random()*50.0), (int)(Math.random()*50.0));
	}

	public double distance(City b){
		return Math.hypot((b.x-this.x), (b.y-this.y));
	}

	public void link(){
		for(City c : map){
			new Road(this, c);
		}
	}

	public void addRoad(Road r){
		roads.add(r);
	}

	public ArrayList<Road> getRoads(){
		return roads;
	}

	public static void createMap(int nbNode){
		for(; nbNode > 0; nbNode--){
			City n = new City();
			n.link();
			map.add(n);
		}
	}

	public static ArrayList<City> getMap(){
		return map;
	}

	public String toString(){
		return "x: "+String.valueOf(x)+" - y: "+ String.valueOf(y)+"\n";
	}

	public static void fill(){
		for(City c: map){
			new Ant(c);
		}
	}
}
