package com.TDT.room_identification;

public class LocationGuessThread implements Runnable{
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
		isRunning = true;
		return true;
	}
}
