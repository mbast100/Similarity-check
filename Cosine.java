/**
 * @author Mark Bastawros
 * Cosine similarity is a measure of similarity between two non-zero vectors
 * of an inner product space that measures the cosine of the angle between them
 */
import java.lang.Math; 

public class Cosine implements Similarity{

//Calculates the cosine similarity measure between two documents represented by their WordMap objects.

    public double score( WordMap a, WordMap b ){

        if (a == null || b == null){
            throw new NullPointerException();
        }
        if (a.size() == 0 || b.size() == 0){
            throw new IllegalArgumentException();
        }

        Integer [] alpha = a.counts();
        Integer [] beta = b.counts();


        String [] letraA = a.keys();
        String [] letraB = b.keys();


        double top=0;
        double bot1=0;
        double bot2=0;

        int skip=0;
        for (int i=0; i<alpha.length; i++){

            bot1 += (Math.pow(alpha[i], 2))  ;
            for (int j=skip; j<beta.length; j++){

                bot2 += (Math.pow(beta[j], 2))  ;
                
                if(a.contains(letraA[i]) && b.contains(letraA[i])){
                    top += (alpha[i] * beta[j]);
                    skip=j;
                    break;
                }
            }
        }
        double bot = Math.sqrt(bot1) * Math.sqrt(bot2) ;
        return top/bot;

    }
}