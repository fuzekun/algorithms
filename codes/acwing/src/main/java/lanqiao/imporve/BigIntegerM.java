package lanqiao.imporve;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/21 23:11
 * @Description:    大数乘法
 */
public class BigIntegerM {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        String a = s[0], b = s[1];
        BigInteger numa = new BigInteger(a);
        BigInteger numb = new BigInteger(b);
        System.out.println(numa.add(numb));
    }
}
