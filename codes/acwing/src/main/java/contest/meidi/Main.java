package contest.meidi;

/**
 * @author: Zekun Fu
 * @date: 2023/4/8 20:08
 * @Description:
 */
import java.util.*;

public class Main {
    private static int[] nums;
    private static int len;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = dfs(0, 0, false, true);
        dp = new int[100][100];
        List<Integer>list = new ArrayList<Integer>();
        while (n != 0) {
            list.add(n % 10);
            n /= 10;
        }
        len = list.size();
        nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[len - i - 1] = list.get(i);
        }
        System.out.println(dfs(0, 0, false, true));
    }

    private static int[][] dp;
    private static int dfs(int cur, int cnt, boolean isNum , boolean isLimit) {
        if (cur == len) {
            return cnt;
        }

        int ans = dp[cur][cnt];
        if (ans > 0 && !isLimit && isNum) return ans;

        ans = 0;
        if (!isNum) ans += dfs(cur + 1, cnt, false, false);

        int up = isLimit ? nums[cur] : 9;
        for (int i = isNum ? 0 : 1; i <= up; i++) {
            ans += dfs(cur + 1, cnt + (i == 1 ? 1 : 0), true, isLimit && up == i);
        }

        if (isNum && !isLimit) dp[cur][cnt] = ans;

        return ans;
    }

}