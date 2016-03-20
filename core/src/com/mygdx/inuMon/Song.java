package com.mygdx.inuMon;

import com.badlogic.gdx.audio.Music;

import java.util.Random;

/**
 * Created by sushi on 14/02/16.
 */
public class Song {

    private int bmp;
    private int startMs;
    private int score;
    private int totalscore;
    private int performance;
    private Music song;
    private int[] onset;
    private int[] direction;

    public Song(Music song, int bmp, int startMs){
        this.song = song;
        this.bmp = bmp;
        this.startMs = startMs;
    };
    public Song(Music song, int[] onset){
        this.song = song;
        this.onset = onset;
    };
    //add i point to score
    public void addscore(){
        this.score++;

    };

    //setup game points
    public int[] getonset(){
        if(onset!=null){
            return this.onset;
        }else {
            onset = new int[60];
            onset[0] = this.startMs;
            int step = 60000 / this.bmp;
            for (int i = 1; i < 60; i++) {
                onset[i] = onset[i - 1] + 2 * step;
            }
            return onset;
        }
    };
    public int[] getHitDirection(){
        Random random = new Random();
        direction = new int[60];
        for (int i=0; i<60; i++){
            direction[i] = random.nextInt(2);
        }
        return direction;
    }

    //calculate how many stars get in game
    public void calcstat(){
        if (this.score/this.totalscore < 0.3){
            this.performance = 1;
        }else if(this.score/this.totalscore < 0.6){
            this.performance = 2;
        }else{
            this.performance = 3;
        }
    };

    //return the performance in star number
    public int getPerformance(){
        return this.performance;
    };


    public Music getSong(){
        return this.song;
    };

    public int getBmp(){ return this.bmp;};

    public int getStartMs() {return this.startMs;};

    public int getscore(){
        return this.score;
    }

    public void resetscore(){
        this.score = 0;
    }

}
