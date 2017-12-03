
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

public class Manager {

    public ArrayList<File> createListOfFiles(String[] filenames) {
        ArrayList<File> files = new ArrayList<>();

        for(int i = 0; i < filenames.length; i++) {
            File file = new File();

            file.file_id = i;
            file.name = filenames[i];

            files.add(file);
        }

        return files;
    }

    public AVLTree saveWords(ArrayList<File> files) {
        AVLTree tree = new AVLTree();

        for(File file : files) {
            FileReader fr = file.openFile();

            BufferedReader br = file.readFile(fr);

            try {
                String line = br.readLine();

                while(br.ready()) {
                    String[] words = line.split("[ ,.!;:?]");

                    for(String word: words){
                       if(word.length() >= 4){
                            Word w = new Word(Normalizer.normalize(word.toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), file);
                            if(!this.exists(tree, w)) {
                                tree.root = tree.insert(tree.root, w);

                            }
                       }
                    }

                    line = br.readLine();
                }

                String[] words = line.split("[ ,.!;:?]");

                for(String word: words){
                    if(word.length() >= 4){
                         Word w = new Word(Normalizer.normalize(word.toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""), file);
                         if(!this.exists(tree, w)) {
                             tree.root = tree.insert(tree.root, w);

                         }
                    }
                 }

            } catch (IOException ex) {
                System.out.println("Não foi possível ler a linha em '" + file.name + "'");
            }
        }
        return tree;

    }

    public boolean exists(AVLTree tree, Word word) {
        AVLNode node = tree.search(tree.root, word);
        if(node == null) {
            tree.keys.add(word.content);
            return false;
        } else {
            if(node.key.file == word.file) {
                node.key.number++;
                return true;
            } else {
                return false;
            }
        }
    }

    public int count(File file, AVLTree tree, String key) {
        int counter = 0;

        AVLNode foundNode = tree.search(tree.root, new Word(key, file));

        if(foundNode != null) {
            return foundNode.key.number;
        }

        return counter;
    }

    public void show_occurs(AVLTree tree, ArrayList<File> files) {
        for(String key : tree.keys) {
            System.out.println();
            System.out.print(key);
            for(File file : files) {
                System.out.print(", " + file.file_id);
                System.out.print(" " + this.count(file, tree, key));
            }
        }
    }
}
