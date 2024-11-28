import java.util.ArrayList;
import java.util.Scanner;

class MaxHeap {
    private ArrayList<Integer> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    // Get the parent index
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Get the left child index
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // Get the right child index
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Add a new element to the heap
    public void add(int element) {
        heap.add(element);
        heapifyUp(heap.size() - 1);
    }

    // Remove the root element (maximum element) from the heap
    public int remove() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int root = heap.get(0);
        int lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            heapifyDown(0);
        }
        return root;
    }

    // Heapify the element at index upwards
    private void heapifyUp(int index) {
        while (index > 0 && heap.get(parent(index)) < heap.get(index)) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    // Heapify the element at index downwards
    private void heapifyDown(int index) {
        while (true) {
            int left = leftChild(index);
            int right = rightChild(index);
            int largest = index;

            if (left < heap.size() && heap.get(left) > heap.get(largest)) {
                largest = left;
            }

            if (right < heap.size() && heap.get(right) > heap.get(largest)) {
                largest = right;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    // Swap two elements in the heap
    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // Display the heap as an array
    public void printHeap() {
        System.out.println("Heap stored in array format: " + heap);
    }

    // Display the heap as a tree
    public void printTree() {
        int height = (int) Math.ceil(Math.log(heap.size() + 1) / Math.log(2));

        int maxWidth = (int) Math.pow(2, height - 1);
        int index = 0;

        for (int i = 0; i < height; i++) {
            int levelWidth = (int) Math.pow(2, i);
            int spacesBetween = maxWidth / levelWidth;
            int spacesBefore = spacesBetween / 2;

            // Print leading spaces
            for (int j = 0; j < spacesBefore; j++) {
                System.out.print("  ");
            }

            // Print nodes in the current level
            for (int j = 0; j < levelWidth && index < heap.size(); j++) {
                System.out.print(heap.get(index++));
                for (int k = 0; k < spacesBetween - 1; k++) {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a number to add, 'remove' to remove the max, 'array' to view array format, or 'exit' to quit: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            if (input.equalsIgnoreCase("remove")) {
                try {
                    int removed = maxHeap.remove();
                    System.out.println("Removed element: " + removed);
                    maxHeap.printHeap();
                    maxHeap.printTree();
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.equalsIgnoreCase("array")) {
                maxHeap.printHeap();
            } else {
                try {
                    int number = Integer.parseInt(input);
                    maxHeap.add(number);
                    maxHeap.printHeap();
                    maxHeap.printTree();
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid integer.");
                }
            }
        }

        scanner.close();
    }
}