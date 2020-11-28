package model;
import java.util.ArrayList;

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
			if(!roads.contains(r)){
				bottom += (double)r.getPoids()*1.0/r.getDistance();
			}
		}

		for(Road r: pos.getRoads()){
			double top = r.getPoids()*1.0/r.getDistance();
			probaPred += top/bottom;

			if(choix < probaPred){
				path = r;
				break;
			}
		}

		lk += path.getDistance();
		pos = path.getDestination(pos);
		roads.add(path);
	}

	public void dropPhem(){
		for(Road r: roads){
			r.augmentePoids(lk);
		}
	}

	public City getPos(){
		return pos;
	}

	public static void patrouille(){
		for(Ant a: ants){
			a.move();
		}
	}
}
