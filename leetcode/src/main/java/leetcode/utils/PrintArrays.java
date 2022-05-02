package leetcode.utils;

/*
*
*   对数组的输出
* */
public class PrintArrays {

    private PrintArrays() {}

    public static void print2DIntArray(int[][] arr) {
        if (arr.length == 0) {
            System.out.println("[]");
            return ;
        }
        int n = arr.length, m = arr[0].length;
        System.out.print('[');
        for (int i = 0; i < n; i++) {
            System.out.print('[');
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j]);
                if (j != m - 1) System.out.print(',');
                else System.out.print(']');
            }
            if (i != n - 1) System.out.print(',');
            else System.out.println(']');
        }
    }
    public static void print1DIntArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(",");
            else System.out.println("]");
        }
    }

    public static void print1DObjArray(Object[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) System.out.print(",");
            else System.out.println("]");
        }
    }

    public static void print2DObjArray(Object[][] arr) {
        if (arr.length == 0) {
            throw new RuntimeException("必须是二维数组啊，亲人");
        }
        int n = arr.length, m = arr[0].length;
        System.out.print('[');
        for (int i = 0; i < n; i++) {
            System.out.print('[');
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j]);
                if (j != m - 1) System.out.print(',');
                else System.out.print(']');
            }
            if (i != n - 1) System.out.print(',');
            else System.out.println(']');
        }
    }


    public static void main(String[] args) {
        int[][] nums = {{323,432},{42,42}};
        print2DIntArray(nums);
        int[] num = {123,434};
        print1DIntArray(num);
    }
}
