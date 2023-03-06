package lanqiao.imporve;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author: Zekun Fu
 * @date: 2022/10/23 23:25
 * @Description: 实现加减乘除以及括号的运算
 *
 *
 *
 * 23 * (23 + 24 * 3)
 * (23 * 23 + 1)=
 * 1 + 1 = 2
 * -(1 + 1) 没有两个数字,遇见左括号不计算
 *
 * 问题1：连续两个符号，x = 0，会放入栈中,加上符号位，如果不是数字不放进去
 * 问题2：如果栈顶是 * 碰见左括号是否要运算，没法运算啊。
 * 问题3: 如果栈顶是( 是否要运算,肯定不运算。
 * 问题4： 如果最外部加上了括号，已经计算完了，又加了一个)
 * 那么此时数字栈中只有一个元素，无法弹出两个。
 * 符号栈已经空了，说明计算完成了，不会有那种情况。
 */

public class CalCulate {
    // 比较两个运算符的优先级, 判断是否出栈
    private static boolean cmp(char op1, char op2) {
        if (op1 == '(') return false;
        else if (op1 == '*' || op1 == '/') {
            if (op2 != '(') return true;
        }
        else if (op1 == '+' || op1 == '-') {
            if (op2 == '+' || op2 == '-' || op2 == '=')
                return true;
        }
        return false;
    }
    private static int cal(int x, char op, int y ){
        if (op == '+') return x + y;
        if (op == '-') return x - y;
        if (op == '*') return x * y;
        return x / y;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s += '=';               // 处理最后一个运算符
        s = s.replace(" ", "");
        char[] chars = s.toCharArray();
        int x = 0, flag = 0;
        Stack<Integer>nums = new Stack<>();
        Stack<Character>ops = new Stack<>();
        // 防止出现负数打头的情况
        nums.push(0);
        for (char c : chars) {
            if (Character.isDigit(c)) {
                x = x * 10 + c - '0';
                flag = 1;
            }
            else if (c != ')'){
                if (flag == 1) nums.push(x); x = flag = 0;
                while (!ops.empty() && cmp(ops.peek(), c)) {
                    char op = ops.pop();
                    int b = nums.pop();
                    int a = nums.pop();
                    nums.push(cal(a, op, b));
                }
                ops.push(c);
            } else { // 对于右括号特殊处理
                if (flag == 1) nums.push(x); x = flag = 0;
                while (ops.peek() != '(') {
                    int b = nums.pop();
                    int a = nums.pop();
                    nums.push(cal(a, ops.pop(), b));
                }
                ops.pop();
            }
        }
        System.out.println(nums.pop());
    }
}
