import java.util.*;
import java.io.*;

public class AOC2021D07P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(x -> Integer.parseInt(x)).sorted().toArray();
		int total = 0;
		for (int x : nums) total += Math.abs(nums[nums.length / 2] - x);
		
		out.println(total);
		
		out.close();

	}
}