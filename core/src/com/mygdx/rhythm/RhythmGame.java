package com.mygdx.rhythm;

import com.badlogic.gdx.Game;


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
	public void setKnock(boolean knock){
		this.knock = knock;
	}
	public boolean isKnock() {
		return knock;
	}

	public float getLastUpdate() {
		return lastUpdate;
	}

	public float getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(float currentTime) {
		this.currentTime = currentTime;
	}

	public void setLastUpdate(float lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
