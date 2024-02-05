public class Main {
    public static void main(String[] args) {
        // create a new CompleteBinaryTree object
        mert_bayraktar_q2 tree = new mert_bayraktar_q2();

        // generate a complete binary tree with 10 nodes
        tree.generate_complete_binary_tree(50);

        // print the size of the tree
        System.out.println("Size: " + tree.getSize());

        // print the node weights
        System.out.print("Node weights: ");
        for (int weight : tree.getNodeWeights()) {
            System.out.print(weight + " ");
        }
        System.out.println();

        // print the edge weights
        System.out.println("Edge weights:");
        int[][] edgeWeights = tree.getEdgeWeights();
        for (int i = 0; i < edgeWeights.length; i++) {
            for (int j = 0; j < edgeWeights[i].length; j++) {
                System.out.print(edgeWeights[i][j]+ " ");
            }
            System.out.println();
        }
            // use the greedy strategy to choose the child with the smallest sum of edge and node weights
            long startTimeGreedy = System.nanoTime();
            tree.greedy();
            long endTimeGreedy = System.nanoTime();
            // print the running time in milliseconds
            System.out.println("Running time of greedy algorithm: " + (endTimeGreedy - startTimeGreedy) / 1e6 + " milliseconds");


            // find the minimum total weight using the recursive algorithm
            long startTimeRecursive = System.nanoTime();
            int minWeight = tree.recursive(0);
            System.out.println("Minimum total weight: " + minWeight);
            long endTimeRecursive = System.nanoTime();
            System.out.println("Running time of recursive algorithm: " + (endTimeRecursive - startTimeRecursive) / 1e6 + " milliseconds");

        
        long startTimeDynamic = System.nanoTime();
        // create an array to store the minimum total weights of the nodes in the tree
       int[] minWeights = new int[tree.getSize()];
        // call the findMinimumTotalWeight method
        int minWeightDynamic = tree.dynamic(0, minWeights);

        // print the result
        System.out.println("Minimum total weight: " + minWeightDynamic);
        long endTimeDynamic = System.nanoTime();
        System.out.println("Running time of dynamic programming approach: " + (endTimeDynamic - startTimeDynamic) / 1e6 + " milliseconds");

    }
}

