package dataStructure;

import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2022/8/10 8:03
 * @Description: 树状数组的模板
 */
public class TreeArray {

    private int[] c;
    private int n;

    public TreeArray(int _n) {
        n = _n;
        c = new int[n + 5];
        Arrays.fill(c, 0);
    }

    private int low_bit(int x) {
        return x & -x;
    }
    public void add(int x, int num) {
        for (int i = x; i <= n; i += low_bit(i)) c[i] += num;
    }
    public int sum(int x) {
        int res = 0;
        for (int i = x; i != 0; i -= low_bit(i)) res += c[i];
        return res;
    }
}
