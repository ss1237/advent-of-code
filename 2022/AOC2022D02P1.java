import java.util.*;
import java.io.*;

public class AOC2022D02P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		long points = 0;
		
		while (in.hasNextLine()) {
			String x = in.nextLine();
			int a = x.charAt(0) - 'A' + 1;
			int b = x.charAt(2) - 'X' + 1;
			points += b;
			if (a == b) {
				points += 3;
			}
			else if ((a == 1 && b == 2) || (a == 2 && b == 3) || (a == 3 && b == 1)) {
				points += 6;
			}
		}
		
		out.println(points);
		out.close();
	}
}