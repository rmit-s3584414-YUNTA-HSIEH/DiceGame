package model;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * 
 * Skeleton example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see model.interfaces.GameEngineCallback
 * 
 */

/*
 * 
 *Logger globalLogger = Logger.getLogger("global");
Handler[] handlers = globalLogger.getHandlers();
for(Handler handler : handlers) {
    globalLogger.removeHandler(handler);
}
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private Logger logger = Logger.getLogger("assignment1");
	private	Handler[] handlers = logger.getHandlers();
	public GameEngineCallbackImpl()
	{
		// FINE shows rolling output, INFO only shows result
		
		logger.setLevel(Level.FINE);
		for(Handler handler : handlers) {
			logger.removeHandler(handler);
		}
	     // PUBLISH this level
		ConsoleHandler handler =new ConsoleHandler();
	     handler.setLevel(Level.FINER);
	     logger.addHandler(handler);
	     logger.setUseParentHandlers(false);
	}

	@Override
	public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine)
	{
		// intermediate results logged at Level.FINE
		logger.log(Level.FINE, String.format("%s: ROLLING %s", 
				player.getPlayerName(),dicePair.toString()));
		// TO DO: complete this method to log results
	}

	@Override
	public void result(Player player, DicePair result, GameEngine gameEngine)
	{
		// final results logged at Level.INFO
		logger.log(Level.INFO, String.format("%s: *RESULT* %s", 
				player.getPlayerName(),result.toString()));
		// TO DO: complete this method to log results
	}

	@Override
	public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {
		logger.log(Level.FINE, String.format("%s: ROLLING %s", 
				"House",dicePair.toString()));
		
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine) {
		logger.log(Level.INFO, String.format("%s: *RESULT* %s", 
				"House",result.toString()));
		ArrayList<Player>players=(ArrayList<Player>) gameEngine.getAllPlayers();
		for(Player player:players) {
			logger.log(Level.INFO, player.toString());
		}
		
	}

	// TO DO: complete the GameEngineCallback interface implementation

}
