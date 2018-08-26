import java.util.Arrays;

public class lesson6 {


    public static int[] task1(int[] arr){
        int last4;
        for (int i = arr.length - 1; i > 0; i--) {
            if(arr[i] == 4){
                last4 = i;
                int[] result = Arrays.copyOfRange( arr, last4, arr.length );
                return result;
            }
        }
        throw new RuntimeException();
    }

    public static boolean task2(int[] arr){
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] != 1 && arr[i] != 4) return false;
        }
        return true;
    }

}
