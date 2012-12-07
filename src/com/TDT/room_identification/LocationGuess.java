package com.TDT.room_identification;

import java.util.Timer;
import java.util.TimerTask;

import com.TDT.room_identification.*;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager.WakeLock;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LocationGuess extends Activity {

	private boolean isRunning = false;
	private LocationGuessThread c = new LocationGuessThread();
	Timer timer = new Timer();
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_guess);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_location_guess, menu);
        return true;
    }
    
    
    protected void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                mHandler.obtainMessage(1).sendToTarget();
            }
        }, 0, 500);
    }
    
	public Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
        	if(isRunning) {
        		TextView statusText = (TextView) findViewById(R.id.CurrentLocationLabel);
        		statusText.setText(c.getBestRoom());
        	}
        }
	};
    public void startStopGuessing(View view) {
    	/* Handle the button logging (Start/Stop) */
    	Button editText = (Button) findViewById(R.id.StartStopGuessing);
    	TextView statusText = (TextView) findViewById(R.id.CurrentLocationLabel);
    	ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
    	
    	if(editText.getText().equals("Start Guessing")) {
    		c.prep(this);
    		new Thread(c).start();
    		pb.setVisibility(pb.VISIBLE);
    		isRunning = true;
    		Toast.makeText(LocationGuess.this,"Beginning Guessing Process...", Toast.LENGTH_SHORT).show();
    		statusText.setText("");
    		startTimer();
    		editText.setText("Stop Guessing");
    	} else if (editText.getText().equals("Stop Guessing")) {
    		isRunning = false;
    		c.stop();
    		pb.setVisibility(pb.INVISIBLE);
    		Toast.makeText(LocationGuess.this, "Stopping Guessing Process...", Toast.LENGTH_SHORT).show();
    		statusText.setText("(off)");
    		editText.setText("Start Guessing");
    	}
    }
}
