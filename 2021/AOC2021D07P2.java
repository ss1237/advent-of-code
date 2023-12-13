import java.util.*;
import java.io.*;

public class AOC2021D07P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(x -> Integer.parseInt(x)).sorted().toArray();
		long avg = Math.round(Arrays.stream(nums).sum() * 1.0 / nums.length);
		
		long min = Long.MAX_VALUE;
		
		for (int i = 0; i < 2000; i++) {
			long temp = 0;
			for (int x : nums) temp += (Math.pow(x - i, 2) + Math.abs(x - i)) / 2;
			
			min = Math.min(min, temp);
		}
		
		out.println(min);
		
		out.close();

	}
}