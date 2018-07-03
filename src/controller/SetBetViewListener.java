package controller;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JOptionPane;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.SetBet;

public class SetBetViewListener extends MouseAdapter{
	
	private GameEngineCallbackGUI frame;
	private JList list;
	
	public SetBetViewListener(GameEngineCallbackGUI frame,JList list)
	{
		this.frame=frame;
		this.list=list;
	}
	

	 @Override
	 public void mouseClicked(MouseEvent event)
	 {
		 if(!(frame.getGameEngine().getAllPlayers().size()>0))
			{
				JOptionPane.showMessageDialog(frame.getFram(), "please add player!");
				return;
			}
		 int SelectedIndex = list.getSelectedIndex();
		 if(SelectedIndex!=-1)
		 {
			 new SetBet(frame,SelectedIndex);
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(frame.getFram(), "please select a player");
				return;
		 }
	 }

}
