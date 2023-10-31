import java.util.ArrayList;       // optional

public class ConnectCoins {

    private final UnionFind uf1;
    private final UnionFind uf2;  // optional
    private final boolean[][] ccMatrix;   // optional
    private final int row;        // optional
    private final int column;     // optional


    /*
     *****  DO NOT CHANGE ANY INSTANCE VARIABLES ABOVE *****
     *****  DO NOT ADD ANY INSTANCE VARIABLES **************
     *****  DO NOT ADD ANY LIBRARIES ******** **************
     *****  VIOLATION = 0 MARKS IN PART B ******************
     */

    /*
     ***** HELPER METHODS START *****
     */

    // Add your own helper methods here
    // INCLUDE your helper methods in your submission !

    private void connect(int i, int j, int[][] map){
        if (i-1 >= 0 && i-1 <= column - 1 && j >= 0 && j <= row -1 && ccMatrix[i-1][j])
            uf1.union(map[i][j], map[i-1][j]);
        if (i+1 >= 0 && i+1 <= column -1 && j >= 0 && j <= row -1 && ccMatrix[i+1][j])
            uf1.union(map[i][j], map[i+1][j]);
        if (i >= 0 && i <= column-1 && j-1 >= 0 && j-1 <= row -1 && ccMatrix[i][j-1])
            uf1.union(map[i][j], map[i][j-1]);
        if (i >= 0 && i <= column-1 && j+1 >= 0 && j+1 <= row -1 && ccMatrix[i][j+1])
            uf1.union(map[i][j], map[i][j+1]);
    }

    private int putCoin(int i, int j, int[][] map){
        int[] neibor = {-1, -1, -1, -1}; // left-top right-top left-bottom right-bottom
        int sum = 1;
        if (i-1 >= 0 && i-1 <= column - 1 && j >= 0 && j <= row -1 && ccMatrix[i-1][j]){
            sum += uf1.sizeOf(map[i-1][j]);
            neibor[0] = map[i-1][j];
        }
        if (i+1 >= 0 && i+1 <= column -1 && j >= 0 && j <= row -1 && ccMatrix[i+1][j]){
            if (neibor[0] != -1){
                if (!uf1.isSameGroup(neibor[0], map[i+1][j])) {
                    sum += uf1.sizeOf(map[i + 1][j]);
                    neibor[1] = map[i + 1][j];
                }
            } else {
                sum += uf1.sizeOf(map[i + 1][j]);
                neibor[1] = map[i + 1][j];
            }
        }
        if (i >= 0 && i <= column-1 && j-1 >= 0 && j-1 <= row -1 && ccMatrix[i][j-1]) {
            boolean conn = false;
            for (int k = 0; k < 2; k++) {
                if (neibor[k] == -1) continue;
                if (uf1.isSameGroup(neibor[k], map[i][j-1]))
                    conn = true;
            }
            if (!conn) {
                sum += uf1.sizeOf(map[i][j-1]);
                neibor[2] = map[i][j-1];
            }
        }
        if (i >= 0 && i <= column-1 && j+1 >= 0 && j+1 <= row -1 && ccMatrix[i][j+1]) {
            boolean conn = false;
            for (int k = 0; k < 3; k++) {
                if (neibor[k] == -1) continue;
                if (uf1.isSameGroup(neibor[k], map[i][j+1]))
                    conn = true;
            }
            if (!conn) {
                sum += uf1.sizeOf(map[i][j+1]);
                neibor[3] = map[i][j+1];
            }
        }
        return sum;
    }

    private int[][] scanMarix(boolean[][] grid){
        int ufIdx = 0;
        int[][] mapping = new int[column][row];
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (grid[i][j]){
                    mapping[i][j] = ufIdx;
                    ufIdx++;
                } else {
                    mapping[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (grid[i][j]) {
                    connect(i, j, mapping);
                }
            }
        }
//        uf1.printParent();
        return mapping;
    }
    /*
     ***** HELPER METHODS END *****
     */


    // COURSEWORK 3 PART B.1 Connect Coin CONSTRUCTOR

    /**
     * Initializes the instance variable including a UnionFind data structure.
     * @param ccMatrix is s a 2-D boolean array of true (T) and false (F) values
     *                 to represent the 2-D space where A T in a coordinate indicates that there is a coin
     *                 at that position in the 2-D space, while an F indicates an empty space
     */
    public ConnectCoins(boolean[][] ccMatrix) {
        uf2 = null;
        this.ccMatrix = ccMatrix;
        row = ccMatrix[0].length;
        column = ccMatrix.length;
        int coinNum = 0;
        for (int i = 0; i < column; i++)
            for (int j = 0; j < row; j++)
                if(ccMatrix[i][j])
                    coinNum++;
//        System.out.println(coinNum);
        uf1 = new UnionFind(coinNum);
    }

    // COURSEWORK 3 PART B.2 Connect Coins PLACE MAX CONNECTED COINS

    /**
     * @return a 2-element integer array that represents the coordinate in [row, column],
     * so that a coin that is placed in that coordinate will give the maximum number of newly connected coins.
     * If there are multiple possible such placements, return the left-and-topmost coordinate.
     */

    public int[] placeMaxConnCoins() {
        int[] pos = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[][] mapping = scanMarix(this.ccMatrix);
        int max = 1;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (!ccMatrix[i][j]){
                    int temp = putCoin(i, j, mapping);
                    if (max == temp){
                        if (i <= pos[0] && j < pos[1]){
                            pos[0] = i;
                            pos[1] = j;
                        }
                    }else if (max < temp){
                        pos[0] = i;
                        pos[1] = j;
                        max = temp;
                    }
                }
            }
        }
        return pos;
    }


    // COURSEWORK 3 PART B.3 Connect Coins MAX CONNECTED COINS

    /**
     * @return the maximum number of newly connected coins after placing a new coin.
     */

    public int maxConnCoins() {
        int[][] mapping = scanMarix(this.ccMatrix);
        int max = 1;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (!ccMatrix[i][j]){
                    max = Math.max(putCoin(i, j, mapping), max);
                }
            }
        }
        return max;
    }

}
