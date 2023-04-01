package binary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.EnumSet;
import java.util.Scanner;
import java.util.PrimitiveIterator.OfDouble;


/**
 * 
 * 	分巧克力，最大化最小值，经典二分问题
 * 
 * 1. 注意一个等号的问题
 * 2. 注意使用long进行保存sum，或者在大于m的时候及时
 * */
public class Acwing1227 {
	private static void make_in() throws Exception{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("in.txt"))));
		int maxn = (int)1e2, maxv = (int)1e5, maxp = (int)1e5;
		out.write(maxn + " " + maxp + "\n");
		for (int i = 0; i < maxn; i++) {
			// 最后一行不用换行
			if (i != maxn - 1)
				out.write(maxv + " " + maxv + "\n");
			else out.write(maxv + " " + maxv);
		}
		out.flush();
		out.close();
	}
	public static void main(String[] args) throws Exception {
//		make_in();
//		if (true) return ;
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File("in.txt"))));
		String[] input = in.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int[][] recs = new int[n][2];
		for (int i= 0; i < n; i++) {
			input = in.readLine().split(" ");
			recs[i][0] = Integer.parseInt(input[0]);
			recs[i][1] = Integer.parseInt(input[1]);
		}
		int l = 1, r = (int)1e5 + 5;
		while (l < r) {
			int mid = l + r >> 1;
			if (check(recs, mid, m)) l = mid + 1;
			else r = mid;
		}
		System.out.println(l - 1);
	}
	private static boolean check(int[][]nums,  int x, int m) {
		long sum = 0;
		for (int[] num : nums) {
			sum += (long)(num[0] / x) * (num[1] / x);
		}
		if (x == 1) System.out.println(sum);
		return sum >= m;
	}
}
