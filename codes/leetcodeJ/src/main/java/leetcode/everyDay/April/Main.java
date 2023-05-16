package leetcode.everyDay.April;


/*
*
*
*   leetcode的每日一题
* 分类
* 4.20 ： 字符串 + 简单的栈模拟
*
*
*
*
* */
import javafx.util.Pair;

import java.util.*;

public class Main {

//    private static final Main main = new Main(); // 常量和静态变量，在类加载的时候就会进行初始化操作,这就是饿汉式
    //   使用单例模式,获取本类
//    public synchronized static Main getSingle() { // 直接懒汉式
//        if (main == null) main = new Main();
//        return main;
//    }

//    public static Main getInstance() {
//        return main;
//    }

    private static class MainHolder {
        /*
        *
        *   在加载原本类的时候加载静态内部类
        * */
        private static final Main main = new Main();
    }

    public static Main getInstance() {
        /**
         *  一旦调用了这个方法，就会加载静态内部类
         *  而类的加载又是单线程的，所以一定会产生一个单例。
         *
         *
         */
        return MainHolder.main;
    }



    // 构造函数成为私有，防止被实例化
    private Main() {

    }

    private static int q_4_16(int n) {
        if (n == 1) {
            return 9;
        }
        int mod = 1337;
        int up = (int) Math.pow(10, n) - 1;
        int down = (int) Math.pow(10, n - 1);
        for (int x = up; x >= down; x--) {
            long p = x, t = x;
            while (t != 0) {
                p = p * 10 + t % 10;
                t /= 10;
            }
//            if (x >= 900000) System.out.println("x = " + x + " p = " + p);
            for (int y = up; (long) y * y >= p; y--) {
                if (p % y == 0) {
                    return (int) (p % mod);
                }
            }
        }
        return -1;
    }

    private static int largestPalindrome(int n) {
        if (n == 1) {
            return 9;
        }
        int up = (int) Math.pow(10, n) - 1;

        for (int x = up; x >= 0; --x) { // 枚举回文数的左半部分
            long p = x, t = x;
            while (t != 0) {
                p = p * 10 + t % 10;
                t /= 10;
            }
//            System.out.println("x = " + left + " p = " + p);
            for (long y = up; y * y >= p; y--) {
                if (p % y == 0) { // x 是 p 的因子
                    return (int) (p % 1337);
                }
            }
        }

        return -1;
    }


    private static void test_4_16() {
        //{9,987,123,597,677,1218,877,475}
//        for (int i = 1; i <= 8; i++)
        System.out.println(q_4_16(6));
        System.out.println(largestPalindrome(6));
    }

