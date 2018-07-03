package model;

import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine{
	
	private ArrayList<Player> Players=new ArrayList<>();
	private ArrayList<GameEngineCallback> GameEngineCallbacks=new ArrayList<>();
	

	@Override
	public boolean placeBet(Player player, int bet) {
		if(player.placeBet(bet)) {
			return true;
		}
		return false;
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) {
		DicePair dice=null;
		//Rolling dice 1 and dice 2. start from initial Delay to final Delay
		for(int i=initialDelay;i<finalDelay;i+=delayIncrement) {
			dice=rollDice(NUM_FACES);
			for(GameEngineCallback callback:GameEngineCallbacks) {
				callback.intermediateResult(player, dice, this);
			}
			try {
			    Thread.sleep(delayIncrement);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
		}
		//the results of dice 1 and dice 2 
		dice=rollDice(NUM_FACES);
		for(GameEngineCallback callback:GameEngineCallbacks) {
			callback.result(player, dice, this);
		}
		player.setRollResult(dice);
		
		
		
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) {
		DicePair dice=null;
		//Rolling dice 1 and dice 2. start from initial Delay to final Delay
		for(int i=initialDelay;i<finalDelay;i+=delayIncrement) {
			dice=rollDice(NUM_FACES);
			for(GameEngineCallback callback:GameEngineCallbacks) {
				callback.intermediateHouseResult(dice, this);
			}
			try {
			    Thread.sleep(delayIncrement);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		//the results of dice 1 and dice 2 
		dice=rollDice(NUM_FACES);
		//calculate result
		gameResult(dice);
		//display result
		for(GameEngineCallback callback:GameEngineCallbacks) {
			callback.houseResult(dice, this);
		}
	}

	@Override
	public void addPlayer(Player player) {
		Players.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		if(!Players.isEmpty()) {
			for(Player player:Players) {
				if(player.getPlayerId().equals(id)) {
					return player;
				}
			}
		}
		
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		for(Player thePlayer:Players) {
			if(thePlayer==player) {
				return Players.remove(thePlayer);
			}
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		GameEngineCallbacks.add(gameEngineCallback);
		
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		for(GameEngineCallback callBack: GameEngineCallbacks) {
			if(callBack==gameEngineCallback) {
				return GameEngineCallbacks.remove(callBack);
			}
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		ArrayList<Player> copyPlayer = new ArrayList<>(Players);
		return copyPlayer;
	}
	
	private DicePair rollDice(int faces) {
		DicePair dices;
		int dice1=(int) ((Math.random()*faces)+1);
		int dice2=(int) ((Math.random()*faces)+1);
		return dices= new DicePairImpl(dice1,dice2,faces);
	}
	

	
	//private method for calculating game result depend on the value of dice
	private void gameResult(DicePair houseResult) {
		//sum two dices of house
		int HouseResult=(houseResult.getDice1()+houseResult.getDice2());
		for(Player player:Players) {
			if(player.getRollResult()!=null)
			{
				//compare dices sum between players and house
				int pDice1=player.getRollResult().getDice1();
				int pDice2=player.getRollResult().getDice2();
				//player win
				if((pDice1+pDice2)>HouseResult&&player.getBet()!=0) {
				player.setPoints(player.getPoints()+player.getBet());
				//player lose
				}
				else if((pDice1+pDice2)<HouseResult&&player.getBet()!=0) {
				player.setPoints(player.getPoints()-player.getBet());
				}
			}
			
		}
	}
}
