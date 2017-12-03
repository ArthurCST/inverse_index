

public class AVLNode {
    Word key;
    int height;
    AVLNode left, right;

    public AVLNode(Word key) {
        this.key = key;
	       this.height = 1;
    }
}
