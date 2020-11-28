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
		City.createMap(10);
		City.fill();
		for(City c: City.getMap())
			Ant.patrouille();
	}
}
