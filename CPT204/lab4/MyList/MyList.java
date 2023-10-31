public class MyList {
    private int value;
    private MyList next;

    public MyList(int value, MyList next) {
        this.value = value;
        this.next = next;
    }

    // LAB EXERCISE 4.1 MYLIST ITERATIVE SQUARE MUTATE 

    /**
     * Square the elements of a MyList. Mutates the MyList.
     * @param list is a MyList object.
     */
    public static void iterSquareMutList(MyList list) {
        while (list != null){
            list.value = list.value * list.value;
            list = list.next;
        }
    }


    // LAB EXERCISE 4.2 MYLIST RECURSIVE SQUARE MUTATE 

    /**
     * Square the elements of a MyList. Mutates the MyList.
     * @param list is a MyList object.
     */
    public static void recSquareMutList(MyList list) {
        // base case
		if (list == null) return;
        // recursive step
        else {
            list.value = list.value * list.value;
            recSquareMutList(list.next);
        }
    }


    // LAB EXERCISE 4.3 MYLIST ITERATIVE SQUARE IMMUTATE 

    /**
     * Square the elements of a MyList. Does not mutate the MyList.
     * @param list is a MyList object.
     * @return another MyList with all of input MyList's element squared.
     */
    public static MyList iterSquareList(MyList list) {
        if (list == null)return null;
		MyList list2 = new MyList(list.value * list.value, list.next);
        list = list.next;
        MyList pointer = list2;
        while (list != null){
            pointer.next = new MyList(list.value * list.value, list.next);
            list = list.next;
            pointer = pointer.next;
        }
        return list2;
    }


    // LAB EXERCISE 4.4 MYLIST RECURSIVE SQUARE IMMUTATE 

    /**
     * Square the elements of a MyList. Does not mutate the MyList.
     * @param list is a MyList object.
     * @return another MyList with all of input MyList's element squared.
     */
    public static MyList recSquareList(MyList list) {
        // base case
		if (list == null) return null;
        // recursive step
		MyList list2 = new MyList(list.value * list.value, list.next);
        MyList pointer = list2;
		return recSquareListHelper(list.next, list2, pointer);
    }
    public static MyList recSquareListHelper(MyList list, MyList list2, MyList pointer) {
        // base case
        if (list == null) return list2;
        // recursive step
        pointer.next = new MyList(list.value * list.value, list.next);
        return recSquareListHelper(list.next, list2, pointer.next);
    }


    // EXERCISE 4.1 MYLIST ITERATIVE CATENATE MUTATE 

    /**
     * Catenate two MyLists, listA and listB. Mutate listA.
     * @param listA is a MyList object.
     * @param listB is a MyList object.
     * @return a list consisting of the elements of listA followed by the
     * elements of listB.
     */
    public static MyList iterCatMutList(MyList listA, MyList listB) {
        if (listA == null && listB == null) return null;
        if (listA == null) {
            MyList pointer = new MyList(listB.value, listB.next);
            listB = listB.next;
            listA = pointer;
            while (listB != null) {
                pointer.next = new MyList(listB.value, listB.next);
                listB = listB.next;
                pointer = pointer.next;
            }
        } else {
            MyList pointer = listA;
            for (int i = 1; i < listA.iterSize(); i++) pointer = pointer.next;
            while (listB != null) {
                pointer.next = new MyList(listB.value, listB.next);
                listB = listB.next;
                pointer = pointer.next;
            }
        }
		return listA;
    }


    // EXERCISE 4.2 MYLIST RECURSIVE CATENATE MUTATE 

    /**
     * Catenate two MyLists, listA and listB. Mutate listA.
     * @param listA is a MyList object.
     * @param listB is a MyList object.
     * @return a list consisting of the elements of listA followed by the
     * elements of listB.
     */
    public static MyList recCatMutList(MyList listA, MyList listB) {
        if (listA == null)return listB;
        MyList list = listA;
        listA = recCatMutListHelper(listA);
        listA.next = listB;
        return list;
    }
    public static MyList recCatMutListHelper(MyList list) {
        // base case
        if (list.next == null)return list;
        // recursive step
        return recCatMutListHelper(list.next);
    }

    // EXERCISE 4.3 MYLIST ITERATIVE CATENATE IMMUTATE 

    /**
     * Catenate two MyLists, listA and listB. Does not mutate listA.
     * @param listA is a MyList object.
     * @param listB is a MyList object.
     * @return a list consisting of the elements of listA followed by the
     * elements of listB.
     */
    public static MyList iterCatList(MyList listA, MyList listB) {
        if (listA == null && listB == null) return null;
        MyList list;
        if (listA != null) {
            list = new MyList(listA.value, listA.next);
            listA = listA.next;
        } else {
            list = new MyList(listB.value, listB.next);
            listB = listB.next;
        }
        MyList pointer = list;
        while (listA != null){
            pointer.next = new MyList(listA.value, listA.next);
            listA = listA.next;
            pointer = pointer.next;
        }
        while (listB != null){
            pointer.next = new MyList(listB.value, listB.next);
            listB = listB.next;
            pointer = pointer.next;
        }
		return list;
    }


    // EXERCISE 4.4 MYLIST RECURSIVE CATENATE IMMUTATE 

    /**
     * Catenate two MyLists, listA and listB. Does not mutate listA.
     * @param listA is a MyList object.
     * @param listB is a MyList object.
     * @return a list consisting of the elements of listA followed by the
     * elements of listB.
     */
    public static MyList recCatList(MyList listA, MyList listB) {
        if (listA == null)return listB;
        MyList list = new MyList(listA.value, null);
        MyList pointer = list;
        pointer = recCatListHelper(pointer, listA.next);
        pointer.next = listB;
        return list;
    }
    public static MyList recCatListHelper(MyList pointer, MyList list) {
        // base case
        if (list == null) {
            return pointer;
        // recursive step
        } else {
            pointer.next = new MyList(list.value, null);
        return recCatListHelper(pointer.next, list.next);
        }
    }





    /*
     *
     *****  Do NOT modify the codes below from the lecture notes!  *****
     *****  Only for your JUnit Testing purposes!                  *****
     *
     */


    /**
     * @return the size of the MyList iteratively.
     */
    public int iterSize() {
        MyList p = this;
        int size = 0;
        while (p != null) {
            size += 1;
            p = p.next;
        }
        return size;
    }

    /**
     * @return the size of the MyList recursively.
     */
    public int recSize() {
        // base case
        if (next == null) {
            return 1;
        }
        // recursive step
        return 1 + this.next.recSize();
    }

    /**
     * @param i is a valid index of MyList.
     * @return the ith value of this MyList.
     */
    public int get(int i) {
        // base case
        if (i == 0) {
            return value;
        }
        // recursive step
        return next.get(i - 1);
    }

    /**
     * @param args is a variable number of integers.
     * @return a new MyList containing the integers in args.
     */
    public static MyList ofEntries(Integer... args) {
        MyList result, p;
        if (args.length > 0) {
            result = new MyList(args[0], null);
        } else {
            return null;
        }
        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.next) {
            p.next = new MyList(args[k], null);
        }
        return result;
    }

    /**
     * @param l is a MyList object.
     * @return true iff l is a MyList object containing the same sequence of
     * integers as this.
     */
    public boolean equals(Object l) {
        if (!(l instanceof MyList)) {
            return false;
        }
        MyList list = (MyList) l;
        MyList p;
        for (p = this; p != null && list != null; p = p.next, list = list.next) {
            if (p.value != list.value) {
                return false;
            }
        }
        if (p != null || list != null) {
            return false;
        }
        return true;
    }

    public String toString() {
        int size = this.recSize();
        String output= "[";
        for (int i = 0; i < size; i++) {
            output = output + this.get(i);
            if (i != size-1)
                output = output + ", ";
        }
        output = output + "]";
        return output;
    }
	
	
}
