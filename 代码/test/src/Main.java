

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/4/1 19:06
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        BigInteger jin = new BigInteger(k + "");
        BigInteger nums1 = new BigInteger("0");
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            BigInteger t = new BigInteger(a + "");
            nums1 = nums1.multiply(jin);
            nums1 = nums1.add(t);
        }
        n = sc.nextInt(); k = sc.nextInt();
        BigInteger nums2 = new BigInteger("0");
        jin = new BigInteger(k + "");
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            BigInteger t = new BigInteger(a + "");
            nums2 = nums2.multiply(jin);
            nums2 = nums2.add(t);
        }
        if (nums1.compareTo(nums2) > 0) System.out.println(">");
        else if (nums1.compareTo(nums2) == 0) System.out.println("=");
        else System.out.println("<");
    }
}
