package pre_usm;


import java.util.Scanner;

public class Acwing795 {
	/**
	 * 
	 * @return 返回区间的和
	 * */
	private static int getSum(int[][] sum, int x1, int y1, int x2, int y2) {
		return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, m, t;
		n = sc.nextInt();
		m = sc.nextInt();
		t = sc.nextInt();
		int [][] sum = new int[n + 1][m + 1];
		// 求前缀和
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int a = sc.nextInt();
				sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + a;  
			}
		}
		
		for (int i = 0; i < t; i++) {
			int x1, y1, x2, y2;
			x1 = sc.nextInt();
			y1 = sc.nextInt();
			x2 = sc.nextInt();
			y2 = sc.nextInt();
			int ans = getSum(sum, x1, y1, x2, y2);
			System.out.println(ans);
		}
	}
	
}
