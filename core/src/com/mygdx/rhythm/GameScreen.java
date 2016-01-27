package com.mygdx.rhythm;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    int beatIndex = 0;
    float currentTime;
    Boolean touchIsenabled= true;
    public GameScreen(RhythmGame rhythmGame){
        this.rhythmGame = rhythmGame;
        batch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        beatTime = Assets.beatTime;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        generalUpadate();
        batch.begin();
        batch.draw(Assets.background, 0, 0, width, height);
        currentTime =  Assets.music.getPosition()*1000;
        rhythmGame.setCurrentTime(currentTime);
        if(Math.abs(currentTime-beatTime[beatIndex])<=200){
            batch.draw(Assets.hit, 10, 10);
            rhythmGame.setLastUpdate(beatTime[beatIndex]);
        }else if(currentTime-beatTime[beatIndex]>200){
            beatIndex++;
        }

        if(touchIsenabled){
            if(touch) {
                if (isLeft) {
                    batch.draw(Assets.sprite_left,(width-Assets.sprite_left.getWidth())/2,height/2-Assets.sprite_left.getHeight()/2);
                } else {
                    batch.draw(Assets.sprite_right,(width-Assets.sprite_right.getWidth())/2,height/2-Assets.sprite_right.getHeight()/2);
                }
            }else {
                batch.draw(Assets.sprite_corgi,(width-Assets.sprite_corgi.getWidth())/2,height/2-Assets.sprite_corgi.getHeight()/2);
            }

            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            touch = false;
        }else{
            if(rhythmGame.isKnock()){
                batch.draw(Assets.sprite_right,(width-Assets.sprite_right.getWidth())/2,height/2-Assets.sprite_right.getHeight()/2);
            }else {
                batch.draw(Assets.sprite_corgi, (width - Assets.sprite_corgi.getWidth()) / 2, height / 2 - Assets.sprite_corgi.getHeight() / 2);
            }

            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (rhythmGame.isKnock()){
                batch.draw(Assets.hit, 100, 100);
            }

        }
        if(time<1000){
            time++;
        }else{
            Assets.music.dispose();
        }
        batch.end();
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
        Assets.music.play();
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
