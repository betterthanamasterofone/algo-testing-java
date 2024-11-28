import java.util.Arrays;

public class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
        T key;
        Node left, right, parent;

        Node(T item, Node parent) {
            this.key = item;
            this.left = this.right = null;
            this.parent = parent;
        }
    }

    private Node root;
    private Object[] array;  // Internal array to store elements with nulls for absent children
    private int size;        // The number of elements in the tree

    // Constructor
    public BinarySearchTree() {
        root = null;
        array = new Object[1];  // Initial capacity for the root node
        size = 0;
    }

    // Insert a key into the binary search tree
    public void insert(T key) {
        root = insertRec(root, key, null);
        size++;
        updateArray();  // Update the internal array after insertion
        printArray();
    }

    private Node insertRec(Node root, T key, Node parent) {
        // If the tree is empty, return a new node
        if (root == null) {
            return new Node(key, parent);
        }

        // Otherwise, recur down the tree
        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key, root);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key, root);
        }

        // Return the (unchanged) node pointer
        return root;
    }

    // Remove a key from the binary search tree
    public void remove(T key) {
        Node nodeToRemove = search(root, key);
        if (nodeToRemove != null) {
            subtreeRemove(nodeToRemove);
            size--;
            updateArray();  // Update the internal array after deletion
            printArray();
        }
    }

    // Search for a node with a given key
    private Node search(Node root, T key) {
        if (root == null || root.key.equals(key)) {
            return root;
        }

        if (key.compareTo(root.key) < 0) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
        }
    }

    // Procedure to remove a subtree node
    private Node subtreeRemove(Node z) {
        if (z.left != null || z.right != null) {  // z is not a leaf
            Node y;
            if (z.left != null) {
                y = treePredecessor(z);  // y is predecessor
            } else {
                y = treeSuccessor(z);  // y is successor
            }
            z.key = y.key;  // Replace z's key with y's key
            return subtreeRemove(y);  // Recursively remove y
        } else if (z.parent != null) {  // z is a leaf and not the root
            if (z.parent.left == z) {
                z.parent.left = null;
            } else {
                z.parent.right = null;
            }
        } else {  // z is the only node in the tree
            root = null;
        }
        return z;
    }

    // Find the maximum value in the left subtree (predecessor)
    private Node treePredecessor(Node z) {
        Node current = z.left;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    // Find the minimum value in the right subtree (successor)
    private Node treeSuccessor(Node z) {
        Node current = z.right;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Update the array representation to correctly reflect the binary tree structure
    private void updateArray() {
        int capacityNeeded = getCapacityNeeded(root, 0);
        if (capacityNeeded > array.length) {
            resize(capacityNeeded);
        }
        array = new Object[capacityNeeded];
        Arrays.fill(array, null); // Fill with null to represent empty slots
        populateArray(root, 0);
    }

    // Get the required capacity to store the tree with nulls at correct indices
    private int getCapacityNeeded(Node node, int index) {
        if (node == null) {
            return 0;
        }
        int leftCapacity = getCapacityNeeded(node.left, 2 * index + 1);
        int rightCapacity = getCapacityNeeded(node.right, 2 * index + 2);
        return Math.max(index + 1, Math.max(leftCapacity, rightCapacity));
    }

    // Helper method to resize the array
    private void resize(int newCapacity) {
        array = Arrays.copyOf(array, newCapacity);
    }

    // Helper method to populate the array with the tree nodes
    private void populateArray(Node node, int index) {
        if (node != null && index < array.length) {
            array[index] = node.key;
            populateArray(node.left, 2 * index + 1);
            populateArray(node.right, 2 * index + 2);
        }
    }

    // Print the tree structure in a visual format
    public void printTree() {
        printTreeRec(root, "", true);
    }

    private void printTreeRec(Node node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.key);
            printTreeRec(node.left, prefix + (isTail ? "    " : "│   "), false);
            printTreeRec(node.right, prefix + (isTail ? "    " : "│   "), true);
        }
    }

    // Print the array with 'null' for empty spaces
    public void printArray() {
        System.out.println(this);
    }

    // Override toString to display the array with 'null' for empty spaces
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                sb.append(array[i]);
            } else {
                sb.append("null");
            }
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Adding elements
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        // Printing the tree structure
        System.out.println("Tree structure:");
        bst.printTree();

        // Printing the array representation
        System.out.println("Array representation:");
        bst.printArray();

        // Removing elements
        System.out.println("\nRemoving elements...");
        bst.remove(20);
        bst.remove(30);
        bst.remove(50);

        // Printing the tree structure after removals
        System.out.println("Tree structure after removals:");
        bst.printTree();

        // Printing the array representation after removals
        System.out.println("Array representation after removals:");
        bst.printArray();
    }
}
