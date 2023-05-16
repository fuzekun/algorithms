package leetcode.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *
 * 将字符串抓化成数组，
 * 为了leetcode中更方便调试。
 * 可以直接将[[3,4,], [1,2]]这种转化成二维数组
 *
 */

public class ChangeToArrayOrList {
    private static int idx;
    private ChangeToArrayOrList() {}  // 不提供构对象，只提供方法

    /**
     *
     * @param chars 代表数组的字符串，类型一定是int类型。
     * @return 返回多维的list。对应的维度和[[[一样的。
     *
     */
    @SuppressWarnings("unchecked")
    private static List changeToIntList(char[] chars) {
        List list = new ArrayList();
        if (chars[idx] == '[') {                                   // 还有新的层，将本层的所有数组计算出来
            while(idx < chars.length && chars[idx] == '[') {
                // 去掉左括号
                idx++;
                // 碰见新的[，递归处理子数组
                List nlist = changeToIntList(chars);
                list.add(nlist);
                // 处理完成后，如果是逗号，表明本层可能还有
                if (idx < chars.length && chars[idx] == ',')
                    idx++;
            }
            // 本层处理完成，去掉],回到上一层
            idx++;
        } else {                                                  // 最后一层，把所有的数字计算出来。
            while(idx < chars.length && (Character.isDigit(chars[idx]) || chars[idx] == '-') ) {
                // 获取符号
                int flag = 1;
                if (chars[idx] == '-') {
                    flag = -1;
                    idx++;
                }
                // 计算一个数字
                StringBuilder b = new StringBuilder();
                while(Character.isDigit(chars[idx])) {
                    b.append(chars[idx]);
                    idx++;
                }
                int val = Integer.valueOf(b.toString()) * flag;
                list.add(val);
                // 如果还有逗号，说明可能还有数字。
                while (idx < chars.length && chars[idx] == ',')
                    idx++;
            }
            // 最后一层处理完成，去掉最后的],返回上一层
            idx++;
        }
        return list;
    }
    /**
     *
     * @param chars 代表数组的字符串，类型一定是String类型。
     * @return 返回多维的list。对应的维度和[[[一样的。
     *
     */
    @SuppressWarnings("unchecked")
    private static List changeToStringList(char[] chars) {
        List list = new ArrayList();
        if (chars[idx] == '[') {                                // 碰见新的层，处理本层的所有数组
            while(idx < chars.length && chars[idx] == '[') {
                // 去掉左括号
                idx++;
                // 递归处理子层
                List nlist = changeToStringList(chars);
                list.add(nlist);
                // 处理完成后，如果是，说明可能还有。
                if (idx < chars.length && chars[idx] == ',')
                    idx++;
            }
            // 本层处理完成了，去掉 ]， 返回上一层
            idx++;
        } else {                                                // 最后一层
            while(idx < chars.length && (Character.isLetter(chars[idx]) || chars[idx] == '\"')) {
                // 处理每一个字符串
                StringBuilder b = new StringBuilder();
                while(Character.isLetter(chars[idx]) || chars[idx] == '\"') {
                    b.append(chars[idx]);
                    idx++;
                }
                list.add(b.toString());
                // 本层可能还有，去掉逗号，看下一个是否仍旧是字符串
                while (idx < chars.length && chars[idx] == ',')
                    idx++;
            }
            idx++;
        }
        return list;
    }

    /**
     *
     *
     * @param s 标志数组的字符串，比如["\"a", "\"b"]这种
     * @return 转化之后的数组得到{"a", ""b}这种
     *
     * 1. 逗号可以多少，但是括号一定要匹配
     * 2. 需要int 类型的数组, long不行
     * 3. 每一维度的维度可以不一样
     */
    public static List changeToIntList(String s) {
        // 验证括号是否正确
        if (!checkKuo(s)) {
            throw new IllegalArgumentException("括号不匹配!");
        }
        // 去掉空格
        s = s.replace(" ", "");
        // 初始化参数
        idx = 0;
        // 边界情况
        if (s.length() == 0) {
            return new ArrayList();
        }
        // 返回
        List list = changeToIntList(s.toCharArray());
        return (List)list.get(0);
    }

    /**
     *
     *
     * @param s 标志数组的字符串，比如["\"a", "\"b"]这种
     * @return 转化之后的数组得到{"a", ""b}这种
     *
     * 1. 逗号可以多少，但是括号一定要匹配
     * 2. 需要String类型的数组，用""引起来的这种
     * 3. 维度可以不一样
     */
    public static List changeToStringList(String s) {
        // 验证括号是否正确
        if (!checkKuo(s)) {
            throw new IllegalArgumentException("括号不匹配!");
        }
        // 去掉空格
        s = s.replace(" ", "");
        // 初始化参数
        idx = 0;
        // 边界情况
        if (s.length() == 0) {
            return new ArrayList();
        }
        // 返回
        List list = changeToStringList(s.toCharArray());
        return (List)list.get(0);
    }


