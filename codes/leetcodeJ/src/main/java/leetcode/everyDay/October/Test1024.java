package leetcode.everyDay.October;

/**
 * @author: Zekun Fu
 * @date: 2022/10/22 18:16
 * @Description:    测试已经有的数字是否可以生成1024
 * 每个数字只能使用一次，每一个运算符也一样
 * 只能计算一次
 */

public class Test1024 {

    private static int[] nums = {5, 7, 7, 2, 2,19, 0, 23};
    private static char[] opt = {'%', '|', '>'};

    private static int cal(int a, int op, int b) {
        if (op == 0 && b != 0) return a % b;
        else if (op == 1) return a | b;
        else if (op == 2) return a >> b;
        return 0;
    }
    public static void main(String[] args) {
        int n = nums.length, m = opt.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = nums[i],  y = nums[j];
                for (int op = 0; op < m; op++) {
                    if (cal(x, y, op) == 1024) {
                        System.out.println(x + opt[op] + y);
                    }
                }
            }
        }
    }
}
