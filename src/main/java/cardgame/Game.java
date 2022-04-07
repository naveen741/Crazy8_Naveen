package cardgame;
public class Game {	
	public static void main(String[] args) {		
		GamePlay ob=new GamePlay();
		ob.start();
		System.err("================================================");
		System.out.println("Game Starts");
		ob.play();
	}
}
