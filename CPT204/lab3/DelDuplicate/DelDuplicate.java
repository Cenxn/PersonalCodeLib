public class DelDuplicate {
	
    /**
     * Remove adjacent duplicate characters in a string.
     * For example, delDuplicate("aaabbc") → "abc".
     * @param input is the input string.
     * @return the resulting string.
     */
    public static String delDuplicate(String input) {
        // base case
        if(input.length() <= 1)return input;
        // recursive step
        if (input.charAt(0) == input.charAt(1))
            return delDuplicate(input.substring(1));
        else return input.substring(0, 1) + delDuplicate(input.substring(1));
    }
}
