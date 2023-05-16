package leetcode.utils;

/*
*
*   对数组的输出
* */
public class PrintArrays {

    private PrintArrays() {}

    public static void print2DIntArray(int[][] arr) {
        System.out.print('[');
        for (int i = 0; i < arr.length; i++) {
            System.out.print('[');
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                if (j != arr[i].length - 1) System.out.print(',');
                else System.out.print(']');
            }
            if (i != arr.length - 1) System.out.print(',');
        }
        System.out.println("]");
    }
    public static void print1DIntArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }
    public static void print3DIntArray(int[][][] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("[");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print("[");
                for (int k = 0; k < arr[j].length; k++) {
                    System.out.print(String.format("%d", arr[i][j][k]));
                    if (k != arr[j].length - 1) System.out.printf(",");
                    else System.out.printf("]");
                }
                if (j != arr[i].length - 1) System.out.printf(",");
                else System.out.printf("]");
            }
            if (i != arr.length - 1) System.out.printf(",");
        }
        System.out.println("]");
    }


    public static void print1DObjArray(Object[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }

    public static void print2DObjArray(Object[][] arr) {
        System.out.print('[');
        for (int i = 0; i < arr.length; i++) {
            System.out.print('[');
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                if (j != arr[i].length - 1) System.out.print(',');
                else System.out.print(']');
            }
            if (i != arr.length - 1) System.out.print(',');
        }
        System.out.println("]");
    }


    public static void main(String[] args) {
        int[][] nums = {{323,432},{42,42}};
        print2DIntArray(nums);
        int[] num = {123,434};
        print1DIntArray(num);
    }
}
