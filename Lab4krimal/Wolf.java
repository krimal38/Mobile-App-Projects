
class Wolf extends Thread{
    int wolf_num;
    Boat b;
    Wolf(int num, Boat b ) {
	    this.wolf_num = num;
	    this.b = b;
    }
    public void run(){ 
	    System.out.println("Generated Wolf"+wolf_num);
	    b.board_wolf();
	    System.out.println("Wolf"+wolf_num+" crossed");
    }
}

