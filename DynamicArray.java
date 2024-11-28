import java.util.Arrays;

public class DynamicArray<T> {
    private Object[] array;
    private int size;  // The number of actual elements in the array

    // Constructor
    public DynamicArray() {
        array = new Object[1];  // Initial capacity
        size = 0;
    }

    // Add an element to the array
    public void add(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[size] = item;
        size++;
        printArray();  // Print the array after adding an item
    }

    // Get the element at a specific index
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    // Remove an element from the array
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;  // Set the last element to null after shifting
        size--;

        // Halve the array size if necessary
        if (size > 0 && size <= array.length / 4) {
            resize(array.length / 2);
        }

        printArray();  // Print the array after removing an item
    }

    // Resize the array to a new capacity
    private void resize(int newCapacity) {
        array = Arrays.copyOf(array, newCapacity);
    }

    // Get the current size of the array (number of actual elements)
    public int size() {
        return size;
    }

    // Get the current capacity of the array
    public int capacity() {
        return array.length;
    }

    // Print the entire array with 'None' for empty spaces
    public void printArray() {
        System.out.println(this);
    }

    // Override toString to display the array with 'None' for empty spaces
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i < size) {
                sb.append(array[i]);
            } else {
                sb.append("None");
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
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();

        // Adding elements
        dynamicArray.add(10);
        dynamicArray.add(20);
        dynamicArray.add(30);
        dynamicArray.add(40);

        // Array should resize now
        dynamicArray.add(50);

        // Removing elements
        dynamicArray.remove(2);
        dynamicArray.remove(2);
        dynamicArray.remove(1);
    }
}