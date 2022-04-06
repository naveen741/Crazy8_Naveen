package cardgame;
public class Game {	
	public static void main(String[] args) {		
		GamePlay ob=new GamePlay();
		ob.start();
		System.out.println("================================================");
		System.out.println("Game Starts");
		ob.play();
	}
}