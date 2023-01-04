import java.util.*;
import java.io.*;

public class AOC2022D06P2 {
	static String filename = "aoc";
	static int len = 14;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".out")));
		
		String line = in.nextLine();
		
		for (int i = len - 1; i < line.length(); i++) {
			if (isUnique(line.substring(i - len + 1, i + 1))) {
				out.println(i + 1);
				break;
			}
		}
		
		out.close();
	}
	
	static boolean isUnique(String s) {
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) return false;
			}
		}
		return true;
	}
}