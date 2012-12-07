package com.TDT.room_identification;
import com.TDT.room_identification.*;

public class LocationGuessThread implements Runnable{
	RangeStruct rs = new RangeStruct();
	private boolean isRunning = false;
	
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning) {
			
		}
	}

	public void stop() {
		isRunning = false;
	}
	
	public boolean prep() {
		/* Will load the database file into memory */
		rs.loadFile();
		isRunning = true;
		return true;
	}
}
