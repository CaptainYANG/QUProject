package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RhythmGame extends Game {
	MenuScreen menuScreen;
	@Override
	public void create () {
		Assets.load();
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen) ;
	}
}
