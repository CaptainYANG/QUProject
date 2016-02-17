package com.mygdx.rhythm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by yuyin on 2016/1/12.
 */
public class GameScreen implements Screen{
    RhythmGame rhythmGame;
    SpriteBatch batch;
    Boolean touch = false;
    int time = 0;
    Boolean isLeft;
    int height;
    int width;
    int[] beatTime;
    int[] hitdirection;
    int beatIndex = 0;
    float currentTime;
    Boolean isPaused = false;
    Boolean touchIsenabled= true;
    Stage stage = new Stage();
    SpriteDrawable resumeDrawable = new SpriteDrawable(Assets.sprite_resume);
    SpriteDrawable pauseDrawable = new SpriteDrawable(Assets.sprite_pause);
    SpriteDrawable homeDrawable = new SpriteDrawable(Assets.sprite_home);
    SpriteDrawable againDrawable = new SpriteDrawable(Assets.sprite_again);
    ImageButton resumeButton = new ImageButton(resumeDrawable);
    ImageButton pauseButton = new ImageButton(pauseDrawable);
    ImageButton homeButton = new ImageButton(homeDrawable);
    ImageButton againButton = new ImageButton(againDrawable);
    final static int sleepTime = 50;
    Boolean added=false;


    private String score;

    BitmapFont scorefont;
    private Songs song;

    //here play from the new obj songs


