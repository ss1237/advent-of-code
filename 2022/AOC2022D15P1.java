import java.util.*;
import java.io.*;

public class AOC2022D15P1 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<int[]> ranges = new ArrayList();
		HashSet<Integer> taken = new HashSet<Integer>();
		int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, lim = 2000000;
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" |=|, |: ");
			
			int sx = Integer.parseInt(line[3]);
			int sy = Integer.parseInt(line[5]);
			int bx = Integer.parseInt(line[11]);
			int by = Integer.parseInt(line[13]);
			int dist = Math.abs(sx - bx) + Math.abs(sy - by);
			if (dist - Math.abs(lim - sy) >= 0) {
				int diff = dist - Math.abs(lim - sy);
				ranges.add(new int[] {sx - diff, sx + diff});
				minX = Math.min(minX, sx - diff);
				maxX = Math.max(maxX, sx + diff);
			}
			
			if (sy == lim) taken.add(sx);
			if (by == lim) taken.add(bx);
		}
		
		boolean[] vis = new boolean[maxX - minX + 1];
		int ct = 0;
		
		for (int[] r : ranges) {
			System.out.println(Arrays.toString(r));
			for (int i = r[0]; i <= r[1]; i++) {
				if (!vis[i - minX] && !taken.contains(i)) {
					ct++;
					vis[i - minX] = true;
				}
			}
		}
		
		out.println(ct);
		out.close();
	}
}