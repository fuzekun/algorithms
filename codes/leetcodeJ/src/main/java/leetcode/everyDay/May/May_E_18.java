package leetcode.everyDay.May;

import java.util.Random;

/*
*
*
*   乘法表中的第k大数字
* */
public class May_E_18 {

    /*
    *
    *   快速找第k小的数字
    * 1. 获取枢轴
    * 2. 小的移动到左边，大的移动到右边
    * */
    Random random = new Random();
    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    void random_Partition(int[] nums, int l, int r) {
        //[l, r] 长度最长为 r - l + 1, 得到的random[0, r - 1]所以加上l
        int p = random.nextInt(r - l + 1) + l;
        swap(p, l, nums);
    }
    private void findk(int[] nums, int l, int r, int k) {
        if (l >= r) return ;
        random_Partition(nums, l, r);
        int a = nums[l];
        int i = l, j = r;
        while(i < j) {
            while(i < j && nums[j] >= a) j--;
            while(i < j && nums[i] <= a) i++;
            swap(i, j, nums);
        }
        swap(i, l, nums);
        if (i == k) return ;
        else if (i > k) findk(nums, l, i, k);
        else findk(nums, i + 1, r, k);
    }
    public int findKthNumber(int m, int n, int k) {
        int[] nums = new int[m * n + 1];
        int cnt = 0;
        Random r = new Random();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                nums[cnt++] = i * j;
                System.out.print(nums[cnt - 1] + " ");
            }
            System.out.println();
        }
        System.out.println();
        k--;
        findk(nums, 0, cnt - 1, k);
        return nums[k];
    }

    public static void main(String[] args) {
        System.out.println(new May_E_18().findKthNumber(6,6, 6));
    }
}
