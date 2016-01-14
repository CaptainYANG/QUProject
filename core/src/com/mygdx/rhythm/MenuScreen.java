package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by yuyin on 2016/1/13.
 */
public class MenuScreen implements Screen{
    RhythmGame rhythmGame;
    SpriteBatch batch;
    Table table = new Table();
    Stage stage = new Stage();
    private TextButton buttonPlay = new TextButton("Play", Assets.skin);
    private TextButton buttonSettings = new TextButton("Settings", Assets.skin);
    int time = 0;
    public MenuScreen(RhythmGame rhythmGame){
        this.rhythmGame = rhythmGame;
        batch = new SpriteBatch();
    }
    @Override
    public void show() {
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(rhythmGame));
            }
        });
        table.add(buttonPlay).size(stage.getWidth()*0.2f, stage.getHeight()*0.10f).padBottom(stage.getHeight()*0.04f).row();
        stage.addActor(table);
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
        this.dispose();
    }
}
