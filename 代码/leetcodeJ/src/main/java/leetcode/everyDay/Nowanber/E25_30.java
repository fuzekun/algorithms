package leetcode.everyDay.Nowanber;

import leetcode.utils.ChangeToArrayOrList;

/**
 * @author: Zekun Fu
 * @date: 2022/11/25 13:05
 * @Description:
 */
public class E25_30 {

    public int expressiveWords(String s, String[] words) {
        // 如果你添加了，就需要添加三个。
        // 双指针。

        // 1. t中的字符个数 > s中的 return false
        // 2. t中的字符个数 < s中的
        //     1. s中的字符个数 >= 3 直接指针移动到下一个不同的字符处
        //     2. return false
        // 3. t中的字符个数 = s中的，直接双指针移动

        // 首先比较两个字符是否相等，如果不相等直接break
        // 其次向后面看个数，使用上面的规则
        // 最后两个指针向后移动

        // 写题目的最后，考虑下边界的输出。

        // 最小的：全部相等
        // 边界：
        // 1. 最后一个字符不行 xxxyy, xxxy
        // 2. 最后一个字符可以 xxyyy, xxy
        //
        int n = s.length(), ans = 0;
        for (String t : words) {
            int m = t.length();
            int i = 0, j = 0;
            while (i < n && j < m) {            // 不需要flag了，一旦不相等，就肯定不行的。
                if (s.charAt(i) != t.charAt(j)) {
                    break;
                }
                int cnts = 1, cntt = 1, flag0 = 1, flag1 = 1;
                while (i + 1 < n && s.charAt(i) == s.charAt(i + 1)) {
                    cnts++;
                    i++;
                }
                while (j + 1 < m && t.charAt(j) == t.charAt(j + 1)) {
                    cntt++;
                    j++;
                }
                if (cntt > cnts || (cntt != cnts && cnts < 3)) {
                    break;
                }
                i++; j++;
            }
            if (i == n && j == m) ans++;
        }
        return ans;
    }



    public static void main(String[] args) {
        String[] array1 = ChangeToArrayOrList.changeTo1DString("[xxxyyy]");
        String s = "xxxyyyyy";
        E25_30 e = new E25_30();
        int ans = e.expressiveWords(s, array1);
        System.out.println(ans);
    }
}
