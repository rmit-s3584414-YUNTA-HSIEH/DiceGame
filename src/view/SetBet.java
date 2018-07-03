package view;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.SetBetActionListener;
import model.SimplePlayer;
import model.interfaces.GameEngine;

public class SetBet extends JDialog{
	
	private GameEngineCallbackGUI frame;
	
	public SetBet(GameEngineCallbackGUI frame,int index)
	{
			
		super(frame.getFram(),"Set Bet",true);
		
		setLocation(frame.getFram().getX()+100,frame.getFram().getY()+100);
		GameEngine game = frame.getGameEngine();
		ArrayList<SimplePlayer> players = (ArrayList)frame.getGameEngine().getAllPlayers();
		
		SimplePlayer player = players.get(index);
		
		Container a = getContentPane();
		JPanel container = new JPanel();
		Button setBet = new Button("Set");		
		TextField bet=new TextField();
		
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		container.add(new JLabel("player ID: "+player.getPlayerId()));
		container.add(Box.createRigidArea(new Dimension(0, 10)));
		container.add(new JLabel("player Name: "+player.getPlayerName()));
		container.add(Box.createRigidArea(new Dimension(0, 10)));
		container.add(new JLabel("player Point: "+player.getPoints()));
		container.add(Box.createRigidArea(new Dimension(0, 10)));
		container.add(new JLabel("Enter player Bet: "));
		container.add(Box.createRigidArea(new Dimension(0, 10)));
		container.add(bet);
		container.add(Box.createRigidArea(new Dimension(0, 10)));
		container.add(setBet);
		container.add(Box.createRigidArea(new Dimension(0, 10)));
		a.add(container);
		setBet.addActionListener(new SetBetActionListener(frame,bet,player,this));
		setSize(250,250);
		setVisible(true);
		
	}

}
