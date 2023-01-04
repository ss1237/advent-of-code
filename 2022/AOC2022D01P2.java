import java.util.*;
import java.io.*;

public class AOC2022D01P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<Long> vals = new ArrayList();
		
		while (in.hasNextLine()) {
			String s = in.nextLine();
			long cur = 0;
			
			while (!s.equals("")) {
				cur += Long.parseLong(s);
				s = in.hasNextLine() ? in.nextLine() : "";
			}
			
			vals.add(cur);
		}
		
		Collections.sort(vals);
		long max = 0;
		for (int i = 1; i <= 3; i++) {
			max += vals.get(vals.size() - i);
		}
		out.println(max);
		
		out.close();
	}
}