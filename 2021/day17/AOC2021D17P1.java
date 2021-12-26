import java.util.*;
import java.io.*;

public class AOC2021D17P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));

		String[] line = in.nextLine().split("[=.,]");
		/*for (String s : line) {
			System.out.println("\"" + s + "\"");
		}*/
		
		int minX = Integer.parseInt(line[1]);
		int maxX = Integer.parseInt(line[3]);
		int minY = Integer.parseInt(line[5]);
		int maxY = Integer.parseInt(line[7]);
		
		//System.out.println(minX + " " + maxX + " " + minY + " " + maxY);
		
		System.out.println(minY * (minY + 1) / 2);
		
		out.close();

	}
}