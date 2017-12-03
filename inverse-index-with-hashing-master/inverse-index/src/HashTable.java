
import java.util.ArrayList;



class HashTable {
    public HashNode[] node;		
    private int size;
    public ArrayList<String> keys = new ArrayList<>();

    public HashTable(int size) {
        this.size = size;
        
    }
    
    public void create() {
        this.node = new HashNode[this.size];

    }

    public int hash(String palavra) {
        int soma = 0;
        
        for(int i = 0; i < palavra.length(); i++) {
            soma = Math.abs((soma * 256 + palavra.charAt(i))) % this.size;
        }
        
        return soma;
    }
    
    public void insert(Word word) {
        int h = hash(word.content);
        
        if(!this.keys.contains(word.content)) {
            this.keys.add(word.content);
        }
        
        if(this.node[h] == null) {
            this.node[h] = new HashNode();
            this.node[h].words.add(word);
            
        } else {
            ArrayList<Word> list = search(word.content);
            
            for(Word w : list) {
                if(word.content == w.content && word.file == w.file) {
                    w.number++;
                    return;
                }
            }
            this.node[h].words.add(word);
        }	
    }

    public ArrayList<Word> search(String content) {
        int h = hash(content);

        return this.node[h].words;
    }
}