import java.util.Random;

public class RiverCrossing{
    public static void main(String[] args) {
	int total = 0;
	Boat boat = new Boat();
	Random r = new Random();
	while (total < 100) {  // generate 100 animals 
	   try{
	    Thread.sleep(500);
	   } catch (Exception e) {
	   } 
	   int choice = r.nextInt(2);
	   if (choice ==0) {
	    Goat goat = new Goat(total,boat);
	    goat.start();
	   } else {
	    Wolf wolf = new Wolf(total,boat);
	    wolf.start();
	   }
	    total++;
	}
    }
}


