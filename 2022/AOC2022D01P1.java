import java.util.*;
import java.io.*;

public class AOC2022D01P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		long max = 0;
		
		while (in.hasNextLine()) {
			String s = in.nextLine();
			long cur = 0;
			
			while (!s.equals("")) {
				cur += Long.parseLong(s);
				s = in.hasNextLine() ? in.nextLine() : "";
			}
			
			max = Math.max(max, cur);
		}
		
		out.println(max);
		out.close();
	}
}