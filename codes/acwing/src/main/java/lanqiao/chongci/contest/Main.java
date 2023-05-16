package lanqiao.chongci.contest;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt();
		if (n == 2) {                       // 
		    System.out.println("YES");
		    return ;
		}
		int flag = 1;
		int cnt = 0;
		// 表示成n进制，如果是1或者0，砝码放在左边, 不产生进位。如果是n - 1，砝码放在右边，产生进位
		while (m != 0) {
			// 当前的位
			int cur = m % n;
			
			// 如果是n - 1，把砝码放在右边, 否则
			if (cur > 1 && cur != n - 1) {
				flag = 0;
				break;
			}
			
			// 产生的进位应该放在这里
			if (cur == n - 1) m++;	
			
			m /= n;
			cnt++;
		}
//		System.out.println(cnt);
		// 最多有101个1
		if (flag == 1 && cnt <= 101) System.out.println("YES");
		else System.out.println("NO");
	}
}
