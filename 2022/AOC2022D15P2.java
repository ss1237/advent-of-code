import java.util.*;
import java.io.*;

public class AOC2022D15P2 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<long[]> borders = new ArrayList();
		int lim = 4000000;
		ArrayList<long[]> sensors = new ArrayList();
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" |=|, |: ");
			
			long sx = Integer.parseInt(line[3]);
			long sy = Integer.parseInt(line[5]);
			long bx = Integer.parseInt(line[11]);
			long by = Integer.parseInt(line[13]);
			long dist = Math.abs(sx - bx) + Math.abs(sy - by) + 1;
			
			sensors.add(new long[] {sx, sy, dist - 1});
			
			for (int i = 0; i < dist; i++) {
				borders.add(new long[] {sx + dist - i, sy + i});
				borders.add(new long[] {sx - i, sy + dist - i});
				borders.add(new long[] {sx - dist + i, sy - i});
				borders.add(new long[] {sx + i, sy - dist + i});
			}
			
		}
		
		o: for (long[] l : borders) {
			long x = l[0], y = l[1];
			if (x < 0 || x > lim || y < 0 || y > lim) continue;
			for (long[] s : sensors) {
				if (Math.abs(s[0] - x) + Math.abs(s[1] - y) <= s[2]) continue o;
			}
			out.println(4000000 * x + y);
			break;
		}
		
		out.close();
	}
}