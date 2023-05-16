package lanqiao.imporve;

import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/28 16:46
 * @Description: N车
 *
 * 明显的暴力做法，这个说明自己前两天做的暴力并不熟练。
 * 应该会有dp的做法。就是直接统计
 * 性质1：每一列行每一行只能放置1个
 * 性质2：第一列放在任意位置都可以划分成更小问题f(n - 1)，所以递推式式n * f(n - 1)
 *
 * 比如2列，2 * 1
 * 3列, 3 * (2) = 6
 *
 */
public class NCar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = 1;
        for (int i = 2; i <= n; i++) ans *= i;
        System.out.println(ans);
    }

}
