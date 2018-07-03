package model;

import model.interfaces.DicePair;

public class DicePairImpl implements DicePair{
	
	final private int dice1;
	final private int dice2;
	final private int numFaces;
	
	public DicePairImpl(int dice1, int dice2, int numFaces) {
		//check the value of dice is less than or equal numFaces
		this.dice1=dice1;
		this.dice2=dice2;
		this.numFaces=numFaces;
	}

	@Override
	public int getDice1() {
		return dice1;
	}

	@Override
	public int getDice2() {
		return dice2;
	}

	@Override
	public int getNumFaces() {
		return numFaces;
	}
	@Override
	public String toString() {
		return "Dice 1: "+dice1+",  Dice 2:"+dice2+" .. Total: "+(dice1+dice2);
	}

}
