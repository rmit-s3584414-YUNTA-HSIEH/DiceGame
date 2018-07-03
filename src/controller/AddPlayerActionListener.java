package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameEngineCallbackGUI;
import view.DisplayAddPlayer;


public class AddPlayerActionListener implements ActionListener{

	private GameEngineCallbackGUI frame;

	public AddPlayerActionListener(GameEngineCallbackGUI frame)
	{
		super();
		this.frame = frame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new DisplayAddPlayer(frame);
	}

}
