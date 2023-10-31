import java.util.*;


public class NumWords {

    /**
     * Find how many times each word appears in a list.
     * For example, numWords(["a", "b", "a", "a", "c", "b"]) → {"a": 3, "b": 2, "c": 1}.
     * @param list is a list of words.
     * @return a map with distinct words as keys and num of occurrences as values.
     */
    public static Map<String, Integer> numWords(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        int num;
        for (String word : list)
            map.put(word, map.getOrDefault(word,0)+1);
        return map;
    }

}