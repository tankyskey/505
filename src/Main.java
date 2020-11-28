import model.*;

public class Main{

	public static void main(String args[]){
		simulation();
	}

	public static void test(){
		City.createMap(10);

		for(City c : City.getMap()){
			System.out.println(c);
		}
		Ant a = new Ant(City.getMap().get(0));
		System.out.println("pos: " + a.getPos());
		a.move();
		System.out.println("pos: " + a.getPos());
		a.move();
		System.out.println("pos: " + a.getPos());
	}

	public static void simulation(){
		City.createMap(5);
		for(City a: City.getMap()){
			for(int i=1; i<City.get_nbCities(); i++){
				System.out.println("patrouille");
				Ant.patrouille();
			}
			System.out.println("=========[reset]=========");
			Ant.resetAnt();
			System.out.println("");
		}

		System.out.println("----");
		for(Road r: Road.get_shortestPath()){
			System.out.println(r);
		}

	}
}
