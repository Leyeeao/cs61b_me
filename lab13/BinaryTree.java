import jh61b.junit.In;

public class BinaryTree<T> {

    TreeNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode<T> t) {
        root = t;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    /* Returns the height of the tree. */
    public int height() {
        // TODO: YOUR CODE HERE
        TreeNode<T> curr = this.root;
        if (curr == null) {
            return 0;
        }
        BinaryTree left = new BinaryTree(curr.left);
        BinaryTree right = new BinaryTree(curr.right);
        return 1 + Math.max(left.height(), right.height());
    }

    /* Returns true if the tree's left and right children are the same height
       and are themselves completely balanced. */
    public boolean isCompletelyBalanced() {
        // TODO: YOUR CODE HERE
        if (this.height() == 0 || this.height() == 1) {
            return true;
        }
        BinaryTree left = new BinaryTree(this.root.left);
        BinaryTree right = new BinaryTree(this.root.right);
        if (left.height() == right.height()) {
            return left.isCompletelyBalanced() && right.isCompletelyBalanced();
        }
        return false;
    }

    /* Returns a BinaryTree representing the Fibonacci calculation for N. */
    public static BinaryTree<Integer> fibTree(int N) {
        BinaryTree<Integer> result = new BinaryTree<Integer>();
        if (N == 0 || N == 1) {
            result.root = new TreeNode<>(N);
            return result;
        }
        result.root = new TreeNode<>(fibonacci(N), fibTree(N-1).root, fibTree(N-2).root);
        return result;
    }

    /**
     *  first three number in series are
     * 0, 1, 1
     * so return numbers as is
     */
    private static int fibonacci(int n) {
        if (n < 2) {return n;}
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /* Print the values in the tree in preorder: root value first, then values
       in the left subtree (in preorder), then values in the right subtree
       (in preorder). */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    /* Print the values in the tree in inorder: values in the left subtree
       first (in inorder), then the root value, then values in the first
       subtree (in inorder). */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    /* Prints out the contents of a BinaryTree with a description in both
       preorder and inorder. */
    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    /* Fills this BinaryTree with values a, b, and c. DO NOT MODIFY. */
    public void sampleTree1() {
        root = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    /* Fills this BinaryTree with values a, b, and c, d, e, f. DO NOT MODIFY. */
    public void sampleTree2() {
        root = new TreeNode("a",
                  new TreeNode("b", new TreeNode("d", new TreeNode("e"),
                  new TreeNode("f")), null), new TreeNode("c"));
    }

    /* Fills this BinaryTree with the values a, b, c, d, e, f. DO NOT MODIFY. */
    public void sampleTree3() {
        root = new TreeNode("a", new TreeNode("b"), new TreeNode("c",
               new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }

    /* Fills this BinaryTree with the same leaf TreeNode. DO NOT MODIFY. */
    public void sampleTree4() {
        TreeNode leafNode = new TreeNode("c");
        root = new TreeNode("a", new TreeNode("b", leafNode, leafNode),
                                 new TreeNode("d", leafNode, leafNode));
    }

    /* Creates two BinaryTrees and prints them out in inorder. */
    /* meiting creates the test for height() and isCompletedlyBalanced() methods. */
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println("The height of the empty tree:" + t.height());
        System.out.println("Is the empty tree completedly balanced:" + t.isCompletelyBalanced()+"\n");
        t.sampleTree1();
        print(t, "sample tree 1");
        System.out.println("The height of sample tree 1:" + t.height());
        System.out.println("Is sample tree 1 completedly balanced:" + t.isCompletelyBalanced()+"\n");
        t.sampleTree2();
        print(t, "sample tree 2");
        System.out.println("The height of sample tree 2:" + t.height());
        System.out.println("Is sample tree 2 completedly balanced:" + t.isCompletelyBalanced()+"\n");
        t.sampleTree3();
        print(t, "sample tree 3");
        System.out.println("The height of sample tree 3:" + t.height());
        System.out.println("Is sample tree 3 completedly balanced:" + t.isCompletelyBalanced()+"\n");
        t.sampleTree4();
        print(t, "sample tree 4");
        System.out.println("The height of sample tree 4:" + t.height());
        System.out.println("Is sample tree 4 completedly balanced:" + t.isCompletelyBalanced());
        BinaryTree<Integer> fib = new BinaryTree().fibTree(5);
        print(fib, "the Fibonacci tree of 5");
        System.out.println("The height of the Fibonacci tree of 5:" + fib.height());
        System.out.println("Is the Fibonacci tree of 5 completedly balanced:" + fib.isCompletelyBalanced()+"\n");
    }

    /* Note: this class is public in this lab for testing purposes. However,
       in professional settings as well as the rest of your labs and projects,
       we recommend that you keep your inner classes private. */
    static class TreeNode<T> {

        private T item;
        private TreeNode<T> left;
        private TreeNode<T> right;

        TreeNode(T obj) {
            item = obj;
            left = null;
            right = null;
        }

        TreeNode(T obj, TreeNode<T> left, TreeNode<T> right) {
            item = obj;
            this.left = left;
            this.right = right;
        }

        public T getItem() {
            return item;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        void setItem(T item) {
            this.item = item;
        }

        void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        void setRight(TreeNode<T> right) {
            this.right = right;
        }

        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }

        // TODO: ADD HELPER METHODS HERE
    }
}
