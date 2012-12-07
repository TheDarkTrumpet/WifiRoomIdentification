package com.TDT.room_identification;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LocationGuess extends Activity {

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
}
