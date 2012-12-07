package com.TDT.room_identification;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.util.Log;

import com.TDT.room_identification.*;

public class LocationGuessThread implements Runnable{
	RangeStruct rs = new RangeStruct();
	private boolean isRunning = false;

	private WifiManager m_wifiManager;
	private WifiInfo m_wifiInfo;
	private WifiLock m_wifiLock;
	private List<WifiConfiguration> m_wifiConfigList;
	private List<ScanResult> m_wifiList;
	private long m_timeStamp;
	private List<ScanResult> m_wholeList;
	
	private String BestRoomGuess = "(off)";
	
	public void run() {
		// TODO Auto-generated method stub
		while(isRunning) {	
			try {
				guessLocation();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getBestRoom() {
		return BestRoomGuess;
	}
	
	public void stop() {
		isRunning = false;
		BestRoomGuess = "(off)";
	}
	
	public boolean prep(Context context) {
		m_wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		m_wifiList=new ArrayList<ScanResult>();
		rs.loadFile();
		isRunning = true;
		return true;
	}
	
	private ArrayList<String[]> getScanningResults () {
		ArrayList<String[]> scanning_results = new ArrayList<String[]>();
		if(m_wifiManager.startScan())
		{
			m_wholeList=m_wifiManager.getScanResults();
			if(m_wholeList!=null) 
			{	for(int i=0;i<m_wholeList.size();i++)
				{
					if (!m_wholeList.get(i).SSID.equals("eduroam"))
						continue;
					String[] lresults = {m_wholeList.get(i).BSSID,
								String.valueOf(m_wholeList.get(i).level)};
					scanning_results.add(lresults);
					
				}
			}
			m_wifiConfigList=m_wifiManager.getConfiguredNetworks();	
		}
		return scanning_results;
	}
	
	private void guessLocation() {
		ArrayList<String[]> cur_location = getScanningResults();
		BestRoomGuess = rs.classifyLocation(cur_location);
	}
}
