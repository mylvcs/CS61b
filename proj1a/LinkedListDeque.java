public class LinkedListDeque<GType> {
    private class OneNode {
        public OneNode prev; //sentinel's forward link always points to the last element
        public GType item;
        public OneNode next; //sentinel's backward link always points to the first element

        public OneNode(OneNode p, GType i, OneNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private OneNode sentinel;
    private int size;

    /** Creates an empty deque. */
    public LinkedListDeque(){
        sentinel = new OneNode(null,null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Creates a deque with x  */
    public LinkedListDeque(GType x){
        sentinel = new OneNode(null, null, null);
        sentinel.next = new OneNode(sentinel, x,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Adds an item to the front of the Deque.
     *  O(c)  */
    public void addFirst(GType x){
        OneNode oldFrontNode = sentinel.next;
        OneNode newNode = new OneNode(sentinel, x, oldFrontNode);
        sentinel.next = newNode;
        oldFrontNode.prev = newNode;
        size += 1;
    }

    /** Adds an item to the back of the Deque.
     * O(c) */
    public void addLast(GType x){
        OneNode oldBackNode = sentinel.prev;
        OneNode newNode = new OneNode(oldBackNode, x, sentinel);
        sentinel.prev = newNode;
        oldBackNode.next = newNode;
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
        OneNode P = sentinel;
        for (int i = 0; i<size; i++){
            P = P.next;
            System.out.print(P.item);
            System.out.print(" ");
        }
    }

    /** Removes and returns the item at the front of the Deque.
     * If no such item exists, returns null.O(c). */
    public GType removeFirst(){
        if (isEmpty()){
            return null;
        }

        OneNode oldFrontNode = sentinel.next;
        sentinel.next = oldFrontNode.next;
        oldFrontNode.next.prev = sentinel;
        size -= 1;
        return oldFrontNode.item;
    }

    /** Removes and returns the item at the back of the Deque.
     * If no such item exists, returns null. O(1) */
    public GType removeLast(){
        if (isEmpty()){
            return null;
        }

        OneNode oldBackNode = sentinel.prev;
        sentinel.prev = oldBackNode.prev;
        oldBackNode.prev.next = sentinel;
        size -= 1;
        return oldBackNode.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * Use iteration.  */
    public GType get(int index){
        if (index > size-1){
            return null;
        }

        OneNode P = sentinel;
        for (int i = 0; i<=index; i++){
            P = P.next;
        }
        return P.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     * Use recursion. */
    public GType getRecursive(int index) {
        if (index > size-1){
            return null;
        }

        OneNode P = sentinel;
        return getRecursive(index, P.next);
    }

    public GType getRecursive(int index, OneNode P){
        if (index==0){
            return P.item;
        }
        return getRecursive(index-1, P.next);
    }

}