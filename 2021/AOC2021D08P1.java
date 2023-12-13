import java.util.*;
import java.io.*;

public class AOC2021D08P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		
		int ct = 0;
		
		while (in.hasNextLine()) {
			
			String[] output = in.nextLine().split(" ");
			
			for (int i = 11; i <= 14; i++) {
				if (output[i].length() != 5 && output[i].length() != 6) ct++;
			}
			
		}
		
		out.println(ct);
		
		out.close();

	}
}