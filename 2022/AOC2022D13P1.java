import java.util.*;
import java.io.*;

public class AOC2022D13P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		int idx = 1, sum = 0;

		while (in.hasNextLine()) {
			String s1 = in.nextLine();
			String s2 = in.nextLine();

			DeepList dl1 = new DeepList(s1);
			DeepList dl2 = new DeepList(s2);

			sum += (dl1.compareTo(dl2) < 0) ? idx : 0;

			if (in.hasNextLine()) in.nextLine();
			idx++;
		}

		out.println(sum);

		out.close();
	}

	static class DeepList implements Comparable<DeepList> {

		String s;

		public DeepList(String str) {
			s = str.replace("10", ":");
		}

		@Override
		public int compareTo(DeepList o) {
			String l = s, r = o.s;
			int rIdx = 0, lIdx = 0;
			while (rIdx < r.length() && lIdx < l.length()) {
				if (l.charAt(lIdx) == r.charAt(rIdx)) {
					rIdx++;
					lIdx++;
					continue;
				}
				if (l.charAt(lIdx) == ']') return -1;
				if (r.charAt(rIdx) == ']') return 1;
				if (l.charAt(lIdx) == '[') {
					r = r.substring(0, rIdx) + "[" + r.charAt(rIdx) + "]" + r.substring(rIdx + 1);
					continue;
				}
				if (r.charAt(rIdx) == '[') {
					l = l.substring(0, lIdx) + "[" + l.charAt(lIdx) + "]" + l.substring(lIdx + 1);
					continue;
				}
				return l.charAt(lIdx) - r.charAt(rIdx);
			}
			return l.length() == lIdx ? -1 : 1;
		}

	}
}