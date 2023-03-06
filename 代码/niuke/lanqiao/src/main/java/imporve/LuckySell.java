package lanqiao.imporve;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/17 18:23
 * @Description:    幸运的店家
 */
public class LuckySell {

//    public static void main(String[] args) {
//        long n;
//        Scanner sc = new Scanner(System.in);
//        n = sc.nextInt();
//        int ans = 0;
//        while (n > 0) {
//            long i = 1;
//            for (i = 1; i <= n; i *= 3) {}
//            i /= 3;
//            n -= i;
//            ans ++;
//        }
//        System.out.println(ans);
////        HashMap<Integer, Integer>mp = new HashMap<>();
////        mp.remove(1);
//    }
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        // 滑动窗口, 如果窗口中种类大于2直接移动否则不移动
        int ans = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            int x = fruits[j];
            mp.put(x, mp.getOrDefault(x, 0) + 1);
            while (mp.size() > 2) {
                mp.put(fruits[i], mp.get(fruits[i]) - 1);
                if (mp.get(fruits[i]) == 0)
                    mp.remove(fruits[i]);
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(new LuckySell().totalFruit(arr));
    }
}
