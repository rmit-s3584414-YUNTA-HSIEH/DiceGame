package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class RollPlayerActionListener implements ActionListener{
	
	private GameEngineCallbackGUI frame;
	private JList list;
	
	public RollPlayerActionListener(GameEngineCallbackGUI frame,JList list)
	{
		this.frame=frame;
		this.list=list;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		GameEngine game = frame.getGameEngine();
		//non player
		if(!(game.getAllPlayers().size()>0))
		{
			JOptionPane.showMessageDialog(frame.getFram(), "please add player!");
			return;
		}
		//not select palyer
		int selectIndex = list.getSelectedIndex();
		if(selectIndex==-1)
		{
			JOptionPane.showMessageDialog(frame.getFram(), "please select a player");
			return;
		}
		
		ArrayList<Player> players = (ArrayList<Player>)frame.getGameEngine().getAllPlayers();		
		Player player = players.get(selectIndex);
		//player not exist or not set bet yet
		if(player==null||player.getBet()==0)
		{
			JOptionPane.showMessageDialog(frame.getFram(), "please place bet");
			return;
		}
		
		
		new Thread() {
			
			@Override
			public void run() {
				int initialDelay=1;
				int finalDelay=2500;
				int delayIncrement=500;
				int selectIndex = list.getSelectedIndex();
				ArrayList<Player> players = (ArrayList<Player>)frame.getGameEngine().getAllPlayers();		
				Player player = players.get(selectIndex);
				frame.getGameEngine().rollPlayer(player, initialDelay, finalDelay, delayIncrement);
			};
		}.start();
		
		
		
	}

}
