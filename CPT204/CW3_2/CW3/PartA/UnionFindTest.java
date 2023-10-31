import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {

    @Test
    public void testFromBookisSameGroup() {
        UnionFind uf = new UnionFind(16);
        uf.union(4, 0);
        uf.union(10, 3);
        uf.union(3, 0);
        uf.union(14, 8);
        uf.union(9, 2);
        uf.union(8, 2);
        uf.union(2, 0);
        uf.union(15, 11);
        uf.union(12, 5);
        uf.union(11, 5);
        uf.union(13, 6);
        uf.union(7, 1);
        uf.union(6, 1);
        uf.union(5, 1);
        uf.union(1, 0);
        uf.printParent();// -16 0 0 0 0 1 1 1 2 2 3 5 5 6 8 11

        uf.isSameGroup(10, 11);
        uf.printParent();// -16 0 0 0 0 0 1 1 2 2 0 0 5 6 8 11
    }

    @Test
    public void testFromBookSizeOf() {
        UnionFind uf = new UnionFind(16);
        uf.union(4, 0);
        uf.union(10, 3);
        uf.union(3, 0);
        uf.union(14, 8);
        uf.union(9, 2);
        uf.union(8, 2);
        uf.union(2, 0);
        uf.union(15, 11);
        uf.union(12, 5);
        uf.union(11, 5);
        uf.union(13, 6);
        uf.union(7, 1);
        uf.union(6, 1);
        uf.union(5, 1);
        uf.union(1, 0);
        uf.printParent();// -16 0 0 0 0 1 1 1 2 2 3 5 5 6 8 11

        assertEquals(uf.sizeOf(11), 16);
        assertEquals(uf.sizeOf(10), 16);
        uf.printParent();// -16 0 0 0 0 0 1 1 2 2 0 0 5 6 8 11
    }

    @Test
    public void testFromBookfind() {
        UnionFind uf = new UnionFind(16);
        uf.union(4, 0);
        uf.union(10, 3);
        uf.union(3, 0);
        uf.union(14, 8);
        uf.union(9, 2);
        uf.union(8, 2);
        uf.union(2, 0);
        uf.union(15, 11);
        uf.union(12, 5);
        uf.union(11, 5);
        uf.union(13, 6);
        uf.union(7, 1);
        uf.union(6, 1);
        uf.union(5, 1);
        uf.union(1, 0);
        uf.printParent();// -16 0 0 0 0 1 1 1 2 2 3 5 5 6 8 11

        uf.union(11, 10);
        uf.printParent();// -16 0 0 0 0 0 1 1 2 2 0 0 5 6 8 11
    }

    @Test
    public void testCompre(){
        UnionFind uf = new UnionFind(8);
        uf.union(1, 0);
        uf.union(3, 2);
        uf.union(2, 0);

        uf.union(5, 4);
        uf.union(7, 6);
        uf.union(6, 4);

        uf.printParent();// -4 0 0 2 -4 4 4 6
        uf.union(0, 4);
        uf.printParent();// 4 0 0 2 -8 4 4 6

        assertEquals(uf.find(3), 4);

        uf.printParent();// 4 0 4 4 -8 4 4 6
    }

    @Test
    public void testUnionFindBig(){
        UnionFind uf = new UnionFind(19);
        uf.printParent(); //-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
        assertEquals(1, uf.sizeOf(16));
        assertFalse(uf.isSameGroup(16, 0));
        for(int i=1; i<=15; i+=2) {
            uf.union(i+1, i);
        }
        uf.printParent();//-1 -2 1 -2 3 -2 5 -2 7 -2 9 -2 11 -2 13 -2 15 -1 -1
        assertFalse(uf.isSameGroup(0, 16));
        assertTrue(uf.isSameGroup(15, 16));
        assertEquals(1, uf.sizeOf(0));
        assertEquals(2, uf.sizeOf(15));
        assertEquals(2, uf.sizeOf(16));
        for(int i=1; i<=15; i+=2) {
            uf.union(i+1, i);
        }
        uf.printParent();//-1 -2 1 -2 3 -2 5 -2 7 -2 9 -2 11 -2 13 -2 15 -1 -1
        uf.union(1, 3);
        uf.union(5, 8);
        uf.union(10, 11);
        uf.union(14, 16);
        uf.printParent();//-1 3 1 -4 3 7 5 -4 7 11 9 -4 11 15 13 -4 15 -1 -1
        assertEquals(4, uf.sizeOf(6));
        assertEquals(4, uf.sizeOf(13));
        assertFalse(uf.isSameGroup(1, 8));
        assertFalse(uf.isSameGroup(11, 15));
        uf.union(2, 5);
        uf.union(14, 10);
        assertTrue(uf.isSameGroup(1, 8));
        assertTrue(uf.isSameGroup(11, 15));
        uf.printParent();//-1 7 3 7 3 7 5 -8 7 11 11 -8 11 15 15 11 15 -1 -1
        assertEquals(8, uf.sizeOf(2));
        assertEquals(8, uf.sizeOf(10));
        assertEquals(8, uf.sizeOf(7));
        assertEquals(8, uf.sizeOf(3));
        uf.union(8, 7);
        uf.union(14, 12);
        uf.printParent();//-1 7 3 7 3 7 5 -8 7 11 11 -8 11 15 15 11 15 -1 -1
        assertFalse(uf.isSameGroup(1, 13));
        uf.union(14, 2);
        assertTrue(uf.isSameGroup(13, 1));
        uf.printParent();//-1 7 7 7 3 7 5 -16 7 11 11 7 11 7 11 11 15 -1 -1
        assertEquals(16, uf.sizeOf(14));
        assertEquals(16, uf.sizeOf(2));
        uf.union(0, 14);
        uf.printParent();//7 7 7 7 3 7 5 -17 7 11 11 7 11 7 11 11 15 -1 -1
        assertEquals(17,uf.sizeOf(0));
        uf.union(17, 18);
        uf.union(16, 17);
        uf.printParent();//7 7 7 7 3 7 5 -19 7 11 11 7 11 7 11 11 7 18 7
        assertEquals(19, uf.sizeOf(17));
    }

    @Test
    public void testDS_1() {
        UnionFind uf = new UnionFind(4);
        uf.union(1, 0);
        assertTrue(uf.isSameGroup(1, 0));
        assertEquals(2, uf.sizeOf(1));
        assertEquals(0, uf.parent(1));
        assertEquals(-2, uf.parent(0));
        uf.union(3, 2);
        assertFalse(uf.isSameGroup(3, 1));
        uf.union(3, 1);
        assertEquals(2, uf.parent(3));
        assertTrue(uf.isSameGroup(3, 1));
        assertEquals(0, uf.parent(3));
    }

    @Test
    public void sizeOfTest_1() {
        UnionFind uf = new UnionFind(4);
        assertEquals(1, uf.sizeOf(0));
        uf.union(0, 1);
        assertEquals(2, uf.sizeOf(0));
        uf.union(3, 1);
        assertEquals(3, uf.sizeOf(0));
    }

    @Test
    public void sizeOfTest_2() {
        UnionFind uf = new UnionFind(10);
        uf.union(1, 0);
        uf.union(1, 2);
        uf.union(2, 3);
        assertEquals(4, uf.sizeOf(3));
        uf.union(9, 8);
        uf.union(8, 7);
        assertEquals(3, uf.sizeOf(7));
        uf.union(7, 3);
        assertEquals(7, uf.sizeOf(0));
    }

    @Test
    public void testUnionFind() {
        UnionFind uf = new UnionFind(3);
        assertEquals(-1, uf.parent(0));
        assertEquals(-1, uf.parent(1));
        assertEquals(-1, uf.parent(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateTest_1() {
        UnionFind uf = new UnionFind(5);
        uf.validate(10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateTest_2() {
        UnionFind uf = new UnionFind(5);
        uf.validate(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateTest_4() {
        UnionFind uf = new UnionFind(5);
        uf.validate(5);
    }

    @Test
    public void validateTest_3() {
        UnionFind uf = new UnionFind(5);
        uf.validate(1);
    }
}
