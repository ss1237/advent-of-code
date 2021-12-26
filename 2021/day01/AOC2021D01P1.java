import java.util.*;
import java.io.*;

public class AOC2021D01P1 {
	static String filename = "aoc";

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileInputStream(filename + ".in"));

		int i = in.nextInt();
		int ct = 0;
		
		while (in.hasNextInt()) {
			int next = in.nextInt();
			if (next > i) ct++;
			i = next;
		}

		System.out.println(ct);
	}
}