import java.util.*;
import java.io.*;

public class AOC2022D10P2 {
	static String filename = "aoc";
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		int next = 20, curCycle = 0, curVal = 1;
		boolean[] lit = new boolean[240];
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			if (line[0].equals("noop")) {
				if (Math.abs((curCycle % 40) - curVal) <= 1) lit[curCycle] = true;
				curCycle++;
			}
			else {
				if (Math.abs((curCycle % 40) - curVal) <= 1) lit[curCycle] = true;
				if (Math.abs(((curCycle + 1) % 40) - curVal) <= 1) lit[curCycle + 1] = true;
				curCycle += 2;
				curVal += Integer.parseInt(line[1]);
			}
			
			System.out.println(curCycle + " " + curVal);
		}
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 40; j++) {
				out.print(lit[i * 40 + j] ? "#" : ".");
			}
			out.println();
		}
		
		out.close();
	}
	
}