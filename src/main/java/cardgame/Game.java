package cardgame;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {	
	static Logger logger=Logger.getLogger(Game.class.getName());
	public static void main(String[] args) {		
		GamePlay ob=new GamePlay();
		ob.start();
		logger.log(Level.INFO,"Game Starts");
		ob.play();
	}
}
