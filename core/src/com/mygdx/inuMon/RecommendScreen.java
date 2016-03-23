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
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
/**
 * Created by yuyin on 2016/1/27.
 */
public class RecommendScreen implements Screen{
    private Stage stage = new Stage();

    RhythmGame rhythmGame;
    RecommendScreen recommendScreen;
    SpriteBatch batch;

    int width;
    int height;
    private Table table;
    private Boolean tableflag;
    private Boolean isPaused;

    private SpriteDrawable songlist1 = new SpriteDrawable(Assets.sprite_songlist1);
    private SpriteDrawable songlist2 = new SpriteDrawable(Assets.sprite_songlist2);
    private SpriteDrawable songlist3 = new SpriteDrawable(Assets.sprite_songlist3);
    private ImageButton songlist_1 = new ImageButton(songlist1);
    private ImageButton songlist_2 = new ImageButton(songlist2);
    private ImageButton songlist_3 = new ImageButton(songlist3);



    public RecommendScreen(RhythmGame rhythmGame){
        this.rhythmGame = rhythmGame;
        this.recommendScreen = this;
        batch = new SpriteBatch();

        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        tableflag = false;
        isPaused = false;
    }

    @Override
    public void show() {

       /* TextButton button1 = new TextButton("Sakura", Assets.skin);
        TextButton button2 = new TextButton("my love", Assets.skin);
        TextButton button3 = new TextButton("I'm happy", Assets.skin);
        */


       songlist_1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                rhythmGame.song = Assets.sakura;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(rhythmGame));
            }
        });

        songlist_2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                rhythmGame.song = Assets.myLove;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(rhythmGame));
            }
        });
        songlist_3.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                rhythmGame.song = Assets.imHappy;
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(rhythmGame));
            }
        });

        songlist_1.setSize(stage.getWidth() , stage.getHeight() );
        songlist_2.setSize(stage.getWidth() , stage.getHeight() );
        songlist_3.setSize(stage.getWidth() , stage.getHeight() );

        table = new Table();
        table.setPosition(width/2,height/2);
        table.top().center();
        //table.background((Drawable) Assets.table);
        table.row().height(150);
        table.add(songlist_1).width(600).pad(30);
        //table.add(nameText).width(100);
        table.row().height(150);
        table.add(songlist_2).width(600).pad(30);
        //table.add(addressText).width(100);
        table.setVisible(true);
        //table.setPosition(50, stage.getHeight());
        table.row().height(150);
        table.add(songlist_3).width(600).pad(30);


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
