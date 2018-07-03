package view;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerActionListener;
import controller.ExitListener;
import view.GameEngineCallbackGUI;

public class MainMenu extends JMenuBar{
	
	public MainMenu(GameEngineCallbackGUI frame)
	{
	JMenu GameMenu = new JMenu("Menu");
	GameMenu.setMnemonic(KeyEvent.VK_I);
	this.add(GameMenu);
	
	JMenuItem AddPlayerItem = new JMenuItem("Add Player",getIcon("new"));
	AddPlayerItem.addActionListener(new AddPlayerActionListener(frame));
	JMenuItem exitMI = new JMenuItem("Exit", getIcon("exit"));
	GameMenu.add(AddPlayerItem);
	GameMenu.add(exitMI);
	exitMI.addActionListener(new ExitListener(this));
	}
	
	private ImageIcon getIcon(String name) {
		return new ImageIcon(getClass().getResource("/images/" + name + ".png"));
	}

}
