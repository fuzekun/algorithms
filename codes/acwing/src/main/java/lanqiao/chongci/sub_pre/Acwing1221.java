package lanqiao.chongci.sub_pre;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.zip.Inflater;
/**
 * 
 * 
 * 	四平方和
 * 
 * 1. 分成两部分的和
 * 2. 使用map保存第二部分的和，保存前一部分就行了
 * 3. 后一部分一定是某一个数字的平和和。设这个数字是d，那么下标就是sqrt(d)
 * 
 * */
public class Acwing1221 {
	
	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		n = sc.nextInt();
		ArrayList<Integer>numss = new ArrayList<>();
		int sz = (int)Math.sqrt(n + 1) + 1;
		for (int i = 0; i <= sz; i++) {
			numss.add(i * i); 
		}
		int[] nums = numss.stream().mapToInt(Integer::valueOf).toArray();
		Map<Integer, int[]>mp = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				// 重要减枝
				if (nums[i] + nums[j] > n) break;
				// 只存储第一个就行了
				if (mp.containsKey(nums[i] + nums[j])) continue;
				mp.put(nums[i] + nums[j], new int[] {i, j});
			}
		}
		
		int flag = 0;
		for (int i = 0; i < nums.length && flag == 0; i++) {
			for (int j = i; j < nums.length && flag == 0; j++) {
				// 重要减枝
				if (nums[i] + nums[j] > n) break;
				// 如果不包含，直接继续
				int y = n - nums[i] - nums[j]; 
				if (!mp.containsKey(y)) continue;
				int[] poss = mp.get(y);
				System.out.println(i + " " + j + " " + poss[0] + " "+ poss[1]);
				flag = 1;
				// 包含找到第一个就行了
//				for (int k = j; k < nums.size() && flag == 0; k++) {
//					int l = k, r = nums.size();
//					int x = n - nums.get(i) - nums.get(j) - nums.get(k);
//					while (l < r) {
//						int mid = l + r >> 1;
//						if (nums.get(mid) == x) {
//							System.out.println(i + " " + j + " " + k + " " + mid);
//							flag = 1;
//							break;
//						}
//						else if (nums.get(mid) > x) {
//							r = mid;
//						}
//						else l = mid + 1;
//					}
//				}
			}
		}
	}
}
