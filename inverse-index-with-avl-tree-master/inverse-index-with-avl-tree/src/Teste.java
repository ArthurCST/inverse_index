
import java.util.ArrayList;


public class Teste {
    public static void main(String[] args) {
        String[] nameOfFiles = {"teste.txt"};
        
        Manager manager = new Manager();
        
        ArrayList<File> files = manager.createListOfFiles(nameOfFiles);
        
        AVLTree tree = manager.saveWords(files);        
        
        manager.show_occurs(tree, files);

    }
}

