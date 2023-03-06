package lanqiao.imporve;

/**
 * @author: Zekun Fu
 * @date: 2022/10/14 10:55
 * @Description:
 */

import java.io.*;
import java.util.*;
public class InputTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a;
        int b;
        while (in.hasNext()) {

            a = in.nextInt();
            b = in.nextInt();
            int c = a + b;
            System.out.println(c);
        }
    }
}