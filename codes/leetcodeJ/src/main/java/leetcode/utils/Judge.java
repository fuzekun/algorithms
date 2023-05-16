package leetcode.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/2/28 11:26
 * @Description: 判断两个结果是否相同
 */
public class Judge {
    public static boolean judge1DIntArray(int[] ans, int[] expect) {
        if (ans.length != expect.length) throw new IllegalArgumentException("数组长度不匹配，无法判断内容是否相同!");
        int n = ans.length;
        for (int i = 0; i < n ; i++) {
            if (ans[i] != expect[i]) {
                System.out.println(String.format("下标 %d 不相同, 给出的数组为:", i));
                System.out.println(Arrays.toString(ans));
                System.out.println("实际为:");
                System.out.println(Arrays.toString(expect));
                return false;
            }
        }
        System.out.println("相同!");
        return true;
    }
}
