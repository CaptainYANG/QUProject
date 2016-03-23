package com.mygdx.inuMon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by yuyin on 2016/1/27.
 */
public class GuideScreen2 implements Screen{
    RhythmGame rhythmGame;
    private Stage stage = new Stage();
    private SpriteBatch batch = new SpriteBatch();
    private SpriteDrawable buttonOn = new SpriteDrawable(Assets.sprite_buttonOn);
    private SpriteDrawable buttonOff = new SpriteDrawable(Assets.sprite_buttonOff);
    private ImageButton touchEnable = new ImageButton(buttonOn,buttonOff,buttonOff);
    private SpriteDrawable okbutton = new SpriteDrawable(Assets.sprite_okbutton);
    private ImageButton ok = new ImageButton(okbutton);
    private BitmapFont settingfont = new BitmapFont();
    private boolean touch = true;
    int height;
    int width;

    public GuideScreen2(RhythmGame rhythmGame) {
        this.rhythmGame = rhythmGame;
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
    }

    @Override
    public void show() {

        ok.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(rhythmGame));
            }
        });

        ok.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        ok.setPosition(stage.getWidth() * 0.3f, stage.getHeight() * 0.05f);
        stage.addActor(ok);
        InputMultiplexer impx = new InputMultiplexer();
        impx.addProcessor(stage);
        Gdx.input.setInputProcessor(impx);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9F, 0.58F, 0.1F, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Assets.guide1, 0, 0, width, height);
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
