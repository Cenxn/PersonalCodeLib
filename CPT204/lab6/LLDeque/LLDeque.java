public class LLDeque<T> {

    private class Node {
        Node prev;
        T item;
        Node next;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /**
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    // EXERCISE 6.1 COPY CONSTRUCTOR

    /**
     * Creates a (deep) copy of another Deque object.
	 * @param other is another LLDeque<T> object.
     */
    public LLDeque(LLDeque<T> other) {
		this.sentinel = new Node(null, null, null);
        this.size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        Node pointer = other.sentinel.next;
        while (pointer.item != null) {
            this.addLast(pointer.item);
            pointer = pointer.next;
        }
    }


    // EXERCISE 6.2 ADD NOT NULL TO FRONT

    /**
     * Adds an non-null item of type T to the front of the deque.
     * @param item is a type T object.
     * @throws IllegalArgumentException if the item is null.
     */
    public void addFirst(T item) {

        if (item == null) throw new IllegalArgumentException();

        Node node = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }


    // EXERCISE 6.3 ADD LEGAL ITEM TO FRONT

    /**
     * Adds the first item of type T to the front of the deque,
     * or the second item of type T instead if the first item is illegal.
     * @param item1 is a type T object.
     * @param item2 is a type T object.
     */
    public void addLegalFirst(T item1, T item2) {
		try{
            this.addFirst(item1);
        } catch (IllegalArgumentException e){
            this.addFirst(item2);
        }
    }
	
	
	
	
    /*
     *************************************************************
     * You can copy paste Lab 5 codes below if you want to use it
     *************************************************************
     */


    // LAB EXERCISE 5.1 EMPTY CONSTRUCTOR

    /**
     * Creates an empty deque.
     */
    public LLDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    // LAB EXERCISE 5.4 ITERATIVE GET ITEM

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null.
     * Does not mutate the deque.
     * @param index is an index where 0 is the front.
     * @return the ith item of the deque, null if it does not exist.
     */
    public T iterGet(int index) {
        if (size == 0 || index <0 || index >= size)return null;
        Node pointer = sentinel;
        while (index != 0){
            pointer = pointer.next;
            index -= 1;
        }
        return pointer.next.item;
    }

    // EXERCISE 5.1 ADD TO BACK

    /**
     * Adds an item of type T to the back of the deque.
     * @param item is a type T object added to the deque.
     */
    public void addLast(T item) {

//        if (item == null)throw new IllegalArgumentException();

        Node node = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    public void printDeque() {
        Node pointer = sentinel.next;
        while (pointer != sentinel){
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }
}
