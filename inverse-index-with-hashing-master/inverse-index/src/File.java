
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class File {    
    int file_id;
    String name;
    
    public File() {}
    
    public File(int file_id, String name) {
        this.file_id = file_id;
        this.name = name;
    }
    
    private int getNumberOfWords(ArrayList<File> files) {
        int counter = 0;

        for(File file : files) {
                FileReader fr = file.open();

                BufferedReader br = new BufferedReader(fr);

                try {
                String line = br.readLine();

                while(br.ready()) {
                    String[] words = line.split("[ ,.!;:?]");
           
                    for(String word: words){
                       if(!word.isEmpty() && word.length() >= 4){  
                            counter += 1; 
                        }
                    }

                    line = br.readLine();
                }
                
                String[] words = line.split("[ ,.!;:?]");
           
                for(String word: words){
                   if(!word.isEmpty() && word.length() >= 4){  
                        counter += 1; 
                    }
                }
                
            } catch (IOException ex) {
                System.out.println("Não foi possível ler a linha em '" + file.name + "'");
            }

        }

        return counter;

    }
    
    public ArrayList<File> create(String[] filenames) {
        ArrayList<File> files = new ArrayList<>();
        
        for(int i = 0; i < filenames.length; i++) {
            File file = new File();
            
            file.file_id = i;
            file.name = filenames[i];
            
            files.add(file);
        } 
        
        return files;
    }
    
    
    private FileReader open() {
        try {
            return new FileReader("/home/laws/" + this.name);
        } catch (FileNotFoundException ex) {
            System.out.println("'" + this.name + "' não foi encontrado!");
            return null;
        }
    }
    
    
    private BufferedReader read(FileReader fr) {
        return new BufferedReader(fr);
    }
    
    public HashTable saveWords(ArrayList<File> files) {
        HashTable table = new HashTable(getNumberOfWords(files));
        table.create();
        
        for(File file : files) {
            FileReader fr = file.open();
            
            BufferedReader br = this.read(fr);
            
            try {
                String line = br.readLine();

                while(br.ready()) {
                    String[] words = line.split("[ ,.!;:?]");
           
                    for(String word: words){
                       if(!word.isEmpty() && word.length() >= 4){  
                            table.insert(new Word(word.toLowerCase(), file));
                        }
                    }

                    line = br.readLine();
                }
                
                String[] words = line.split("[ ,.!;:?]");
           
                for(String word: words){
                    if(!word.isEmpty() && word.length() >= 4){  
                        table.insert(new Word(word.toLowerCase(), file));
                    }
                }
                
            } catch (IOException ex) {
                System.out.println("Não foi possível ler a linha em '" + file.name + "'");
            }
        }
        return table;
        
    }
    
    public int count(File file, ArrayList<Word> list, String key) {
        int counter = 0;

        for(Word word : list) {
            if(word.content.equals(key) && word.file == file) {
                return word.number;
            }
        }
        return counter;
    }
    
    public void show_repeticoes(HashTable table, ArrayList<File> files) {
	for(String key : table.keys) {
            int h = table.hash(key);
            
            System.out.println();
            System.out.print(key);
            
            for(File file : files) {
                System.out.print(", " + file.file_id);
                System.out.print(" " + this.count(file, table.node[h].words, key));
            }
	}
    }
}
