package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class InOutUtil {
	
	private InOutUtil() {
		
	}
	private static Scanner scanner = new Scanner(System.in);
	private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	
	public static Scanner getSanScanner() {
		return scanner;
	}
	
	public static BufferedReader getBufferedReader() {
		return in;
	}
	
	public static BufferedWriter getBufferedWriter() {
		return out;
	}
	
	
	public static int getN(String s) {
		return Integer.parseInt(s);
	}
	
	/**
	 * 将输入的字符串，转化成int类型的数组
	 * 
	 * 1 2 3 -> {1, 2, 3}
	 * 12 3 4 -> {12, 3, 4}
	 * 11 22 {11, 22}
	 * */
	public static int[] toNumArray(String s) {
		String[] input = s.split(" ");
		int[] ans = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			ans[i]= Integer.parseInt(input[i]); 
		}
		return ans;
	}
	
}
