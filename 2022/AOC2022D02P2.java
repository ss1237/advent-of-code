import java.util.*;
import java.io.*;

public class AOC2022D02P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		long points = 0;
		
		while (in.hasNextLine()) {
			String x = in.nextLine();
			int a = x.charAt(0) - 'A' + 1;
			int b = x.charAt(2) - 'X' + 1;
			if (b == 1) {
				points += (a+1)%3 + 1;
			}
			else if (b == 2) {
				points += a + 3;
			}
			else {
				points += (a%3) + 1 + 6;
			}
		}
		
		out.println(points);
		out.close();
	}
}