import java.util.*;
import java.io.*;

public class AOC2021D03P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		String s = in.nextLine();
		int[] ct = new int[s.length()];
		
		while (in.hasNextLine()) {
			for (int i = 0; i < s.length(); i++) {
				ct[i] += (s.charAt(i) == '1') ? 1 : -1;
			}
			
			s = in.nextLine();
		}
		
		int gamma = 0;
		
		for (int i = 0; i < s.length(); i++) {
			gamma += (ct[s.length() - 1 - i] > 0) ? (1 << i) : 0;
		}

		out.println(gamma * ((1 << s.length()) - 1 - gamma));
		out.close();

	}
}