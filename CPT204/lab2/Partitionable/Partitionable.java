import java.util.List;

public class Partitionable {

    /**
     * Decide whether a list is partitionable.
     * For example, isPartitionable([1, 1, 1, 2, 1]) -> true,
     * and isPartitionable([2, 1, 1, 2, 1]) -> false.
     * @param list is a non-empty list of integers.
     * @return true iff list is partitionable.
     */
    public static boolean isPartitionable(List<Integer> list) {
        if((list.size() == 1 && list.get(0) == 0)
        || list.size() == 0) return true;
        int rightidx = list.size(), leftidx = 0;
        int rightsum = 0, leftsum = list.get(leftidx);
        while(rightidx - 1 > leftidx){
            if(rightsum >= leftsum){
                leftidx++;
                leftsum += list.get(leftidx);
            }else{
                rightidx--;
                rightsum += list.get(rightidx);
            }
//            System.out.println("rightsum = "+rightsum+" leftsum = "+leftsum);
//            System.out.println("rightidx = "+rightidx+" leftidx = "+leftidx);
        }
        return rightsum == leftsum || rightsum + leftsum == 0;
//        int left = 0, sum = 0;
//        for (Integer integer : list) {
//            sum += integer;
//        }
//        if(sum % 2 != 0){
//            return false;
//        } else if (sum == 0) {
//            return true;
//        } else {
//            for (Integer integer : list) {
//                left += integer;
//                if (left == sum / 2) {
//                    return true;
//                }
//            }
//        }
//        return false;
    }
}