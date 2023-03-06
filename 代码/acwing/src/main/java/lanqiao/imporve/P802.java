package lanqiao.imporve;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author: Zekun Fu
 * @date: 2022/10/23 23:09
 * @Description: 计算器，栈的应用
 */
public class P802 {

    private static int cal(int x, char op, int y) {
        if (op == '+') return x + y;
        if (op == '-') return x - y;
        return x;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] chars = s.toCharArray();
        int x = 0, ans = 0;
        Stack <Character>op = new Stack<>();
        op.push('+');
        for (char c : chars) {
            if (Character.isDigit(c)) x = x * 10 + c - '0';
            else if (c != ' '){
                if (!op.empty()) {
                    ans = cal(ans, op.pop(), x);
                }
                op.push(c);
                x = 0;
            }
        }
        System.out.println(ans);
    }
}
