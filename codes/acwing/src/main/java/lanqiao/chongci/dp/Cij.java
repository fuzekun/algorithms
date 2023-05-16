package lanqiao.chongci.dp;
/**
 * 
 * 杨辉三角
 * */
import java.util.Scanner;
//1:无需package
//2: 类名必须Main, 不可修改

public class Cij {
  // private static final int = 1e5;
  public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      int n = scan.nextInt();
      int maxn = (int)1e3;
      int[][] c = new int[maxn + 1][maxn + 1];
      int idx = 0;
      int[] nums = new int[(maxn + 1) * (maxn + 1)];
      c[0][0] = 1;
      nums[++idx] = 1;
      for (int i = 1; i <= maxn; i++) {
        for (int j = 0; j <= i; j++) {
          if (j == 0) c[i][j] = 1;
          else c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
          nums[idx++] = c[i][j];
        }
      }
      for (int i = 0; i <= idx; i++) {
//        System.out.println(nums[i]);
         if (nums[i] == n) {
           System.out.println(i);
           break;
         }
      }
      scan.close();
  }
  
}