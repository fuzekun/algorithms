package leetcode.categories.math;

import leetcode.utils.ChangeToArrayOrList;
import leetcode.utils.PrintArrays;

import java.util.Arrays;

public class tubao {

    /*
    *
    * 1. 按照第一维排序，二维度偏序
    * 2. 最左边结点入栈，但是不标记，防止倒数第二个点不是凸包上的点
    * 3. 从左到右进行扫描，如果栈中最少有两个元素，并且cross(top,second, p) < 0
    *    - 出栈，令vis[top] = 0； 直到不满足上面条件为止
    *    - 把p放入栈中， 令vis[p] = 1;
    * 4.令m = st.size();
    * 5. 在从右到左进行扫描，如果vis[p] = 0;
    *       如果栈中元素最少有m + 1个元素并且，并且。。
    *       - 出栈，直到不满足上面条件。
    *       -
    * 6. 最后一个元素出栈。因为0被放入两次
    *
    *
    * 调试技巧，直接看结果，和答案排序后进行对比，看哪个除了问题。
    * 之后就可以定位到是求上凸包，还是下凸包出现了问题。
    * 还可以定位到是否是统计答案的时候出现了直接使用i而不是st[i]的情况
    *
    *
    * 常见错误
    * - 小于4个点的时候，所有的点都是凸包。如果不判断，有一个点的时候是错误的。
    * - 排序不对
    * - vis数组忘记了
    * - 判断的时候，cross中的p,q,r传递的不对
    *
    * */

    private boolean cross(int[]p, int[]q, int[] r) {
        return ((q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0])) < 0;
    }
    public int[][] outerTrees(int[][] tree) {
        int n = tree.length;
        if (n < 4) return tree;
        Arrays.sort(tree, (a, b)->{
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });


        PrintArrays.print2DIntArray(tree);

        int[] st = new int[n + 2];
        boolean[] vis = new boolean[n + 2];
        int idx = 1;
        st[0] = 0;
        for (int i = 1; i < n; i++) {
            while(idx > 1 && cross(tree[st[idx - 2]], tree[st[idx - 1]], tree[i]))
                vis[st[--idx]] = false;
            st[idx++] = i;
            vis[i] = true;
        }
        int m = idx;
        for (int i = n - 2; i >= 0; i--) {
            if (vis[i]) continue;
            while(idx > m && cross(tree[st[idx - 2]], tree[st[idx - 1]], tree[i]))
                idx--;
            st[idx++] = i;
        }

        int[][] ans = new int[--idx][2];
        for (int i = 0; i < idx; i++) {
            ans[i] = tree[st[i]];
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "[[1,5]]";
        int[][] nums = ChangeToArrayOrList.changeTo2DIntArray(s);
        PrintArrays.print2DIntArray(nums);
        int[][] ans = new tubao().outerTrees(nums);
        PrintArrays.print2DIntArray(ans);
    }
}
