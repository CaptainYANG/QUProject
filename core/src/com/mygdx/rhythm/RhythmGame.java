package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;


public class RhythmGame extends Game {
	MenuScreen menuScreen;
	float lastUpdate = 0;
	float currentTime = 0;
	boolean knock = false;
	boolean touchIsEnabled = true;
	Songs song ;

	public void setTouchIsEnabled(boolean touchIsEnabled) {
		this.touchIsEnabled = touchIsEnabled;
	}

	public boolean isTouchIsEnabled() {
		return touchIsEnabled;
	}

	@Override
	public void create () {
		Assets.load();
		this.song = Assets.recommendSong;
		Gdx.input.setCatchBackKey(true);
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
