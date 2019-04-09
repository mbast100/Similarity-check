

public class TreeWordMap implements WordMap {

    private static class Elem {

        private String key;
        private int count;
        private Elem left, right;
        //String array for keys


        private Elem(String key) {
            this.key = key;
            count = 1;
            left = null;
            right = null;
        }

    }

    private String[] box;
    private Integer[] countArray;
    private int position;
    private int positionCounts;

    private Elem root;
    private int size;

    public TreeWordMap(){
        size = 0;
        root = null;
        position = 0;
    }

    /**
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param word the specified word
     * @return true if and only if this WordMap contains the specified word
     * @throws NullPointerException if the value of the parameter is null
     */
    
    public boolean contains(String key) {
        boolean found = false;
        Elem current = root;
        while (! found && current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {
                found = true;
            } else if (test < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return found;
    }
    
    /**
     * Increments by 1 the counter associated with the specified
     * word. If the specified word is absent from the data structure,
     * a new association is created.
     *
     * @param word the specified word
     * @throws NullPointerException if the value of the parameter is null
     */
    
    public void update(String key) {

        if(key == null){throw new NullPointerException("key can not be null");}

        if(size() == 0 ){
            root = new Elem(key);
            size++;
        }
        else if(contains(key)){
            Elem current = root;
            while (true) {
                int test = key.compareTo(current.key);
                if (test == 0) {
                    current.count += 1;
                    break;
                } else if (test < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }
        else{
            Elem current;
            current =root;
            boolean flag = false;
            while(flag == false){
                int test = key.compareTo(current.key);
                
                if(test < 0){
                    if(current.left != null){
                    //pointer = current;
                        current = current.left;
                    }
                    else{ 
                        current.left = new Elem(key);
                        size++;
                        flag = true;
                    }

                }
                else if(test > 0){
                        if(current.right != null){current = current.right;}

                        else{
                            current.right = new Elem(key);
                            size++;
                            flag = true;
                        }
                }
            }    
        }    
    }
    
    /**
     * Returns the count associated with the specified word or 0 if
     * the word is absent.
     *
     * @param word the specified word
     * @return the count associated with the specified word or 0 if absent
     * @throws NullPointerException if the value of the parameter is null
     */
    
    public int get(String key) {
        boolean found = false;
        Elem current = root;
        while (current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {
                return current.count;
            } else if (test < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return 0;
    }
    
    /**
     * Returns the logical size of this WordMap. That is the number of
     * associations currently stored in it.
     *
     * @return the logical size of this WordMap
     */
    
    public int size() {
        return size;
    }
    
    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */

     
    
    public String[] keys() {
        if(size() == 0){throw new IllegalStateException("nothing in the list");}

        box = new String[this.size()];
        box = key(root);  
        for(int i = 0; i < box.length; i ++){

        }  
        return box;
    }

    private String[] key( Elem current){
        if( current == null){
            return box;
        }
        else{
            key(current.left);
            box[position++] = current.key;
            key(current.right);

        }
        return box;

    }
  

/**
 * Returns all the counts associated with keys in this
 * WordMap. The counts are in the same order as that of the keys
 * returned by the method keys().
 *
 * @return all the counts
 */

    public Integer[] counts() {
        if(size() == 0){throw new IllegalStateException("nothing in the list");}

        positionCounts = 0;
        countArray = new Integer[this.size()];
        return count(root);
    }
    
    private Integer[] count( Elem current){

        if( current == null){
            return countArray;
        }
        else{
            count(current.left);
            countArray[positionCounts++] = current.count;
            count(current.right);
        }
        return countArray;

    }
}
    