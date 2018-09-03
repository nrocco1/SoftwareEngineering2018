package edu.nd.se2018.homework.hwk1;

import java.util.HashMap;
import java.util.Map;
import java.lang.Integer;
import java.lang.String;

public class Question2 {

	public Question2(){}
	
	public String getMostFrequentWord(String input, String stopwords){
		Map<String, Integer> wordCounts = new HashMap<>();
		String[] inputStringList = input.split(" ");
		String[] stopWordsList = stopwords.split(" ");
		
		for (String s:inputStringList) {
			Integer stopWordFlag = 0;
			for (String s2:stopWordsList) {
				if (s.equals(s2)) {
					stopWordFlag = 1;
					break;
				}
			}
			if (stopWordFlag == 0) {
				Integer addInt = 1;
				if (wordCounts.get(s) != null) {
					addInt = wordCounts.get(s);
				}
				wordCounts.put(s, addInt+1);
			}
		}
		
		Map.Entry<String, Integer> maxEntry = null;    // Retrieved from Stack Overflow, is getting the Entry with the maximum Value
		
		for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
		    	maxEntry = entry;
		    }
		}
		
		Integer multipleTied = 0;
		for (Map.Entry<String, Integer> entry2:wordCounts.entrySet()) { 		// Adapted from above, sets flag if there are multiple keys with same maximum value
			if (entry2.getValue().compareTo(maxEntry.getValue()) == 0 && !entry2.getKey().equals(maxEntry.getKey())) {
				multipleTied = 1;
			}
		}
		
		String r = maxEntry.getKey();
		if (multipleTied == 1) {
			r = null;
		}
		
		return r;
	}
}
