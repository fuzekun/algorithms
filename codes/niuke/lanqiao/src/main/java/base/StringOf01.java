package lanqiao.base;

/**
 * @author: Zekun Fu
 * @date: 2022/10/5 15:13
 * @Description: 01字符串
 *
 * 1. 输出的格式是否正确，会不会出现调试的时候的输出忘了删了
 * 2. 读题，重新检查一些参数的设置，比如n和m是否读反了，具体数值是否正确，比如本题的32和64
 */
public class StringOf01 {
    public static void main(String[] args) {
        for (int i = 0; i < 32; i++) {
//            int[] nums = new int[5];
//            System.out.print(i + ":");
            // n - i - 1 位位移，得到的是从右到左的第i位。
            for (int j = 0; j < 5; j++) {
                System.out.print((i >> (4 - j)) & 1);
            }
            System.out.println();
        }
    }
}
