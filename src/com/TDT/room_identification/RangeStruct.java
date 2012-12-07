package com.TDT.room_identification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import au.com.bytecode.opencsv.*;

public class RangeStruct {
	Map<String, Object> ranges = new HashMap<String, Object>();
	private Context context;
	
	private void addToHash(String room, String wifi, int low, int high) {
		if(!ranges.containsKey(room)) {
			ranges.put(room, new HashMap<Object, Object>());
		}
		HashMap<String, int[]> hashMap = (HashMap<String, int[]>) ranges.get(room);
		HashMap<String, int[]> room_struct = hashMap;
		int[] ranges = {low,high};
		room_struct.put(wifi, ranges);
	}
	
	public void printMap() {
	    Iterator it = ranges.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        Log.v("WARNING", pairs.getKey().toString());
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	public void classifyLocation(ArrayList<String[]> cur_values) {
		Iterator room_iterator = ranges.entrySet().iterator();
		while (room_iterator.hasNext()) {
			Map.Entry pairs = (Map.Entry)room_iterator.next();
			String room = pairs.getKey().toString();
			int num_in_bucket = 0;
			
			HashMap wifi_map = (HashMap) pairs.getValue();
			Iterator wifi_iterator = wifi_map.entrySet().iterator();
			while(wifi_iterator.hasNext()) {
				String mac_addr = pairs.getKey().toString();
				int[] levels = (int[]) pairs.getValue();
			}
		}
	}
	
	public boolean loadFile () {
		CSVReader reader;
		String Room;  // index 0
		String wifi;  // index 1
		int low, high; // index 2,3
		ranges.clear();
		try {
			reader = new CSVReader(new FileReader(Environment.getExternalStorageDirectory().toString() + File.separator + "ranges.csv"));
			
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Log.v("WARNING", "In nextLine");
				Room = nextLine[0];
				wifi = nextLine[1];
				low = Integer.valueOf(nextLine[2]);
				high = Integer.valueOf(nextLine[3]);
				addToHash(Room,wifi,low,high);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printMap();
		return true;
	}
}
