package leetcode.everyDay.October;

/**
 * @author: Zekun Fu
 * @date: 2022/10/20 9:55
 * @Description: 第k个语法符号
 */
public class leet779 {
    public static int kthGrammar(int n, int k) {
        if (n == 1) return 0;
        // 下面的规律是从第二行开始的
        int pre_cnt = (int)Math.pow(2, n - 2);
        if (k == pre_cnt + 1) return 1;
        if (k <= pre_cnt) return kthGrammar(n - 1, k);
        k -= pre_cnt;
        int x = (int)(Math.log(k)/ Math.log(2))+ 1;
        return kthGrammar(x, k - (int)Math.pow(2, x - 1) + 1);
    }
    public static void main(String[] args) {
        System.out.println((Math.log(4)) / Math.log(2));
        System.out.println(kthGrammar(4, 8));
    }
}
