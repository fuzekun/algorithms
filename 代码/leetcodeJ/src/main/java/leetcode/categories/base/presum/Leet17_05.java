package leetcode.categories.base.presum;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: Zekun Fu
 * @date: 2023/3/11 14:12
 * @Description:
 * 面试17.05 字母与数字
 *
 * 字母看成1，数字看成-1，转化成和为0的最长子数组问题
 * 使用前缀和 + hash进行解决
 *
 * 1. 对于选择最小小标的逻辑出现了错误
 * 2
 */
public class Leet17_05 {
    public String[] findLongestSubarray(String[] array) {
        HashMap<Integer, Integer>mp = new HashMap<>();
        mp.put(0, -1);
        int maxlen = 0;
        int n = array.length;
        int minId = n;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(array[i].charAt(0))) {
                sum++;
            } else sum--;
            if (mp.containsKey(sum)) {
                int preId = mp.get(sum);
                // 存在子数组, 更新最大值和下标最小值
                if (i - preId > maxlen) {
                    // 大于的时候，直接更新下标
                    maxlen = i - preId;
                    minId = preId + 1;
                } else if (i - preId == maxlen) {
                    // 等于的时候，选择小的下标
                    minId = Math.min(preId + 1, minId);
                }
            } else {
                // 不存在子数组,放入最该值的最左端点。
                mp.put(sum, i);
            }
        }
        String[] ans = new String[maxlen];
        System.arraycopy(array, minId, ans, 0, maxlen);
//        System.out.println(String.format("sum = %d", sum));
        return ans;
    }
    public static void main(String[] args) {
        String[]arr = new String[]{"42","10","O","t","y","p","g","B","96","H","5","v","P","52","25","96","b","L","Y","z","d","52","3","v","71","J","A","0","v","51","E","k","H","96","21","W","59","I","V","s","59","w","X","33","29","H","32","51","f","i","58","56","66","90","F","10","93","53","85","28","78","d","67","81","T","K","S","l","L","Z","j","5","R","b","44","R","h","B","30","63","z","75","60","m","61","a","5","S","Z","D","2","A","W","k","84","44","96","96","y","M"};
//        String[] arr = {"42","10","O","t","y","p","g","B","96","H","5","v","P","52","25","96","b","L","Y","z","d","52","3","v","71","J","A","0","v","51","E","k","H","96","21","W","59","I","V","s","59","w","X","33","29","H","32","51","f","i","58","56","66","90","F","10","93","53","85","28","78","d","67","81","T","K"};
//        String[] arr = {"T", "K", "1", "2"};
        Leet17_05 t = new Leet17_05();
        String[] ans = t.findLongestSubarray(arr);
        System.out.println(ans.length);
        System.out.println(Arrays.toString(ans));
//        String[] arr2 = {"52","3","v","71","J","A","0","v","51","E","k","H","96","21","W","59","I","V","s","59","w","X","33","29","H","32","51","f","i","58","56","66","90","F","10","93","53","85","28","78","d","67","81","T","K","S","l","L","Z","j","5","R","b","44","R","h","B","30","63","z","75","60","m","61","a","5"};
//        System.out.println(arr2.length);
    }
}
