public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }

    }

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given 2-3 TREE. */
    public RedBlackTree(TwoThreeTree<T> tree) {
        Node<T> ttTreeRoot = tree.root;
        root = buildRedBlackTree(ttTreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null) {
            return null;
        }

        if (r.getItemCount() == 1) {
            // TODO: Replace with code to create a 2-node equivalent
            root = new RBTreeNode(true, r.getItemAt(0),
                    buildRedBlackTree(r.getChildAt(0)), buildRedBlackTree(r.getChildAt(1)));
            return root;
        } else {
            // TODO: Replace with code to create a 3-node equivalent
            RBTreeNode<T> redNode = new RBTreeNode<>(false, r.getItemAt(1),
                    buildRedBlackTree(r.getChildAt(0)), buildRedBlackTree(r.getChildAt(1)));
            root = new RBTreeNode<>(true, r.getItemAt(0),
                    redNode, buildRedBlackTree(r.getChildAt(2)));

            return root;
        }
    }

    /* Flips the color of NODE and its children. Assume that NODE has both left
       and right children. */
    void flipColors(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        node.left.isBlack = node.isBlack;
        node.isBlack = node.right.isBlack;
        node.right.isBlack = node.left.isBlack;
    }

    /* Rotates the given node NODE to the right. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        RBTreeNode res = node.left;
        res.left = node.right;
        node.right = res;


        return res;
    }

    /* Rotates the given node NODE to the left. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        // TODO: YOUR CODE HERE
        RBTreeNode res = node.right;
        node.right = res.left;
        res.left = node;


        return res;
    }

    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /* Inserts the given node into this Red Black Tree*/
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        // Insert (return) new red leaf node.
        if (node == null) {
            return new RBTreeNode<>(false, item);
        }

        // Handle normal binary search tree insertion.
        int comp = item.compareTo(node.item);
        if (comp == 0) {
            return node; // do nothing.
        } else if (comp < 0) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }

        RBTreeNode<T> gp = null;
        RBTreeNode<T> p = null;
        RBTreeNode<T> curr = node;
        while (curr != null){
            if (curr.item == item) {
                break;
            }
            else if (item.compareTo(curr.item) < 0) {
                gp = p;
                p = curr;
                curr = curr.left;
            } else {
                gp = p;
                p = curr;
                curr = curr.right;
            }

        }
        // handle "middle of three" and "right-leaning red" structures
        if (curr == p.right && isRed(p)) {
            rotateLeft(p);
            rotateRight(gp);
            flipColors(curr);
        }

        // handle "smallest of three" structure
        else if (curr == p.left && isRed(p)) {
            rotateRight(gp);
            flipColors(p);
        }
        // handle "largest of three" structure
        else if (curr == p.right && !isRed(p)) {
            flipColors(p);
        }
        
        // TODO: YOUR CODE HERE
        return node; //fix this return statement
    }

    /* Returns whether the given node NODE is red. Null nodes (children of leaf
       nodes are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

}
