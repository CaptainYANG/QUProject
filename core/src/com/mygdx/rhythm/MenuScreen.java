package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javafx.scene.control.ToggleButton;

/**
 * Created by yuyin on 2016/1/13.
 */
public class MenuScreen implements Screen{
    RhythmGame rhythmGame;
    MenuScreen menuScreen;
    SpriteBatch batch;
    boolean touchIsEnabled;
    Stage stage = new Stage();
    private TextButton recommend = new TextButton("Recommend", Assets.skin);
    private TextButton myMusic = new TextButton("My Music", Assets.skin);
    private TextButton listenToWorld = new TextButton("Listen to world", Assets.skin);
    private TextButton settings = new TextButton("Settings", Assets.skin);
    int time = 0;
    public MenuScreen(RhythmGame rhythmGame){
        this.rhythmGame = rhythmGame;
        this.menuScreen = this;
        batch = new SpriteBatch();
        touchIsEnabled = rhythmGame.isTouchIsEnabled();
    }
    @Override
    public void show() {
        recommend.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rhythmGame.setTouchIsEnabled(touchIsEnabled);
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(rhythmGame));
            }
        });
        myMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(rhythmGame));
            }
        });
        listenToWorld.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(rhythmGame));
            }
        });
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SettingScreen(rhythmGame));

            }
        });
        /**
         * layout of menu buttons
         */
        recommend.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        recommend.setPosition(stage.getWidth() * 0.3f, stage.getHeight() * 0.75f);
        myMusic.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        myMusic.setPosition(stage.getWidth() * 0.3f, stage.getHeight() * 0.6f);
        listenToWorld.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        listenToWorld.setPosition(stage.getWidth() * 0.3f, stage.getHeight() * 0.45f);
        settings.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        settings.setPosition(stage.getWidth() * 0.3f, stage.getHeight() * 0.3f);
        stage.addActor(recommend);
        stage.addActor(myMusic);
        stage.addActor(listenToWorld);
        stage.addActor(settings);
        InputMultiplexer impx = new InputMultiplexer();
        impx.addProcessor(stage);
        Gdx.input.setInputProcessor(impx);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9F,0.58F,0.1F,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        this.dispose();
    }
}
