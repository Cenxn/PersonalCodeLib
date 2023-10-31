/**
 * @author 1iin
 */
public class DFSSolution {

    private final boolean[][] ccMatrix;

    boolean[][] isVisited;

    public DFSSolution(boolean[][] ccMatrix) {
        this.ccMatrix = ccMatrix;
    }

    public int[] placeMaxConnCoins() {
        int maxSize = 1;
        int[] res = new int[2];
        for (int i = 0; i < ccMatrix.length; i++) {
            for (int j = 0; j < ccMatrix[0].length; j++) {
                isVisited = new boolean[ccMatrix.length][ccMatrix[0].length];
                isVisited[i][j] = true;
                int size = dfs(i, j);
                if (size > maxSize) {
                    maxSize = size;
                    res[0] = i;
                    res[1] = j;
                }

            }
        }
        return res;

    }


    public int maxConnCoins() {
        int maxSize = 1;
        for (int i = 0; i < ccMatrix.length; i++) {
            for (int j = 0; j < ccMatrix[0].length; j++) {
                isVisited[i][j] = true;
                isVisited = new boolean[ccMatrix.length][ccMatrix[0].length];
                int size = dfs(i, j);
                maxSize = Math.max(maxSize, size);
            }
        }
        return maxSize;

    }

    public int dfs(int row, int col) {
        isVisited[row][col] = true;
        int size = 1;
        if (isValid(row + 1, col)) {
            size += dfs(row + 1, col);
        }
        if (isValid(row - 1, col)) {
            size += dfs(row - 1, col);
        }
        if (isValid(row, col + 1)) {
            size += dfs(row, col + 1);
        }
        if (isValid(row, col - 1)) {
            size += dfs(row, col - 1);
        }
        return size;
    }

    public boolean isValid(int row, int col) {
        return row >= 0 && row < ccMatrix.length && col >= 0 && col < ccMatrix[0].length && ccMatrix[row][col] && !isVisited[row][col];
    }


}