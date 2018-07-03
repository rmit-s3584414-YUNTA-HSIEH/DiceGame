package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

public class SimplePlayer implements Player{
	
	private String playerId;
	private String playerName;
	private int points;
	private int bet;
	private DicePair rollResult;
	
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerId=playerId;
		this.playerName=playerName;
		this.points=initialPoints;
	}
	
	
	@Override
	public String getPlayerName() {
		return playerName;
	}
	@Override
	public void setPlayerName(String playerName) {
		this.playerName=playerName;		
	}
	@Override
	public int getPoints() {
		return points;
	}
	@Override
	public void setPoints(int points) {
		this.points=points;
	}
	@Override
	public String getPlayerId() {
		return this.playerId;
	}
	@Override
	public boolean placeBet(int bet) {
		if(this.points>=bet) {
			this.bet=bet;
			return true;
		}
		return false;
	}
	@Override
	public int getBet() {
		return this.bet;
	}
	@Override
	public DicePair getRollResult() {	
		return this.rollResult;
	}
	@Override
	public void setRollResult(DicePair rollResult) {
		this.rollResult=rollResult;
	}
	@Override
	public String toString() {
		return "Player: id="+this.playerId+", name="+this.playerName+", points="+this.points;
	}
}