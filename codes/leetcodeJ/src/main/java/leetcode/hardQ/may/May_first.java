package leetcode.hardQ.may;

import leetcode.everyDay.May.May_E_1;

/*
*
*
*   5月的第一天的刷题
*  65. 有效数字
*
* 小数点两侧必须得有一个数字
* e的两侧必须都需要由数字
* 小数点后面不可以有正负号
* 小数点后面可以有e，但是这个e前面不可以有数字
* * */
public class May_first {


    // 去除e和.的情况, 保证必须要有一个数字
    private boolean check1(String s) {
//        System.out.println(s);
        char chars[] = s.toCharArray();
        char pre = '#';
        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                pre = 'N';
            } else if (ch == '+' || ch == '-'){
                if (pre != '#') return false;
                pre = '+';
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean hasNum(String s) {
        char[] ss = s.toCharArray();
        for (char ch: ss) {
            if (Character.isDigit(ch)) return true;
        }
        return false;
    }

    // 检查小数点之后，或者没有小数点的情况
    private boolean check2(String s) {
        int Ecnt = 0, ecnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'E')
                Ecnt++;
            else if (ch == 'e')
                ecnt++;
        }
        if (Ecnt + ecnt > 1) return false;
        if (Ecnt != 0) {
            String[] ss = s.split("E");
            if (ss.length != 2) return false;   //
            return (check1(ss[0]) && check1(ss[1])) && (hasNum(ss[0]) && hasNum(ss[1]));
        } else if (ecnt != 0) {
            String[] ss = s.split("e");
            if (ss.length != 2) return false;
            return (check1(ss[1]) && check2(ss[1])) && (hasNum(ss[0]) && hasNum(ss[1]));
        } else {
            return check1(s);
        }
    }
    public boolean hasC(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '+' || ch == '-')
                return true;
        }
        return false;
    }
    public boolean isNumber(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.')
                cnt++;
        }
        if (cnt > 1) return false;
        else if (cnt == 1) {
            String[] ss = s.split("\\.");
            // 1. 首先保证小数点两侧至少有一个数字
            if (ss.length == 0) return false;
            if (ss.length == 1) {
                if (!hasNum(ss[0])) return false;
                return check1(ss[0]);
            }
            // 3. 小数点后面不能有正负号
            if (hasC(ss[1])) return false;
            return (check1(ss[0]) && check2("0"+ ss[1])) && (hasNum(ss[0]) || hasNum(ss[1]));
        } else {
            return check2(s);
        }
    }


}
