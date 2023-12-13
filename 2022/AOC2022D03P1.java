import java.util.*;
import java.io.*;

public class AOC2022D03P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		long total = 0;
		
		while (in.hasNextLine()) {
			String s = in.nextLine();
			o: for (char a : s.substring(0,s.length() / 2).toCharArray()) {
				for (char b : s.substring(s.length() / 2).toCharArray()) {
					if (a == b) {
						total += (a <= 'Z') ? (a - 'A' + 27) : (a - 'a' + 1);
						break o;
					}
				}
			}
		}
		
		out.println(total);
		out.close();
	}
}