package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollingPanel extends JFrame{

	private JLabel diceLabel=new JLabel("",JLabel.CENTER);
	private JLabel dice1=new JLabel("",JLabel.CENTER);
	private JLabel dice2=new JLabel("",JLabel.CENTER);
	private JPanel panel;
	
	public RollingPanel()
	{
		setTitle("Player Rolling panel");
		setBounds(200, 200, 400, 400);
		panel =new JPanel();
		diceLabel.setBorder(BorderFactory.createTitledBorder("Dice"));
		diceLabel.setFont(new Font("Courier",Font.BOLD,30));  
		panel.setLayout(new FlowLayout());
		panel.add(dice1);
		panel.add(dice2);
		add(diceLabel,BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);

	}
	
	public JLabel getLabel()
	{
		return diceLabel;
	}
	
	public JLabel getDice1()
	{
		return dice1;
	}
	public JLabel getDice2()
	{
		return dice2;
	}
}
