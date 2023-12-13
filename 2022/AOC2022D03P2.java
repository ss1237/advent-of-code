import java.util.*;
import java.io.*;

public class AOC2022D03P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		long total = 0;
		
		while (in.hasNextLine()) {
			String s1 = in.nextLine();
			String s2 = in.nextLine();
			String s3 = in.nextLine();
			
			o: for (char a : s1.toCharArray()) {
				for (char b : s2.toCharArray()) {
					for (char c : s3.toCharArray()) {
						if (a == b && b == c) {
							total += (a <= 'Z') ? (a - 'A' + 27) : (a - 'a' + 1);
							break o;
						}
					}
				}
			}
		}
		
		out.println(total);
		out.close();
	}
}