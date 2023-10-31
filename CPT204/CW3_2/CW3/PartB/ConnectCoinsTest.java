import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;
import java.util.Random;

public class ConnectCoinsTest {

    @Test
    public void testChangedFromyql() {

        for (int i = 0; i < 100; i++) {
            System.out.println(i);

            int rowCount = (int) (Math.random() * 100) + 1;
            int colCount = (int) (Math.random() * 100) + 1;
            boolean[][] ccMatrix = new boolean[rowCount][colCount];
            for (int j = 0; j < rowCount; j++) {
                for (int k = 0; k < colCount; k++) {
                    ccMatrix[j][k] = Math.random() >= 0.5;
                }
            }
            ConnectCoins cc = new ConnectCoins(ccMatrix);
            DFSSolution dfs = new DFSSolution(ccMatrix);
            int[] res1 = cc.placeMaxConnCoins();
            int[] res2 = dfs.placeMaxConnCoins();
//            for (int j = 0; j < ccMatrix.length; j++) {
//                System.out.println(Arrays.toString(ccMatrix[j]));
//            }
//
//            System.out.println(res1[0] + " " + res2[0]);
//            System.out.println(res1[1] + " " + res2[1]);
//            System.out.println(cc.maxConnCoins() + " " + dfs.maxConnCoins());

            if (res1[0] != res2[0] || res1[1] != res2[1] || cc.maxConnCoins() != dfs.maxConnCoins()) {
                System.out.println("oops");
                for(boolean[] nums:ccMatrix){
                    System.out.print("[");
                    for(boolean num:nums)System.out.print(num+", ");
                    System.out.print("]");
                    System.out.println();
                }
//                System.out.println(Arrays.toString(ccMatrix));
                System.out.println(Arrays.toString(cc.placeMaxConnCoins()));
                System.out.println(Arrays.toString(dfs.placeMaxConnCoins()));
//                break;
            }
        }
    }

    @Test
    public void testFromyql() {

        for (int i = 0; i < 100; i++) {
            System.out.println(i);

            int rowCount = (int) (Math.random() * 1000) + 1;
            int colCount = (int) (Math.random() * 1000) + 1;
            boolean[][] ccMatrix = new boolean[rowCount][colCount];
            for (int j = 0; j < rowCount; j++) {
                for (int k = 0; k < colCount; k++) {
                    ccMatrix[j][k] = Math.random() >= 0.5;
                }
            }
            ConnectCoins cc = new ConnectCoins(ccMatrix);
            DFSSolution dfs = new DFSSolution(ccMatrix);
            int[] res1 = cc.placeMaxConnCoins();
            int[] res2 = dfs.placeMaxConnCoins();
//            for (int j = 0; j < ccMatrix.length; j++) {
//                System.out.println(Arrays.toString(ccMatrix[j]));
//            }
//
//            System.out.println(res1[0] + " " + res2[0]);
//            System.out.println(res1[1] + " " + res2[1]);
//            System.out.println(cc.maxConnCoins() + " " + dfs.maxConnCoins());

            if (res1[0] != res2[0] || res1[1] != res2[1] || cc.maxConnCoins() != dfs.maxConnCoins()) {
                System.out.println("oops");
                System.out.println(Arrays.toString(ccMatrix));
                System.out.println(Arrays.toString(cc.placeMaxConnCoins()));
                System.out.println(Arrays.toString(dfs.placeMaxConnCoins()));
                break;
            }
        }
    }

    @Test
    public void random2(){
        Random random = new Random();
        boolean[][] ccMatrix  = new boolean[1000][1000];
        int T = 50;
        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < 1000; j++)
                ccMatrix[i][j] = true;

