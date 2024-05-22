import java.util.Random;

public class mert_bayraktar_q2 {
    // the number of nodes in the tree
    public int size;
    // the array of node weights
    private int[] nodeWeights;
    // the 2D array of edge weights
    private int[][] edgeWeights;
    // a random number generator
    private Random rand;

    public mert_bayraktar_q2() {
        // create a new random number generator
        rand = new Random();
    }

    public void generate_complete_binary_tree(int size) {
        // initialize the size and create the arrays

        this.size = size;
        nodeWeights = new int[size];
        edgeWeights = new int[size][size];

        // set the node weights and edge weights
        for (int i = 0; i < size; i++) {
            // set the node weight to a random value between 1 and 20 (inclusive)
            nodeWeights[i] = rand.nextInt(20) + 1;

            // set the edge weights for the children of this node
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            if (leftChild < size) {
                // set the edge weight between the current node and its left child
                edgeWeights[i][leftChild] = rand.nextInt(20) + 1;
            }
            if (rightChild < size) {
                // set the edge weight between the current node and its right child
                edgeWeights[i][rightChild] = rand.nextInt(20) + 1;
            }
        }
    }

    // RECURSIVE APPROACH

        // implement a recursive algorithm to find the minimum total weight
        public int recursive(int currentNode) {
            // base case: if we have reached a leaf node, return the node weight
            // The below line is the base case of the recursive function. 
            // If currentNode is greater than or equal to the size of the tree, the method returns 0. 
            // The time complexity of this line is O(1).
            if (currentNode >= size) {
                return 0;
            }
        //The below line calculates the index of the left child of the current node. The time complexity of this line is O(1).
// The below line calculates the index of the right child of the current node. The time complexity of this line is O(1).
// The below lines initialize the variables leftWeight and rightWeight to 0. The time complexity of these lines is O(1).
            // recursive case: find the minimum total weight for the left and right children
            int leftChild = 2 * currentNode + 1;
            int rightChild = 2 * currentNode + 2;
            int leftWeight = 0;
            int rightWeight = 0;
            // The below two lines of the recursive method are the recursive case of the function. 
            // If the left or right child exists, 
            // the method calls itself recursively to calculate the minimum total weight for the child, 
            // and adds the edge weight between the current node and the child.

// The time complexity of these lines is O(1) for the addition and O(n) for the recursive call, 
// for a total of O(n). This is because the recursive call to the recursive method has a time complexity of O(n), 
// and the addition operation has a time complexity of O(1).
            if (leftChild < size) {
                leftWeight = recursive(leftChild) + edgeWeights[currentNode][leftChild];
            }
            if (rightChild < size) {
                rightWeight = recursive(rightChild) + edgeWeights[currentNode][rightChild];
            }
        // The final line of the recursive method returns the minimum of the left and right weights, plus the node weight 
        //of the current node. 
        // The time complexity of this line is O(1) for the Math.min function and O(1) for the array access, for a total of O(1).
            // return the minimum of the left and right weights, plus the node weight
            return Math.min(leftWeight, rightWeight) + nodeWeights[currentNode];
        }

        // DYNAMIC APPROACH


        public int dynamic(int currentNode, int[] minWeights) {
            // base case: if we have reached a leaf node, return the node weight

            // The below line checks if the current node is a leaf node, and if it is, it returns 0. This takes O(1) time. 
            if (currentNode >= size) { 
                return 0;
            }

            // The below line checks if the minimum weight for the current node has already been calculated, 
            // and if it has, it returns the stored value. This takes O(1) time.

            // check if the minimum weight for the current node has already been calculated
            if (minWeights[currentNode] != 0) {
                return minWeights[currentNode];
            }
            // The below 2 lines calculate the indices of the left and right children of the current node. 
            // This takes O(1) time.
            // recursive case: find the minimum total weight for the left and right children
            int leftChild = 2 * currentNode + 1;
            int rightChild = 2 * currentNode + 2;
            // The below 2 lines  lines initialize the leftWeight 
            // and rightWeight variables to 0. This takes O(1) time.

            int leftWeight = 0;
            int rightWeight = 0;
            // These lines calculate the minimum total weights for the left and 
            // right children of the current node by calling the dynamic method recursively 
            // and adding the edge weight between the current node and the child. 
            // The time complexity of these lines is O(1) for the addition and O(n) for the recursive call, for a total of O(n).
            if (leftChild < size) {
                // access the left child
                leftWeight = dynamic(leftChild, minWeights) + edgeWeights[currentNode][leftChild];
            }
            if (rightChild < size) {
                // access the right child
                rightWeight = dynamic(rightChild, minWeights) + edgeWeights[currentNode][rightChild];
            }

            // The below line returns the minimum of the leftWeight and rightWeight variables, 
            // plus the node weight of the current node. This takes O(1) time.
            // store the minimum of the left and right weights in the minWeights array
            minWeights[currentNode] = Math.min(leftWeight, rightWeight);
    
            // return the minimum of the left and right weights, plus the node weight
            return minWeights[currentNode] + nodeWeights[currentNode];
        }
    
    

    // implement the greedy strategy of choosing the child with the smallest sum of edge and node weights
    public void greedy() {
        // start at the root node
        int currentNode = 0;

        // continue until we reach a leaf node
        while (currentNode < size) {
            // get the left and right children of the current node
            int leftChild = 2 * currentNode + 1;
            int rightChild = 2 * currentNode + 2;

            // choose the child with the smallest sum of edge and node weights
            if (leftChild >= size || rightChild >= size) {
                // we have reached a leaf node, so we can stop
                break;
            } else if (nodeWeights[leftChild] + edgeWeights[currentNode][leftChild] < nodeWeights[rightChild] + edgeWeights[currentNode][rightChild]) {
                // the left child has the smallest sum, so move to the left child
                currentNode = leftChild;
            } else {
                // the right child has the smallest sum, so move to the right child
                currentNode = rightChild;
            }
        }

        // print the node that was reached
        System.out.println("Reached node " + currentNode + " with weight " + nodeWeights[currentNode]);
    }

    // getter methods for the size, node weights, and edge weights
    public int getSize() {
        return size;
    }

    public int[] getNodeWeights() {
        return nodeWeights;
    }
    public int[][] getEdgeWeights() {
        return edgeWeights;
    }
}

