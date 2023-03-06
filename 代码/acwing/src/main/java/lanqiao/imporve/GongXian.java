package lanqiao.imporve;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/11/2 10:04
 * @Description: 共线
 */


public class GongXian {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] a = new double[n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextDouble();
            b[i] = sc.nextDouble();
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Double, Integer>cnt = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                double k;
                if (a[i] == a[j]) k = Double.MAX_VALUE;
                else k = (b[i] - b[j]) / (a[i] - a[j]);
                cnt.put(k, cnt.getOrDefault(k, 1) + 1);
                ans = Math.max(ans, cnt.get(k));
            }
        }
        System.out.println(ans);
    }
}
