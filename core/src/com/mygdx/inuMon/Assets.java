package com.mygdx.inuMon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by yuyin on 2016/1/12.
 */
public class Assets {
    public static Texture texture_corgi;
    public static Texture texture_left;
    public static Texture texture_right;
    public static Sprite sprite_corgi;
    public static Sprite sprite_left;
    public static Sprite sprite_right;
    public static Texture buttonOn;
    public static Texture buttonOff;
    public static Texture table;
    public static Skin skin;
    public static Texture background;
    public static Texture backgroundMenu;
    public static Texture backgroundSetting;
    public static Texture gamename;
    public static Texture hit;
    public static Sprite sprite_back;
    public static Sprite sprite_hit;
    public static Music music;
    public static String[] beatInfo;
    public static int[] beatTime;
    public static Sprite sprite_buttonOn;
    public static Sprite sprite_buttonOff;
    public static Texture resume;
    public static Sprite sprite_resume;
    public static Texture home;
    public static Sprite sprite_home;
    public static Texture pause;
    public static Sprite sprite_pause;
    public static Texture again;
    public static Sprite sprite_again;
    public static Texture recommend;
    public static Sprite recommend_sprite;
    public static Texture setting;
    public static Sprite setting_sprite;
    public static Texture local;
    public static Sprite local_sprite;
    public static Music music0;
    public static Song song0;
    public static Song song1;
    public static Song recommendSong;
    public static Sprite sakura1;
    public static Sprite sakura2;
    public static Sprite sakura3;
    public static Sprite sakura4;
    public static Sprite sakura5;
    ///test from here

    public static void load(){
        texture_corgi = new Texture(Gdx.files.internal("midCorgi.png"));
        texture_left = new Texture(Gdx.files.internal("leftCorgi.png"));
        texture_right = new Texture(Gdx.files.internal("rightCorgi.png"));
        buttonOn = new Texture(Gdx.files.internal("buttonOn.png"));
        buttonOff = new Texture(Gdx.files.internal("buttonOff.png"));
        table = new Texture(Gdx.files.internal("table.png"));
        background = new Texture(Gdx.files.internal("backgroundSetting.png"));
        backgroundMenu = new Texture(Gdx.files.internal("backgroundMenu.jpg"));
        backgroundSetting = new Texture(Gdx.files.internal("backgroundSetting.png"));
        gamename = new Texture(Gdx.files.internal("gamename.gif"));
        hit = new Texture(Gdx.files.internal("hit.png"));
        sprite_corgi = new Sprite(texture_corgi);
        sprite_left = new Sprite(texture_left);
        sprite_right = new Sprite(texture_right);
        sprite_back = new Sprite(background);
        sprite_hit = new Sprite(hit);
        sprite_buttonOn = new Sprite(buttonOn);
        sprite_buttonOff = new Sprite(buttonOff);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        music = Gdx.audio.newMusic(Gdx.files.internal("mylove.mp3"));
        music0 = Gdx.audio.newMusic(Gdx.files.internal("YCJZ.mp3"));
        song0 = new Song(music0, 124, 3000);
        song1 = new Song(music, 60, 3000);
        ////test from here
        beatInfo = Gdx.files.internal("mylovenormal.txt").readString().split(System.getProperty("line.separator"));
        beatTime = new int[beatInfo.length];
        beatTime = getBeatTime(beatInfo);
        recommendSong = new Song(music,beatTime);
        resume =new Texture(Gdx.files.internal("resume.png"));
        sprite_resume = new Sprite(resume);
        pause =new Texture(Gdx.files.internal("pause.png"));
        sprite_pause = new Sprite(pause);
        home =new Texture(Gdx.files.internal("home.png"));
        sprite_home = new Sprite(home);
        again =new Texture(Gdx.files.internal("again.png"));
        sprite_again = new Sprite(again);
        sakura1 = new Sprite(new Texture(Gdx.files.internal("sakura1.png")));
        sakura2 = new Sprite(new Texture(Gdx.files.internal("sakura2.png")));
        sakura3 = new Sprite(new Texture(Gdx.files.internal("sakura3.png")));
        sakura4 = new Sprite(new Texture(Gdx.files.internal("sakura4.png")));
        sakura5 = new Sprite(new Texture(Gdx.files.internal("sakura5.png")));

        recommend = new Texture(Gdx.files.internal("recommendation.png"));
        recommend_sprite = new Sprite(recommend);
        setting = new Texture(Gdx.files.internal("setting.png"));
        setting_sprite = new Sprite(setting);
        local = new Texture(Gdx.files.internal("localmusic.png"));
        local_sprite= new Sprite(local);
    }
    public static int[] getBeatTime(String[] beatInfo){
        String[] temp;
        for(int i=0;i<beatInfo.length;i++){
            temp = beatInfo[i].split(",");
            beatTime[i] = Integer.parseInt(temp[2]);
        }
        return beatTime;
    }
}
