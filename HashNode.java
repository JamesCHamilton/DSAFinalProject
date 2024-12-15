package DSAFinalProject;

public class HashNode {
    String key;
    Object[] values;
    int valueCount;

    public HashNode(String key, Object value){
        this.key = key;
        this.values = new Object[10];
        this.values[0] = value;
        this.valueCount = 1;
    }


    // Average case = O(1), Worst case = O(n)
    void addValue(Object value){
        if(valueCount == values.length){
            Object[] newValues = new Object[values.length+ 1];
            for (int i = 0; i < values.length; i++){
                newValues[i] = values[i];
            }
            values = newValues;
        }
        values[valueCount++] = value;
    }

    
}
