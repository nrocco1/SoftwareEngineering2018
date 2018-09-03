package edu.nd.se2018.homework.hwk1;

import java.util.HashSet;
import java.util.Set;
import java.lang.Integer;

public class Question1 {
		
	public Question1(){}
	
	public int getSumWithoutDuplicates(int[] numbers){
		Set<Integer> found = new HashSet<>();
		Integer sum = 0;
		for (Integer x:numbers) {
			if (found.contains(x)) {
				continue;
			}
			else {
				found.add(x);
				sum += x;
			}
		}
		
		return sum;	
	}
}
