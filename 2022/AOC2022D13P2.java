import java.util.*;
import java.io.*;

public class AOC2022D13P2 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<DeepList> l = new ArrayList();
		
		while (in.hasNextLine()) {
			String s1 = in.nextLine();
			String s2 = in.nextLine();
			
			l.add(new DeepList(s1));
			l.add(new DeepList(s2));
			
			if (in.hasNextLine()) in.nextLine();
		}
		
		l.add(new DeepList("[[2]]"));
		l.add(new DeepList("[[6]]"));
		
		Collections.sort(l);
		
		int prod = 1;
		for (int i = 0; i < l.size(); i++) {
			DeepList dl = l.get(i);
			if (dl.s.equals("[[2]]") || dl.s.equals("[[6]]")) {
				prod *= i + 1;
			}
		}
		
		out.println(prod);
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