    public GameScreen(RhythmGame rhythmGame){
        this.rhythmGame = rhythmGame;
        this.song = rhythmGame.song;
        this.song.resetscore();
        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        beatTime = this.song.getonset();
        hitdirection = this.song.getHitDirection();
        touchIsenabled = rhythmGame.isTouchIsEnabled();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        generalUpadate();
        batch.begin();
        batch.draw(Assets.background, 0, 0, width, height);
        scorefont.setColor(Color.BLACK);
        scorefont.getData().setScale(3,3);
        scorefont.draw(batch, score, width-200, height-30);

        if(!isPaused) {
            if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
                isPaused=true;
                this.pause();
            }
        currentTime =  song.getSong().getPosition()*1000;
        rhythmGame.setCurrentTime(currentTime);
        if(beatIndex<song.getonset().length&&Math.abs(currentTime-beatTime[beatIndex])<=150){
            if (hitdirection[beatIndex]==0){
                batch.draw(Assets.hit, 10, 10);
                //Todo:do sth here to add animation to notes
                if(touchIsenabled){
                    if (touch){
                        batch.draw(Assets.hit, 10, 100);
                        if(added == false) {
                            song.addscore();
                            score = "score: "+song.getscore();
                            added = true;
                        }
                    }else {
                        added=false;
                    }
                }else{
                    if (rhythmGame.isKnock()) {
                        batch.draw(Assets.hit, 10, 100);
                        if(added == false) {
                            song.addscore();
                            score = "score: "+song.getscore();
                            added = true;
                        }
                    }else {
                        added=false;
                    }
                }
            }else {
                batch.draw(Assets.hit, width*0.6f, 10);
                //Todo:do sth here to add animation to notes
                if(touchIsenabled){
                    if (touch){
                        batch.draw(Assets.hit, width*0.6f, 100);
                        if(added == false) {
                            song.addscore();
                            score = "score: "+song.getscore();
                            added = true;
                        }
                    }else {
                        added=false;
                    }
                }else{
                    if (rhythmGame.isKnock()) {
                        batch.draw(Assets.hit, 10, 100);
                        if(added == false) {
                            song.addscore();
                            score = "score: "+song.getscore();
                            added = true;
                        }
                    }else {
                        added=false;
                    }
                }
            }
            rhythmGame.setLastUpdate(beatTime[beatIndex]);
        }else if(beatIndex<song.getonset().length&&currentTime-beatTime[beatIndex]>150){
            beatIndex++;
        }
            if (touchIsenabled) {
                if (touch) {
                    if (isLeft) {
                        batch.draw(Assets.sprite_left, (width - Assets.sprite_left.getWidth()) / 2, height / 2 - Assets.sprite_left.getHeight() / 2);
                    } else {
                        batch.draw(Assets.sprite_right, (width - Assets.sprite_right.getWidth()) / 2, height / 2 - Assets.sprite_right.getHeight() / 2);
                    }
                } else {
                    batch.draw(Assets.sprite_corgi, (width - Assets.sprite_corgi.getWidth()) / 2, height / 2 - Assets.sprite_corgi.getHeight() / 2);
                }
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                touch = false;
            } else {
                if (rhythmGame.isKnock()) {
                    if(beatIndex<hitdirection.length&&hitdirection[beatIndex]==0){
                        batch.draw(Assets.sprite_left, (width - Assets.sprite_left.getWidth()) / 2, height / 2 - Assets.sprite_right.getHeight() / 2);
                    }else {
                        batch.draw(Assets.sprite_right, (width - Assets.sprite_right.getWidth()) / 2, height / 2 - Assets.sprite_right.getHeight() / 2);
                    }
                } else {
                    batch.draw(Assets.sprite_corgi, (width - Assets.sprite_corgi.getWidth()) / 2, height / 2 - Assets.sprite_corgi.getHeight() / 2);
                }
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if(time<500){
                time++;
            }else{
                gameover();
                this.pause();

                batch.draw(Assets.table, 100, 100, width - 200, height - 200);
                score = "score: "+song.getscore()+" / " + song.getonset().length;
                scorefont.draw(batch, score, 2*width/5, 2*height/3);
            }

            if(beatIndex == this.song.getonset().length){
                gameover();
                batch.draw(Assets.table, 100, 100, width - 200, height - 200);
                score = "score: "+song.getscore()+" / " + song.getonset().length;
                scorefont.draw(batch, score, 2*width/5, 2*height/3);

            }

        }else{
            batch.draw(Assets.sprite_corgi, (width - Assets.sprite_corgi.getWidth()) / 2, height / 2 - Assets.sprite_corgi.getHeight() / 2);
            pauseButton.setVisible(false);
            resumeButton.setPosition(0, stage.getHeight() - 150);
            stage.addActor(resumeButton);

            homeButton.setPosition(resumeButton.getWidth() + 10, stage.getHeight() - 150);
            stage.addActor(homeButton);

            if(resumeButton.isPressed()){
                isPaused = false;
                song.getSong().play();
                pauseButton.setVisible(true);
                resumeButton.remove();
                homeButton.remove();
            }
            if(homeButton.isPressed()){
                song.getSong().stop();
                homeButton.remove();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(rhythmGame));
            }


        }
        batch.end();
        stage.act();
        stage.draw();
    }
    public boolean beatShow(float currentMusicTime,int currentIndex){
        boolean show = false;
        if(Math.abs(currentMusicTime-beatTime[currentIndex])<200){
            show = true;
        }else if((currentMusicTime-beatTime[currentIndex])>200){
            currentIndex++;
        }
        return show;

    }
    public void generalUpadate(){
        if(Gdx.input.isTouched()){
            touch = true;
            if(Gdx.input.getX()<Gdx.graphics.getWidth()/2){
                isLeft = true;
            }else{
                isLeft = false;
            }
        }
    }
    @Override
    public void show() {
        InputMultiplexer impx = new InputMultiplexer();
        impx.addProcessor(stage);
        Gdx.input.setInputProcessor(impx);
        Gdx.input.setCatchBackKey(true);
        pauseButton.setPosition(0, height - 150);
        stage.addActor(pauseButton);
        pauseButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                isPaused = true;
                pause();
            }
        });

        stage.addActor(pauseButton);

        score = "score: 0";
        scorefont = new BitmapFont();

        this.song.getSong().play();
    }


    @Override
    public void resize(int width, int height) {

    }

    public void gameover(){
        song.getSong().stop();
        pauseButton.setVisible(false);
        resumeButton.setVisible(false);
        againButton.setPosition(width/3,height/3);
        homeButton.setPosition(3*width/5,height/3);

        stage.addActor(againButton);
        stage.addActor(homeButton);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(rhythmGame));
        }
        if(againButton.isPressed()){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(rhythmGame));

        };

        if(homeButton.isPressed()){
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(rhythmGame));
        }



    }

    @Override
    public void pause() {
        song.getSong().pause();
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
