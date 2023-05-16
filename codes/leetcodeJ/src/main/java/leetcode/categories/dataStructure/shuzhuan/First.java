package leetcode.categories.dataStructure.shuzhuan;

import leetcode.utils.ChangeToArrayOrList;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/4/22 16:11
 * @Description:
 * 离散化 + 树状数组
 */
public class First {



    private void liSan(Map<Double, Integer>mp, List<Double>nums) {

        int cnt = 0;
        for (Double x : nums) {
            if (mp.containsKey(x)) continue;
            mp.put(x, cnt);
        }
    }
    public int fieldOfGreatestBlessing(int[][] forceField) {
        Map<Double, Integer>xid = new HashMap<>();
        Map<Double, Integer>yid = new HashMap<>();

        List<Double>xlist = new ArrayList<>();
        List<Double>ylist = new ArrayList<>();
        for (int[] force : forceField) {
            double x = force[0], y = force[1];
            double len = (double)force[2] / 2.0;
            double left = x - len, right = x + len, down = y - len, up = y + len;
            xlist.add(left); xlist.add(right);
            ylist.add(up); ylist.add(down);
        }
        xlist.sort(Double::compareTo);
        ylist.sort(Double::compareTo);
//        liSan(xid, xlist);
//        liSan(yid, ylist);

        int ans = 0;
        for (Double curx : xlist) {
            for (Double cury : ylist) {
                int cnt = 0;
                for (int[] force : forceField) {
                    double x = force[0], y = force[1];
                    double len = (double)force[2] / 2.0;
                    double left = x - len, right = x + len, down = y - len, up = y + len;
                    if (left <= curx && right >= curx && up >= cury && down <= cury)
                        cnt++;
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        First f = new First();
//        int ans = f.fieldOfGreatestBlessing(ChangeToArrayOrList.changeTo2DIntArray("[[0,0,1],[1,0,1]]"));
        int ans = f.fieldOfGreatestBlessing(ChangeToArrayOrList.changeTo2DIntArray("[[932,566,342],[546,489,250],[723,454,748],[830,887,334],[617,534,721],[924,267,892],[151,64,65],[318,825,196],[102,941,940],[748,562,582],[76,938,228],[921,15,245],[871,96,823],[701,737,991],[339,861,146],[484,409,823],[574,728,557],[104,845,459],[363,804,94],[445,685,83],[324,641,328],[626,2,897],[656,627,521],[935,506,956],[210,848,502],[990,889,112]]"));
        System.out.println(ans);
        int[] nums = new int[2];
        int maxv = Arrays.stream(nums).max().getAsInt();
    }
}
