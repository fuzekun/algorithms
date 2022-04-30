package String;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.Logger;

/*
*
*
*   马拉车算法
*
*   工厂模式，根据需求不同，创建不同的马拉车对象
* 1. 直接返回长度的
* 2. 返回长度和位置的
* 3. 返回最长字符串的
*
* 工厂模式是什么？
* 对象的选择问题。但是这些对象，都有统一的接口，也就是统一的输入和输出。
* 中间的处理过程可能不一样，这些产品都是属于统一类的。
* spring中都属于bean，都有共同的部分。
*
* 1. C, R,
* 2.
* */
public class Manacher {

    public final Logger logger = Logger.getGlobal();
    public String preHandle(String s) {
        StringBuilder ans = new StringBuilder("#");
        char [] chars =  s.toCharArray();
        for (char ch : chars) {
            ans.append(ch);
            ans.append("#");
        }
//        System.out.println(ans);

        return ans.toString();
    }
    public int []process(String s) {

        int n = s.length();
        char []  str = s.toCharArray();
        int[] pArr = new int[n];
        Arrays.fill(pArr, 1);           // 数组初始化为1, 最小的半径就是1

        int C = -1;
        int R = -1; // 讲述的时候R代表最有的扩成功的位置，代码中，最右扩成功的位置的下一个
        int maxv = Integer.MIN_VALUE;
        int pos = -1;
        for (int i = 0; i < n; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while(i + pArr[i] < n && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else break;
            }

            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (maxv < pArr[i]) {
                maxv = pArr[i];
                pos = i;
            }
        }
        int [] ans = {pos, maxv - 1};
        return  ans;
    }

    public String afterProcess(String s, int[] a) {
        int pos = a[0];
        int maxv = a[1];
        s = s.substring(pos - maxv + 1, pos + maxv); // 结尾需要加上1
        char[] chars = s.toCharArray();
        StringBuilder ans = new StringBuilder("");
        for (char ch : chars) {
            if (ch != '#') ans.append(ch);
        }
        return ans.toString();
    }



    public static void main(String[] args) {
        Manacher manacher = new Manacher();

        String[] s = {
          "zabcdcbafabcdcbax",
          "abcdcbkfefkbcdcba",
          "abcdedcbafsfabcdedcbk"
        };
        for (int i = 0; i < s.length; i++) {
            String pre = manacher.preHandle(s[i]);
            int a[] = manacher.process(pre);
            String ans = manacher.afterProcess(pre, a);

            System.out.println("总长:" + s[i].length());
            System.out.println("最长长度:" + ans.length());
            System.out.println("字符串为:" + ans);
        }
    }
}
