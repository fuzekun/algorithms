package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class InAndOutUitl {
//	private static Scanner sc = new Scanner(System.in);
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	private int curn;
	private int[] curLine;
	public InAndOutUitl(){
		// TODO Auto-generated constructor stub
		curn = -1;
	}
	
	public String nextLine() throws Exception {
		return in.readLine();
	}
	public int[] toIntArray(String s) {
		String[] input = s.split(" ");
		int[] ans = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			ans[i] = Integer.parseInt(input[i]);   
		}
		return ans;
	}
	/**
	 * 
	 * 如果输入终止返回空。
	 * */
	public Integer nextInt() throws Exception{
		// 新的一行
		if (curn == -1) {
			curn = 0;
			String tmp = in.readLine();
			if (tmp == null) return null;
			curLine = toIntArray(tmp);
		}
		int ans = curLine[curn++];
		// 读完这一行指针指向下一行
		if (curn == curLine.length) curn = -1;
		return ans;
	}
	
	public void write(String s) throws Exception {
		out.write(s);
	}
	public void flush() throws Exception {
		out.flush();
	}

}
