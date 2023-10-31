public class WeightedQuickUnion {

    private int[] parent;

    /*
     * Returns the parent of element p.
     * If p is the root of a tree, returns the negative size
     * of the tree for which p is the root.
     */
    public int parent(int p) {
        return parent[p];
    }

    /* Prints the parents of the elements, separated by a space */
    public void printParent() {
        for (int element : parent) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    /*
     ***** HELPER METHODS START *****
     */

    // Add your own helper methods here
	// INCLUDE your helper methods in your submission !

    /**
     * Find the root of the element
     */
    public int findRoot(int p){
        int find_root_p = parent[p];
        int root_p = p;
        while (find_root_p >= 0){
            root_p = find_root_p;
            find_root_p = parent[find_root_p];
        }
//        System.out.println("root of "+ p +" is "+root_p);
        return root_p;
    }

    /**
     * return a int array, the 0 index is root index, the 1 index
     * is the distence from the item to its root
     * @param p index to find the root
     * @return findRootandHeight[0] = root, findRootandHeight[1] = height
     */
    private int[] findRootandHeight(int p){
        int find_root_p = parent[p];
        int[] findRootandHeight = new int[2];
        findRootandHeight[0] = p;
        findRootandHeight[1] = 0;
        while (find_root_p >= 0){
            findRootandHeight[1] ++;
            findRootandHeight[0] = find_root_p;
            find_root_p = parent[find_root_p];
        }
//        System.out.println("root of "+ p +" is "+root_p);
        return findRootandHeight;
    }
	

    /*
     ***** HELPER METHODS END *****
     */


    // LAB EXERCISE 9.3  CONSTRUCTOR

    /**
     * Creates a Union Find data structure with n elements,
     * 0 through n-1.
     * Initially, each element is in its own set.
     * @param N the number of elements
     */
    public WeightedQuickUnion(int N) {
        this.parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
    }


    // LAB EXERCISE 9.4 VALIDATE

    /**
     * Validates that p is a valid element/index.
     * @throws IllegalArgumentException if p is not a valid index.
     */
    public void validate(int p) {
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException();
        }
    }


    // CODING ASSIGNMENT 9.1  SIZE OF

    /**
     * Returns the size of the set element p belongs to.
     * @param p an element
     * @return the size of the set containing p
     */
    public int sizeOf(int p) {
        int find_size = parent[p];
        while(find_size >= 0){
            find_size = parent[find_size];
        }
        find_size = Math.abs(find_size);
//        System.out.println("Size of "+p+" is "+find_size);
		return find_size;
    }
	
	
	// CODING ASSIGNMENT 9.2  IS SAME GROUP

    /**
     * Returns true iff elements p is in the same group as q.
     * @param p an element
     * @param q the other element
     * @return true if p and q are in the same group
     *         false otherwise
     * @throws IllegalArgumentException if p or q is not a valid index.
     */
    public boolean isSameGroup(int p, int q) {
        try{
            validate(p);
            validate(q);
            int root_p = findRoot(p);
            int root_q = findRoot(q);
            return root_p == root_q;
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }


    // CODING ASSIGNMENT 9.3  UNION

    /**
     * Combines two elements p and q together,
     * by combining the sets containing them.
     * @param p an element
     * @param q the other element
     * @throws IllegalArgumentException if p or q is not a valid index.
     */
    public void union(int p, int q) {
        try{
            validate(p);
            validate(q);

            int root_p = findRoot(p);
            int root_q = findRoot(q);

            if (root_p == root_q) return;

            int size_p = sizeOf(p);
            int size_q = sizeOf(q);

            if(size_p > size_q){
                parent[root_q] = root_p;
                parent[root_p] = 0 - (size_p + size_q);
            } else {
                parent[root_p] = root_q;
                parent[root_q] = 0 - (size_q + size_p);
            }
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }


    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(4);
        uf.union(1, 0);
        uf.union(3, 2);
        uf.union(3, 1);
        uf.printParent();
    }

}