    public static int[] q_4_19(String s, char c) {
        int[] pre = new int[26];
        char[] chars = s.toCharArray();
        int n = s.length();
        int[] dp = new int[n];
        int INF = 0x3f3f3f3f;
        Arrays.fill(dp, INF);
        dp[0] = (chars[0] == c ? 0 : INF);
        for (int i = 1; i < n; i++) {
            if (chars[i] == c) {
                dp[i] = 0;
            } else dp[i] = dp[i - 1] + 1;
            System.out.println("i = " + "dp[i] = " + dp[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            if (chars[i] == c) continue;
            dp[i] = Math.min(dp[i], dp[i + 1] + 1);
            System.out.println("i = " + "dp[i] = " + dp[i]);
        }
        return dp;
    }



    private String getName(StringBuilder s, Pair[] dirs, int idx) {
        StringBuilder tmp = new StringBuilder();
        for (int j = 0; j < idx; j++) {
            if (j != 0) tmp.append("/");
            tmp.append(dirs[j].getKey());
        }
        if (tmp.length() != 0) tmp.append("/");
        tmp.append(s);
        return tmp.toString();
    }

    public int lengthLongestPath(String input) {
        /**

         1. \n \t就是代表换行和指标符
         2. 如何找到路径
         使用栈保存目录，另外记录栈中所有字符串的长度len。
         1. 记录当前的目录以及当前目录的层数\t的个数
         2. 如果遇见的\t小于等于栈顶层数，就出栈，否则是文件就统计，是目录就入栈。

         是字符串的时候进行以下操作
         1. 碰见的是\n
         说明字符串已经终止了，
            首先，需要先返回当前的层级，也就是出栈
            其次，
            1. 如果是目录，入栈。
            2. 如果是文件，统计并更新答案。
            最后，
            重置flag, layer和len

         2. 碰见的是\t
         - 只需要统计层数

         3. 碰见的是字符
         说明已经算完了是多少层的目录或者文件
         - 说明已经算完了是在第几层了，直接需要统计文件名了


         注意事项
         1. 不管是文件还是文件夹，都需要更新目录
         2. 如果在跟文件夹下，不需要加上/
         3. 最后处理的一个文件还是需要看文件夹层数的。

         */
        int maxn = (int) 1e4 + 5;
        char[] chars = input.toCharArray();
        int n = chars.length;

        int layer = 0;                                      // 当前的层数
        int flag = 0;                                       // 是否是文件
        StringBuilder s = new StringBuilder();              // 当前的字符串
        Pair<String, Integer>[] dirs = new Pair[maxn];       // 当前的文件夹
        int idx = 0;

        int maxv = 0;
        for (int i = 0; i < n; i++) {
            char ch = chars[i];
            if (ch == '\n' || ch == '\t') {
                if (chars[i] == '\n') { // 字符串终止了

                    while (idx >= 1 && dirs[idx - 1].getValue() >= layer) idx--;

                    if (flag == 1) {        // 文件更新答案
                        flag = 0;
                        String name = getName(s, dirs, idx);
                        System.out.println(name);
                        maxv = Math.max(maxv, name.length());
                    } else {                // 目录放到栈中
                        dirs[idx++] = new Pair<String, Integer>(s.toString(), layer);
                    }
                    layer = 0;
                    s.delete(0, s.length());
                } else {
                    layer++;
                }
            } else {                // 新的字符串了
                s.append(ch);
                if (ch == '.') flag = 1;
            }
        }

        // 最后还要看是否是文件
        if (flag == 1) {
            while (idx >= 1 && dirs[idx - 1].getValue() >= layer) idx--;

            String name = getName(s, dirs, idx);
            System.out.println(name);
            maxv = Math.max(name.length(), maxv);
        }
        return maxv;
    }
    private int getLen(int len, int []dirlen, int idx) {
        int sum = len + (idx == 0 ? 0 : 1);
        for (int j = 0; j < idx; j++) {
            sum += dirlen[j] + (j == 0 ? 0 : 1);
        }
        return sum;
    }
    public int lengthLongestPath3(String input) {
        char [] chars = input.toCharArray();
        int maxn = input.length();

        int layer = 0;                  // 层数
        int flag = 0;                   // 是否是文件
        int len = 0;                    // 字符串的长度
        int []dirlen = new int[maxn];  // 文件夹的长度
        int []dirlyr = new int[maxn];  // 文件夹的层数
        int idx = 0;                   // 文件夹栈

        int ans = 0;
        for (char ch : chars) {
            if (ch == '\n' || ch == '\t') {
                if (ch == '\n') {       // 字符串终止
                    while(idx >= 1 && dirlyr[idx - 1] >= layer) idx--;
                    if (flag == 1) {    // 文件
                        flag = 0;
                        int sum = getLen(len, dirlen, idx);
                        System.out.println("idx = " + idx + " sum = " + sum);
                        ans = Math.max(ans, sum);
                    } else {            // 目录
                        dirlyr[idx] = layer;
                        dirlen[idx] = len;
                        idx++;
                    }
                    len = layer = 0;
                }
                else {
                    layer++;
                }
            } else {                                // 是字符串的情况下
                len++;
                if (ch == '.') flag = 1;
            }
        }

        if (flag == 1) {
            while(idx >= 1 && dirlyr[idx - 1] >= layer) idx--;
            int sum = getLen(len, dirlen, idx);
            System.out.println("idx = " + idx + "sum = " + sum);
            ans = Math.max(ans, sum);
        }
        return ans;
    }
    public int lengthLongestPath4(String input) {
        char [] chars = input.toCharArray();
        int maxn = input.length();

        int layer = 0;                  // 层数
        int flag = 0;                   // 是否是文件
        int len = 0;                    // 字符串的长度
        int []dirlen = new int[maxn];  // 文件夹的长度
        int []dirlyr = new int[maxn];  // 文件夹的层数
        int idx = 0;                   // 文件夹栈

        int ans = 0;
        for (char ch : chars) {
            if (ch == '\n') {       // 字符串终止
                while (idx >= 1 && dirlyr[idx - 1] >= layer) idx--;
                if (flag == 1) {    // 文件
                    ans = Math.max(ans, getLen(len, dirlen, idx));
                } else {            // 目录
                    dirlyr[idx] = layer;
                    dirlen[idx] = len;
                    idx++;
                }
                flag = len = layer = 0;
            } else if (ch == '\t') {
                layer++;
            } else {
                len++;
                if (ch == '.') flag = 1;
            }
        }

        if (flag == 1) {
            while(idx >= 1 && dirlyr[idx - 1] >= layer) idx--;
            ans = Math.max(ans, getLen(len, dirlen, idx));
        }
        return ans;
    }
    public int lengthLongestPath5(String input) {
        /*
        *   idx = 0？
        *   1. 使用idx代替dirlen[idx],
        *   由于idx > len, 而dirlen[idx] >= layer出栈
        *   所以只要 idx > layer就出栈
        *   2. 栈顶存放全路径的长度，而不是单个的长度，就不用遍历了。
        *   3. 结尾加上"\n" 进行预处理
        * */
        char[] chars = input.toCharArray();
        int n = chars.length;
        // 当前层数， 是否文件，栈深度， 当前长度
        int layer = 0, flag = 0, idx = 0, len = 0, ans = 0;
        int []dirs = new int[n];

        for (char ch : chars) {
            if (ch == '\n') {
                while(idx > 0 && idx > layer) idx--;
                if (idx > 0) len += dirs[idx - 1] + 1;
                if (flag == 1) ans = Math.max(ans, len);
                dirs[idx++] = len;
                flag = layer = len = 0;
            } else if (ch == '\t') layer++;
            else {
                len++;
                if (ch == '.') flag = 1;
            }
        }
        return ans;
   }

    public int lengthLongestPath2(String input) {
        int n = input.length();
        int pos = 0;
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();

        while (pos < n) {
            /* 检测当前文件的深度 */
            int depth = 1;
            while (pos < n && input.charAt(pos) == '\t') {
                pos++;
                depth++;
            }
            /* 统计当前文件名的长度 */
            boolean isFile = false;
            int len = 0;
            String name = "";
            while (pos < n && input.charAt(pos) != '\n') {
                if (input.charAt(pos) == '.') {
                    isFile = true;
                }
                name += input.charAt(pos);
                len++;
                pos++;
            }
            System.out.println(name);
            /* 跳过当前的换行符 */
            pos++;

            while (stack.size() >= depth) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                len += stack.peek() + 1;
            }
            if (isFile) {

                ans = Math.max(ans, len);
            } else {
                stack.push(len);
            }
        }
        return ans;
    }
    public int lengthLongestPath6(String s) {
        s += "\n";
        char[] chars = s.toCharArray();
        int n = chars.length;
        int len = 0, flag = 0, layer = 0, idx = 0, ans = 0;
        int[] dirs = new int[n];

        for (char ch : chars) {
            if (ch == '\n') {
                while(idx > layer) idx--;
                if (idx > 0) len += dirs[idx - 1] + 1;
                if (flag == 1) ans = Math.max(ans, len);
                else dirs[idx++] = len;
                layer = flag = len = 0;
            } else if (ch == '\t') layer++;
            else {
                len++;
                if (ch == '.') flag = 1;
            }
        }
        return ans;
    }
    public String toGoatLatin(String sentence) {
        /*
         *
         *   山羊拉丁文
         *  1. 如果是大写的没考虑
         *  2. 最后一个字符是i - 1，不是i
         *  3. 如何预处理，从而不用考虑空格呢？
         *  4. 不用修改每一个单词，直接统计入答案就行了。
         * */

        String [] words = sentence.split(" ");
        char [] chs = {'a', 'e', 'i', 'o', 'u'};
        StringBuilder addTail = new StringBuilder("a");
        StringBuilder ans =  new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i != 0) ans.append(" ");
            int flag = 0;
            char firstC = words[i].charAt(0);
            for (int ch : chs) {
                if (firstC == ch || firstC == Character.toUpperCase(ch))
                    flag = 1;
            }
            if (flag == 1) ans.append(words[i]).append("ma");
            else {
                ans.append(words[i].substring(1)).append(words[i].charAt(0)).append("ma");
            }
            ans.append(addTail);
            addTail.append("a");
        }

