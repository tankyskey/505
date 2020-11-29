package model;
import java.util.ArrayList;
import java.lang.Math;

public class City{
	private int x, y;
	private static int nbCities;
	private static ArrayList<City> map = new ArrayList<City>();
	private ArrayList<Road> roads;

	public City(int x, int y){
		this.x = x;
		this.y = y;
		roads = new ArrayList<Road>();
	}

	public City(){
		this((int)(Math.random()*200.0), (int)(Math.random()*200.0));
	}
	
	public void setCoordonnee() {
		this.x = (int)(Math.random()*50.0);
		this.y = (int)(Math.random()*50.0);
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

	public static int get_nbCities(){
		return nbCities;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public static void createMap(int nbNode){
		map.clear();
		Road.get_roads().clear();
		nbCities = nbNode;
		System.out.println("creating city...");
	
		for(; nbNode > 0; nbNode--){
			
			
			City n = new City();
			while (estMemeCoordonee(n) == true) {
				n.setCoordonnee();
			}
			new Ant(n);
			n.link();
			map.add(n);
			System.out.println(n);
		}
		System.out.println("================");
	}
	
	public static boolean estMemeCoordonee(City c) {
		for(int i = 0; i < map.size(); i++) {
			if (c.equals(map.get(i))){
				return true;
			}
		}
		return false;
	}

	public static ArrayList<City> getMap(){
		return map;
	}

	public String toString(){
		return String.valueOf(x)+"; "+ String.valueOf(y);
	}

	public boolean equals(City c){
		if(c == this)
			return true;
		return (c.x == x && c.y ==y);
	}
}
