package lanqiao.imporve;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/28 9:50
 * @Description: 字符串压缩
 */
public class StringCondese {
/*
*   简单模拟题
* */

    public static void main(String[] args) {
        int[] cnt = new int[256];
        Arrays.fill(cnt, 0);
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isLetter(c)) {
                ans.append(c);
                continue;
            }
            cnt[c]++;
            if (cnt[c] == 1 || cnt[c] == 3 || cnt[c] == 6)
                ans.append(c);
        }
        System.out.println(ans);
    }
}
