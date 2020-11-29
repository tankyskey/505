import model.*;
import view.*;

public class Main{

	public static void main(String args[]){
		new MainView(Road.get_roads(), City.getMap());
	}
}
