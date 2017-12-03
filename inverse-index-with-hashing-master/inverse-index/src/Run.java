
import java.util.ArrayList;


public class Run {
    public static void main(String[] args) { 
        String[] filenames = {"teste.txt"};
        
        File file = new File();
        
        ArrayList<File> files = file.create(filenames);
        
        HashTable table = file.saveWords(files);
        
        file.show_repeticoes(table, files);
        
    }
}
