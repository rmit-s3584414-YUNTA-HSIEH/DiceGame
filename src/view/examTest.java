package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class examTest extends JFrame{
	
	JLabel label;
	JLabel statusBar;
	public static void main(String args[])
	{
		examTest test= new examTest();
	}
	public examTest()
	{
		label = new JLabel("",JLabel.CENTER);
		statusBar= new JLabel("status",JLabel.CENTER);
		toolBar bar = new toolBar(label,this);
		setBounds(100, 100, 600, 700);
		setLayout(new BorderLayout());			
		add(bar,BorderLayout.NORTH);
		add(label,BorderLayout.CENTER);
		add(statusBar,BorderLayout.SOUTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}

}

class toolBar extends JToolBar
{
	JLabel frameLabel = new JLabel();
	JFrame frame = new JFrame();
	JButton button1 = new JButton(getIcon("coins"));
	JButton button2 = new JButton(getIcon("coins"));
	JButton button3 = new JButton(getIcon("coins"));
	JButton button4 = new JButton(getIcon("coins"));
	JButton button5 = new JButton(getIcon("coins"));
	static int number=0;
	public toolBar(final JLabel frameLabel,final JFrame frame)
	{
		this.frameLabel = frameLabel;
		this.frame=frame;
		add(button1);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(showConfirmDialog(frame,"select a option")==1) {
					frameLabel.setIcon(getIcon("1"));		
				}										
			}
			
		});
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		
	}
	
	public static int showConfirmDialog(Component parentComponent,Object message)throws HeadlessException
			 {
			final JDialog log = new JDialog((JFrame)parentComponent, (String)message, true);
			JButton yes = new JButton("YES");
			yes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					number =1;
					log.dispose();
				}
				
			});
			JButton no = new JButton("NO");
			JButton cancel = new JButton("Cancel");
			log.setBounds(100,100,300,300);
			log.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
			log.add(yes);log.add(no);log.add(cancel);
			log.setVisible(true);
	
				return number;
		
			 }
	private ImageIcon getIcon(String name) {
		return new ImageIcon(getClass().getResource("/images/" + name + ".png"));
	}
	
}
