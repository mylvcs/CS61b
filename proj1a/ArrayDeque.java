
public class ArrayDeque<GType> {
    private GType[] items;
    private int head; // mark the head index of array
    private int tail; // mark the tail index of array
    private int size;
    private int RFACTOR = 2; // 扩容 factor
    private double USAGE = 0.25; // usage factor

    /** 构造一个初始容量 8 的数组，初始有效数据成员为0,
     * when head == tail, the array is empty */
    public ArrayDeque() {
        items = (GType[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    /** 改变列表容量, capacity为改变后的容量.
     * 1. Copy the [nominated head - real head] part: the firstChunk,
     *      and position it at the beginning of the new container
     * 2. Copy the rest [real head - nominated tail] part, following the above part.
     * 3. Modify the head and tail.
     * */
    private void resize(int capacity) {
        GType[] newContainer = (GType[]) new Object[capacity];
        if (head > tail){
            int firstChunk = items.length - head;
            System.arraycopy(items, head, newContainer, 0, firstChunk);
            System.arraycopy(items, 0, newContainer, firstChunk, size - firstChunk);
        } else{
            System.arraycopy(items, head, newContainer, 0, size);
        }

        items = newContainer;
        head = 0;
        tail = size -1;
    }

    /** Return the usage rate */
    public double checkUsage(){
        return size / items.length;
    }

    /** Adds an item to the front of the Deque. */
    public void addFirst(GType x){
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        if (isEmpty()){
            /** for empty array, just dial the head and tail back to the initial position */
            head = 0;
            items[head] = x;
            tail = 0;
        } else {
            int insert = (head - 1 + items.length) % items.length;
            items[insert] = x;
            head = insert;
        }

        size += 1;
    }

    /** Adds an item to the back of the Deque. */
    public void addLast(GType x){
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        if (isEmpty()){
            /** for empty array, just dial the head and tail back to the initial position */
            head =0;
            items[head] = x;
            tail = 0;
        } else{
            int insert = (tail + 1)% items.length;
            items[insert] = x;
            tail = insert;
        }

        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if (size>0){
            return false;
        }
        return true;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Prints the items in the Deque from first to last, separated by a space */
    public void printDeque(){
        for (int i = 0; i<size; i++){
            System.out.print(items[ (i + head)% items.length]);
            System.out.print(" ");
        }
    }

    /** Removes and returns the item at the front of the Deque.
     * If no such item exists, returns null.O(c). */
    public GType removeFirst(){
        if (isEmpty()){
            return null;
        }

        GType save = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size -= 1;

        if (size >= 16 && checkUsage() < USAGE){
            resize(size * RFACTOR);
        }

        return save;
    }

    /** Removes and returns the item at the back of the Deque.
     * If no such item exists, returns null. O(1) */
    public GType removeLast(){
        if (isEmpty()){
            return null;
        }

        GType save = items[tail];
        items[tail] = null;
        tail = (tail - 1 + items.length) % items.length;
        size -= 1;

        if (size >= 16 && checkUsage() < USAGE ){
            resize(size * RFACTOR);
        }

        return save;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * Use iteration.  */
    public GType get(int index){
        if (index > size-1){
            return null;
        }

        return items[(index + head)%items.length];
    }

}