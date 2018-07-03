package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ExitListener implements ActionListener {

	private Component owner;

	public ExitListener(Component owner) {
		this.owner = owner;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(owner, "are your sure exit application?", "Exit",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}
