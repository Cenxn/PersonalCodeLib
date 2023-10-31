import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EvenAppend {

    /**
     * Append words that appear the 2nd, 4th, 6th, etc. time in a list.
     * For example, evenAppend(["a", "b", "b", "a", "a", "b", "b"]) → "bab".
     * @param list is a list of words.
     * @return a concatenation of even appearing words.
     */
    public static String evenAppend(List<String> list) {
        if (list.size() == 0)return "";
        String res = "";
        Map<String, Integer> map = new HashMap<>();
        for (String word : list) {
            map.put(word, map.getOrDefault(word, 0) + 1);
            if(map.get(word) == 2){
                res += word;
                map.put(word, 0);
            }
        }
		return res;
    }

}