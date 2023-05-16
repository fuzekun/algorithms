package offer;


import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/4/17 19:11
 * @Description: 美的笔试题目
 */
public class First {

    static Set<Integer> set = new HashSet<>();
    static int n;
    static char[]s;
    static boolean bg = true;
    private static void dfs(int cur, int sum, int num1, int num2) {
        // 递归基
        if (cur == n) {
            if (sum == 2) {
                if (!set.contains(num1)) {
                    if (bg) {
                        bg = false;
                        System.out.print(num1);
                    }
                    else {
                        System.out.print("," + num1);
                    }
                    set.add(num1);
                }
                if (!set.contains(num2)) {
                    System.out.print("," + num2);
                    set.add(num2);
                }
            }
            return ;
        }
        // 选择
        if (sum < 2) {
            dfs(cur + 1, sum + 1, num1 * 10 + s[cur] - '0', num2 + (int)Math.pow(10, sum) * (s[cur] - '0'));
        }
        // 不选择
        dfs(cur + 1, sum, num1, num2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine().toCharArray();
        n = s.length;
        dfs(0, 0, 0, 0);
    }
}
