package tjx1;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class CharValue {
	
	public int adverage(ArrayList<Integer> a) {
		int sum = 0;
		for(int i =0; i < a.size(); i++) {
			sum += a.get(i);
		}
		return sum / a.size();
	}
	
	public double variance(ArrayList<Integer> a) {
		int sum = 0;
		for(int i = 0; i < a.size(); i++) {
			sum += i;
		}
		double average = sum / a.size();
		
		double var = 0;
		for(int i = 0; i < a.size(); i++){
		    var+=(a.get(i) - average) * (a.get(i) - average);
		}
		return Math.sqrt(var / a.size());
	}
	
	public int mostNum(ArrayList<Integer> a) {
		int temp = 0;
		int count = 1;
		int countMax = 0;
		NumComparator sortNum = new NumComparator();
		Collections.sort(a, sortNum);
		
		for(int i = 0; i < a.size(); i++) {
			if(a.get(i) == a.get(i + 1)) {
				count++;
			}else {
				if(count >= countMax) {
					countMax = count;
					temp = i;
				}
				count = 1;
			}
		}
		
		return a.get(temp);
	}
	
	public double coefficient(ArrayList<Integer> a) {
		return variance(a) / adverage(a);
	}
	
	public double skew(ArrayList<Integer> a) {
		double sum = 0;
		
		for(int i = 0; i < a.size(); i++) {
			sum += (a.get(i)-adverage(a)) * (a.get(i)-adverage(a)) * (a.get(i)-adverage(a));
		}
		
		return sum / (a.size() * variance(a) * variance(a) * variance(a) * variance(a));
	}
	
	public double kurt(ArrayList<Integer> a) {
		double sum = 0;
		
		for(int i = 0; i < a.size(); i++) {
			sum += (a.get(i)-adverage(a)) * (a.get(i)-adverage(a)) * (a.get(i)-adverage(a) * (a.get(i)-adverage(a)));
		}
		
		return sum / (a.size() * variance(a) * variance(a) * variance(a));
	}

}