    /**
     *
     * @param s 表示数组的字符串[ 1,3, 234,-2,]
     * @return  数组{1,3,234,-2}
     *
     * 1. 可以有空格，多余的逗号
     * 2. 括号一定要匹配，并且一定要有
     * 3. 一定是int类型的数组，不能是long类型，或者double类型
     */
    public static int [] changTo1DIntArray(String s) {
        if (!checkKuo(s)) {
            throw new IllegalArgumentException("括号不匹配!");
        }
        // 去掉空格
        s = s.replace(" ", "");
        // 计算并返回
        List<Integer> list = changeToIntList(s);
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
    /**
     *
     * @param s 表示数组的字符串[[ 1,3, 234,-2,], [1,32]]
     * @return  数组{{1,3,234,-2}, {1,32}}
     *
     * 1. 可以有空格，多余的逗号
     * 2. 括号一定要匹配，并且一定要有
     * 3. 一定是int类型的数组，不能是long类型，或者double类型
     * 4. 最后一维的维度可以不一样
     */
    public static int[][] changeTo2DIntArray(String s) {
        s = s.replace(" ", "");
        List<List<Integer>> list = changeToIntList(s);
        int[][] ret = list.stream().map(x->x.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
        return ret;
    }
    /**
     *
     * @param s 表示数组的字符串[[[ 1,3, 234,-2,], [1,32]], [[1,2]]]
     * @return  数组{{{1,3,234,-2}, {1,32}}, {{1,2}}};
     *
     * 1. 可以有空格，多余的逗号
     * 2. 括号一定要匹配，并且一定要有
     * 3. 一定是int类型的数组，不能是long类型，或者double类型
     * 4. 最后一维的维度可以不一样
     */
    public static int[][][] changeTo3DIntArray(String s) {
        s = s.replace(" ", "");
        List<List<List<Integer>>> list = changeToIntList(s);
        int[][][] array = list.stream().
                map(
                        l -> l.stream().map(
                                x->x.stream().mapToInt(Integer::valueOf).toArray()               // 创建一维数组
                        ).toArray(int[][]::new)                                           // 创建二维数组
                ).toArray(int[][][]::new);                                                 // 创建三维数组
        return array;
    }
    /**
     *
     *
     * @param s 标志数组的字符串，比如["\"a", "\"b"]这种
     * @return 转化之后的数组得到{"a", ""b}这种
     *
     * 1. 逗号可以多少，但是括号一定要匹配
     * 2. 需要String类型的数组，用""引起来的这种
     * 3. 维度可以不一样
     */
    public static String[] changeTo1DString(String s) {
        s = s.replace(" ", "");
        List list = changeToStringList(s);
//        if (list == null) {
//            throw new RuntimeException("必须保证是一维数组啊，亲");
//        }
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = (String) list.get(i);
        }
        return ans;
    }

    /**
     *
     *
     * @param s 标志数组的字符串，比如[["\a\", "\b\"],[\"a\"]]这种
     * @return 转化之后的数组得到{{"a", ""b}, {"a"}这种
     *
     * 1. 逗号可以多少，但是括号一定要匹配
     * 2. 需要String类型的数组，用""引起来的这种
     * 3. 维度可以不一样
     */
    public static String[][] changTo2DString(String s) {
        s = s.replace(" ", "");
        List<List<String>> list = changeToStringList(s);
        return list.stream().map(x->x.stream().toArray(String[]::new)).toArray(String[][]::new);
    }

    /**
     *
     *
     * @param s 检测括号是否匹配
     * @return 匹配或者不匹配
     */
    private static boolean checkKuo(String s) {
        Stack<Character> st = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '[') st.push('[');
            if (ch == ']') {
                if (st.empty() || st.pop() != '[') return false;
            }
        }
        return st.empty();
    }



    public static void main(String[] args) {

        String s = "[[\"fda\"],[\"dsfa\"]]";
        List list = changeToStringList(s);
        System.out.println(list);
        s = "[\"mass\",\"as\",\"hero\",\"superhero\"]";
        String[] sArr = changeTo1DString(s);
        PrintArrays.print1DObjArray(sArr);
        s = "[[-1,1],[-2,2],[2,0],[2,4],[3,3],[4,2,4]]";
        list = changeToIntList(s);
//        System.out.println(s);
        int[][]arrs = changeTo2DIntArray(s);
        PrintArrays.print2DIntArray(arrs);

        s = "[[[1,-1,],[2, -21]],[ [3, -322],[4,-323],]]";
//        System.out.println(s);
        list = changeToIntList(s);
        int[][][] arrss = changeTo3DIntArray(s);
        PrintArrays.print3DIntArray(arrss);


        s = "[13,43,42,1,]";
        int[] arr = changTo1DIntArray(s);
        System.out.println(Arrays.toString(arr));

    }
}
