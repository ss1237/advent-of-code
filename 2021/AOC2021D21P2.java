import java.util.*;
import java.io.*;

public class AOC2021D21P2 {
	static String filename = "aoc";
	static int[][] freq = new int[][] {{3, 1}, {4, 3}, {5, 6}, {6, 7}, {7, 6}, {8, 3}, {9, 1}};

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		int p1 = Integer.parseInt(in.nextLine().split(": ")[1]);
		int p2 = Integer.parseInt(in.nextLine().split(": ")[1]);
		
		long[] ans = solve(p1, p2, 0, 0);
		System.out.println(Math.max(ans[0], ans[1]));
	}
	
	public static long[] solve(int p1, int p2, int t1, int t2) {
		if (t1 >= 21) {
			return new long[] {1, 0};
		}
		if (t2 >= 21) {
			return new long[] {0, 1};
		}
		
		long p1w = 0, p2w = 0;
		
		for (int[] pair : freq) {
			long[] temp = solve(p2, (p1 + pair[0] - 1) % 10 + 1, t2, t1 + (p1 + pair[0] - 1) % 10 + 1);
			
			p1w += temp[1] * pair[1];
			p2w += temp[0] * pair[1];
		}
		
		return new long[] {p1w, p2w};
	}
}