package com.TDT.room_identification;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import au.com.bytecode.opencsv.*;

public class RangeStruct {
	
	public boolean loadFile () {
		CSVReader reader;
		String Room;  // index 0
		String wifi;  // index 1
		int low, high; // index 2,3
		
		try {
			reader = new CSVReader(new FileReader("res/raw/ranges.csv"), '\t');
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				Room = nextLine[0];
				wifi = nextLine[1];
				low = Integer.valueOf(nextLine[2]);
				high = Integer.valueOf(nextLine[3]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
