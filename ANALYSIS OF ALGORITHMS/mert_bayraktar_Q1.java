public class mert_bayraktar_Q1 {

    static class BSTNode {
        int cost;
        int root;
    }

    static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    static int[] keys;

    static int optimalSearchTree(int keys[], int freq[], int n, BSTNode[][] cost) {

        mert_bayraktar_Q1.keys = keys;

        // Initialize each element of the matrix
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                cost[i][j] = new BSTNode();
            }
        }

        // For a single key, cost is equal to frequency of the key
        for (int i = 0; i < n; i++) {
            cost[i][i].cost = freq[i];
            cost[i][i].root = i;
        }

        // Now we need to consider chains of length 2, 3, ... .
        // L is chain length.
        for (int L = 2; L <= n; L++) {
            // i is row number in cost[][]
            for (int i = 0; i <= n - L + 1; i++) {
                // Get column number j from row number i and chain length L
                int j = i + L - 1;
                cost[i][j].cost = Integer.MAX_VALUE;
                int off_set_sum = sum(freq, i, j);
                            // Try making all keys in interval keys[i..j] as root
            for (int r = i; r <= j; r++) {

                // c = cost when keys[r] becomes root of this subtree
                int c = ((r > i) ? cost[i][r - 1].cost : 0) + ((r < j) ? cost[r + 1][j].cost : 0) + off_set_sum;
                if (c < cost[i][j].cost) {
                    cost[i][j].cost = c;
                    cost[i][j].root = r;
                }
            }
        }
    }
    return cost[0][n - 1].cost;
}

// A utility function to get sum of array elements freq[i] to freq[j]
static int sum(int freq[], int i, int j) {
    int s = 0;
    for (int k = i; k <= j; k++) {
        if (k >= freq.length) {
            continue;
        }
        s += freq[k];
    }
    return s;
}

static Node constructOptimalSearchTree(BSTNode[][] cost) {
    return constructOptimalSearchTreeRecursive(cost, 0, cost.length - 1);
}

static Node constructOptimalSearchTreeRecursive(BSTNode[][] cost, int start, int end) {
    if (start > end) {
        return null;
    }
    int rootIndex = cost[start][end].root;
    Node root = new Node(keys[rootIndex]);
    if (start <= rootIndex - 1) {
        root.left = constructOptimalSearchTreeRecursive(cost, start, rootIndex - 1);
    }
    //root.left = constructOptimalSearchTreeRecursive(cost, start, rootIndex - 1);
    //root.right = constructOptimalSearchTreeRecursive(cost, rootIndex + 1, end);
    //root.right = constructOptimalSearchTreeRecursive(cost, rootIndex + 1, end);
    if (rootIndex + 1 <= end) {
        root.right = constructOptimalSearchTreeRecursive(cost, rootIndex + 1, keys.length - 1);
    }    
    //root.right = constructOptimalSearchTreeRecursive(cost, rootIndex + 1, keys.length - 1);
    return root;
}

public static Node search(Node root, int key)
{
    // Base Cases: root is null or key is present at root
    if (root==null || root.key==key)
        return root;

    // Key is greater than root's key
    if (root.key < key)
        return search(root.right, key);
        // Key is smaller than root's key
    return search(root.left, key);
}


public static void inorder(Node root) {
    if (root != null) {
        inorder(root.left);
        System.out.print(root.key + " ");
        inorder(root.right);
    }
}

public static void main(String[] args) {
    int keys[] = {10, 12, 20};
    int freq[] = {50,8,34};
    int n = keys.length;
    BSTNode[][] cost = new BSTNode[n + 1][n + 1];
    optimalSearchTree(keys, freq, n, cost);
    System.out.println("Cost of Optimal BST is " + cost[0][n - 1].cost);
    System.out.println("Root of Optimal BST is " + keys[cost[0][n - 1].root]);
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            System.out.println("Cost of Optimal BST for subproblem keys[" + i + ".." + j + "] is " + cost[i][j].cost);
            System.out.println("Root of Optimal BST for subproblem keys[" + i + ".." + j + "] is " + keys[cost[i][j].root]);
    }
    }
    Node root = constructOptimalSearchTree(cost);
    inorder(root);
    System.out.println("Optimal BST constructed successfully!");
}
}
