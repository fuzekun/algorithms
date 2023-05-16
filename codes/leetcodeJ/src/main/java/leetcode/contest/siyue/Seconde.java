package leetcode.contest.siyue;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/4/17 9:55
 * @Description: 102场双周赛
 */
public class Seconde {

     private class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }
    // 第三题
    public TreeNode replaceValueInTree2(TreeNode root) {
         root.val = 0;
         final int maxn = (int)1e5 + 5;
         List<TreeNode>[]que = new List[maxn];
         int cur = 0;
         for (int i = 0; i < maxn; i++) que[i] = new ArrayList<>();
         que[cur].add(root);
         while (!que[cur].isEmpty()) {
             int sum = 0;                       // 下一层结点的和
             for (TreeNode node: que[cur]) {
                 if (node.left != null) {
                     sum += node.left.val;
                    que[cur + 1].add(node.left);
                 }
                 if (node.right != null) {
                     sum += node.right.val;
                     que[cur + 1].add(node.right);
                 }
             }
             // 计算左右的和
             for (TreeNode node : que[cur]) {
                 int tmp = 0;       // 保存原来左孩子的结点的值
                 if (node.left != null) {
                     tmp = node.left.val;
                     node.left.val = sum - node.right.val - node.left.val;   // 减去兄弟和自己的值
                 }
                 if (node.right != null) {
                     node.right.val = sum - node.right.val - tmp;
                 }
             }
             cur++;
         }
         return root;
    }

    class PR {
         TreeNode a, b;
         public PR(TreeNode a, TreeNode b) {
             this.a = a;
             this.b = b;
         }
    }
    // 第三题,自己的方法
    public TreeNode replaceValueInTree(TreeNode root) {
        final int maxn = (int)1e5 + 5;
        List<PR>[] que = new List[maxn];
        for (int i = 0; i < maxn; i++) que[i] = new LinkedList<>();
        int cur = 0;
        que[cur].add(new PR(root, new TreeNode(-1)));
        while (!que[cur].isEmpty()) {
            int sum = 0;
            for (PR p : que[cur]) {
                TreeNode node = p.a, fa = p.b;
                sum += node.val;
                if (node.left != null) que[cur + 1].add(new PR(node.left, node));
                if (node.right != null) que[cur + 1].add(new PR(node.right, node));
            }

            // 一旦计算过，孩子的值就变了，使用这个保存，计算过就不用重新计算了。
            HashMap<TreeNode, Integer>mp = new HashMap<>();
            for (PR p : que[cur]) {
                TreeNode node = p.a, fa = p.b;
                if (mp.containsKey(node)) continue;
                if (node == fa.left) {
                    int tmp = node.val;
                    node.val = sum - node.val;
                    if (fa.right != null) {     // 同时更新兄弟
                        node.val -= fa.right.val;
                        fa.right.val = sum - tmp - fa.right.val;
                        mp.put(fa.right, 1);
                    }
                    mp.put(node, 1);
                }
                else {  // 右孩子
                    int tmp = node.val;
                    node.val = sum - node.val;
                    if (node.left != null) {
                        node.val -= fa.left.val;
                        fa.left.val = sum - tmp - fa.left.val;
                        mp.put(fa.left, 1);
                    }
                    mp.put(node, 1);
                }
            }
            cur++;
        }
        return root;
    }
    // 第三题,自己的方法改进
    public TreeNode replaceValueInTree3(TreeNode root) {
         final int maxn = (int)1e5 + 5;
         List<PR>[] que = new List[maxn];
         for (int i = 0; i < maxn; i++) que[i] = new LinkedList<>();
         int cur = 0;
         que[cur].add(new PR(root, new TreeNode(-1)));
         while (!que[cur].isEmpty()) {
             int sum = 0;
             for (PR p : que[cur]) {
                 TreeNode node = p.a, fa = p.b;
                 sum += node.val;
                 if (node.left != null) que[cur + 1].add(new PR(node.left, node));
                 if (node.right != null) que[cur + 1].add(new PR(node.right, node));
             }

             // 一旦计算过，孩子的值就变了，使用这个保存，计算过就不用重新计算了。
             HashMap<TreeNode, Integer>mp = new HashMap<>();
             for (PR p : que[cur]) {
                 TreeNode node = p.a, fa = p.b;
                 int childSum = 0;

                 if (mp.containsKey(fa)) childSum = mp.get(fa);
                 else {
                     if (fa.left != null) childSum += fa.left.val;
                     if (fa.right != null) childSum += fa.right.val;
                    mp.put(fa, childSum);
                 }
                 node.val = sum - childSum;
             }
             cur++;
         }
         return root;
    }
}
