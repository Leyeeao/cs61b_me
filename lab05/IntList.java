import jh61b.junit.In;

/** A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 *
 * @author Maurice Lee and Wan Fung Chui
 */

public class IntList {

    /** The integer stored by this node. */
    public int item;
    /** The next node in this IntList. */
    public IntList next;

    /** Constructs an IntList storing ITEM and next node NEXT. */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /** Constructs an IntList storing ITEM and no next node. */
    public IntList(int item) {
        this(item, null);
    }

    /** Returns an IntList consisting of the elements in ITEMS.
     * IntList L = IntList.list(1, 2, 3);
     * System.out.println(L.toString()) // Prints 1 2 3 */
    public static IntList of(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next = new IntList(items[i]);
            last = last.next;
        }
        return head;
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException
     * if index out of bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public int get(int position) {
        if (position < 0) {
            throw  new IllegalArgumentException("Please input position within indexing range.");
        }
        else {
            if (position == 0) {
                return item;
            }
            if (next == null){
                throw  new IllegalArgumentException("Please input position within indexing range.");
            }
            return next.get(position - 1);
        }

    }

    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "1 2 3".
     *
     * @return The String representation of the list.
     */
    public String toString() {
        //TODO: YOUR CODE HERE
        IntList p = this;
        String res = new String();
        while (p.next != null) {
            res += p.item + " ";
            p = p.next;
        }
        res += p.item;
        return res;
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * NOTE: A full implementation of equals requires checking if the
     * object passed in is of the correct type, as the parameter is of
     * type Object. This also requires we convert the Object to an
     * IntList, if that is legal. The operation we use to do this is called
     * casting, and it is done by specifying the desired type in
     * parenthesis. An example of this is on line 84.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof IntList)) {
            return false;
        }
        IntList otherLst = (IntList) obj;
        IntList thisLst = this;
        while (otherLst != null) {
            if (thisLst.item != otherLst.item || thisLst == null) {
                return false;
            }
            thisLst = thisLst.next;
            otherLst = otherLst.next;
        }
        if (thisLst != null) {
            return false;
        }

        return true;
    }

    /**
     * Adds the given value at the end of the list.
     *
     * @param value, the int to be added.
     */
    public void add(int value) {
        IntList last = new IntList(value,null);
        IntList p = this;
        while (p.next != null) {
            p = p.next;
        }
        p.next = last;
    }

    /**
     * Returns the smallest element in the list.
     *
     * @return smallest element in the list
     */
    public int smallest() {
        IntList p = this;
        int min = p.item;
        while (p != null) {
            if (p.item < min) {
                min = p.item;
            }
            p = p.next;
        }
        return min;
    }

    /**
     * Returns the sum of squares of all elements in the list.
     *
     * @return The sum of squares of all elements.
     */
    public int squaredSum() {
        IntList p = this;
        int sum = 0;
        while (p != null) {
            sum += p.item * p.item;
            p = p.next;
        }
        return sum;
    }

    /**
     * Destructively squares each item of the list.
     *
     * @param L list to destructively square.
     */
    public static void dSquareList(IntList L) {
        while (L != null) {
            L.item = L.item * L.item;
            L = L.next;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L list to non-destructively square.
     * @return the squared list.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.item * L.item, null);
        IntList ptr = res;
        L = L.next;
        while (L != null) {
            ptr.next = new IntList(L.item * L.item, null);
            L = L.next;
            ptr = ptr.next;
        }
        return res;
    }

    /** Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L list to non-destructively square.
     * @return the squared list.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.item * L.item, squareListRecursive(L.next));
    }

    /**
     * Returns a new IntList consisting of A followed by B,
     * destructively.
     *
     * @param A list to be on the front of the new list.
     * @param B list to be on the back of the new list.
     * @return new list with A followed by B.
     */
    public static IntList dcatenate(IntList A, IntList B) {
        IntList L = A;
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        while (L.next != null) {
            L = L.next;
        }
        L.next = B;
        return A;
    }

    /**
     * Returns a new IntList consisting of A followed by B,
     * non-destructively.
     *
     * @param A list to be on the front of the new list.
     * @param B list to be on the back of the new list.
     * @return new list with A followed by B.
     */
     public static IntList catenate(IntList A, IntList B) {
         if (A == null) {
             return B;
         }
         if (B == null) {
             return A;
         }
         IntList res = new IntList(0,null);
         IntList p = res;
         IntList pa = A;
         IntList pb = B;
         while (pa != null) {
             p.next = new IntList(pa.item, null);
             p = p.next;
             pa = pa.next;
         }
         while (pb != null) {
             p.next = new IntList(pb.item, null);
             p = p.next;
             pb = pb.next;
         }
         return res.next;
     }
}