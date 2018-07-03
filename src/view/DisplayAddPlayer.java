package view;

import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.addActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class DisplayAddPlayer extends JDialog{
	

	public DisplayAddPlayer(GameEngineCallbackGUI frame)
	{
		super(frame.getFram(),"add a player",true);
	
		JPanel container = new JPanel();
		Container a = getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		TextField Name=new TextField();
		TextField Point=new TextField();
		Button add = new Button("Add");
		container.add(new Label("Player Name:"));
		container.add(Name);
		container.add(new Label("Player Point:"));
		container.add(Point);
		container.add(add);
		setBounds(100,100,200,200);
		container.setBorder(new EmptyBorder(10, 5, 10, 5));
		a.add(container);
		add.addActionListener(new addActionListener(frame,this,Name,Point));
		setLocationRelativeTo(null);
		setVisible(true);
				
	}

	
}
