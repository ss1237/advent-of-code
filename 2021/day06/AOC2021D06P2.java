import java.util.*;
import java.io.*;

public class AOC2021D06P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		long[][] init = new long[9][2];

		String[] nums = in.nextLine().split(",");
		for (String s : nums) {
			init[Integer.parseInt(s)][0]++;
		}
		
		
		long[][] trans = new long[9][9];
		for (int i = 0; i < 8; i++) {
			trans[i][i + 1]++;
		}
		trans[6][0]++;
		trans[8][0]++;
		
		long[][] result = multiply(expo(trans, 256), init);
		
		long ans = 0;
		
		for (int i = 0; i < result.length; i++) ans += result[i][0];

		out.println(ans);
		out.close();

	}
	
	static long[][] expo(long[][] base, long n) {
		long[][] ret = new long[base.length][base[0].length];
		for (int i = 0; i < base.length; i++) ret[i][i] = 1;

		while (n > 0) {
			if (n % 2 == 1) {
				ret = multiply(ret, base);
			}
			n /= 2;
			base = multiply(base, base);
		}
		return ret;
	}

	static long[][] multiply(long[][] a, long[][] b) {
		long[][] c = new long[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				for (int k = 0; k < b[0].length; k++) {
					c[i][k] += a[i][j] * b[j][k];
				}
			}
		}
		return c;
	}
}