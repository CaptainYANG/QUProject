package com.mygdx.inuMon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by sushi on 16/02/16.
 */
public class SelectSongs implements Screen {
    private Stage stage = new Stage();

    RhythmGame rhythmGame;
    SelectSongs selectscreen;
    SpriteBatch batch;

    int width;
    int height;
    private Table table;
    private Boolean tableflag;
    private Boolean isPaused;

    public SelectSongs(RhythmGame rhythmGame){
        this.rhythmGame = rhythmGame;
        this.selectscreen = this;
        batch = new SpriteBatch();

        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        tableflag = false;
        isPaused = false;
    }

    @Override
    public void show() {

        TextButton button1 = new TextButton("YCJX", Assets.skin);
        TextButton button2 = new TextButton("my love", Assets.skin);
        TextButton button3 = new TextButton("Back", Assets.skin);


        button1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                rhythmGame.song = Assets.song0;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new com.mygdx.inuMon.GameScreen(rhythmGame));
            }
        });

        button2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                rhythmGame.song = Assets.song1;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new com.mygdx.inuMon.GameScreen(rhythmGame));
            }
        });
        button3.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(rhythmGame));
            }
        });

        button1.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        button2.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        button3.setSize(stage.getWidth() * 0.4f, stage.getHeight() * 0.10f);
        table = new Table();
        table.setPosition(width/2,height/2);
        table.top().center();
        //table.background((Drawable) Assets.table);
        table.row().height(70);
        table.add(button1).width(300).pad(10);
        //table.add(nameText).width(100);
        table.row().height(70);
        table.add(button2).width(300).pad(10);
        //table.add(addressText).width(100);
        table.setVisible(true);
        //table.setPosition(50, stage.getHeight());
        table.row().height(70);
        table.add(button3).width(300).pad(10);


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
        batch.begin();
        batch.draw(Assets.backgroundSetting, 0, 0, width, height);
        batch.end();
        stage.act();
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(rhythmGame));
        }


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

    }
}
