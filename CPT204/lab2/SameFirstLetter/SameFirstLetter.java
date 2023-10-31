import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SameFirstLetter {

    /**
     * Create a map with first letter as key and words with that same
     * first letter separated by comma.
     * For example, numWords(["alice", "bob", "apple", "banana"]) →
     * {"a": "alice,apple", "b": "bob,banana"}.
     * @param list is a list of strings.
     * The strings are non-empty.
     * @return a map with first letter and comma-separated-words pair.
     */
    public static Map<String, String> sameFirstLetter(List<String> list) {
        Map<String, String> res = new HashMap<>();
        for (String word: list){
            String temp = String.valueOf(word.charAt(0));
            if (res.containsKey(temp))
                res.put(temp, res.get(temp) +","+ word);
            else
                res.putIfAbsent(temp, word);
        }
        return res;
    }

}