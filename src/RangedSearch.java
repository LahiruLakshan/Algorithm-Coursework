public class RangedSearch {
    public static int findInRange (int [] A, int low , int high ){
        int first =0, last =A.length , middle =A.length /2;
        while (first <= last ){
            if(A[ middle ]<low )
                last =middle -1;
            else if(A[ middle ]> high )
                first = middle +1;
            else
                return middle ;
            middle =( first + last )/2;
        }
        return -1;
    }
    public static void main(String[] args) {
        int [] A = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 30,33};
        int range = findInRange(A, 13, 19);
        System.out.println(range);
    }

}
