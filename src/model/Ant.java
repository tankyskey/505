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
			if(!visited.contains(r.getDestination(pos))){		// on elimine les chemins menant aux villes deja visité.
				bottom += r.getPoids()*(1.0/r.getDistance());	// on calcule le diviseur
			}
		}

		for(Road r: pos.getRoads()){
			if(!visited.contains(r.getDestination(pos))){
				double top = r.getPoids()*(1.0/r.getDistance());// on calcule le denominateur
				probaPred += top/bottom;						// on calcule la probabilité d'aller dans cette ville.

				if(choix < probaPred){
					System.out.println("from ["+pos+"] to [" +r.getDestination(pos)+"]");
					path = r;
					break;
				}
			}
		}

		if(path != null){
			lk += path.getDistance();
			pos = path.getDestination(pos);
			roads.add(path);
		}
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
		System.out.println(ants.size());
		for(Ant a: ants){
			System.out.println("moving out!"+a);
			a.move();
		}
	}

	public void setLk(double lk){
		this.lk = lk;
	}

	public static void resetAnt(){
		Ant bestAnt = ants.get(0);
		for(Ant a: ants){
			System.out.println("lk: "+a.lk);
			if(a.lk <= bestAnt.lk){
				bestAnt = a;
				Road.set_shortestPath(new ArrayList<Road>(bestAnt.roads));
			}
			a.visited.clear();
			a.roads.clear();
			a.setLk(0);
		}
	}

	public String toString(){
		return pos.toString();
	}
}
