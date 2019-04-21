
public class Jaccard implements Similarity{


    public double score( WordMap a, WordMap b ){

        if (a ==null || b==null){
            throw new NullPointerException();
        }
        if (a.size()==0 || b.size()==0){
            throw new IllegalArgumentException();
        }

        String [] A = a.keys();
        String [] B = b.keys();

        double intersection=0;
        int skip = 0;
        for (int i=0; i < A.length; i++){
            for (int j = skip; j < B.length; j++){
  
                if( A[i].equals(B[j])){
                    intersection++;
                    skip = j;
                    break;
                }
            }

            
        }

        double union= a.size()+b.size()-intersection;
        

        return intersection/union;
    }

    public String flag(double result){

        String out = new String();
        if(result < 0){
            throw new IllegalArgumentException();
        }
        else if(result > 50.0){
            out = "Warning this may be a copy!";
        }
        return out;
    }
}