import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxStretch {

    /**
     * Find the largest stretch in a list.
     * For example, maxStretch([8, 5, 1, 2, 3, 4, 5, 10]) = 6.
     * @param list is a list of integers.
     * @return the largest stretch in list.
     */
    public static int maxStretch(List<Integer> list) {
        if (list.size() == 0)return 0;
        int max = 1;
        java.util.Map<Integer,Integer> idx = new java.util.HashMap<Integer, Integer>();
        for (int i = 0; i < list.size(); i++) {
            idx.putIfAbsent(list.get(i), i);
            if(idx.containsKey(list.get(i)))
                max = Math.max(max, i - idx.get(list.get(i)) + 1);
        }
        return max;
    }
}