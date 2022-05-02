package leetcode.utils;

import java.util.ArrayList;
import java.util.List;

/*
*
*   leetcode中的数组转换成实际的数组
*
* 1. 算法过程
* 2. 如何设计着个代码？ 是否要让idx成为全局变量，并且是静态的
* 3. 辅助函数changeToList是否应该使用static
*
* 很多设计上面的问题
* 1. 如何返回数组，而不是List, 转化成1维，2维，还是其他维。
* 2. 返回的怎么是Integer类型的，最后一维
*
* */
public class ChangeToArrayOrList {
    private static int idx;
    private ChangeToArrayOrList() {}  // 不提供构对象，只提供方法
    @SuppressWarnings("unchecked")
    private static List changeToIntList(char[] chars) {
        List list = new ArrayList();
        if (chars[idx] == '[') {
            while(idx < chars.length && chars[idx] == '[') {
                idx++;                             // 去掉左括号
                List nlist = changeToIntList(chars);
                list.add(nlist);
                while(idx < chars.length && (chars[idx] == ']' || chars[idx] == ','))
                    idx++;                             // 去掉逗号和右括号
            }
        } else {                                        // 字母
            while(idx < chars.length && Character.isDigit(chars[idx])) {
                StringBuilder b = new StringBuilder();
                while(Character.isDigit(chars[idx])) {
                    b.append(chars[idx]);
                    idx++;
                }
                list.add(Integer.valueOf(b.toString()));
                idx++;                          // 逗号和右括号去掉
            }
        }
        return list;
    }
    @SuppressWarnings("unchecked")
    private static List changeToStringList(char[] chars) {
        List list = new ArrayList();
        if (chars[idx] == '[') {
            while(idx < chars.length && chars[idx] == '[') {
                idx++;                             // 去掉左括号
                List nlist = changeToStringList(chars);
                list.add(nlist);

                while(idx < chars.length && (chars[idx] == ']' || chars[idx] == ','))
                    idx++;                             // 去掉逗号和右括号
            }
        } else {                                    // 字符串
            while(idx < chars.length && (Character.isLetter(chars[idx]) || chars[idx] == '\"')) {
                StringBuilder b = new StringBuilder();
                while(Character.isLetter(chars[idx]) || chars[idx] == '\"') {
                    b.append(chars[idx]);
                    idx++;
                }
                list.add(b.toString());
                idx++;                          // 逗号和右括号去掉
            }
        }
        return list;
    }

    /*
    *
    *   转化成int类型的列表
    *   1. 保证必须是列表类型的字符串
    *   1.1 一定含有[]结构，并且是关闭的
    *   1.2 一定是以 ']'结尾代表，而不是以 ','结尾
    *   1.3 其中的变量一定是int类型而不是String类型
    *
    * */
    public static List changeToIntList(String s) {
        s = s.replace(" ", "");
        idx = 0;

        // 这里需要先验证是否符合上面两条数据，如果不符合直接返回
        if (s.length() == 0)
        {
            throw new RuntimeException("不是正确的数组类型啊，亲");
        }

        List list = changeToIntList(s.toCharArray());
        return (List)list.get(0);
    }

    public static List changeToStringList(String s) {
        s = s.replace(" ", "");
        idx = 0;

        // 这里需要先验证是否符合上面两条数据，如果不符合直接返回
        if (s.length() == 0)
        {
            throw new RuntimeException("不是正确的数组类型啊，亲");
        }
        List list = changeToStringList(s.toCharArray());
        return (List)list.get(0);
    }



    public static int [] changTo1DIntArray(String s) {
        s = s.replace(" ", "");
        List list = changeToIntList(s);
        if (list == null) {
            throw new RuntimeException("必须保证是一维数组啊，亲");
        }
        int [] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = (Integer) list.get(i);
        }
        return ans;
    }

    /*
    *
    *   转换成int类型的二维数组
    *
    * 1. 保证每一维的大小相同
    * 2. 保证一定是二维的
    * 3. 保证不为空
    * */

    public static int[][] changeTo2DIntArray(String s) {
        s = s.replace(" ", "");
        List list = changeToIntList(s);
        if (list == null || list.size() == 0) {
            throw new RuntimeException("必须保证是二维数组啊，亲");
        }
        List list1 = (List)list.get(0);
        int m = list1.size(), n = list.size();
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            list1 = (List)list.get(i);

            if (list1.size() > m) {
                throw new RuntimeException("数组越界了啊，亲，必须保证维度相同啊");
            }
            for (int j = 0; j < m; j++) {
                ans[i][j] = (Integer) list1.get(j);
            }
        }
        return ans;
    }

    public static String[] changeTo1DString(String s) {
        s = s.replace(" ", "");
        List list = changeToStringList(s);
        if (list == null) {
            throw new RuntimeException("必须保证是一维数组啊，亲");
        }
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = (String) list.get(i);
        }
        return ans;
    }
    public static String[][] changTo2DString(String s) {
        s = s.replace(" ", "");
        List list = changeToStringList(s);
        if (list == null || list.size() == 0) {
            throw new RuntimeException("必须保证是二维数组啊，亲");
        }
        List list1 = (List)list.get(0);
        int m = list1.size(), n = list.size();
        String[][] ans = new String[n][m];
        for (int i = 0; i < n; i++) {
            list1 = (List)list.get(i);

            if (list1.size() != m) {
                throw new RuntimeException("数组越界了啊，亲，必须保证维度相同啊");
            }
            for (int j = 0; j < m; j++) {
                ans[i][j] = (String) list1.get(j);
            }
        }
        return ans;
    }


    public static void main(String[] args) {

        String s = "[[\"fda\"],[\"dsfa\"]]";
        List list = changeToStringList(s);
        System.out.println(list);

        s = "[[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]";
        list = changeToIntList(s);
        System.out.println(s);
        int[][]arrs = changeTo2DIntArray(s);
        PrintArrays.print2DIntArray(arrs);


        s = "[13,43,42,1]";
        int[] arr = changTo1DIntArray(s);

        System.out.println();

        s = "[\"mass\",\"as\",\"hero\",\"superhero\"]";
        String[] sArr = changeTo1DString(s);
        PrintArrays.print1DObjArray(sArr);

        s = "[[\"mass\",\"as\"],[\"hero\",\"superhero\"]]";
        String[][] sArrs = changTo2DString(s);
        PrintArrays.print2DObjArray(sArrs);
    }
}
