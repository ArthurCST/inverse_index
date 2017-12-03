
import java.util.ArrayList;


public class BSTree {
    BSNode root;
    ArrayList<String> keys = new ArrayList<>();
    
    BSNode insert(BSNode node, Word key) {
        if (node == null)
            return (new BSNode(key));

        if (key.content.compareTo(node.key.content) < 0)
            node.left = insert(node.left, key);
        else if (key.content.compareTo(node.key.content) >= 0)
            node.right = insert(node.right, key);
        else
            return node;
        
        return node;
    }
    
    public BSNode search(BSNode root, Word key) {
        if (root == null || (root.key.content.compareTo(key.content) == 0 && root.key.file == key.file))
            return root;

        if (root.key.content.compareTo(key.content) > 0)
            return search(root.left, key);

        return search(root.right, key);
    }
}
