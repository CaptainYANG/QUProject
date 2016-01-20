package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class RhythmGame extends Game {
	MenuScreen menuScreen;
	float lastUpdate = 0;
	float currentTime = 0;
	boolean knock = false;

	@Override
	public void create () {
		Assets.load();
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen) ;
	}


	public boolean hasKnocked () {
		//currentTime = System.currentTimeMillis();
		if (Math.abs(currentTime - lastUpdate)<400){
			if (knock) {return true;}
		}

		return false;
	}
}
