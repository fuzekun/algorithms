package binary;

/**
 * 
 * 
 * 1. 注意long保存
 * 2. 0， 0， 0， 是最后一层，也就是空层。如果遍历了，sum = 0, 如果最后所有结点都是负，会出现空层为最大值的情况
 * */
import java.util.ArrayList;
import java.util.Scanner;

public class Acwing1240 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n + 1];
		for (int i = 0; i < n; i++) {
			nums[i + 1] = sc.nextInt();
		}
		// 存放结点的id
		ArrayList<Integer>[]que = new ArrayList[100];
		que[1] = new ArrayList<>();
		que[1].add(1);
		int cur = 1;
		int ans = 1;
		// 这里使用long 保存和的最大值
		long maxv = nums[0];
		while (!que[cur].isEmpty()) {
			que[cur + 1] = new ArrayList<>(); 
			// 使用long 保存
			long sum = 0;
			// 遍历所有的结点, 如果结点id不超过就放到下一层
			for (int x : que[cur]) {
				// 这里为了防止sum == 0，不会遍历最后空直接结点的层，也就是最后等于0的层不会被放进去。
				if (x << 1 <= n) que[cur + 1].add(x << 1);
				if ((x << 1 | 1) <= n) que[cur + 1].add(x << 1 | 1);
				sum += nums[x];
			}
			if (sum > maxv) {
				maxv = sum;
				ans = cur;
			}
			cur++;
		}
		System.out.println(ans);
	}
}
