package com.mygdx.inuMon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by yuyin on 2016/1/27.
 */
public class SettingScreen implements Screen{
    RhythmGame rhythmGame;
    private Stage stage = new Stage();
    private SpriteBatch batch = new SpriteBatch();
    private SpriteDrawable buttonOn = new SpriteDrawable(Assets.sprite_buttonOn);
    private SpriteDrawable buttonOff = new SpriteDrawable(Assets.sprite_buttonOff);
    private ImageButton touchEnable = new ImageButton(buttonOn,buttonOff,buttonOff);
    private TextButton ok = new TextButton("OK", Assets.skin);
    private BitmapFont settingfont = new BitmapFont();
    private boolean touch = true;

    public SettingScreen(RhythmGame rhythmGame) {
        this.rhythmGame = rhythmGame;
    }

    @Override
    public void show() {
        touchEnable.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (touch) {
                    touch = false;
                } else {
                    touch = true;
                }
            }
        });
        ok.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                rhythmGame.setTouchIsEnabled(touch);
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(rhythmGame));
            }
        });
        touchEnable.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        touchEnable.setPosition(stage.getWidth() * 0.4f, stage.getHeight() * 0.50f);
        ok.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        ok.setPosition(stage.getWidth() * 0.3f, stage.getHeight() * 0.20f);
        stage.addActor(touchEnable);
        stage.addActor(ok);
        InputMultiplexer impx = new InputMultiplexer();
        impx.addProcessor(stage);
        Gdx.input.setInputProcessor(impx);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9F,0.58F,0.1F,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        settingfont.setColor(Color.WHITE);
        settingfont.getData().setScale(3,3);
        settingfont.draw(batch, "Play mode", stage.getWidth() * 0.3f, stage.getHeight() * 0.6f);
        batch.end();
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
