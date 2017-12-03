import java.util.ArrayList;


public class AVLTree {
    AVLNode root;
    ArrayList<String> keys = new ArrayList<>();
    
    int height(AVLNode node) {
        if (node == null)
            return 0;

        return node.height;
    }


    int max(int a, int b) {
        return (a > b) ? a : b;
    }


    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }


    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }


    int getBalance(AVLNode node) {
        if (node == null)
            return 0;

        return height(node.left) - height(node.right);
    }


    AVLNode insert(AVLNode node, Word key) {
        if (node == null)
            return (new AVLNode(key));

        if (key.content.compareTo(node.key.content) < 0)
            node.left = insert(node.left, key);
        else if (key.content.compareTo(node.key.content) >= 0)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.content.compareTo(node.left.key.content) < 0)
            return rightRotate(node);

        if (balance < -1 && key.content.compareTo(node.right.key.content) >= 0)
            return leftRotate(node);

        if (balance > 1 && key.content.compareTo(node.left.key.content) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.content.compareTo(node.right.key.content) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public AVLNode search(AVLNode root, Word key) {
        if (root == null || (root.key.content.compareTo(key.content) == 0 && root.key.file == key.file))
            return root;

        if (root.key.content.compareTo(key.content) > 0)
            return search(root.left, key);

        return search(root.right, key);
    }
}
