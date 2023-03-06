package lanqiao.contest;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/31 16:22
 * @Description: 蓝桥杯的校内模拟赛
 */
public class FirstC {

    public static void first() {
        int n = 2022;
        int cnt = 0;
        while (n != 0) {
            n >>= 1;
            cnt++;
        }
        System.out.println(cnt);
    }
    public static void third() {
        double n = 0;
        double sum = 0;
        while (sum <= 12) {
            n++;
            sum += 1.0/n;
        }
        System.out.println(n);
        n = 1;
        sum = 0;
        while (n <= 91380) {
            sum += 1.0 / n;
            n++;
        }
        System.out.println(sum);

//        BigInteger n = new BigInteger("1");
//        BigInteger sumu = new BigInteger("0");
//        BigInteger sumd = new BigInteger("1");
//        BigInteger one = new BigInteger("1");
//        while (sumu.divide(sumd).doubleValue() < 12) {
//            sumu = sumd.add(sumu.multiply(n));
//            sumd = sumd.multiply(n);
//            n = n.add(one);
//            System.out.println(n);
//        }
//        // 具体在看是否是整除，如果是整除就应该加上1，否则就不应该加上1
//        if (sumd.multiply(new BigInteger("12")).compareTo(sumu) == 0) {
//            System.out.println("应该加上1");
//        }
//        else System.out.println("不用加上1");
////        System.out.println(sumu);
//        System.out.println(n);

    }
    public static void forth() throws Exception {
        Scanner sc = new Scanner(new File("files/input.txt"));
//        while (sc.hasNextLine()) {
//        }
    }
    public static void main(String[] args)throws Exception {
//        first();
//        third();
        forth();
    }
}
