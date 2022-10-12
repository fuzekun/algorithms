package leetcode.everyDay.October;

import leetcode.utils.ReadData;

import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2022/10/7 10:44
 * @Description:    10月6号的每日一题
 * 三等分数组，使得数组中的二进制表示的值相同
 * 性质1：1的个数应该相等
 * 性质2：最后一个等分块，末尾0的个数是确定的
 *
 */
public class Leet927 {
    public int[] threeEqualParts(int[] arr) {
        int s = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            s += arr[i];
        }
        if (s == 0) return new int[]{0, 2};
        if (s % 3 != 0) return new int[]{-1, -1};
        int tmp = 0, first = 0, second = -1, third = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1 && first == 0) first = i;
            tmp += arr[i];
            if (tmp == s / 3) {
                i ++;                       // 从下一个开始找
                while (i < n && arr[i] != 1) i++;    // 找到下一个为1的为止, 不一定可以找到
                if (second == -1) second = i;
                else {
                    third = i;
                    break;
                }
                i--;
                tmp = 0;
            }
        }
        StringBuilder num = new StringBuilder();
        // 不可以使用int，因为太大而爆掉了num了
        for (int i = third; i < n; i++) {
            num.append(Integer.toString(arr[i]));
        }
        int len = num.length();
        StringBuilder snum = new StringBuilder();
        StringBuilder tnum = new StringBuilder();
        if (first + len > second || second + len > third) {
            return new int[]{-1, -1};
        }
        for (int i = first; i < first + len; i++) {
            snum.append(Integer.toString(arr[i]));
        }
        for (int i = second; i < second + len; i++) {
            tnum.append(Integer.toString(arr[i]));
        }
        String thr = num.toString();
        String fir = snum.toString();
        String sen = tnum.toString();
        if (thr.compareTo(fir) == 0 && thr.compareTo(sen) == 0) {
            return new int[]{first + len - 1, second + len};
        }
        return new int[]{-1, -1};
    }
    public static void main(String[] args) throws Exception{
        int[] arr = ReadData.getArray("leetcode927");
        Leet927 l = new Leet927();
        System.out.println(Arrays.toString(l.threeEqualParts(arr)));
    }
}
/*
* [0,1,0,1,1]
* */
