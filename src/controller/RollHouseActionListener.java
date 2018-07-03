package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class RollHouseActionListener implements ActionListener{
		
	private GameEngineCallbackGUI frame;
	
	public RollHouseActionListener(GameEngineCallbackGUI frame)
	{
		this.frame=frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		GameEngine game = frame.getGameEngine();
		//check have player in dice game
		if(!(game.getAllPlayers().size()>0))
		{
			JOptionPane.showMessageDialog(frame.getFram(), "please add player!");
			return;
		}
		
		ArrayList<Player> players = (ArrayList<Player>)frame.getGameEngine().getAllPlayers();
		int count=0;
		//check that at least one player has already rolled dice
		for(Player p:players)
		{
			if(p.getRollResult()!=null)
			{
				break;
			}
			count++;
		}
		
		if(count==players.size())
		{
			JOptionPane.showMessageDialog(frame.getFram(), "Roll a player first!");
			return;
		}
		
		
		
new Thread() {
			
			@Override
			public void run() {
				int initialDelay=1;
				int finalDelay=2500;
				int delayIncrement=500;
				frame.getGameEngine().rollHouse(initialDelay, finalDelay, delayIncrement);
			};
		}.start();
		
	}

}
