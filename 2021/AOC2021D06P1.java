import java.util.*;
import java.io.*;

public class AOC2021D06P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		int[] precomp = new int[6];

		for (int n = 0; n < precomp.length; n++) {
			ArrayList<Integer> fish = new ArrayList<Integer>();
			fish.add(n);

			for (int i = 0; i < 80; i++) {
				for (int j = 0; j < fish.size(); j++) {
					if (fish.get(j) == 0) {
						fish.set(j, 6);
						fish.add(9);
					}
					else {
						fish.set(j, fish.get(j) - 1);
					}
				}
			}

			precomp[n] = fish.size();
		}

		int ans = 0;
		
		String[] nums = in.nextLine().split(",");
		for (String s : nums) {
			ans += precomp[Integer.parseInt(s)];
		}

		out.println(ans);
		out.close();

	}
}