        while(T-- > 0){
            int r = random.nextInt(999), c = random.nextInt(999);
            ccMatrix[r][c] = false;
            ConnectCoins cc = new ConnectCoins(ccMatrix);
            int[] arr = cc.placeMaxConnCoins();
            int res = cc.maxConnCoins();
            System.out.println("finish: res:" + res + "\t" + Arrays.toString(arr));
            assertEquals(res, 1000000);
            assertArrayEquals(arr, new int[] {r, c});
            ccMatrix[r][c] = true;
        }
    }

    @Test
    public void maxMar1() {
        boolean[][] ccMatrix = new boolean[1000][1000];
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 1;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {0, 0};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void maxMar2() {
        boolean[][] ccMatrix = new boolean[1000][1000];
        ccMatrix[999][999] = true;
        ccMatrix[999][998] = true;
        ccMatrix[998][999] = true;
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 4;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {997, 999};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void maxMar3() {
        boolean[][] ccMatrix = new boolean[1000][1000];
        for (int i = 0; i < 1000; i++) {
            ccMatrix[30][i] = true;
        }
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 1001;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {29, 0};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void maxMar4() {
        boolean[][] ccMatrix = new boolean[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                ccMatrix[i][j] = true;
            }
        }
        ccMatrix[500][500] = false;
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 1000000;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {500, 500};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void givenTestCase() {
        boolean[][] ccMatrix = {{true,  false, true,  true},
                                {true,  false, true,  false},
                                {true,  false, true,  false},
                                {false, true,  false, true},
                                {false, true,  false, true},
                                {true,  false, false, true}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 10;
        assertEquals(scores, cc.maxConnCoins());
    }

    @Test
    public void givenTestCase2() {
        boolean[][] ccMatrix = {{true,  false, true,  true},
                                {true,  false, true,  false},
                                {true,  false, true,  false},
                                {false, true,  false, true},
                                {false, true,  false, true},
                                {true,  false, false, true}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int[] position = {2, 1};
        int[] ans = cc.placeMaxConnCoins();
//        System.out.println(ans[0]+ " ," + ans[1]);
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void minimiunTest() {
        boolean[][] ccMatrix = {{false}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 1;
        assertEquals(scores, cc.maxConnCoins());
    }

    @Test
    public void extremeTest1() {
        boolean[][] ccMatrix = {{false, true},
                                {true, false}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 3;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {0, 0};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void extremeTest2() {
        boolean[][] ccMatrix = {{true, true},
                                {true, false}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 4;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {1, 1};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void normalTest() {
        boolean[][] ccMatrix = {{true, false, true, true},
                                {true, false, true, false}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 6;
        assertEquals(scores, cc.maxConnCoins());
    }

    @Test
    public void normalTest2() {
        boolean[][] ccMatrix = {{false, true, true},
                                {true, false, true},
                                {true, true, false}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 7;
        int[] position = {0, 0};
        int[] ans = cc.placeMaxConnCoins();
//        System.out.println(ans[0]+ " ," + ans[1]);
        assertEquals(scores, cc.maxConnCoins());
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void normalTest3() {
        boolean[][] ccMatrix = {{true, false, true},
                                {false, false, false},
                                {true, false, true}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 3;
        assertEquals(scores, cc.maxConnCoins());
    }

    @Test
    public void normalTest4() {
        boolean[][] ccMatrix = {{false, false, false},
                                {false, false, false},
                                {false, false, false}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 1;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {0, 0};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void normalTest5() {
        boolean[][] ccMatrix = {{false, true, false},
                                {true, false, true},
                                {false, true, false}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 5;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {1, 1};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void normalTest6() {
        boolean[][] ccMatrix = {{true, true, false},
                                {true, false, true},
                                {true, true, false}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 7;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {0, 2};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

    @Test
    public void normalTest7() {
        boolean[][] ccMatrix = {{true, true, true},
                                {true, false, true},
                                {true, true, true}};
        ConnectCoins cc = new ConnectCoins(ccMatrix);
        int scores = 9;
        assertEquals(scores, cc.maxConnCoins());
        int[] position = {1, 1};
        int[] ans = cc.placeMaxConnCoins();
        assertTrue(Arrays.equals(position, ans));
    }

}
