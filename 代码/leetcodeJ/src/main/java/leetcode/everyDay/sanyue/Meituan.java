package leetcode.everyDay.sanyue;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author: Zekun Fu
 * @date: 2023/3/25 18:58
 * @Description:
 */
public class Meituan {
    public static boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length!=popA.length){
            return false;
        }
        int p=0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<pushA.length;i++){
            //临时栈开始是空的，所以先添加元素
            stack.push(pushA[i]);
            //如果临时栈不为空，并且临时栈的栈顶元素和出栈序列中的当前元素相等，那就将栈顶元素出栈，出栈序列数组下标++
            while(!stack.empty()&&stack.peek()==popA[p]){
                stack.pop();
                p++;
            }

        }
        return stack.empty(); //或者p==pushA.length;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T != 0) {
            int n = sc.nextInt();
            int[] push = new int[n];
            int[] out = new int[n];
            for (int i = 0; i < n; i++) {
                push[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                out[i] = sc.nextInt();
            }
            if (IsPopOrder(push, out))
                System.out.println("Yes");
            else System.out.println("No");
            T--;
        }
    }
}
