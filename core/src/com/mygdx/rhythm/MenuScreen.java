package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import javafx.scene.control.ToggleButton;

/**
 * Created by yuyin on 2016/1/13.
 */
public class MenuScreen implements Screen{
    RhythmGame rhythmGame;
    MenuScreen menuScreen;
    SpriteBatch batch;


    boolean touchIsEnabled;
    final float buttonWidth = 0.3f;
    final float buttonPositionHeight = 0.3f;
    Stage stage = new Stage();
    private SpriteDrawable setting = new SpriteDrawable(Assets.setting_sprite);
    private SpriteDrawable localmusic = new SpriteDrawable(Assets.local_sprite);
    private SpriteDrawable recommendation = new SpriteDrawable(Assets.recommend_sprite);
    private ImageButton settings = new ImageButton(setting);
    private ImageButton myMusic = new ImageButton(localmusic);
    private ImageButton recommend = new ImageButton(recommendation);

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

                ((Game) Gdx.app.getApplicationListener()).setScreen(new SelectSongs(rhythmGame));

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
        recommend.setSize(stage.getWidth() * buttonWidth, stage.getWidth() * buttonWidth);
        recommend.setPosition(stage.getWidth() * 0.1f, stage.getHeight() * buttonPositionHeight);
        myMusic.setSize(stage.getWidth() * buttonWidth, stage.getWidth() * buttonWidth);
        myMusic.setPosition(stage.getWidth() * 0.3f, stage.getHeight() * buttonPositionHeight);
        settings.setSize(stage.getWidth() * buttonWidth, stage.getWidth() * buttonWidth);
        settings.setPosition(stage.getWidth() * 0.5f, stage.getHeight() * buttonPositionHeight);
        stage.addActor(recommend);
        stage.addActor(myMusic);
        stage.addActor(settings);
        InputMultiplexer impx = new InputMultiplexer();
        impx.addProcessor(stage);
        Gdx.input.setInputProcessor(impx);
        Gdx.input.setCatchBackKey(true);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9F, 0.58F, 0.1F, 0);
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
