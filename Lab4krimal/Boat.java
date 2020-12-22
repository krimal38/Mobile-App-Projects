
class Boat {
	int num_goats ;
	int num_wolves;
	int goat_slots; 
	int wolf_slots;

	int num_goat_boats = 0;
	int num_wolf_boats = 0;
	int num_mixed_boats = 0;
	Boat() { num_goats=0;num_wolves = 0;};
	public synchronized void board_goat() {
 	  num_goats++;
	  if (num_goats == 4) {
		  num_goats = 0;
                  goat_slots = 3; 
                  wolf_slots = 0;
		  num_goat_boats++;
		  notifyAll();
	  } else if ((num_goats == 2) && (num_wolves >= 2)) {
		 num_goats = 0; num_wolves -= 2;
		goat_slots = 1; wolf_slots = 2; 
		  num_mixed_boats++;
		notifyAll();
	  } else try { while (goat_slots == 0) wait();
	                  goat_slots--;}
	         catch (Exception e) { }
			
	}
	public synchronized void board_wolf() {
 	  num_wolves++;
	  if (num_wolves == 4) {
		  num_wolves = 0;
                  goat_slots = 0; wolf_slots = 3;
		  num_wolf_boats++;
		  notifyAll();
	  } else if ((num_goats >= 2) && (num_wolves == 2)) {
		 num_goats -= 2; num_wolves = 0;
		goat_slots = 2; wolf_slots = 1; 
	        num_mixed_boats++;
		notifyAll();
	  } else try { while (wolf_slots == 0) wait();
	                  wolf_slots--;}
	         catch (Exception e) { }
			
	}
}


