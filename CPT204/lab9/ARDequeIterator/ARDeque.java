public class ARDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    /**
     * @return the size of the array used in the deque.
     */
    public int itemsLength() {
        return items.length;
    }

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


    /*
     ******************* HELPER METHODS START *******************
     ***** Include your helper method(s) in EACH Submission *****
     *********************** that uses it ***********************
     */

    /* sub index in the circular array */
    private int subOne(int index){
        if(index == 0) return itemsLength()-1;
        return index - 1;
    }

    /* add index in the circular array */
    private int addOne(int index){
        return (index + 1) % items.length;
    }

    /* Resizes the underlying array to the target capacity. */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int curr = addOne(nextFirst);
        for(int i=0; i<size; i++){
            a[i] = items[curr];
            curr = addOne(curr);
        }
        items = a;
        nextFirst = capacity-1;
        nextLast = size;
    }

    /*
     ******************** HELPER METHODS END ********************
     */


    // LAB EXERCISE 8.1 EMPTY CONSTRUCTOR

    /**
     * Creates an empty deque.
     */
    @SuppressWarnings("unchecked")
    public ARDeque() {
        items = (T[]) new Object[4];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }


    // LAB EXERCISE 8.2 ADD TO BACK

    /**
     * Adds an item of type T to the back of the deque.
     * @param item is a type T object to be added.
     */
    public void addLast(T item) {
        if(size == itemsLength())
            resize(2 * size);
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size++;
    }


    // LAB EXERCISE 8.3 PRINT ITEMS

    /**
     * Prints the items in the deque from first to last,
     * separated by a space, ended with a new line.
     */
    public void printDeque() {
        int curr = addOne(nextFirst);
        for(int i=0; i<size;i++){
            System.out.print(items[curr]+" ");
            curr = addOne(curr);
        }
        System.out.println();
    }


    // LAB EXERCISE 8.4 GET ITEM

    /**
     * Gets the item at the given index.
     * Does not mutate the deque.
     * @param index is an index where 0 is the front.
     * @return the index-th item of the deque.
     * @throws IndexOutOfBoundsException if no item exists at the given index.
     */
    public T get(int index) {
        if (size == 0 || index>=size || index<0)
            throw new IndexOutOfBoundsException("Index "+index+ " is not valid");
        return items[addOne(nextFirst + index)];
    }


    // EXERCISE 8.1 ADD TO FRONT

    /**
     * Adds an item of type T to the front of the deque.
     * @param item is a type T object to be added.
     */
    @SuppressWarnings("unchecked")
    public void addFirst(T item) {
        if (size == itemsLength())
            resize(size * 2);
        items[nextFirst] = item;
        nextFirst = subOne(nextFirst);
        size++;
    }


    // EXERCISE 8.2 DELETE FRONT

    /**
     * Deletes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return the first item of the deque, null if it does not exist.
     */
    @SuppressWarnings("unchecked")
    public T delFirst() {
        T x = items[addOne(nextFirst)];
        items[addOne(nextFirst)] = null;
        if (x != null){
            size--;
            nextFirst = addOne(nextFirst);
            if(size <= itemsLength()/4){
                resize(itemsLength() / 2);
            }
        }
        return x;
    }


    // EXERCISE 8.3 DELETE BACK

    /**
     * Deletes and returns the item at the back  of the deque.
     * If no such item exists, returns null.
     * @return the last item of the deque, null if it does not exist.
     */
    @SuppressWarnings("unchecked")
    public T delLast() {
        T x = items[subOne(nextLast)];
        items[subOne(nextLast)] = null;
        if (x != null){
            size--;
            nextLast = subOne(nextLast);
            if(size <= itemsLength()/4){
                resize(itemsLength() / 2);
            }
        }
        return x;
    }


    // EXERCISE 8.4 COPY CONSTRUCTOR

    /**
     * Creates a (deep) copy of another Deque object.
     * @param other is another ARDeque<T> object.
     */
    @SuppressWarnings("unchecked")
    public ARDeque(ARDeque<T> other) {
        this.items =(T[]) new Object[other.itemsLength()];
        int pointer = (other.nextFirst + 1) % other.items.length;
        for(int i=0; i<other.size; i++){
            this.items[i] = other.items[pointer];
            pointer = (pointer + 1) % other.items.length;
        }
        nextFirst = items.length - 1;
        nextLast = other.size;
        size = other.size;
    }

}