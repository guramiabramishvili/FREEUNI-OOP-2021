import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		HashMap<T, Integer> amp = new HashMap<>();
		HashMap<T, Integer> bmp = new HashMap<>();
		int ans = 0;

		for(T val : a){
			if(amp.containsKey(val)) amp.put(val,amp.get(val) + 1);
			else amp.put(val,1);
		}
		for(T val : b){
			if(bmp.containsKey(val)) bmp.put(val,bmp.get(val) + 1);
			else bmp.put(val,1);
		}

		for(T val : amp.keySet()){
			if(bmp.containsKey(val) && (bmp.get(val) == amp.get(val))) ans++;
		}

		return ans; // YOUR CODE HERE
	}
	
}
