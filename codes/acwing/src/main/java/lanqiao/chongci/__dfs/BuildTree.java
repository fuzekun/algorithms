package lanqiao.chongci.__dfs;


import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * 	根据前中序，构建一棵二叉树
 * 
 * 1. 找到根结点
 * 2. 中序左边的左孩子，右边的右孩子
 * 
 * 1. 对于空树
 * 2. 对于只有一个结点的树
 * */
public class BuildTree {
	
	private static class Tree {
		int val;
		Tree lc, rc;
		public Tree() {
			
		}
		public Tree(int val) {
			this.val = val;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int after_nums[] = new int[n];
		int mid_nums[] = new int[n];
		for (int i = 0; i < n; i++) {
			after_nums[i] = sc.nextInt(); 
		}
		for (int i = 0; i < n; i++) {
			mid_nums[i] = sc.nextInt(); 
		}
		Tree root = build(after_nums, mid_nums, 0, after_nums.length, 0, mid_nums.length);
		ArrayList<Tree>[] que = new ArrayList[100];
		int cur = 0;
		que[cur] = new ArrayList<>();
		que[cur].add(root);
		
		while (!que[cur].isEmpty()) {
			que[cur + 1] = new ArrayList<>();
			for (Tree node : que[cur]) {
				System.out.print(node.val + " ");
				if (node.lc != null) que[cur + 1].add(node.lc);
				if (node.rc != null) que[cur + 1].add(node.rc);
			}
			cur++;
		}
		System.out.println();
	}
	/**
	 * 
	 * [af_l, af_r)
	 * [mid_l, mid_r)
	 * */
	private static Tree build(int[] after_nums, int[] mid_nums, int af_l, int af_r, int mid_l, int mid_r) {
		if (af_l >= af_r) {
			return null;
		}
		Tree root = new Tree(after_nums[af_r - 1]);
		int root_id = mid_l;
		while (root_id < mid_r && mid_nums[root_id] != root.val) {
			root_id ++;
		}
		if (root_id == mid_r) {
			System.out.println(af_l + " " + af_r + " " + mid_l + " " + mid_r);
			throw new IllegalArgumentException("构建失败，中序中不包含后续的结点");
		}
		int len_l = root_id - mid_l;	// 左子树的长度
		root.lc = build(after_nums, mid_nums, af_l, af_l + len_l, mid_l, root_id);
		root.rc = build(after_nums, mid_nums, af_l + len_l, af_r - 1, root_id + 1, mid_r);
		return root;
	}
}
