import java.util.*;
import java.io.*;

public class AOC2022D04P2 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		long total = 0;
		
		while (in.hasNextLine()) {
			String[] line = in.nextLine().split("[-,]");
			int[] s = new int[4];
			for (int i = 0; i < 4; i++) s[i] = Integer.parseInt(line[i]);
			
			if (!(s[0] < s[2] && s[1] < s[2]) && !(s[2] < s[0] && s[3] < s[0])) {
				total++;
			}
		}
		
		out.println(total);
		out.close();
	}
}