/**
 *  Resizing-array-based Binary Heap
 */
public class ARBinHeap<T extends Comparable<T>> implements MinPQ<T> {

    private T[] heap;     // store items at indices 1 to n
    private int size;     // number of items on binary heap

    /*
     ********************
     Helper methods start
     ********************
     */
	 private boolean greater(int i, int j) {
         return ((Comparable<T>) heap[i]).compareTo(heap[j]) > 0;
     }
     @SuppressWarnings("unchecked")
     private void swap(int i, int j) {
         T temp = heap[i];
         heap[i] = heap[j];
         heap[j] = temp;
     }
    @SuppressWarnings("unchecked")
     private void swim(int k) {
         while (k > 1 && greater(k/2, k)) {
             swap(k, k/2);
             k = k/2;
         }
     }
    @SuppressWarnings("unchecked")
     private void sink(int k) {
         while (2*k <= size) {
             int j = 2*k;
             if (j < size && greater(j, j+1)) j++;
             if (!greater(k, j)) break;
             swap(k, j);
             k = j;
         }
     }
     @SuppressWarnings("unchecked")
     private void resize(int length){
         T[] temp = (T[]) new Comparable[length];
         for (int i = 1; i <= size; i++) {
             temp[i] = heap[i];
         }
         heap = temp;
     }
    /*
     ******************
     Helper methods end
     ******************
     */


    // LAB EXERCISE 13.1 CONSTRUCTORS

    /**
     * Initializes an empty binary heap with the given initial capacity.
     * @param  initCapacity the initial capacity of this binary heap
     */
    @SuppressWarnings("unchecked")
    public ARBinHeap(int initCapacity) {
		heap = (T[]) new Comparable[initCapacity + 1];
        size = 0;
    }


    /**
     * Initializes an empty binary heap.
     */
    @SuppressWarnings("unchecked")
    public ARBinHeap() {
		heap = (T[]) new Comparable[1];
		size = 0;
    }


    // LAB EXERCISE 13.2 GETMIN

    /**
     * Returns a smallest item on this binary heap.
     * This binary heap is not empty.
     * @return a smallest item on this binary heap
     */
    @Override
    public T getMin() {
        return heap[1];
    }


    // LAB EXERCISE 13.3 ADD

    /**
     * Adds a new item to this binary heap.
     * @param item the item to add to this binary heap
     */
    @Override
    public void add(T item) {
        if (size == heap.length-1) resize(2 * heap.length);
        heap[++size] = item;
        System.out.println(item);
        swim(size);
    }


    // LAB EXERCISE 13.4 DELMIN

    /**
     * Removes and returns a smallest item on this binary heap.
     * This binary heap is not empty.
     * @return a smallest item on this binary heap
     */
    @Override
    public T delMin() {		
        T r = heap[1];
        heap[1] = heap[size];
        heap[size] = null;
        size--;
        sink(1);
        return r;
    }


    // DO NOT MODIFY CODE BELOW
    // for testing and visualization

    /**
     * Returns true if this binary heap is empty.
     * @return true if this binary heap is empty;
     *         false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Returns the number of items on this binary heap.
     * @return the number of items on this binary heap
     */
    @Override
    public int size() {
        return size;
    }

    public T[] toArray() {
        return heap;
    }


}
