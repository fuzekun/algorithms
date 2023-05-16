package lanqiao.chongci.pre_usm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 
 * 本题的参数边界
 * 如果r == 0和r == maxx的时候是不一样的。
 * 如果r很大的情况下，应该直接返回所有的和。
 * 如果r == 0的情况下，应该注意x1可能会出界，所以应该直接当作边界情况处理
 * 
 * 前缀和的边界
 * 1. 需要的参数边界 x1 >= 0 && y1 >= 0。 x1 <= x2 && y1 <= y2
 * 2. long
 * */

public class Acwing99 {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int r = sc.nextInt();	
		final int maxx = 5003, maxy = 5003;
		int[][] nums = new int[maxx][maxy];
	
		for (int i = 0; i < n; i++) {
			int x, y, w;
			x = sc.nextInt();
			y = sc.nextInt();
			w = sc.nextInt();
			nums[x][y] += w;
		}
		
		long[][] sum = new long[maxx + 1][maxy + 1];
		
		// 前缀和
		for (int i = 1; i <= maxx; i++) {
			for (int j = 1; j <= maxy; j++) {
				sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + nums[i - 1][j - 1];
			}
		}
		if (r == 0) {
			System.out.println(0);
			return;
		}
		if (r >= maxx) {
			System.out.println(sum[maxx][maxy]);
			return ;
		}
		long ans = 0;
		for (int i = 1; i <= maxx - r; i++) {
			for (int j = 1; j <= maxy - r; j++) {
				int x2 = i + r - 1, y2 = j + r - 1;
				ans = Math.max(ans,  getSum(sum, i, j, x2, y2));
			}
		}
		System.out.println(ans);
	}
	private static long getSum(long[][] sum, int x1, int y1, int x2, int y2) {
		return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
	}
}
