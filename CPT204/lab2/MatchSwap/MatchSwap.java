import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchSwap {

    /**
     * Modify a list of strings such that two strings with same
     * first letter are swapped.
     * For example, matchSwap(["ap", "bp", "cp", "aq", "cq", "bq"]) →
     * ["aq", "bq", "cq", "ap", "cp", "bp"].
     * @param list is a list of strings.
     * The strings are non-empty.
     * @return the modified list.
     */
    public static List<String> matchSwap(List<String> list) {
        HashMap<String, Integer> map = new HashMap<>();
        if(list == null || list.size() <= 1) return list;
        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i).substring(0,1);
            if(map.containsKey(key)){
                String temp = list.get(i);
                list.set(i,list.get(map.get(key)));
                list.set(map.get(key),temp);
                map.remove(key);
            }else{
                map.put(key,i);
            }
        }
        return list;
    }

}