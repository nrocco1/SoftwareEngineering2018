package edu.nd.se2018.homework.hwk1;

public class Question3 {
	
	public Question3(){}	
	
    public int getMirrorCount(int[] numbers){
		int[] reversed = new int[numbers.length];
    	
		for (int i = 0; i < numbers.length; i++) {
			reversed[i] = numbers[numbers.length-1-i];
		}
    	
		int matchCount = 0;
		int maxMatchCount = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == reversed[i]) {
				matchCount++;
			}
			else {
				matchCount = 0;
			}
			
			if (matchCount > maxMatchCount) {
				maxMatchCount = matchCount;
			}
		}
    	return maxMatchCount;	
	}
}
