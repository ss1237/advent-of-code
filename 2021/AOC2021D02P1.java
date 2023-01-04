import java.util.*;
import java.io.*;

public class AOC2021D02P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		long x = 0, y = 0;
		
		while (in.hasNext()) {
			String s = in.next();
			int d = in.nextInt();
			
			if (s.equals("forward")) {
				x += d;
			}
			else if (s.equals("down")) {
				y += d;
			}
			else if (s.equals("up")) {
				y -= d;
			}
			else {
				x -= d;
			}
		}
		
		out.println(x * y);
		
		out.close();

	}
}