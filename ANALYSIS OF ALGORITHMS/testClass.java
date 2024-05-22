import java.util.Random;


public class testClass extends mert_bayraktar_Q1 {
    public static void main(String[] args) {
        // Create an array of 100 keys and their corresponding frequencies
        int[] keys = new int[200];
        int[] freq = new int[200];
        Random rand = new Random();
        for (int i = 0; i < 200; i++) {
            keys[i] = i;
            freq[i] = rand.nextInt(100) + 1;
        }

        // Create the optimal BST
        BSTNode[][] cost = new BSTNode[keys.length + 1][keys.length + 1];
        int optimalCost = mert_bayraktar_Q1.optimalSearchTree(keys, freq, keys.length, cost);
        Node optimalBST = mert_bayraktar_Q1.constructOptimalSearchTree(cost);

        // Create two random BSTs
        BinarySearchTree randomBST1 = new BinarySearchTree();
        BinarySearchTree randomBST2 = new BinarySearchTree();
        for (int i = 0; i < 200; i++) {
            int randomKey = keys[rand.nextInt(200)];
            randomBST1.insert(randomKey);
            randomBST2.insert(randomKey);
        }

        // Measure the average running time of search for each tree
        long startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < freq[i]; j++) {
                randomBST1.search(randomBST1.root, keys[i]);
            }
        }
        long endTime = System.nanoTime();
        double randomBST1Time = (endTime - startTime) / 1000000.0;

        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < freq[i]; j++) {
                randomBST2.search(randomBST2.root, keys[i]);
            }
        }
        endTime = System.nanoTime();

        double randomBST2Time = (endTime - startTime) / 1000000.0;
        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
              for (int j = 0; j < freq[i]; j++) {
                mert_bayraktar_Q1.search(optimalBST, keys[i]);
                }
            }
        endTime = System.nanoTime();

        double optimalBSTTime = (endTime - startTime) / 1000000.0;          
                // Print the results
                System.out.println("Optimal BST cost: " + optimalCost);
                System.out.println("Random BST 1 average search time: " + randomBST1Time + " ms");
                System.out.println("Random BST 2 average search time: " + randomBST2Time + " ms");
                System.out.println("Optimal BST average search time: " + optimalBSTTime + " ms");
            }
        }
