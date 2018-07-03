package client;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;

public class GUITestClient {

	public static void main(String[] args) {
		GameEngine GameEngin=new GameEngineImpl();
		new GameEngineCallbackGUI(GameEngin);
	}

}
 