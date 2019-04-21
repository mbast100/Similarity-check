
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WordReader {

    // The content of the file that was read
    
    private String content;

    /**
     * When an object of the class WordReader is created, this
     * constructor reads the content the file specified by the
     * parameter fileName.
     *
     * @param fileName the specified file
     * @throws FileNotFoundException if the file could not be found
     * @throws IOException if there is an error reading the content of the file
     */
    
    public WordReader(String fileName) throws FileNotFoundException, IOException {
        this(fileName, true);
    }
    
    /**
     * When an object of the class WordReader is created, this
     * constructor reads the content the file specified by the
     * parameter fileName.
     *
     * @param fileName the specified file
     * @param caseSensitive if the value is false, the content is transformed to lower case letters
     * @throws FileNotFoundException if the file could not be found
     * @throws IOException if there is an error reading the content of the file
     */
    
    public WordReader(String fileName, boolean caseSensitive) throws FileNotFoundException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        StringBuilder buffer= new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            if (! caseSensitive) {
                line = line.toLowerCase();
            }
            buffer.append(line);
        }
        
        reader.close();

        content = buffer.toString();
    }


    //iterator implementation of java util iterator 
    private class Iterator <String> implements java.util.Iterator<String> {

        // instance variables 
        private int currentPosition;
        private int gramSize; 


        //constructor

        private Iterator( int size){    
            currentPosition = 0 ;
            gramSize = size;
        }
        /**
         * Returns a boolean based on an n-gram can be created in the next position
         *
         * @return  a boolean based on an n-gram can be created in the next position
         */

        public boolean hasNext(){

            if (currentPosition <= content.length()-gramSize){
                return true;
            }
            return false;
        }

        /**
         * Returns a String based on a the next n-gram
         *
         * @return  a String based on a the next n-gram
         */


        public String next(){
            String alpha;
            if(hasNext() == false){
                System.out.println("gram is greater than remaining text");
                return null;
            }
            //            String[] a1= content.split(regex, limit);
            alpha = (String) content.substring(currentPosition, currentPosition+gramSize);         
            currentPosition++;
            return alpha;
        }
    }


    /**
     * Removes all the blank spaces from the content of the text.
     */
    
    public void removeAllBlankCharacters() {
        content = content.replaceAll("\\p{Blank}","");
    }

    /**
     * Returns an iterator over the content in the text.
     *
     * @param size the size of the n-grams to be returned by the method of the iterator
     * @return an iterator over the content in the text
     */
    
    public Iterator<String> iterator(int size) {

        if(size<2 || size> content.length()  ){
            System.out.println("size is smaller than 2 or greater than text length");
            //throw new IllegalArgumentException("size is smaller than 2 or greater than text length");
            return null;
        }
        
        Iterator<String> i = new Iterator<String>(size);

        return i;


    }

}