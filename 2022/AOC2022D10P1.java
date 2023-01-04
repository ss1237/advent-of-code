import java.util.*;
import java.io.*;

public class AOC2022D10P1 {
	static String filename = "aoc";
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		int next = 20, total = 0, curCycle = 0, curVal = 1;
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split(" ");
			if (line[0].equals("noop")) {
				curCycle++;
			}
			else {
				curCycle += 2;
				curVal += Integer.parseInt(line[1]);
			}
			
			if (curCycle >= next) {
				total += curVal * next;
				
				if (line[0].equals("addx")) {
					total -= Integer.parseInt(line[1]) * next;
				}
				
				next += 40;
				
			}
			
			System.out.println(curCycle + " " + curVal);
		}
		
		out.println(total);
		
		out.close();
	}
	
}