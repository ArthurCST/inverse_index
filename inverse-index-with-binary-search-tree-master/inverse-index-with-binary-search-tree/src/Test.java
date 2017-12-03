
import java.util.ArrayList;



public class Test {
    public static void main(String[] args) {
        String[] nameOfFiles = {"entrada5.txt"};
        
        Manager manager = new Manager();
        
        ArrayList<File> files = manager.createListOfFiles(nameOfFiles);
        
        BSTree tree = manager.saveWords(files);        
        
        manager.show_occurs(tree, files);
    }
}
