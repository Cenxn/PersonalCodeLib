import java.util.List;

public class CountRuns {

    /**
     * Count the number of runs in a list.
     * For example, countRuns([1, 2, 2, 2, 3]) = 1.
     * @param list is a list of integers.
     * @return the number of runs in list.
     */
    public static int countRuns(List<Integer> list) {
        if (list.size() == 0)return 0;
        int runs = 0;
        int countadj = 1, temp = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (temp == list.get(i)) countadj++;
            else{
                if(countadj >= 2)runs++;
                temp = list.get(i);
                countadj = 1;
            }
        }
        if (countadj >= 2)runs++;
        return runs;
    }

    /**
     * Find max value in the list, assume the minvalue is 0.
     * @param list input should be all integer large or equal than 0.
     * @return max value
     */
    public static  int getMax(List<Integer> list) {
        int max = 0;
        for (int num : list)
            max = Math.max(max, num);
        return max;
    }
}