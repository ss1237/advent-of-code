import java.util.*;
import java.io.*;

public class AOC2022D18P1 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		ArrayList<int[]> cubes = new ArrayList();
		int area = 0;
		
		while (in.hasNextLine()) {
			String[] coords = in.nextLine().split(",");
			cubes.add(new int[] {Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), Integer.parseInt(coords[2])});
			area += 6;
		}
		
		for (int[] x : cubes) {
			for (int[] y : cubes) {
				if (diff(x, y) == 1) area -= 1;
			}
		}
		
		out.println(area);
		
		out.close();
	}
	
	static int diff(int[] x, int[] y) {
		return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]) + Math.abs(x[2] - y[2]);
	}
}