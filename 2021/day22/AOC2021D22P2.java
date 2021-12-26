import java.util.*;
import java.io.*;

public class AOC2021D22P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		ArrayList<Cube> cubes = new ArrayList();
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] l = line.split("\\.\\.|\\D+\\=");
			long[] nums = new long[6];
			boolean works = true;
			for (int i = 1; i < 7; i++) {
				nums[i - 1] = Integer.parseInt(l[i]);
			}
			
			Cube cur = new Cube(nums, line.contains("on") ? 1 : -1);
			ArrayList<Cube> update = new ArrayList();
			
			for (Cube c : cubes) {
				Cube e = cur.intersection(c);
				if (e != null) {
					update.add(e);
				}
			}
			
			if (cur.sign > 0) {
				cubes.add(cur);
			}
			
			cubes.addAll(update);
		}
		
		long total = 0;
		for (Cube c : cubes) {
			total += (c.x2 - c.x1 + 1) * (c.y2 - c.y1 + 1) * (c.z2 - c.z1 + 1) * c.sign;
		}
		System.out.println(total);
	}
	
	static class Cube {
		long x1, x2, y1, y2, z1, z2, sign;
		
		public Cube(long[] nums, long g) {
			x1 = nums[0];
			x2 = nums[1];
			y1 = nums[2];
			y2 = nums[3];
			z1 = nums[4];
			z2 = nums[5];
			sign = g;
		}
		
		public Cube intersection(Cube o) {
			long ix1 = Math.max(x1, o.x1);
			long ix2 = Math.min(x2, o.x2);
			long iy1 = Math.max(y1, o.y1);
			long iy2 = Math.min(y2, o.y2);
			long iz1 = Math.max(z1, o.z1);
			long iz2 = Math.min(z2, o.z2);
			
			if (ix1 <= ix2 && iy1 <= iy2 && iz1 <= iz2) {
				return new Cube(new long[] {ix1, ix2, iy1, iy2, iz1, iz2}, -o.sign);
			}
			
			return null;
		}
	}
}