        return ans.toString();
    }
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;

        int sum = 0, f = 0, ans = Integer.MIN_VALUE;
        for (int i = 0; i < n * 2; i++) {
            if (i < n) {
                sum += nums[i];
                f += i * nums[i];
            } else {
                f += n * nums[i - n] - sum;
                ans = Math.max(ans, f);
            }
        }
        return ans;
    }
    public int maxRotateFunction2(int[] nums) {
        int n = nums.length;

        int sum = 0, f = 0, ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f += i * nums[i];
        }
        for (int i = 0; i < n; i++) {
            f = f - sum + n * nums[i];
            ans = Math.max(ans, f);
        }
        return ans;
    }
    public int maxRotateFunction3(int[] nums) {
        /*
        *
        *   最为初代的版本，就是根据循环进行计算的。
        *   其实也可以有递推公式。
        *   这就是树状数组中，区间修改，区间求和的递推公式。
        *   先补上一班，用另一种方式求和后，在减去一半就行了。
        * */
        int n = nums.length;
        int m = n << 1;
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = nums[i % n];
        }
        int sum = 0, f = 0, ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) sum += nums[i];
        for (int i = 0; i < m; i++) {
            if (i < n)
                f += i * arr[i];
            else {
                f = f - sum + n * arr[i];
                ans = Math.max(ans, f);
            }
        }
        return ans;
    }
    public int binaryGap(int n) {
        /**

         1. 首先判断是否为1
         2.
         */

        int pre = Integer.MAX_VALUE, cnt = 0, ans = 0;
        for (; n != 0; n >>= 1, cnt++) {
            if ((n & 1) == 1) {
                ans = Math.max(ans, cnt - pre);
                pre = cnt;
            }
            System.out.println("pre = " + pre + " cnt = " + cnt);
        }

        return ans;
    }

    private static int random(int[] nums, int x) {
        /*
        *
        *   返回数组中某一个数字的索引
        *
        *   1. 统计这个数字有多少个，以及每一个的索引是多少
        *   2. 返回其中的任意一个就行了
        *  */
        HashMap<Integer, ArrayList<Integer>>mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (mp.containsKey(nums[i])) {
                List<Integer> list = mp.get(nums[i]);
                list.add(i);
            } else {
                ArrayList<Integer>list = new ArrayList<>();
                list.add(i);
                mp.put(nums[i], list);
            }
        }
        List<Integer>list = mp.get(x);
        int idx = (int)(Math.random() * list.size());        // 返回[0, 10)
        return list.get(idx);
    }


    private static void test_4_19() {
        int[] ans = q_4_19("loveleetcode", 'e');
        System.out.println("ans = ");
        for (int x : ans) {
            System.out.print(x + " ");
        }
    }
    private static void test_4_20() {
        String s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//        s = "file1.txt\nfile2.txt\nlongfile.txt";
//        s = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
//        s = "dir\n        file.txt";
//        System.out.println("len = " + s.length());
        System.out.println(getInstance().lengthLongestPath6(s));
    }

    private static void test_4_21() {
        String s = "I speak Goat Latin";
        String ans = getInstance().toGoatLatin(s);
        System.out.println(ans);
    }

    private static void test4_22() {
        int[] nums = {4,3,2,6};
        Main main = getInstance();
        int ans = main.maxRotateFunction(nums);
        System.out.println("ans = " + ans);
    }

    private static void test_4_24() {
        Main main = getInstance();
        int ans = main.binaryGap(22);
        System.out.println("ans = " + ans);
    }
    public static void main(String[] args) {
//        test_4_16();
//        test_4_19();
//        test_4_20();
//        test_4_21();
//        test4_22();
//        test_4_24();

    }
}
