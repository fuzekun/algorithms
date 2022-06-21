package leetcode.everyDay.May;

import leetcode.utils.ChangeToArrayOrList;
import leetcode.utils.PrintArrays;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/5/20 16:15
 * @Description: 20号的每日一题，简单的区间问题
 */
public class May_E_20 {

    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] ans = new int[n];
        int[][] invals = new int[n][3];
        for (int i = 0; i < n; i++) {
            invals[i][0] = intervals[i][0];
            invals[i][1] = intervals[i][1];
            invals[i][2] = i;
        }
        Arrays.sort(invals, (o1, o2)->o1[0] - o2[0]);
        PrintArrays.print2DIntArray(invals);
        for (int i = 0; i < n; i++) {
            int l = 0, r = n;
            int x = intervals[i][1];
            System.out.println("x = " + x + " mid:");
            while(l < r) {
                int mid = (l + r) >> 1;
                System.out.print(mid + " ");
                if (invals[mid][0] >= x) r = mid;
                else l = mid + 1;
            }
            System.out.println();
            if (l == n) ans[i] = -1;
            else ans[i] = invals[l][2];
        }

        return ans;
    }

    public static void main(String[] args) {
        String s = "[[3,4],[2,3],[1,2]]";
        int[][] intevals = ChangeToArrayOrList.changeTo2DIntArray(s);
        int[] ans = new May_E_20().findRightInterval(intevals);
        PrintArrays.print1DIntArray(ans);
    }
}
