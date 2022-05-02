package leetcode.everyDay.May;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
*
*
*   5.1 号的每日一题
*   合并两颗二叉搜索树
*
* 1. 直接合并不理智，并且不会，还是先记下来，然后双指针比较舒服。
* 2. 使用ArrayList比使用LinkList快了很多，因为少了申请不连续空间的时间。
* ArrayList是连续的，并且有一个初始的值，如果小于了在申请连续的空间。
* 所以ArrayList会快很多。
*
 */
public class May_E_1 {



    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer>nums1 = new ArrayList<>();
        List<Integer>nums2 = new ArrayList<>();
        dfs(root1, nums1);
        dfs(root2, nums2);
        int m = nums1.size(), n = nums2.size();
        int i = 0, j = 0;
        List<Integer>ans = new LinkedList<>();
        while(i < m && j < n) {
            int x = nums1.get(i), y = nums2.get(j);
            if (x < y) {
                ans.add(x);
                i++;
            }else {
                ans.add(y);
                j++;
            }
        }
        while(i < m) ans.add(nums1.get(i++));
        while(j < n) ans.add(nums2.get(j++));
        return ans;
    }
    private void dfs(TreeNode root1, TreeNode root2, List<? super Integer>ans) {
        if (root1 == null ||  root2 == null) {
            if (root1 != null) {
                dfs(root1.left, root2, ans);    // 左
                ans.add(root1.val);             // 中
                dfs(root1.right, root2, ans);   // 右
            }
            if (root2 != null) {
                dfs(root1, root2.left, ans);
                ans.add(root2.val);
                dfs(root1, root2.right, ans);
            }
            return ;
        }

    }

    private void dfs(TreeNode root, List<? super Integer>ans) {
        if (root == null) return;
        dfs(root.left, ans);
        ans.add(root.val);
        dfs(root.right, ans);
    }



    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {};
        TreeNode node1 = create(nums1);
        TreeNode node2 = create(nums2);
        new May_E_1().getAllElements(node1, node2);
    }

    private static TreeNode create(int[] nums) {
        TreeNode ans = new TreeNode();
        return ans;
    }


    private static class TreeNode {
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

}
