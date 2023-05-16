package lanqiao.chongci.sub_pre;




import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * 
 * 
 * 	增减序列
 * 
 * 1. 如何想到的差分
 * 2. 如何避免int出错，改用long
 * 3. 逻辑错误问题。
 * 
 * */
public class Acwing100 {

	public static void main(String[] args) {
		PriorityQueue<Long>pos = new PriorityQueue<>((o1, o2)->Long.compare(o2, o1));			// 3, 2
		PriorityQueue<Long>neg = new PriorityQueue<>();			//-2, -1
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long pre = 0;
		for (int i = 0; i < n; i++) {
			long cur = sc.nextInt();
			long sub = cur - pre;
			pre = cur;
			if (i != 0) {
				if (sub > 0) pos.add(sub);
				if(sub < 0) neg.add(Math.abs(sub));
			}
		}
		
		// 下面的逻辑就是求Math.min(sum(pos), sum(neg))
		long ans = 0;
		while (!pos.isEmpty() && !neg.isEmpty()) {
			long t1 = pos.poll(), t2 = neg.poll();
			if (t1 > t2) {
				ans += t2;
				t1 -= t2;
				pos.add(t1);
			} else if (t1 < t2){
				ans += t1;
				t2 -= t1;
				neg.add(t2);
			} 
			else ans += t1;			// 相等的时候每个加上一个
		}
		long kind = 1;				// 全部选择后面
		while (!pos.isEmpty()) {
			long t = pos.poll();
			kind += t;
			ans += t;
		}
		while (!neg.isEmpty()) {
			long t = neg.poll();
			kind += t;
			ans += t;
		}
		System.out.println(ans + "\n" + kind);
	}
}
