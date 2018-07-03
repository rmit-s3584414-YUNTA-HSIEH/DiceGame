package controller;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class addActionListener implements ActionListener{
	private JDialog dialog;
	private GameEngineCallbackGUI frame;
	private TextField id;
	private TextField name;
	private TextField point;
	
	public addActionListener(GameEngineCallbackGUI frame,JDialog dialog,TextField name,TextField point)
	{
		this.frame=frame;
		this.dialog=dialog;
		this.id=id;
		this.name=name;
		this.point=point;
	}

	private String generateID()
	{
		int ID=1;
		GameEngine game = frame.getGameEngine();
		ArrayList<Player> players = (ArrayList<Player>) game.getAllPlayers();
		if(players.size()==0)
		{
			return Integer.toString(ID);
		}
		ID= players.size()+1;
		return Integer.toString(ID);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String playerID=generateID();		
		String playerName=name.getText();
		int playerPoint=0;
		//convert string to integer, make sure point is a integer
		try 
		{
			playerPoint=Integer.parseInt(point.getText());		
		}
		catch(Exception ee) 
		{
			JOptionPane.showMessageDialog(frame.getFram(), "point should be enterd as integer.");
			dialog.dispose();
			return;
		}
		//point should be positive
		if(!(playerPoint>0))
		{
			JOptionPane.showMessageDialog(frame.getFram(), "point should be more than 0");
			dialog.dispose();
			return;
		}
		
		//if user input is correct, create a new player and add into game
		GameEngine game = frame.getGameEngine();
		game.addPlayer(new SimplePlayer(playerID,playerName,playerPoint));
		//show new player in frame
		frame.NumOfPlayer();
		for(Player p:game.getAllPlayers())
		{
			if(p.getBet()==0)
			{
				frame.getStatus().setText("Player don't set the bet yet!");
				break;
			}
		}
		
		//close dialog
		dialog.dispose();
		
	}

}
