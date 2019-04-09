import java.util.Iterator;

/**
 * An implementation of the interface WordMap using linked elements.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */

// Author: Mark Bastawros and Alessandro Miguel Tirado
// Student number: 8123595, 8349209
// Course: ITI 1121-A
// Assignment: 4
// Question: 2.1 

public class LinkedWordMap implements WordMap {

    /**
     * Returns the logical size of this WordMap. That is the number of
     * associations currently stored in it.
     *
     * @return the logical size of this WordMap
     */

    private static class Node{

        //Node instance variables 

        private int count;
        private String key;
        private Node previous;
        private Node next;

        private Node(String key, int count, Node previous, Node next){
            
            this.key = key;
            this.count = count;
            this.previous = previous;
            this.next = next;

        }
        
    }
    // no tail since it is circular
    private Node head;
    private Node pointer;
    
    // constructor
    public LinkedWordMap(){

        head = new Node(null,0,null,null);
        head.next = head;
        head.previous = head;

    }


    /**
     * Returns the logical size of this WordMap. That is the number of
     * associations currently stored in it.
     *
     * @return the logical size of this WordMap
     */
    
    public int size() {

        int size = 0;
        pointer = head;
        //visit first then count
        while(pointer.next != head){
            pointer = pointer.next;
            size++;
        }
        return size;   
    }

    /**
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param key the specified word
     * @return true if and only if this WordMap contains the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public boolean contains(String key) {

        if(key == null){throw new NullPointerException();}
        pointer = head.next;
        
        while(pointer != head){

            if(pointer.key.equals(key)){return true;}
            
            else{
                pointer = pointer.next;
            }

        }
        return false;
    }
    
    /**
     * Returns the count associated with the specified word or 0 if
     * the word is absent.
     *
     * @param key the specified word
     * @return the count associated with the specified word or 0 if absent
     * @throws NullPointerException if the value of the parameter is null
     */
    
    public int get(String key) {

        if(key == null){
            throw new NullPointerException("key can not be null");
        }
        else if(!contains(key)){ return 0;}

        else{
            pointer = head.next;

            if(pointer.key == key){return pointer.count;}
            else if(head.previous.key == key){return head.previous.count;}

            while(pointer.key != key){
                pointer = pointer.next;
            }
            return pointer.count;
        }

    }
    
    /**
     * Increments by 1 the counter associated with the specified
     * word. If the specified word is absent from the data structure,
     * a new association is created.
     *
     * @param key the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public void update(String key) {

        if(key == null){throw new NullPointerException("key can not be null");}
        
        if(this.size() == 0){

            Node tmp = new Node(key,1,head,head);
            head.next = tmp;
            head.previous = tmp;
        }
        else if(contains(key)){
            
            pointer = head.next;
            while(!pointer.key.equals(key)){pointer = pointer.next;}
            pointer.count ++;
        }

        else if(head.previous.key.compareTo(key) < 0){

            pointer = head.previous;
            Node tmp2 = new Node(key, 1, pointer, head);
            pointer.next = tmp2;
            head.previous = tmp2;
        }
        else {
            pointer = head.next;

            while(pointer.key.compareTo(key)  < 0){

                pointer = pointer.next;
            }
            Node tmp = new Node(key,1,pointer.previous, pointer);
            
            pointer.previous.next = tmp;
            pointer.previous = tmp;


        }

        
    }
    
    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */
    public String[] keys() {

        String[] keys = new String[this.size()];

        if(this.size() == 0){throw new IllegalStateException("nothing in the list");}

    
        int i =0;
        pointer = head.next;
        while(pointer != head){
            keys[i] = pointer.key;
            pointer = pointer.next;
            i++;
        }
    

        return keys;

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

        
        Integer[] counts = new Integer[this.size()];

        int i =0;
        pointer = head.next;
        while(pointer != head){
            counts[i] = pointer.count;
            pointer = pointer.next;
            i++;
        }
        return counts;

        
    }
    
}
