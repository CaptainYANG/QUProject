package com.mygdx.rhythm;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import java.io.File;


public class AndroidLauncher extends AndroidApplication implements SensorEventListener {

	private SensorManager senSensorManager;
	private Sensor senAccelerometer;
	private RhythmGame ourGame = new RhythmGame();
	private long lastUpdate = 0;
	private float last_x, last_y, last_z;
	private static final int SHAKE_THRESHOLD = 150;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(ourGame, config);
		senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

		// Get the device's sample rate and buffer size to enable low-latency Android audio output, if available.
		String samplerateString = null, buffersizeString = null;
		if (Build.VERSION.SDK_INT >= 17) {
			AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
			samplerateString = audioManager.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE);
			buffersizeString = audioManager.getProperty(AudioManager.PROPERTY_OUTPUT_FRAMES_PER_BUFFER);
		}
		if (samplerateString == null) samplerateString = "44100";
		if (buffersizeString == null) buffersizeString = "512";

		System.loadLibrary("MusicToOnset");
		float[] haveResults;

		File musicfile = new File("/assets/mylove.mp3");
		System.out.println(musicfile.getAbsolutePath());

		haveResults = MusicToOnset(musicfile.getAbsolutePath());
		//System.out.printf("bpm:"+haveResults[0] +",startMs:"+haveResults[1]);

		//System.loadLibrary("FrequencyDomain");
		//FrequencyDomain(Integer.parseInt(samplerateString), Integer.parseInt(buffersizeString));
	}



	private native float[] MusicToOnset(String music);

	//private native void FrequencyDomain(long samplerate, long buffersize);

	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {
		Sensor mySensor = sensorEvent.sensor;


		if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			float x = sensorEvent.values[0];
			float y = sensorEvent.values[1];
			float z = sensorEvent.values[2];
			long curTime = System.currentTimeMillis();

			if ((curTime - lastUpdate) > 20) {
				long diffTime = (curTime - lastUpdate);
				lastUpdate = curTime;

				float speed = Math.abs(z - last_z)/ diffTime * 10000;

				if (speed > SHAKE_THRESHOLD) {
//					Toast.makeText(AndroidLauncher.this, "knock",
//							Toast.LENGTH_LONG).show();
					ourGame.setKnock(true);

				}
				else {
					ourGame.setKnock(false);
				}

				//last_x = x;
				//last_y = y;
				last_z = z;
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	protected void onPause() {
		super.onPause();
		senSensorManager.unregisterListener(this);
	}

	protected void onResume() {
		super.onResume();
		senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}



}

