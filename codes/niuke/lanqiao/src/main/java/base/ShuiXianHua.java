package lanqiao.base;

/**
 * @author: Zekun Fu
 * @date: 2022/10/5 13:26
 * @Description: 水仙花数
 * 个位数的立方和
 */
public class ShuiXianHua {
    public static void main(String[] args) {
        for (int i = 100; i <= 999; i++) {
            int sum = 0;
            int x = i;
            while (x != 0) {
                sum += Math.pow(x % 10, 3);
                if (sum > i) break;
                x /= 10;
            }
            if (sum == i) System.out.println(i);
        }
        System.out.println("153\n370\n371\n407");
    }
}
