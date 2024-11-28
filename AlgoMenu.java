import java.util.Scanner;

public class AlgoMenu {

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap();
        AVLTree avlTree = new AVLTree();
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Use Max Heap");
            System.out.println("2. Use AVL Tree");
            System.out.println("3. Use Dynamic Array");
            System.out.println("4. Use Binary Search Tree");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    handleMaxHeap(maxHeap, scanner);
                    break;
                case "2":
                    handleAVLTree(avlTree, scanner);
                    break;
                case "3":
                    handleDynamicArray(dynamicArray, scanner);
                    break;
                case "4":
                    handleBST(bst, scanner);
                    break;
                case "5":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid menu option.");
            }
        }
    }

    private static void handleMaxHeap(MaxHeap maxHeap, Scanner scanner) {
        while (true) {
            System.out.println("\nMax Heap Menu:");
            System.out.println("1. Add an element to the Max Heap");
            System.out.println("2. Remove the max element from the Max Heap");
            System.out.println("3. Print the heap as an array");
            System.out.println("4. Print the heap as a tree");
            System.out.println("5. Go back to main menu");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Enter a number to add: ");
                    try {
                        int number = Integer.parseInt(scanner.nextLine());
                        maxHeap.add(number);
                        System.out.println("Element added.");
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid integer.");
                    }
                    break;
                case "2":
                    try {
                        int removed = maxHeap.remove();
                        System.out.println("Removed element: " + removed);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    maxHeap.printHeap();
                    break;
                case "4":
                    maxHeap.printTree();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid menu option.");
            }
        }
    }

    private static void handleAVLTree(AVLTree avlTree, Scanner scanner) {
        System.out.println("You are now using the AVL Tree. Enter numbers to add them to the tree.");
        System.out.println("Type 'remove' followed by the number to remove an element.");
        System.out.println("Type 'print tree' to print the tree structure.");
        System.out.println("Type 'print array' to print the in-order traversal with array indices.");
        System.out.println("Type 'exit' to go back to the main menu.");

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            if (input.startsWith("remove")) {
                try {
                    int number = Integer.parseInt(input.split(" ")[1]);
                    avlTree.delete(number);
                    System.out.println("Element removed.");
                } catch (Exception e) {
                    System.out.println("Please enter a valid command. Format: 'remove <number>'");
                }
            } else if (input.equalsIgnoreCase("print tree")) {
                avlTree.printTree();
            } else if (input.equalsIgnoreCase("print array")) {
                avlTree.inOrderTraversal();
            } else {
                try {
                    int number = Integer.parseInt(input);
                    avlTree.insert(number);
                    System.out.println("Element added.");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid command.");
                }
            }
        }
    }

    private static void handleDynamicArray(DynamicArray<Integer> dynamicArray, Scanner scanner) {
        System.out.println("You are now using the Dynamic Array.");
        System.out.println("Enter numbers to add them to the array.");
        System.out.println("Type 'remove' followed by the index to remove an element.");
        System.out.println("Type 'print array' to print the array.");
        System.out.println("Type 'exit' to go back to the main menu.");

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            if (input.startsWith("remove")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    dynamicArray.remove(index);
                    System.out.println("Element removed.");
                } catch (Exception e) {
                    System.out.println("Please enter a valid command. Format: 'remove <index>'");
                }
            } else if (input.equalsIgnoreCase("print array")) {
                dynamicArray.printArray();
            } else {
                try {
                    int number = Integer.parseInt(input);
                    dynamicArray.add(number);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid command.");
                }
            }
        }
    }

    private static void handleBST(BinarySearchTree<Integer> bst, Scanner scanner) {
        System.out.println("You are now using the Binary Search Tree.");
        System.out.println("Enter numbers to add them to the tree.");
        System.out.println("Type 'remove' followed by the number to remove an element.");
        System.out.println("Type 'print tree' to print the tree structure.");
        System.out.println("Type 'print array' to print the in-order traversal in the internal array.");
        System.out.println("Type 'exit' to go back to the main menu.");

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            if (input.startsWith("remove")) {
                try {
                    int number = Integer.parseInt(input.split(" ")[1]);
                    bst.remove(number);
                    System.out.println("Element removed.");
                } catch (Exception e) {
                    System.out.println("Please enter a valid command. Format: 'remove <number>'");
                }
            } else if (input.equalsIgnoreCase("print tree")) {
                bst.printTree();
            } else if (input.equalsIgnoreCase("print array")) {
                bst.printArray();
            } else {
                try {
                    int number = Integer.parseInt(input);
                    bst.insert(number);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid command.");
                }
            }
        }
    }
}
