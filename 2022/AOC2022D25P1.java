import java.util.*;
import java.io.*;

public class AOC2022D25P1 {
	static String filename = "aoc";
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		char[] five = new char[] {'=', '-', '0', '1', '2'};
		char[] ten = new char[] {'0', '1', '2', '3', '4'};
		
		long sum = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			for (int i = 4; i >= 0; i--) line = line.replace(five[i], ten[i]);
			sum += Long.parseLong(line, 5) - (Math.pow(5, line.length()) - 1) / 2;
		}
		
		sum += (Math.pow(5, 1 + Math.floor(Math.log(sum) / Math.log(5))) - 1) / 2;
		String fiveStr = Long.toString(sum, 5);
		for (int i = 0; i < 5; i++) fiveStr = fiveStr.replace(ten[i], five[i]);
		
		out.println(fiveStr);
		
		out.close();
	}
	
	
}