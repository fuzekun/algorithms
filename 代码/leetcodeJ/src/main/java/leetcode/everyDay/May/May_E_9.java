package leetcode.everyDay.May;

import leetcode.utils.PrintArrays;

public class May_E_9 {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        ans[0] = 0;
        int minv = -1;
        int maxv = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == 'I') {
                ans[i] = maxv++;
            } else ans[i] = minv--;
        }
        for (int i = 0; i <= n; i++) {
            ans[i] -= minv + 1;
        }

        return ans;
    }

    public int[] diStringMatch2(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        int minv = 0, maxv = n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                ans[i] = minv++;
            } else ans[i] = maxv--;
        }
        ans[n] = minv;
        return ans;
    }


    public static void main(String[] args) {
        int[] ans = new May_E_9().diStringMatch2("IDID");
        PrintArrays.print1DIntArray(ans);
    }
}
