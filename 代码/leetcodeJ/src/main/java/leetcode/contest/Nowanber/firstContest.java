package leetcode.contest.Nowanber;

import java.util.Stack;

/**
 * @author: Zekun Fu
 * @date: 2022/11/6 10:15
 * @Description: 第一场双周赛
 */
public class firstContest {

    public char cal(char a, char op, char b) {
        boolean x = a == 't';
        boolean y = b == 't';
        if (op == '&') {
            if (x & y )return 't';
            else return 'f';
        }
        else {
            if (x | y) return 't';
            return 'f';
        }
    }
    public boolean parseBoolExpr(String expression) {
        char[] chars = expression.toCharArray();
        Stack<Character> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        /**

         1. ( ：直接入栈
         2. ）：符号出栈一个，数字直到遇见了(， 然后计算，结果放回数字栈顶
         3. ，：直接去掉就行了
         4. t, f: 直接入数字栈
         5. & | ！：直接入符号栈
         */
        for (char ch : chars) {
            if (ch == ')') {
                char op = ops.pop();
                if (op == '!') {
                   char t = nums.pop(); nums.pop();
                   if (t == 't') nums.push('f');
                   else nums.push('t');
                } else {        // & |
                    char cur = nums.pop();
                    char t;
                    while ((t = nums.pop()) != '(') {
                        cur = cal(cur, op, t);
                    }
                    nums.push(cur);
                }
            } else if (ch == '&' || ch == '|' || ch == '!') {
                ops.push(ch);
            }
            else if (ch == 't' || ch == 'f' || ch == '('){
                nums.push(ch);
            }
        }
        return nums.pop() == 't';
    }

    public static void main(String[] args) {
        firstContest f = new firstContest();
//        System.out.println(f.parseBoolExpr("|(f, f, t)"));
//        System.out.println(f.parseBoolExpr("&(t,t,f)"));
//        System.out.println(f.parseBoolExpr("!(f)"));
//        System.out.println(f.parseBoolExpr("!(t)"));
//        System.out.println(f.parseBoolExpr("&(!(t),&(f),|(f))"));         // false
//        System.out.println(f.parseBoolExpr("&(!(&(f)),&(t),|(f,f,t))")); // true
        System.out.println(f.parseBoolExpr("&(&(f),&(!(t),&(f),|(f)), &(!(&(f)),&(t),|(f,f,t)))"));
        boolean ans = f.parseBoolExpr("!(&(&(f),&(!(t),&(f),|(f)),&(!(&(f)),&(t),|(f,f,t))))");
        System.out.println(ans);
    }
}
