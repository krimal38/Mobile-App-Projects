
class Goat extends Thread{
    int goat_num;
    Boat b;
    Goat(int num, Boat b ) {
	    this.goat_num = num;
	    this.b = b;
    }
    public void run(){ 
	    System.out.println("Generated Goat"+goat_num);
	    b.board_goat();
	    System.out.println("Goat"+goat_num+" crossed");
    }
}



