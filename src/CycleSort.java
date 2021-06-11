public class CycleSort {

    public static void sort (int [] values ){
        boolean [] inPlace = new boolean [ values . length ]; // initially all false
        for (int i=0; i< values . length ; )
            if( inPlace [i])
// this value is in the right place , move on
                i++;
            else {
// determine where this value belongs
                int p=0;
                for(int j : values )
                    if(j < values [i])
                        p++;
// swap entries at positions i and p
                int temp = values [i];
                values [i] = values [p];
                values [p] = temp ;
                inPlace [p] = true ;
            }
    }

    public static void main(String[] args) {
        int[] values = new int[]{4,3,5,7,9,1};
        sort(values);
        for (int j : values) System.out.print(j + " ");	}
}
