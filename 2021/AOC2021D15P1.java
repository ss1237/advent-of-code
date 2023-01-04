import java.util.*;
import java.io.*;

public class AOC2021D15P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		ArrayList<String> lines = new ArrayList<String>();
		
		while (in.hasNextLine()) lines.add(in.nextLine());
		
		int[][] dp = new int[lines.size()][lines.get(0).length()];
		
		for (int i = 0; i < dp.length; i++) Arrays.setAll(dp[i], x -> Integer.MAX_VALUE);
		
		dp[0][0] = 0;
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				int cur = lines.get(i).charAt(j) - '0';
				
				if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + cur);
				if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + cur);
			}
		}
		
		out.println(dp[dp.length - 1][dp[0].length - 1]);
		out.close();

	}
}