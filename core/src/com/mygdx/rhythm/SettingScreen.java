package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import javafx.scene.control.ToggleButton;

/**
 * Created by yuyin on 2016/1/27.
 */
public class SettingScreen implements Screen{
    MenuScreen menuScreen;
    private Stage stage = new Stage();
    private SpriteDrawable buttonOn = new SpriteDrawable(Assets.sprite_buttonOn);
    private SpriteDrawable buttonOff = new SpriteDrawable(Assets.sprite_buttonOff);
    private ImageButton touchEnable = new ImageButton(buttonOn);
    private TextButton ok = new TextButton("OK", Assets.skin);
    private Label enableTouch = new Label("Use touch to play", Assets.skin);
    private boolean touch = true;

    public SettingScreen(MenuScreen menuScreen) {
        this.menuScreen = menuScreen;
    }

    @Override
    public void show() {
        touchEnable.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(touch){
                    touchEnable.setBackground(buttonOff);
                    touch = false;
                }else{
                    touchEnable.setBackground(buttonOn);
                    touch = true;
                }
            }
        });
        ok.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.touchIsEnabled = touch;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new SettingScreen(menuScreen));
            }
        });
        touchEnable.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        touchEnable.setPosition(stage.getWidth() * 0.4f, stage.getHeight() * 0.50f);
        enableTouch.setPosition(stage.getWidth() * 0.2f, stage.getHeight() * 0.50f);
        ok.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        ok.setPosition(stage.getWidth() * 0.3f, stage.getHeight() * 0.50f);
        stage.addActor(enableTouch);
        stage.addActor(touchEnable);
        stage.addActor(ok);
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
