import java.util.*;
import java.io.*;

public class AOC2021D17P2 {
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
		
		int maxXv = maxX;
		int minYv = minY;
		int minXv = (int) Math.ceil((-1 + Math.sqrt(1 + 8 * minX)) / 2);
		int maxYv = Math.abs(minY) - 1;
		
		int ct = 0;
		
		for (int xv = minXv; xv <= maxXv; xv++) {
			for (int yv = minYv; yv <= maxYv; yv++) {
				int x = 0, y = 0, curxv = xv, curyv = yv;
				
				while (x <= maxX && y >= minY) {
					x += curxv;
					y += curyv;
					if (curxv > 0) curxv--;
					curyv--;
					
					if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
						ct++;
						break;
					}
				}
			}
		}
		
		System.out.println(ct);
		out.close();

	}
}