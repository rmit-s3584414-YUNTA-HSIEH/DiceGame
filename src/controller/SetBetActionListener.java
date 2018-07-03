package controller;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;

public class SetBetActionListener implements ActionListener {

	private GameEngineCallbackGUI frame;
	private TextField UserInputBet;
	private SimplePlayer player;
	private JDialog dialog;
	
	
	public SetBetActionListener(GameEngineCallbackGUI frame,TextField UserInputBet,SimplePlayer player,JDialog dialog)
	{
		this.frame=frame;
		this.UserInputBet =UserInputBet;
		this.player=player;
		this.dialog=dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int bet = 0;
		GameEngine game = frame.getGameEngine();
		
		try
		{
			bet = Integer.parseInt(UserInputBet.getText());		
		}
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(frame.getFram(), "Bet should be enterd as integer.");
			dialog.dispose();
			return;
		}
		
		if(!(bet>0))
		{
			JOptionPane.showMessageDialog(frame.getFram(), "Bet should be more than 0");
			dialog.dispose();
			return;
		}
		
		if(!game.placeBet(player, bet))
		{
			JOptionPane.showMessageDialog(frame.getFram(), "Bet cannot be more than point");
			dialog.dispose();
			return;
		}
		int count=0;
		for(Player p:game.getAllPlayers())
		{
			if(p.getBet()==0)
			{
				break;
			}
			count++;
		}
		if(count==game.getAllPlayers().size())
		{
			frame.getStatus().setText("Ready to roll!");
		}
		else
		{
			frame.getStatus().setText("Still have player didn't set the bet!");
		}
		
		dialog.dispose();
		frame.NumOfPlayer();

	}
}
