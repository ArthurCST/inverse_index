
public class BSNode {
    Word key;
    int height;
    BSNode left, right;

    public BSNode(Word key) {
        this.key = key;
	this.height = 1;
    }
}
