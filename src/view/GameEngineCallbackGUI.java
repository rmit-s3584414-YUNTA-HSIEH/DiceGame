package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import controller.RollHouseActionListener;
import controller.RollPlayerActionListener;
import controller.SetBetViewListener;
import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.*;

public class GameEngineCallbackGUI implements GameEngineCallback{
	
	private JFrame  Fram;
	private JScrollPane scorll;
	private Container contentPane;
	private JList<String> playerList= new JList<>(); ;
	private GameEngine GameEngine;
	private JLabel statusBar=new JLabel("Welcome to Dice Game");
	private RollingPanel PlayerRollingPanel= new RollingPanel();
	private RollingPanel HouseRollingPanel= new RollingPanel();
	
	public GameEngineCallbackGUI(GameEngine GameEngine)
	{
		this.GameEngine=GameEngine;
		GameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		GameEngine.addGameEngineCallback(this);
		Fram = new JFrame("Dice Game");
		Fram.setBounds(200, 200, 800, 600);
		
		Fram.setJMenuBar(new MainMenu(this));
		
		contentPane=new Container();		
		contentPane.setLayout(new BorderLayout());
		
		JToolBar toolbar = new JToolBar();
		
		JButton placeBet = new JButton("Place Bet",getIcon("coins"));
		placeBet.addMouseListener(new SetBetViewListener(this,playerList));
		placeBet.setPreferredSize(new Dimension(100, 50));
		JButton rollPlayer = new JButton("Roll Player",getIcon("roll"));
		rollPlayer.addActionListener(new RollPlayerActionListener(this,playerList));
		rollPlayer.setPreferredSize(new Dimension(100, 50));
		JButton rollHouse = new JButton("Roll House & Result",getIcon("roll"));
		rollHouse.addActionListener(new RollHouseActionListener(this));
		rollHouse.setPreferredSize(new Dimension(100, 50));
		
		toolbar.add(placeBet);
		toolbar.addSeparator();
		toolbar.add(rollPlayer);
		toolbar.addSeparator();
		toolbar.add(rollHouse);
		toolbar.addSeparator();
		
		contentPane.add(toolbar,BorderLayout.NORTH);
		contentPane.add(statusBar,BorderLayout.SOUTH);
		Fram.add(contentPane);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Fram.setLocation(dim.width/2-Fram.getSize().width/2, dim.height/2-Fram.getSize().height/2);
		Fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Fram.setVisible(true);

	}
	
	public GameEngine getGameEngine()
	{
		return this.GameEngine;
	}
	
	public JFrame getFram()
	{
		return this.Fram;
	}
	
	public JLabel getStatus()
	{
		return this.statusBar;
	}
		
	private ImageIcon getIcon(String name) {
		return new ImageIcon(getClass().getResource("/images/" + name + ".png"));
	}
	
	public void NumOfPlayer ()
	{		
		DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<Player> players =(ArrayList<Player>)GameEngine.getAllPlayers();
        for(Player player:players) 
        {
        	listModel.addElement(player.toString()+", Bet="+player.getBet());
        }        
        //create the list
        playerList.setModel(listModel);
        playerList.getPreferredScrollableViewportSize();
        playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scorll =new JScrollPane(playerList);
        contentPane.add(playerList,BorderLayout.CENTER);
        contentPane.revalidate();
	}

	@Override
	public void intermediateResult(final Player player, final DicePair dicePair, GameEngine gameEngine) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				StringBuffer info=new StringBuffer();
				info.append("<html><body>");
				info.append("Player Name: "+player.getPlayerName()+" Rolling<br>");
				info.append("Dice 1: "+dicePair.getDice1()+"<br>");
				info.append("Dice 2: "+dicePair.getDice2()+"<br>");
				info.append("Total: "+(dicePair.getDice1()+dicePair.getDice2())+"<br>");
				info.append("</body></html>");
				PlayerRollingPanel.getLabel().setText(info.toString());
				PlayerRollingPanel.getDice1().setIcon(getIcon(Integer.toString(dicePair.getDice1())));
				PlayerRollingPanel.getDice2().setIcon(getIcon(Integer.toString(dicePair.getDice2())));
				PlayerRollingPanel.setVisible(true);
			}			
		});		
	}

	@Override
	public void result(final Player player, final DicePair result, GameEngine gameEngine) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				StringBuffer info=new StringBuffer();
				info.append("<html><body>");
				info.append("Player Name: "+player.getPlayerName()+"<br>");
				info.append("Dice 1: "+result.getDice1()+"<br>");
				info.append("Dice 2: "+result.getDice2()+"<br>");
				info.append("Result: "+(result.getDice1()+result.getDice2())+"<br>");
				info.append("</body></html>");
				PlayerRollingPanel.getLabel().setText(info.toString());
				PlayerRollingPanel.getDice1().setIcon(getIcon(Integer.toString(result.getDice1())));
				PlayerRollingPanel.getDice2().setIcon(getIcon(Integer.toString(result.getDice2())));
				PlayerRollingPanel.setVisible(true);
			}			
		});		
	}

	@Override
	public void intermediateHouseResult(final DicePair dicePair, GameEngine gameEngine) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				StringBuffer info=new StringBuffer();
				info.append("<html><body>");
				info.append("House Rolling<br>");
				info.append("Dice 1: "+dicePair.getDice1()+"<br>");
				info.append("Dice 2: "+dicePair.getDice2()+"<br>");
				info.append("Total: "+(dicePair.getDice1()+dicePair.getDice2())+"<br>");
				info.append("</body></html>");
				HouseRollingPanel.getLabel().setText(info.toString());
				HouseRollingPanel.getDice1().setIcon(getIcon(Integer.toString(dicePair.getDice1())));
				HouseRollingPanel.getDice2().setIcon(getIcon(Integer.toString(dicePair.getDice2())));
				HouseRollingPanel.setVisible(true);
			}
			
		});
	}

	@Override
	public void houseResult(final DicePair result, final GameEngine gameEngine) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				int houseTotal=result.getDice1()+result.getDice2();
				StringBuffer info=new StringBuffer();
				info.append("<html><body>");
				info.append("Dice 1: "+result.getDice1()+"<br>");
				info.append("Dice 2: "+result.getDice2()+"<br>");
				info.append("House Result: "+houseTotal+"<br>");
				for(Player player:gameEngine.getAllPlayers()){
					if(player.getBet()>0&&player.getRollResult()!=null){
						int playerTotal=player.getRollResult().getDice1()+player.getRollResult().getDice2();
						info.append(player.getPlayerName()+" Result: "+playerTotal);
						if(playerTotal>houseTotal){
							info.append(" win");
						}else if(playerTotal<houseTotal){
							info.append(" lose");
						}else{
							info.append(" tail");
						}
						info.append("<br>");
					}
				}
				info.append("</body></html>");
				HouseRollingPanel.getLabel().setText(info.toString());
				HouseRollingPanel.getDice1().setIcon(getIcon(Integer.toString(result.getDice1())));
				HouseRollingPanel.getDice2().setIcon(getIcon(Integer.toString(result.getDice2())));
				HouseRollingPanel.setVisible(true);
				HouseRollingPanel.pack();
				NumOfPlayer();
			}			
		});	
	}
}
