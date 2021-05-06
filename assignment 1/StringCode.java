import java.util.HashSet;


// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		int currRun = 0;
		int maxRun = 0;
		char curr = '\0';
		if(str.length() != 0) curr = str.charAt(0);
		for(int i=0; i<str.length(); i++){
			if(str.charAt(i) == curr) currRun++;
			else {
				curr = str.charAt(i);
				currRun = 1;
			}
			if(currRun > maxRun) maxRun = currRun;
		}
		return maxRun; // YOUR CODE HERE
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		for(int i=0; i<str.length();){
			String temp = "";
			if(str.charAt(i) >= 48 && str.charAt(i) <= 57){
				if(i == str.length() - 1) temp = "";
				else for(int j = 0; j < (str.charAt(i) - 48); j++)
					temp = temp + str.charAt(i+1);

				str = str.substring(0,i) + temp + str.substring(i + 1);
				i += temp.length();
				continue;
			}
			i++;
		}
		return str;
	}
	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		HashSet<String> st = new HashSet<>();

		for(int i=0; i<=a.length() - len; i++){
			st.add(a.substring(i,i+len));
		}

		for(int j=0; j<=b.length() - len; j++){
			if(st.contains(b.substring(j,j+len))) return true;
		}

		return false; // YOUR CODE HERE
	}
}
