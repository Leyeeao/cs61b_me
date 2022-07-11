import java.util.ArrayList;
import java.util.List;

/**
 * Represent a set of ints.
 */
public class ListSet implements SimpleSet {

    List<Integer> elems;

    public ListSet() {
        elems = new ArrayList<Integer>();
    }

    /** Adds k to the set. */
    public void add(int k) {
        elems.add(k);
    }

    /** Removes k from the set. */
    public void remove(int k) {
        Integer toRemove = k;
        elems.remove(toRemove);
        // TODO - use the above variable with an appropriate List method.
        // The reason is beyond the scope of this lab, but involves
        // method resolution.
    }

    /** Return true if k is in this set, false otherwise. */
    public boolean contains(int k) {
        for (int elem : elems) {
            if (elem == k) {
                return true;
            }
        }
        return false;
    }

    /** Return true if this set is empty, false otherwise. */
    public boolean isEmpty() {
      return this.size() == 0;
    }

    /** Returns the number of items in the set. */
    public int size() {
        return elems.size();
    }

    /** Returns an array containing all of the elements in this collection. */
    public int[] toIntArray() {
        int[] res = new int[this.size()];
        int i = 0;
        for (int elem : elems) {
            res[i] = elem;
            i ++;
        }
        return res;
    }
}
