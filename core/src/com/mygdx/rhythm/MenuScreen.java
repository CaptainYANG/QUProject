package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by yuyin on 2016/1/13.
 */
public class MenuScreen implements Screen{
    RhythmGame rhythmGame;
    MenuScreen menuScreen;
    private SpriteBatch batch;
    int height;
    int width;


    boolean touchIsEnabled;
    final float buttonWidth = 0.5f;
    final float buttonHeight = 0.2f;
    final float buttonPositionWidth = 0.3f;
    final float titleWidth=0.6f;
    final float titleHeight=0.3f;
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
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();

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
        recommend.setSize(stage.getWidth() * buttonWidth, stage.getHeight() * buttonHeight);
        recommend.setPosition(stage.getWidth() * buttonPositionWidth, stage.getHeight()*0.5f);
        myMusic.setSize(stage.getWidth() * buttonWidth, stage.getHeight() * buttonHeight);
        myMusic.setPosition(stage.getWidth() * buttonPositionWidth, stage.getHeight()*0.35f);
        settings.setSize(stage.getWidth() * buttonWidth, stage.getHeight() * buttonHeight);
        settings.setPosition(stage.getWidth() * buttonPositionWidth, stage.getHeight()*0.2f);
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
        batch.begin();
        batch.draw(Assets.backgroundMenu, 0, 0, width, height);
        batch.draw(Assets.gamename,stage.getWidth()*0.2f,stage.getHeight()*0.7f,width*titleWidth,height*titleHeight);
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
