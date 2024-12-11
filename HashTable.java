package DSAFinalProject;

//Worst case for the methods is if resizing has to occur

public class HashTable {
    private HashNode[][] table;
    private int size;

    public HashTable(){
        this.size = 0;
        this.table = new HashNode[16][];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    // O(n)
    private void dynamicResize(){
        if (size >= table.length * .75){
            HashNode[][] oldtable = table;
            table = new HashNode[table.length *2][];
            size++;

            for(HashNode[] bucket : oldtable){
                if (bucket != null){
                    for(HashNode node : bucket){
                        if(node != null){
                            for (int i = 0; i< node.valueCount; i++){
                                put(node.key, node.values[i]);
                            }
                            
                        }
                    }
                }
            }
        }
    }

    
    //Average case = O(1) Worst = O(n)
    public void put(String key, Object value){
        dynamicResize();
        int index = hash(key);

        if(table[index] == null){
            table[index] = new HashNode[1];
            table[index][0] = new HashNode(key,value);
            size++;
            return;
        }

        //if key already exists update preexisting value for key

        for( int i = 0; i < table[index].length; i++){
            if(table[index][i].key.equals(key)){
                table[index][i].addValue(value);
                return;
            }
        }

        // Add new node if key does not exist
        HashNode[] newBucket = new HashNode[table[index].length + 1];
        for (int i = 0; i < table[index].length; i++) {
            newBucket[i] = table[index][i];
        }
        newBucket[newBucket.length - 1] = new HashNode(key, value);
        table[index] = newBucket;
        size++;
    }

    //Average case = O(1) Worst = O(n)
    public Object[] get(String key){
        int index = hash(key);

        if(table[index] != null){
            for(HashNode node : table[index]){
                if(node.key.equals(key)){
                    Object[] result = new Object[node.valueCount];
                    for(int i =0; i<node.valueCount; i++){
                        result[i] = node.values[i];
                    }
                    return result;
                }
            }
        }
        return null;
    }

    //Average case = O(1) Worst = O(n)
    public void remove(String key) {
        int index = hash(key);

        if (table[index] != null) {
            for (int i = 0; i < table[index].length; i++) {
                if (table[index][i].key.equals(key)) {
                    HashNode[] newBucket = new HashNode[table[index].length - 1];
                    for (int j = 0, k = 0; j < table[index].length; j++) {
                        if (j != i) {
                            newBucket[k++] = table[index][j];
                        }
                    }
                    table[index] = newBucket;
                    size--;
                    return;
                }
            }
        }
    }
    //Average case = O(1) Worst = O(n)
    public boolean containsKey(String key) {
        int index = hash(key);

        if (table[index] != null) {
            for (HashNode node : table[index]) {
                if (node.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    //O(1)
    public int size(){
        return size;
    }

    //O(n+m)
    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("Video " + i + ": ");
            if (table[i] != null) {
                for (HashNode node : table[i]) {
                    System.out.print("(" + node.key + " -> ");
                    Object val = node.values[i];
                    if(val instanceof int[]){
                        for (int value :(int[]) val){
                            System.out.print(value + " ");
                        }
                    }else if(val instanceof String[]){
                        for(String value : (String[]) val){
                            System.out.print(value + " ");
                        }
                    }
                }
                System.out.print(")");
            }
        }
        System.out.println();
    }   
}
