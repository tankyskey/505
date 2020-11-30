package model;
import java.util.ArrayList;
import java.lang.Math;
import java.util.HashMap;
import java.util.TreeMap;

public class Ant{
	private City pos;				 // Ville courante
	private ArrayList<City> visited;// Villes visité
	private double lk; 			   // distance totale parcoure
	private ArrayList<Road> roads;// Chemins empreinté
	private static ArrayList<Ant> ants = new ArrayList<Ant>();

	public Ant(City c){
		this.pos = c;
		visited = new ArrayList<City>();
		roads = new ArrayList<Road>();
		ants.add(this);
	}

	public void move(){
		visited.add(pos);
		double choix = Math.random();
		double bottom = 0, probaPred = 0;
		Road path = null;

		for(Road r : pos.getRoads()){
			if(!visited.contains(r.getDestination(pos))){		// on elimine les chemins menant aux villes deja visité.
				bottom += Math.pow(r.getPoids(), Road.A)*Math.pow((1.0/r.getDistance()), Road.B);	// on calcule le diviseur
			}
		}

		TreeMap<Double, Road> candidats = new TreeMap<Double, Road>();
		for(Road r: pos.getRoads()){
			if(!visited.contains(r.getDestination(pos))){
				double top = Math.pow(r.getPoids(), Road.A)*Math.pow((1.0/r.getDistance()), Road.B);// on calcule le denominateur
				candidats.put(top/bottom, r);
			}
		}
		for(Double k: candidats.keySet()){
			path = candidats.get(k);
			probaPred += k;

			if(probaPred >= choix){
				break;
			}
		}

		if(path != null){
			lk += path.getDistance();
			pos = path.getDestination(pos);
			roads.add(path);
		}
	}

	public void dropPhem(){
		for(Road r: roads)
			r.augmentePoids(lk);
	}
	
	public City getPos(){
		return pos;
	}

	public static void patrouille(){
		for(Ant a: ants)
			a.move();
	}

	public void setLk(double lk){
		this.lk = lk;
	}

	public static void resetAnt(){
//		Ant bestAnt = ants.get(0);
		for(Ant a: ants){
//			if(a.lk <= bestAnt.lk){
//				bestAnt = a;
//				Road.set_shortestPath(new ArrayList<Road>(bestAnt.roads));
//			}
			a.dropPhem();
			a.visited.clear();
			a.roads.clear();
			a.setLk(0);
		}
	}

	public String toString(){
		return pos.toString();
	}

	public void setPos(City c){
		this.pos = c;
	}

	public static void setAnts(int nb){
		ants.clear();
		for(; nb>0; nb--){
			int v_index = (int)(Math.random()*(double)(City.get_nbCities()-1));
			City c = City.getMap().get(v_index);
			ants.add(new Ant(c));
		}
	}

	public static void repartion(){
		for(Ant a: ants){
			int v_index = (int)(Math.random()*(double)(City.get_nbCities()-1));
			a.setPos(City.getMap().get(v_index));
		}
	}
}
